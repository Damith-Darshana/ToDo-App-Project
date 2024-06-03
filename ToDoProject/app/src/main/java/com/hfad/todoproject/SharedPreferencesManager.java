package com.hfad.todoproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.hotspot2.pps.Credential;
import android.service.autofill.UserData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hfad.todoproject.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesManager {

    private static final String PREF_KEY_TODO_LIST="todo_list";
    private static SharedPreferences preferences ;

    public SharedPreferencesManager(Context context){
        preferences = context.getSharedPreferences("state_save_data",Context.MODE_PRIVATE);



    }


    public void saveToDolist(List<ToDoModel> list){
        Gson gson = new Gson();
        String dataJson = gson.toJson(list);
        preferences.edit().putString(PREF_KEY_TODO_LIST,dataJson).apply();

    }

    public List<ToDoModel> getToDOList(){
        Gson gson = new Gson();
        String dataJson = preferences.getString(PREF_KEY_TODO_LIST,null);
        if(dataJson == null) {

            return new ArrayList<>();
        }
        else {
            return gson.fromJson(dataJson,new TypeToken<List<ToDoModel>>(){}.getType());

        }



        }




    private static final String PREF_KEY_USER_DATA = "user_data";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    public void saveUserData(String name, String password, String email){

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USERNAME,name);
        editor.putString(KEY_PASSWORD,password);
        editor.putString(KEY_EMAIL,email);
        editor.apply();




    }
    public UserCredentials getUserData() {
        String username = preferences.getString(KEY_USERNAME, null);
        String password = preferences.getString(KEY_PASSWORD, null);
        String email = preferences.getString(KEY_EMAIL, null);
        if (username != null && password != null && email != null) {
            return new UserCredentials(username,email,password);
        } else {
            return null;
        }
    }

    public boolean isLoggedIn() {
        return getUserData() != null;
    }




    }//class end
