package com.example.demo_runtime_permissions;

import android.Manifest;
import android.content.Context;
import android.os.Build;

import androidx.test.core.app.ApplicationProvider;

import com.example.demo_runtime_permissions.Permissions.MyPermissions;
import com.example.demo_runtime_permissions.Permissions.Permission;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;


import static org.mockito.Mockito.when;


@RunWith(RobolectricTestRunner.class)
@Config(manifest= Config.NONE,sdk= Build.VERSION_CODES.O_MR1)
public class MyPermissionTest {
    MyPermissions permissions;
    Permission permission;
    Context context;
    @Before
    public void setup(){
        permissions = Mockito.mock(MyPermissions.class);
        permission = Mockito.mock(Permission.class);
        context = ApplicationProvider.getApplicationContext();
    }

    @Test
    public void testRequestPermission(){

        when(permissions.get(Manifest.permission.ACCESS_COARSE_LOCATION)).thenReturn(null);
        permissions.setPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION,true);

        when(permissions.get(Manifest.permission.ACCESS_COARSE_LOCATION)).thenReturn(new Permission(0,Manifest.permission.ACCESS_COARSE_LOCATION));
        permissions.setPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION,true);

        permissions.setPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION,false);
    }

}
