package com.wangxia.battle.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by 昝奥博 on 2017/11/20 0020
 * Email:18772833900@163.com
 * Explain：签名获取
 */
public class SignUtil {

    /**
     * md5签名算法返回3段字符串
     * @return int,str,sign
     */
    public static String[] getSign() {
        String[] result = new String[3];
        //获取到16位的随机数
        String time = getTime();
        String md5Str = null;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(time.getBytes());
            byte[] digest = md5.digest();
            md5Str =  encryption(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            md5 = MessageDigest.getInstance("MD5");
            String  number = String.valueOf(6666) +time+ String.valueOf(8888);
            md5.reset();
            md5.update(number.getBytes());
            byte[] digest = md5.digest();
            String encryption = encryption(digest);
            md5.reset();
            md5.update((md5Str + encryption.toString()).getBytes());
            byte[] sign = md5.digest();
            String end = encryption(sign);
            result[0] = time;
            result[1] = md5Str;
            result[2] = end;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 返回一个16位数
     * @return
     */
    public static String getTime(){
        String time = String.valueOf(System.nanoTime());
        int length = time.length();
        if(13 != length){
            if(13 > length){
                for (int j = 0; j < (13 - length);j++) {
                    time += j;
                }
            }else {
                time = time.substring( length - 13, length);
            }
        }
        time += getRandom();
        return  time;
    }

    /**
     * 返回一个3位数的数字
     * @return
     */
    public static String getRandom() {
        String result = null;
        Random random = new Random();
        int nextInt = random.nextInt(999);
        if(100 <= nextInt){
            result = String.valueOf(nextInt);
        }else {
            result  = getRandom();
        }

        return result;
    }


    /**
     *
     * @param
     * @return 32位小写密文
     */
    public  static String encryption(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            if (Integer.toHexString(0xFF & digest[i]).length() == 1)
                sb.append("0").append(Integer.toHexString(0xFF & digest[i]));
            else
                sb.append(Integer.toHexString(0xFF & digest[i]));
        }
        return sb.toString();
    }

}
