package com.example.demo_runtime_permissions.Permissions;

import java.io.Serializable;

public class Permission extends AbstractPermission implements Serializable {
    private static final String TAG = "Permission";
    public Permission(int requestCode, String permissionName, String alias,boolean isActive) {
        super(requestCode, permissionName,alias,isActive);
    }


}

