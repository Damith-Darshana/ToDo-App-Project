package com.hfad.todoproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.hfad.todoproject.Adapter.RecyclerAdapter;
import com.hfad.todoproject.Model.ToDoModel;

import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    FloatingActionButton FAB;
    Button cancelButtonAlertBox, okButtonAlertBox;
    EditText editTextAlertBox;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
 RecyclerAdapter adapter;
 List<ToDoModel> itemsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.new_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout_id);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_open_drawer,R.string.nav_close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view_id);

        SharedPreferencesManager sharedPref = new SharedPreferencesManager(this);
        UserCredentials userData;
        userData = sharedPref.getUserData();
        TextView nameOfNavigation = (TextView)navigationView.getHeaderView(0).findViewById(R.id.navigation_header_name);
        TextView emailOfNavigation = (TextView)navigationView.getHeaderView(0).findViewById(R.id.navigation_header_email);
        nameOfNavigation.setText(userData.getUserName());
        emailOfNavigation.setText(userData.getEmail());


        navigationView.setNavigationItemSelectedListener(this);


        FAB = findViewById(R.id.float_btn_id);


        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(this);
        itemsList = sharedPreferencesManager.getToDOList();





        recyclerView=(RecyclerView)findViewById(R.id.recycle_view_layout_id);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(adapter);





       FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder inflater = new AlertDialog.Builder(MainActivity.this); //builds the alert box
                View customDialogView = getLayoutInflater().inflate(R.layout.alert_box_add,null);

                okButtonAlertBox=(Button)customDialogView.findViewById(R.id.ok_btn_alertBox);
                cancelButtonAlertBox=(Button)customDialogView.findViewById(R.id.cancel_btn_alertBox);
                editTextAlertBox = (EditText) customDialogView.findViewById(R.id.editText_alertBox);

                inflater.setView(customDialogView);
                AlertDialog dialog = inflater.create();
                dialog.setCanceledOnTouchOutside(false);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // makes the alert box white part transparent

                cancelButtonAlertBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                okButtonAlertBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Text should be included here

                       String word = editTextAlertBox.getText().toString();
                        ToDoModel item = new ToDoModel();
                        item.setTask(word);
                        itemsList.add(item);
                        editTextAlertBox.setText("");
                        dialog.dismiss();
                    }
                });

                dialog.show();



            }
        });
        adapter.setTasks(itemsList);
        adapter.notifyDataSetChanged();











    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if(id == R.id.navigation_menu_profile){

            showEditDialog(this);

            
        } else if (id == R.id.navigation_menu_aboutus) {

            aboutUs();
            
        } else if (id == R.id.navigation_menu_logout) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to Log Out ?")
                    .setTitle("Log Out");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent intent = new Intent(MainActivity.this,loginSignupScreenActivity.class);
                    startActivity(intent);
                    finish();
                    // Handle positive button click (optional)
                    dialog.dismiss(); // Dismisses the dialog
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_id);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void showEditDialog(Context context){

        EditText userNameUpdateText,emailUpdateText,passwordUpdateText;
        Button saveChangesButton;
        SharedPreferencesManager sharedPreferencesManager;




        AlertDialog.Builder inflater = new AlertDialog.Builder(MainActivity.this); //builds the alert box
        View customDialogView = getLayoutInflater().inflate(R.layout.profile_edit_box,null);

        sharedPreferencesManager = new SharedPreferencesManager(this);
        UserCredentials userData = sharedPreferencesManager.getUserData();

        userNameUpdateText = (EditText)customDialogView.findViewById(R.id.name_edittext_update);
        emailUpdateText = (EditText)customDialogView.findViewById(R.id.email_edittext_update);
        passwordUpdateText = (EditText)customDialogView.findViewById(R.id.password_edittext_update);
        saveChangesButton = (Button)customDialogView.findViewById(R.id.button_update);

        if(userData !=null){
            userNameUpdateText.setText(userData.getUserName());
            emailUpdateText.setText(userData.getEmail());
        }

        inflater.setView(customDialogView);
        AlertDialog dialog = inflater.create();
        dialog.setCanceledOnTouchOutside(false);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String name=userNameUpdateText.getText().toString();
               String email = emailUpdateText.getText().toString();
               String password= passwordUpdateText.getText().toString();
                NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view_id);

                if (password.isEmpty()){
                    password = sharedPreferencesManager.getUserData().getPassword();

                }
                if(email.isEmpty()){
                    email = sharedPreferencesManager.getUserData().getEmail();
                }
                if(name.isEmpty()){
                    name=sharedPreferencesManager.getUserData().getUserName();
                }

                sharedPreferencesManager.saveUserData(name,password,email);

                TextView nameOfNavigation = (TextView)navigationView.getHeaderView(0).findViewById(R.id.navigation_header_name);
                TextView emailOfNavigation = (TextView)navigationView.getHeaderView(0).findViewById(R.id.navigation_header_email);
                nameOfNavigation.setText(name);
                emailOfNavigation.setText(email);


               Toast.makeText(context,"saved",Toast.LENGTH_SHORT).show();
               dialog.dismiss();

            }
        });
        dialog.show();




    }


    public void aboutUs(){

        ImageView backButton;

        AlertDialog.Builder inflater = new AlertDialog.Builder(MainActivity.this); //builds the alert box
        View customDialogView = getLayoutInflater().inflate(R.layout.about_us_details,null);

        backButton=(ImageView)customDialogView.findViewById(R.id.back_button_id);

        inflater.setView(customDialogView);
        AlertDialog dialog = inflater.create();
        dialog.setCanceledOnTouchOutside(false);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.show();



    }




    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout_id);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        else{
            super.onBackPressed();
        }




    }

    @Override

    protected void onPause() {

        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(this);
        sharedPreferencesManager.saveToDolist(itemsList);
        Toast.makeText(this, "items Saved", Toast.LENGTH_SHORT).show();


        super.onPause();
    }
}