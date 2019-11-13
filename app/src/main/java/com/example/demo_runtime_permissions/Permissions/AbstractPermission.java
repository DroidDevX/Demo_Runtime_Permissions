package com.example.demo_runtime_permissions.Permissions;

import java.io.Serializable;

public abstract class AbstractPermission implements Serializable {


    protected boolean isEnabled;
    protected int requestCode;
    protected String permissionName;
    protected String permissionAlias;



    public AbstractPermission(int requestCode, String permissionName, String alias, boolean isEnabled){
        this.requestCode =requestCode;
        this.permissionName = permissionName;
        this.permissionAlias = alias;
        this.isEnabled = isEnabled;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public String getPermissionAlias() {
        return permissionAlias;
    }

    public void setEnabled(boolean isEnabled){
        this.isEnabled = isEnabled;
    }

    public boolean isEnabled(){
        return isEnabled;
    }
}
