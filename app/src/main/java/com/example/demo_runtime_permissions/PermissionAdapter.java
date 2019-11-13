package com.example.demo_runtime_permissions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_runtime_permissions.Permissions.AbstractPermission;
import com.example.demo_runtime_permissions.Permissions.PermissionList;

import java.util.ArrayList;

public class PermissionAdapter extends RecyclerView.Adapter<PermissionAdapter.Viewholder> {

    public  class Viewholder extends RecyclerView.ViewHolder{
        TextView permissionNameTV;
        TextView permissionActiveTV;
        Button permissionRequestButton;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            permissionNameTV = itemView.findViewById(R.id.permissionNameTV);
            permissionActiveTV = itemView.findViewById(R.id.permissionActiveTV);
            permissionRequestButton = itemView.findViewById(R.id.permissionRequestButton);
        }
    }

    public interface PermissionRequestListener{
        void onPermissionRequested(AbstractPermission permission);
    }

    private PermissionRequestListener listener;
    private  ArrayList<AbstractPermission> permissionlist;

    public PermissionAdapter(PermissionRequestListener listener, ArrayList<AbstractPermission> permissionlist){
        this.listener = listener;
        this.permissionlist = permissionlist;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recycerViewItemRoot=LayoutInflater.from(parent.getContext()).inflate(R.layout.permission_item_layout,parent,false);
        return new Viewholder(recycerViewItemRoot);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        final AbstractPermission permission = permissionlist.get(position);

        holder.permissionNameTV.setText(permission.getPermissionAlias());


        if(permission.isEnabled())
            holder.permissionActiveTV.setText("Active");


        else
            holder.permissionActiveTV.setText("Inactive");


        holder.permissionRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPermissionRequested(permission);
            }
        });
    }

    @Override
    public int getItemCount() {
        return permissionlist.size();
    }
}
