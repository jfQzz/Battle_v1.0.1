package com.wangxia.battle.model.bean;

import java.util.List;

/**
 * Created by 昝奥博 on 2017/11/16 0016
 * Email:18772833900@163.com
 * Explain：用户绑定的账户
 */
public class UserBindBean {

    /**
     * info : ok
     * status : 200
     * userid : 3
     * usertel : 13797140032
     * items : [{"type":"1","name":"笑笑","pic":"https://pimg.sg92.com/hz/slyx.png","time":"2017-11-13 20:08:12","retime":"2017-11-13 20:03:07"}]
     */

    private String info;
    private String status;
    private String userid;
    private String usertel;
    private List<ItemsBean> items;

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * type : 1
         * name : 笑笑
         * pic : https://pimg.sg92.com/hz/slyx.png
         * time : 2017-11-13 20:08:12
         * retime : 2017-11-13 20:03:07
         */

        private String type;
        private String name;
        private String pic;
        private String time;
        private String retime;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getRetime() {
            return retime;
        }

        public void setRetime(String retime) {
            this.retime = retime;
        }
    }
}
