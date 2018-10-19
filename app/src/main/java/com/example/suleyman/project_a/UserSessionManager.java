package com.example.suleyman.project_a;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Art Servis on 1/28/2018.
 */

public class UserSessionManager {

    SharedPreferences pref;
    Context context;
    SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;
    private static final String PREFER_NAME = "UserSession";
    private  static final  String IS_USER_LOGIN = "IsUserLoggedIn";
    private  static final  String KEY_NAME = "name";
    private  static final String KEY_USERNAME = "username";
    private static  final String KEY_PASSWORD = "password";
    private static final String KEY_USERID = "userId";
    private static final String KEY_MENUS = "menus";
    private static  final String KEY_TABS = "tabs";

    public UserSessionManager(Context context)
    {
        this.context = context;
        pref = this.context.getSharedPreferences(PREFER_NAME,PRIVATE_MODE);
        editor = pref.edit();

    }

    public  void createUserLoginSession(String name, String username, String password, String userId,
                                        ArrayList<String> menus, ArrayList<String> tabs)
    {
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_USERID, userId);
        editor.putStringSet(KEY_MENUS, new HashSet<String>(menus));
        editor.putStringSet(KEY_TABS, new HashSet<String>(tabs));
        editor.commit();

    }

    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

    public boolean checkLogin(){

        if(!this.isUserLoggedIn())
        {
            Intent i = new Intent(context, Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(i);

            return true;
        }
        return false;
    }

      public String getName()
      {
          return  pref.getString(KEY_NAME, null);
      }

      public String getUserName(){
          return pref.getString(KEY_USERNAME, null);
      }

      public String getPassword(){
          return pref.getString(KEY_PASSWORD, null);
      }

      public String getUserId(){
          return  pref.getString(KEY_USERID, null);
      }

      public HashSet<String> getMenus(){
          return (HashSet<String>) pref.getStringSet(KEY_MENUS, null);

      }

      public HashSet<String> getTabs(){
          return (HashSet<String>) pref.getStringSet(KEY_TABS, null);
      }

      public void logoutUser(){
          editor.clear();
          editor.commit();

          Intent i = new Intent(context, Login.class);
          i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          context.startActivity(i);
      }


}
