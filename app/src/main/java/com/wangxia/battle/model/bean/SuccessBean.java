package com.wangxia.battle.model.bean;

/**
 * Created by 昝奥博 on 2017/11/14 0014
 * Email:18772833900@163.com
 * Explain：
 */
public class SuccessBean {
    /**
     * info : 登陆成功
     * status : 200
     * success: 评论成功
     * msg:返回的提示语
     */
public static final String SUCCESS_TAG = "ok";
    private String info;
    private String status;
    private String success;
    private String msg;
    //文章点赞的次数
    private int val1;
    //文章点赞返回判断依据 0 ： success
    private int error;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getVal1() {
        return val1;
    }

    public void setVal1(int val1) {
        this.val1 = val1;
    }
}
