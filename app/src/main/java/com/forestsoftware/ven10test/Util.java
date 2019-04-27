package com.forestsoftware.ven10test;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by HP-PC on 3/23/2018.
 */

public class Util {
    private static SharedPreferences pref;

    private static SharedPreferences.Editor editor;
    private Context _context;
    int PRIVATE_MODE = 0;

    // Constructor
    public Util(Context context) {
        this._context = context;
        pref = context.getSharedPreferences(Const.PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }



   public void setMessage(String msg){
        editor.putString(Const.MESSAGE,msg);
        editor.commit();

    }

    public String getMessage(){
        return pref.getString(Const.MESSAGE,"");
    }




}
