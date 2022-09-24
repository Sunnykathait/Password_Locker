package com.example.passwordlocker;

import static android.graphics.Color.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<InfromationClass> infromationClassArrayList;
    private Context context;

    static int i = 0;

    public RecyclerViewAdapter(ArrayList<InfromationClass> infromationClassArrayList, Context context) {
        this.infromationClassArrayList = infromationClassArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_info,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        InfromationClass infromationClass = infromationClassArrayList.get(position);
        holder.appName.setText(infromationClass.getApplicationName());
        holder.UserName.setText(infromationClass.getUserName());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context1 = holder.itemView.getContext();
                Intent intent = new Intent(context1 , EnterPassword.class);
                intent.putExtra("userName", holder.UserName.getText().toString());
                intent.putExtra("appName",holder.appName.getText().toString());
                context1.startActivity(intent);
            }

        });

    }



    @Override
    public int getItemCount() {
        return infromationClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView appName , UserName;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.edit_entry);
            appName = itemView.findViewById(R.id.show_appName_txt);
            UserName = itemView.findViewById(R.id.show_userName_txt);
        }

    }

}
