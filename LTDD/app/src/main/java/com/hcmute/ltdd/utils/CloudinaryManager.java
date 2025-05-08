package com.hcmute.ltdd.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.hcmute.ltdd.R;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryManager {

    private static Cloudinary cloudinary;

    public static void init(Context context) {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", context.getString(R.string.cloud_name));
        config.put("api_key", context.getString(R.string.api_key));
        config.put("api_secret", context.getString(R.string.api_secret));

        MediaManager.init(context, config);
    }

    public static void uploadImage(Context context, Uri fileUri, UploadCallback callback) {
        MediaManager.get().upload(fileUri)
                .unsigned("thuexeapplication")
                .callback(callback)
                .dispatch();
    }
}
