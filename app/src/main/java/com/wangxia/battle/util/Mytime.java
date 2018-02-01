package com.wangxia.battle.util;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 说明：时间的工具类
 */
public class Mytime {

    private static String colors;

    /**
     * 得到现在时间
     * 注意: 获取的是手机里显示的系统时间,而不是网络时间
     *
     * @return 字符串 yyyyMMdd HHmmss ,yyyy-MM-dd HH:mm:ss,格式都可以自己定
     */
    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getStringToday2() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 按照指定时间格式得到二个日期间的间隔分钟数
     */
    public static float getTwoDaysMimits(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        float day = 0;  //精确到小数
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (60 * 1000); //除以60秒就可以了
        } catch (Exception e) {
            return 0;
        }
        return day;
    }

    public static String getTwoDaysWords(String oldTime) {
        //当前时间
        float mimits = getTwoDaysMimits(getStringToday(), oldTime);
        String timeStr = null;
        if (mimits < 1) {
            timeStr = "刚刚";
        } else if (mimits < 60) { //60分钟之内
            timeStr = (int) (mimits / 1) + "分钟前";
        } else if (mimits < 60 * 24 && mimits >= 60) {
            timeStr = (int) (mimits / 60) + "小时前";
        } else if (30 * 60 * 24 > mimits && mimits > 60 * 24) {
            timeStr = (int) (mimits / (60 * 24)) + "天前";
        } else if (12 * 30 * 60 * 24 > mimits && mimits > 30 * 60 * 24) {
            timeStr = (int) (mimits / (60 * 24 * 30)) + "月前";
        } else {
            timeStr = "一年前";
        }
        return timeStr;
    }


    /**
     *
     * @param day ：有效时间 ：天
     * @param oldTime 上一个记录时间
     * @return
     */
    public static boolean isDelay(int day,String oldTime) {
        //当前时间
        float mimits = getTwoDaysMimits(getStringToday(), oldTime);
        if(60 * 24*day > mimits){
            return false;
        }else {
            return true;
        }
    }

    //对于app列表里上传时间显示
    public static String showtheUploadTime(TextView mytext, String oldTime, String shangchan) {
        //当前时间
        float mimits = getTwoDaysMimits(getStringToday(), oldTime);
        String timeStr = null;
        mytext.setVisibility(View.VISIBLE); //一开始都显示
        //颜色色值
        if (TextUtils.equals(shangchan, "更新")) {
            colors = "#f47265";
        } else if (TextUtils.equals(shangchan, "上传") || TextUtils.isEmpty(shangchan)) {
            colors = "#FF0033";
        } else {
            colors = "#FF0033";
        }
        if (mimits < 1) {
            timeStr = "刚刚" + shangchan;
            mytext.setText(timeStr);
            mytext.setTextColor(Color.parseColor(colors));
        } else if (mimits < 60) { //60分钟之内
            timeStr = (int) (mimits / 1) + "分钟前" + shangchan;
            mytext.setText(timeStr);
            mytext.setTextColor(Color.parseColor(colors));
        } else if (mimits < 60 * 24 && mimits >= 60) {
            timeStr = (int) (mimits / 60) + "小时前" + shangchan;
            mytext.setText(timeStr);
            mytext.setTextColor(Color.parseColor(colors));
        } else if (30 * 60 * 24 > mimits && mimits > 60 * 24) {
            timeStr = (int) (mimits / (60 * 24)) + "天前" + shangchan;
            mytext.setText(timeStr);
            mytext.setTextColor(Color.parseColor("#555555"));
        } else if (12 * 30 * 60 * 24 > mimits && mimits > 30 * 60 * 24) {
            timeStr = (int) (mimits / (60 * 24 * 30)) + "个月前" + shangchan;
            mytext.setText(timeStr);
            mytext.setTextColor(Color.parseColor("#555555"));
            if (!TextUtils.isEmpty(shangchan)) { //app上传时间,只显示1个月内的
                mytext.setText(""); //直接设置内容为空即可
            }
        } else {
            timeStr = "一年前" + shangchan;
            mytext.setText(timeStr);
            mytext.setTextColor(Color.parseColor("#555555"));
            if (!TextUtils.isEmpty(shangchan)) { //app上传时间,只显示1个月内的
                mytext.setText(""); //直接设置内容为空即可
            }
        }
        return timeStr;
    }

    //对于动态里,评论的时间显示,不要用红色
    public static String showtPingLunTime(TextView mytext, String oldTime) {
        //当前时间
        float mimits = getTwoDaysMimits(getStringToday(), oldTime);
        String timeStr = null;
        if (mimits < 1) {
            timeStr = "刚刚";
            mytext.setText(timeStr);
        } else if (mimits < 60) { //60分钟之内
            timeStr = (int) (mimits / 1) + "分钟前";
            mytext.setText(timeStr);
        } else if (mimits < 60 * 24 && mimits >= 60) {
            timeStr = (int) (mimits / 60) + "小时前";
            mytext.setText(timeStr);
        } else if (30 * 60 * 24 > mimits && mimits > 60 * 24) {
            timeStr = (int) (mimits / (60 * 24)) + "天前";
            mytext.setText(timeStr);
        } else if (12 * 30 * 60 * 24 > mimits && mimits > 30 * 60 * 24) {
            timeStr = (int) (mimits / (60 * 24 * 30)) + "个月前";
            mytext.setText(timeStr);
        } else {
            timeStr = "一年前";
            mytext.setText(timeStr);
//            mytext.setTextColor(Color.parseColor("#555555"));
        }
        return timeStr;
    }

    /**
     * 格式化时间 精确到某一天
     *
     * @param timeStr
     * @return
     */
    public static String formatTime(String timeStr) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String result = null;
        try {
            Date parse = myFormatter.parse(timeStr);
            result = formatter.format(parse);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 比较时间字符串的大小  最近的时间在前,旧时间在后
     *
     * @param DATE1
     * @param DATE2
     * @return 为了与点击率保持一直, 后者要求先显示, 返回的值大于1
     */
    public static int compareDate(String DATE1, String DATE2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
//                System.out.println("dt1在dt2前");
                return -1;
            } else if (dt1.getTime() < dt2.getTime()) {
//                System.out.println("dt1在dt2后");
                return 1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    /**
     * 格式化时间 精确分钟 xx天xx分钟
     *
     * @param minutes 毫秒级别
     * @return
     */
    public static String formatTime(long  minutes) {
        String timeStr;
        if(0 == minutes){
            timeStr = "不足一分钟";
        }else {
             timeStr =  minutes + "分钟";
                if (minutes > 60) {
                    long min = minutes  % 60;
                    long hour = minutes / 60;
                    timeStr = hour + "小时" + min + "分" ;
                    if (hour > 24) {
                        hour = (minutes  / 60) % 24;
                        long day = ((minutes  / 60) / 24);
                        timeStr = day + "天" + hour + "小时" + min + "分";
                    }
                }
        }
        return timeStr;





    }

    /**
     * 比较两个时间是否相等
     * @param newTime
     * @param oldTime
     * 字符串比较存在问题，but why？
     */
    public static boolean isTimeEquals(String newTime, String oldTime) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long day;  //精确到小数
        try {
            Date date = myFormatter.parse(newTime);
            Date mydate = myFormatter.parse(oldTime);
            day = date.getTime() - mydate.getTime();
        } catch (Exception e) {
            return false;
        }
        if(0 == day){
            return  true;
        }else {
            return false;
        }
    }
}
