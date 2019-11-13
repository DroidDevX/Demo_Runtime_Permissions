package com.example.demo_runtime_permissions;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_runtime_permissions.Permissions.AbstractPermission;
import com.example.demo_runtime_permissions.Permissions.PermissionList;
import com.example.demo_runtime_permissions.Permissions.PermissionRequester;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener

{
    private static String BUNDLE_PERMISSIONS ="permissionlist";
    private static final String TAG = "MainActivity";


    int intervalMs=1000;
    Handler taskHandler = new Handler();
    Runnable taskCheckPermissionStatus =new Runnable(){
        @Override
        public void run() {
            try {
                Log.e(TAG,"Checking permission status...");
                if(permissions!=null) {
                    permissions = checkPermissionStatusUpdate(permissions);
                    adapter.notifyDataSetChanged();
                }
            }finally {
                taskHandler.postDelayed(taskCheckPermissionStatus,intervalMs);
            }
        }
    };

    ArrayList<AbstractPermission> permissions;
    PermissionAdapter adapter ;
    RecyclerView runtimePermissionRCView;


    public void startRepeatingTask(Runnable task){
        task.run();
    }

    public void stopRepeatingTask(Runnable task,Handler handler){
        handler.removeCallbacks(task);
    }

    public ArrayList<AbstractPermission> checkPermissionStatusUpdate(ArrayList<AbstractPermission> currentPermissions){
        for(AbstractPermission permission: currentPermissions){
            if(PermissionRequester.permissionGranted(this,permission))
                permission.setEnabled(true);
            else
                permission.setEnabled(false);

        }
        return  currentPermissions;
    }

    @Override
    protected void onDestroy() {
        stopRepeatingTask(taskCheckPermissionStatus,taskHandler);
        super.onDestroy();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.runtime_permission_layout);


        if(savedInstanceState!=null) {
            permissions = (ArrayList<AbstractPermission>)savedInstanceState.getSerializable(BUNDLE_PERMISSIONS);
        }
        else {
            permissions = new PermissionList();
        }

        setupRuntimePermissionRecyclerView();
        findViewById(R.id.viewPermissionBtn).setOnClickListener(this);
        startRepeatingTask(taskCheckPermissionStatus);
    }


    public void setupRuntimePermissionRecyclerView(){
        runtimePermissionRCView = findViewById(R.id.runtimePermissionRCView);

        LinearLayoutManager recyclerViewManager = new LinearLayoutManager(this);
        runtimePermissionRCView.setLayoutManager(recyclerViewManager);
        adapter = new PermissionAdapter(createPermissionRequestListener(), permissions);
        runtimePermissionRCView.setAdapter(adapter);

    }

    public PermissionAdapter.PermissionRequestListener createPermissionRequestListener(){
        return new PermissionAdapter.PermissionRequestListener(){
            @Override
            public void onPermissionRequested(AbstractPermission permission) {
                Log.d(TAG, "onPermissionRequested: ");
                PermissionRequester.requestPermission(MainActivity.this,permission);
            }
        };
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(BUNDLE_PERMISSIONS,permissions);

        super.onSaveInstanceState(outState);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.viewPermissionBtn:displayPermissions(); break;
            default:break;
        }
    }


    private static int REQUEST_APP_SETTINGS=168; //can be anything
    public void displayPermissions(){
        /*This may not work for OS like the one used in OnePlus Oxygen OS
        * Oxygen OS uses a custom view to display the settings. It may display but you may not change permissions
        * */


        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromParts("package",getPackageName(),null);
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String resultMessage = String.format(Locale.ENGLISH,"onActivityResult: requestCode -%d, resultCode -%d",requestCode,resultCode);

        Log.e(TAG,resultMessage);



        super.onActivityResult(requestCode, resultCode, data);
    }
}
