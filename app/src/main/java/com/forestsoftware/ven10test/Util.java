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



   public void setMessage(String msg){editor.putString(Const.MESSAGE,msg);
        editor.commit(); }
    public String getMessage(){
        return pref.getString(Const.MESSAGE,"");
    }

public void setWidth(String width){editor.putString(Const.WIDTH,width);
        editor.commit(); }
    public String getWidth(){
        return pref.getString(Const.WIDTH,"");
    }

public void setHeight(String height){editor.putString(Const.HEIGHT,height);
        editor.commit(); }
    public String getHeight(){
        return pref.getString(Const.HEIGHT,"");
    }

public void setDecentTime(String time){editor.putString(Const.DECENT_TIME,time);
        editor.commit(); }
    public String getDecentTime(){
        return pref.getString(Const.DECENT_TIME,"");
    }
public void setFirstColorCodes(String color){editor.putString(Const.FIRST_COLOR_CODES,color);
        editor.commit(); }
    public String getFirstColorCodes(){
        return pref.getString(Const.FIRST_COLOR_CODES,"");
    }
public void setSecondColorCodes(String color){editor.putString(Const.SECOND_COLOR_CODES,color);
        editor.commit(); }
    public String getSecondColorCdes(){
        return pref.getString(Const.SECOND_COLOR_CODES,"");
    }
public void setMonth(String month){editor.putString(Const.MONTH,month);
        editor.commit(); }
    public String getMonth(){
        return pref.getString(Const.MONTH,"");
    }
public void setDay(String day){editor.putString(Const.DAY,day);
        editor.commit(); }
    public String getDay(){
        return pref.getString(Const.DAY,"");
    }
public void setYear(String year){editor.putString(Const.YEAR,year);
        editor.commit(); }
    public String getYear(){
        return pref.getString(Const.YEAR,"");
    }




}
