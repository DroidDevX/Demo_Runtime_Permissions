package com.example.demo_runtime_permissions;


import android.content.Context;
import android.os.Build;

import androidx.test.core.app.ApplicationProvider;

import com.example.demo_runtime_permissions.Permissions.Permission;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;


@RunWith(RobolectricTestRunner.class)
@Config(manifest= Config.NONE,sdk= Build.VERSION_CODES.O_MR1)
public class PermissionModelTest {
    Permission permission;
    Context context;

    @Before
    public void setup(){
        permission = Mockito.mock(Permission.class);
        context = ApplicationProvider.getApplicationContext();
    }

    @Test
    public void testRequestPermissionNotNull(){
    }

    @Test
    public void testPermissionGranted(){

    }
}
