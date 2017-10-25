package com.wangxia.battle.db.bean;

/**
 * Created by 昝奥博 on 2017/9/19 0019
 * Email:18772833900@163.com
 * Explain：下载对象
 */
public class DownListBean {
    private int id;
    private String icon;
    private String title;
    private long size;
    private String labels;
    private String mark;
    private int state;
    private long currentLength;
    private String downUrl;
    private String path;
    private String speed;
    private Long downloadId;

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getCurrentLength() {
        return currentLength;
    }

    public void setCurrentLength(long currentLength) {
        this.currentLength = currentLength;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public Long getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(Long downloadId) {
        this.downloadId = downloadId;
    }

    public DownListBean(int id, String icon, String title, long size, String labels, String mark, int state, long currentLength, String downUrl, String path, String speed,Long downloadId) {
        this.id = id;
        this.icon = icon;
        this.title = title;
        this.size = size;
        this.labels = labels;
        this.mark = mark;
        this.state = state;
        this.currentLength = currentLength;
        this.downUrl = downUrl;
        this.path = path;
        this.speed = speed;
        this.downloadId = downloadId;
    }

    public DownListBean() {
    }
}
