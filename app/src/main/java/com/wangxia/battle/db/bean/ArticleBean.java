package com.wangxia.battle.db.bean;

/**
 * Created by 昝奥博 on 2017/9/19 0019
 * Email:18772833900@163.com
 * Explain：文章浏览历史
 */
public class ArticleBean {
    private int id;
    private String icon;
    private String title;
    private String desc;
    private int hints;
    private String time;
    private String addTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getHints() {
        return hints;
    }

    public void setHints(int hints) {
        this.hints = hints;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArticleBean(int id, String icon, String title, String desc, int hints, String time, String addTime) {
        this.id = id;
        this.icon = icon;
        this.title = title;
        this.desc = desc;
        this.hints = hints;
        this.time = time;
        this.addTime = addTime;
    }

    public ArticleBean() {
    }
}
