package com.example.rusty.society_shopping;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by deeptansh on 23/4/17.
 */

public class InitializePref {
    Context context;
    SharedPreferences sharedPreferences;
    public static final String myPrefrence="MyPrefs";
    public static final String resident_name = "Resident_name";
    public static final String shop_name = "Shop_name";
    public static final String admin_name = "Admin_name";
    SharedPreferences.Editor editor;
    public void loginResident(Context context,String name){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(myPrefrence, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(resident_name,name);
        editor.commit();

    }
    public void loginShop(Context context,String name){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(myPrefrence, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(shop_name,name);
        editor.commit();

    }
    public void loginAdmin(Context context,String name){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(myPrefrence, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(admin_name,name);
        editor.commit();

    }
    public void logout(){
        sharedPreferences = context.getSharedPreferences(myPrefrence, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }


}
