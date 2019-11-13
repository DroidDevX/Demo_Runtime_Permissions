package com.example.demo_runtime_permissions.Permissions;


import android.Manifest;
import android.os.Build;

import java.io.Serializable;
import java.util.ArrayList;

public class PermissionList extends ArrayList<AbstractPermission> implements Serializable {

    private ArrayList<AbstractPermission> createPermissions(){
        ArrayList<AbstractPermission> names =new ArrayList<>();
        names.add(new Permission(0,Manifest.permission.ACCESS_COARSE_LOCATION,"Location",false));
        if(Build.VERSION.SDK_INT>=23)
            names.add(new Permission(1,Manifest.permission.BODY_SENSORS,"Sensor",false));
            names.add(new Permission(2,Manifest.permission.CAMERA,"Camera",false));
            names.add(new Permission(3,Manifest.permission.READ_CONTACTS,"Contacts",false));
            names.add(new Permission(4,Manifest.permission.RECORD_AUDIO,"Mircophone",false));
            names.add(new Permission(5,Manifest.permission.READ_SMS,"Sms",false));
            names.add(new Permission(6,Manifest.permission.READ_EXTERNAL_STORAGE,"Storage",false));
            names.add(new Permission(7,Manifest.permission.READ_CALL_LOG,"Call log",false));
            names.add(new Permission(8,Manifest.permission.READ_CALENDAR,"Calendar",false));
        return names;
    }

    public PermissionList(){
        addAll(createPermissions());
    }


}
