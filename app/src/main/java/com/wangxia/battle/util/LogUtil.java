package com.wangxia.battle.util;

import android.util.Log;

/**
 * Created by 昝奥博 on 2017/4/26 0026
 * Email:18772833900@163.com
 * Explain：log工具
 */
public class LogUtil {
    //发布时，切记关闭log，设置isOpenLog = false；
    public static final boolean isOpenLog = true;
    private static final String TAG = "----->>>";

    /**
     * information
     * @param result
     */
    public  static void i(String result){
        if(isOpenLog){
            Log.i(TAG, "Info: result = "+result);
        }
    }
    public  static void i(String arg,String arg1){
        if(isOpenLog){
            Log.i(TAG, "Info :  arg =   "+arg+"\t\t\targ1 = "+arg1);
        }
    }
    public  static void i(String arg,String arg1,String arg3){
        if(isOpenLog){
            Log.i(TAG, "Info :  arg =   "+arg+"\t\t\targ1 = "+arg1+"\t\t\targ3 = "+arg3);
        }
    }

    /**
     * warn
     * @param result
     */
    public  static  void w(String result){
        if(isOpenLog){
            Log.w(TAG, "Warn:  result = "+result );
        }
    }

    public  static void w(String arg,String arg1){
        if(isOpenLog){
            Log.i(TAG, "Warn :  arg =   "+arg+"\t\t\targ1 = "+arg1);
        }
    }
    public  static void w(String arg,String arg1,String arg3){
        if(isOpenLog){
            Log.i(TAG, "Warn :  arg =   "+arg+"\t\t\targ1 = "+arg1+"\t\t\targ3 = "+arg3);
        }
    }

    /**
     * error
     * @param result
     */
    public  static  void e(String result){
        if(isOpenLog){
            Log.e(TAG, "Error: result =  "+result );
        }
    }

    public  static void e(String arg,String arg1){
        if(isOpenLog){
            Log.i(TAG, "Error :  arg =   "+arg+"\t\t\targ1 = "+arg1);
        }
    }
    public  static void e(String arg,String arg1,String arg3){
        if(isOpenLog){
            Log.i(TAG, "Error :  arg =   "+arg+"\t\t\targ1 = "+arg1+"\t\t\targ3 = "+arg3);
        }
    }


}
