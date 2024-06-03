package com.hfad.todoproject.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.todoproject.MainActivity;
import com.hfad.todoproject.Model.DialogUtils;
import com.hfad.todoproject.Model.ToDoModel;
import com.hfad.todoproject.R;
import com.google.gson.Gson;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    Button cancelButtonAlertBox,okButtonAlertBox;
    EditText editTextAlertBox;
    private List<ToDoModel> itemList ;
    private MainActivity activity;


    public RecyclerAdapter(MainActivity activity){
        this.activity = activity;

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public CheckBox checkBoxItem;
        public ImageView editImage,deleteImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBoxItem = itemView.findViewById(R.id.chekbox_id);
            editImage = itemView.findViewById(R.id.edit_icon_id);
            deleteImage = itemView.findViewById(R.id.delete_icon_id);




        }
    }

    public void setTasks(List<ToDoModel> list){
        this.itemList = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_elements,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;

    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {

        ToDoModel item = itemList.get(position);
        holder.checkBoxItem.setText(item.getTask());

        holder.editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = holder.getAdapterPosition();
                ToDoModel item = itemList.get(position);
                DialogUtils.showEditDialog(v.getContext(), item, position, new DialogUtils.EditItemListner() {
                    @Override
                    public void onItemEdited(ToDoModel item, int position) {
                        notifyItemChanged(position);
                        Toast.makeText(v.getContext(), "items is Changed", Toast.LENGTH_SHORT).show();
                    }
                });




            }
        });

        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ToDoModel item = itemList.get(position);
                String deleteMessage = "Are You Sure You Want to Delete " + item.getTask() +"?" ;
                DialogInterface.OnClickListener deleteListner = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemList.remove(position);
                        notifyItemRemoved(position);




                    }
                };
                DialogUtils.showDeleteConfirmationDialog(v.getContext(),deleteMessage,deleteListner);



            }
        });



    }




    @Override
    public int getItemCount() {

        return itemList.size();
    }







    }


