package com.wangxia.battle.db.bean;

/**
 * Created by 昝奥博 on 2017/9/19 0019
 * Email:18772833900@163.com
 * Explain：游戏浏览历史
 */
public class GameBean {
    private int id;
    private String icon;
    private String title;
    private long size;
    private String labels;
    private String mark;
    private String time;


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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public GameBean(int id, String icon, String title, long size, String labels, String mark, String time) {
        this.id = id;
        this.icon = icon;
        this.title = title;
        this.size = size;
        this.labels = labels;
        this.mark = mark;
        this.time = time;
    }

    public GameBean() {
    }

    @Override
    public String toString() {
        return "GameBean{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", size=" + size +
                ", labels='" + labels + '\'' +
                ", mark='" + mark + '\'' +
                ", time='" + time + '\'' +
                '}';
    }


}
