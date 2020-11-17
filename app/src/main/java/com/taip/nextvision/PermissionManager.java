package com.taip.nextvision;

import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionManager {
    private static PermissionManager instance = null;
    MainActivity mainActivity;
    String[] permissions;

    public static void initInstance(MainActivity mainActivity, String[] permissions) {
        if (instance == null) {
            instance = new PermissionManager(mainActivity, permissions);
        }
    }

    public static PermissionManager getInstance() throws Exception {
        if (instance != null) {
            return instance;
        }
        throw new Exception("Class not instantiated");
    }

    private PermissionManager(MainActivity mainActivity, String[] permissions) {
        this.mainActivity = mainActivity;
        this.permissions = permissions;
        for (int i = 0; i < this.permissions.length; i++) {
            this.checkPermission(this.permissions[i]);
        }
    }

    private void checkPermission(String permission) {
        if (ContextCompat.checkSelfPermission(mainActivity, permission) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(mainActivity, new String[]{permission}, 0);
            }
        }
    }
}
