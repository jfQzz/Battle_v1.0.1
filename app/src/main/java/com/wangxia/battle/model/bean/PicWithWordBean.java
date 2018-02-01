package com.wangxia.battle.model.bean;

/**
 * Created by 昝奥博 on 2018/1/10 0010
 * Email:18772833900@163.com
 * Explain：图片与文字
 */
public class PicWithWordBean {
    private String pic;
    private String nick;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public PicWithWordBean(String pic, String nick) {
        this.pic = pic;
        this.nick = nick;
    }

    public PicWithWordBean() {
    }
}
