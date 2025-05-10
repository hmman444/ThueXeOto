package com.hcmute.ltdd.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hcmute.ltdd.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateRangePickerBottomSheet extends BottomSheetDialogFragment {

    public interface DateRangePickerListener {
        void onDateSelected(Calendar selectedDate, String selectedTime);
    }

    private DateRangePickerListener listener;
    private Calendar selectedDate;
    private String selectedTime = "21:00";

    public void setDateRangePickerListener(DateRangePickerListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_date_range_picker_bottom_sheet, container, false);

        CalendarView calendarView = view.findViewById(R.id.calendarView);
        TextView textPickUpTime = view.findViewById(R.id.textPickUpTime);
        TextView textSummary = view.findViewById(R.id.textSummary);
        Button btnSelect = view.findViewById(R.id.btn_select_time);

        // Set default time
        textPickUpTime.setText(selectedTime);
        textSummary.setText(formatSummary(null, selectedTime));

        // Handle time selection
        textPickUpTime.setOnClickListener(v -> showTimePickerDialog((hour, minute) -> {
            selectedTime = String.format("%02d:%02d", hour, minute);
            textPickUpTime.setText(selectedTime);
            textSummary.setText(formatSummary(selectedDate, selectedTime));
        }));

        // Handle calendar date selection
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            selectedDate = Calendar.getInstance();
            selectedDate.set(year, month, dayOfMonth);
            textSummary.setText(formatSummary(selectedDate, selectedTime));
        });

        // Handle selection button
        btnSelect.setOnClickListener(v -> {
            if (selectedDate != null) {
                if (listener != null) {
                    listener.onDateSelected(selectedDate, selectedTime);
                }
                dismiss();
            } else {
                Toast.makeText(getContext(), "Vui lòng chọn ngày và giờ!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void showTimePickerDialog(TimeSelectedCallback callback) {
        android.app.TimePickerDialog dialog = new android.app.TimePickerDialog(getContext(),
                (view, hourOfDay, minute) -> callback.onTimeSelected(hourOfDay, minute),
                12, 0, true);
        dialog.show();
    }

    private String formatSummary(Calendar date, String time) {
        if (date == null) return time;
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", new Locale("vi", "VN"));
        String dayOfWeek = dayFormat.format(date.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM", Locale.getDefault());
        String formattedDate = dateFormat.format(date.getTime());
        return String.format("%s, ngày %s, %s", dayOfWeek, formattedDate, time);
    }

    interface TimeSelectedCallback {
        void onTimeSelected(int hour, int minute);
    }
}
