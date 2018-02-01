package com.wangxia.battle.model.bean;

import java.util.List;

/**
 * Created by 昝奥博 on 2017/9/16 0016
 * Email:18772833900@163.com
 * Explain： 视频列表
 */
public class VideoList {

    /**
     * success : true
     * datacount : 4
     * pagecount : 1
     * curpage : 1
     * items : [{"ID":6,"title":"孟婆老司机，千里救山兔！","pic":"http://img2.hackhome.com/img2017/12/22/9/514075286.JPG","image":"http://img2.hackhome.com/img2017/12/22/9/514075286.JPG","hits":"0","realtime":"12-22","time":"2017-12-22 9:51:49"},{"ID":5,"title":"决战平安京中谁最快？结局让人意想不到","pic":"http://img2.hackhome.com/img2017/12/22/9/352138191.JPG","image":"http://img2.hackhome.com/img2017/12/22/9/352138191.JPG","hits":"0","realtime":"12-22","time":"2017-12-22 9:35:36"},{"ID":4,"title":"怎么把山兔从山蛙身上摇下来？决战平安京中你不知的彩蛋！","pic":"http://img2.hackhome.com/img2017/12/22/9/212395140.JPG","image":"http://img2.hackhome.com/img2017/12/22/9/212395140.JPG","hits":"0","realtime":"12-22","time":"2017-12-22 9:21:30"},{"ID":2,"title":"【top5】这些奇葩的败北，你碰到过吗？","pic":"http://img2.hackhome.com/img2017/12/21/9/572160238.JPG","image":"http://img2.hackhome.com/img2017/12/21/9/572160238.JPG","hits":"21","realtime":"12-21","time":"2017-12-21 9:58:24"}]
     */

    private boolean success;
    private int datacount;
    private int pagecount;
    private int curpage;
    private List<ItemsBean> items;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getDatacount() {
        return datacount;
    }

    public void setDatacount(int datacount) {
        this.datacount = datacount;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    public int getCurpage() {
        return curpage;
    }

    public void setCurpage(int curpage) {
        this.curpage = curpage;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * ID : 6
         * title : 孟婆老司机，千里救山兔！
         * pic : http://img2.hackhome.com/img2017/12/22/9/514075286.JPG
         * image : http://img2.hackhome.com/img2017/12/22/9/514075286.JPG
         * hits : 0
         * realtime : 12-22
         * time : 2017-12-22 9:51:49
         */

        private int ID;
        private String title;
        private String pic;
        private String image;
        private String hits;
        private String realtime;
        private String time;

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
    }
}