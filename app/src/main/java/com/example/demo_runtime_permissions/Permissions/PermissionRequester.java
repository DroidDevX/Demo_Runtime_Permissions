package com.example.demo_runtime_permissions.Permissions;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionRequester {


    public static void requestPermission(@NonNull Context context, AbstractPermission permission) {

        if (!permissionGranted(context,permission)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission.getPermissionName())) {
                AlertDialog dialog = createPermissionDialog(context,permission);
                dialog.show();
            } else
                ActivityCompat.requestPermissions((Activity) context, new String[]{permission.getPermissionName()}, permission.getRequestCode());
        }
    }

    private static AlertDialog createPermissionDialog(final Context context, final AbstractPermission permission) {
        return new AlertDialog.Builder(context)
                .setTitle("Request Permission")
                .setMessage("The app needs the " + permission.getPermissionAlias() + " permission to work correctly")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{permission.getPermissionName()}, permission.getRequestCode());
                    }
                }).create();
    }

    public static boolean permissionGranted(Context context, AbstractPermission permission) {
        return ContextCompat.checkSelfPermission(context, permission.getPermissionName())
                == PackageManager.PERMISSION_GRANTED;
    }

}
