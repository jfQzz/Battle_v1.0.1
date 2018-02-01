package com.wangxia.battle.model.bean;

/**
 * Created by 昝奥博 on 2017/12/25 0025
 * Email:18772833900@163.com
 * Explain：
 */
public class VideoDetailBean {

    /**
     * success : true
     * ID : 2
     * title : 【top5】这些奇葩的败北，你碰到过吗？
     * text : 在决战平安京中，你肯定输过，但你遇见过视频中的输法儿吗？咳咳
     * src : http://gslb.miaopai.com/stream/t2GGsNLGVEQYIBdQydy7CIu1H5IY6gBzSHTpSQ__.mp4?ssig=6a1a563343e8566d9d197f01e818fb2b&time_stamp=1513824912932&cookie_id=&vend=1&os=3&partner=1&platform=2&cookie_id=&refer=miaopai&scid=t2GGsNLGVEQYIBdQydy7CIu1H5IY6gBzSHTpSQ__
     * pic : http://img2.hackhome.com/img2017/12/21/9/572160238.JPG
     * image : http://img2.hackhome.com/img2017/12/21/9/572160238.JPG
     * hits : 21
     * realtime : 12-21
     * time : 2017-12-21 9:58:24
     */

    private boolean success;
    private int ID;
    private String title;
    private String text;
    private String src;
    private String pic;
    private String image;
    private String hits;
    private String realtime;
    private String time;
    private int commnetCount;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getRealtime() {
        return realtime;
    }

    public void setRealtime(String realtime) {
        this.realtime = realtime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCommnetCount() {
        return commnetCount;
    }

    public void setCommnetCount(int commnetCount) {
        this.commnetCount = commnetCount;
    }
}
