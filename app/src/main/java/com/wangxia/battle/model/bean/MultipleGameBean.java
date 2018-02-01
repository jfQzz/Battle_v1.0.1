package com.wangxia.battle.model.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by 昝奥博 on 2018/1/3 0003
 * Email:18772833900@163.com
 * Explain：
 */
public class MultipleGameBean implements MultiItemEntity {
    public static final int LABEL = 1;
    public static final int PICS = 2;
    public static final int OTHER = 3;
    private int itemType;
    private String label;
    private List<String> pics;
    private AppInfo appInfo;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public MultipleGameBean( String label) {
        this.itemType = LABEL;
        this.label = label;
    }

    public MultipleGameBean(List<String> pics) {
        this.itemType = PICS;
        this.pics = pics;
    }

    public MultipleGameBean( AppInfo appInfo) {
        this.itemType = OTHER;
        this.appInfo = appInfo;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
