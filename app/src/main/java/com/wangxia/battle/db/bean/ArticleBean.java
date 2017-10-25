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
    private int hints;
    private String publishTime;
    private String author;
    private String authorIco;
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

    public int getHints() {
        return hints;
    }

    public void setHints(int hints) {
        this.hints = hints;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorIco() {
        return authorIco;
    }

    public void setAuthorIco(String authorIco) {
        this.authorIco = authorIco;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArticleBean(int id, String icon, String title, int hints, String publishTime, String author, String authorIco, String time) {
        this.id = id;
        this.icon = icon;
        this.title = title;
        this.hints = hints;
        this.publishTime = publishTime;
        this.author = author;
        this.authorIco = authorIco;
        this.time = time;
    }

    public ArticleBean() {
    }
}
