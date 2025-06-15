package com.hcmute.thuexe.dto.request;

public class CancelBookingRequest {
    private Long bookingId;
    private String reason;
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public CancelBookingRequest(Long bookingId, String reason) {
        this.bookingId = bookingId;
        this.reason = reason;
    }
}
