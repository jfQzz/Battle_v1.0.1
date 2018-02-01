package com.wangxia.battle.model.bean;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by 昝奥博 on 2017/12/22 0022
 * Email:18772833900@163.com
 * Explain：评论的各种对象
 */
public class CommentBean implements Serializable{
    public static final String COMMENT_BEAN = "comment_bean";
    private int type;
    private int objId;
    private int pId;
    private String hints;
    private String saveTxt;
    private String inputTxt;
    //个人的一些信息，icon，nick，phone，brand
    private String userName;
    private String userIcon;
    private String phone;
    private String brand;

    public String getHints() {
        return hints;
    }

    public void setHints(String hints) {
        this.hints = hints;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getObjId() {
        return objId;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public int getPId() {
        return pId;
    }

    public void setPId(int pId) {
        this.pId = pId;
    }

    public String getSaveTxt() {
        return saveTxt;
    }

    public void setSaveTxt(String saveTxt) {
        this.saveTxt = saveTxt;
    }

    public String getInputTxt() {
        return inputTxt;
    }

    public void setInputTxt(String inputTxt) {
        this.inputTxt = inputTxt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 主评论
     * @param type  评论的类型 1：游戏 2：文章 3：视频
     * @param objId 文章,游戏,视频的id
     * @param hints 评论框的提示语
     * @param saveTxt 上一次意外的输入内容
     */
    public CommentBean(int type, int objId,@NonNull String hints,String saveTxt) {
        this.type = type;
        this.objId = objId;
        this.hints = hints;
        this.saveTxt = saveTxt;
    }


    /**
     *
     * 回复
     * @param type  评论的类型 0：游戏 1：文章 2：视频
     * @param objId 文章,游戏,视频的id
     * @param pId 回复的某个用户的id
     * @param hints 评论框的提示语
     * @param saveTxt 上一次意外的输入内容
     */
    public CommentBean(int type, int objId, int pId,@NonNull String hints,String saveTxt) {
        this.type = type;
        this.objId = objId;
        this.pId = pId;
        this.hints = hints;
        this.saveTxt = saveTxt;
    }

    public CommentBean() {
    }

}
