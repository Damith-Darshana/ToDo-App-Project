package com.hfad.todoproject.Model;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.hfad.todoproject.R;

import java.util.zip.Inflater;

public class DialogUtils {

    public static void showEditDialog(Context context,ToDoModel item,int position,EditItemListner listner){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.alert_box_add,null);

        TextView title = dialogView.findViewById(R.id.title_alert_box_id);
        EditText textWord = dialogView.findViewById(R.id.editText_alertBox);
        Button okButtonAlertBoxEdit = dialogView.findViewById(R.id.ok_btn_alertBox);
        Button cancelButtonAlertBoxEdit = dialogView.findViewById(R.id.cancel_btn_alertBox);


        textWord.setText(item.getTask());// pre populate just for visual
        title.setText("Edit To-Do Item");

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        cancelButtonAlertBoxEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        okButtonAlertBoxEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editedText = textWord.getText().toString();
                item.setTask(editedText);
                dialog.dismiss();
                if (listner != null){

                    listner.onItemEdited(item,position);
                }

            }

        });
        dialog.show();
    }
    public  interface EditItemListner{
        void onItemEdited(ToDoModel item, int position);

    }

    public static void showDeleteConfirmationDialog(Context context, String message,
                                                    DialogInterface.OnClickListener deleteListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton("Delete", deleteListener)
                .setNegativeButton("Cancel", null);
        builder.create().show();
    }


}
