package com.wangxia.battle.model.bean;

import java.util.List;

/**
 * Created by 昝奥博 on 2017/9/15 0015
 * Email:18772833900@163.com
 * Explain：评论对象
 */
public class EvaluateBean {

    /**
     * success : true
     * size : 65
     * page : 1
     * datacount : 323
     * pagecount : 65
     * curpage : 1
     * items : [{"ID":100348,"ObjID":258389,"Index":"2","User":"伟雄","UserID":"56143","UserPic":"http://img2.hackhome.com/user/api/656143.jpg","UserType":"0","UserCrad":"0","UserSing":"","UserCity":"","UserSex":"False","Good":"1","Bad":"1","Time":"2017-9-11 14:23:52","Content":"天天遇坑，都掉黄金了","AtName":"null","AtID":"null","Phone":"努比亚 Z9","PalyMinute":"0","Re":[{"ID":100350,"Index":"1","AdminLink":"","User":"伟雄","UserID":"56143","UserPic":"http://img2.hackhome.com/user/api/656143.jpg","ReUserID":"56143","ReUserName":"伟雄","Good":"0","Bad":"0","Time":"2017-9-11 14:27:09","Content":"农药很贵","AtName":"null","AtID":"null","Phone":"努比亚 Z9"},{"ID":100420,"Index":"2","AdminLink":"","User":"__灰色的世界","UserID":"54273","UserPic":"http://img2.hackhome.com/user/api/654273.jpg","ReUserID":"56143","ReUserName":"伟雄","Good":"0","Bad":"0","Time":"2017-9-11 19:51:36","Content":"你自己不就是吗？","AtName":"null","AtID":"null","Phone":"vivo X9"}],"UserApp":{"id":0,"title":"","pic":""}},{"ID":99469,"ObjID":258389,"Index":"3","User":"优优优优乐美","UserID":"10332","UserPic":"http://img2.hackhome.com/user/pic/20178718177748_10332.jpg","UserType":"2","UserCrad":"0","UserSing":"湖北","UserCity":"孝感","UserSex":"True","Good":"2","Bad":"1","Time":"2017-9-7 22:03:03","Content":"都去吃鸡了，没人和我双排吗？[tb_heheda][tb_heheda]","AtName":"null","AtID":"null","Phone":"红米 Note3","PalyMinute":"0","Re":[],"UserApp":{"id":339845,"title":"狼人杀","pic":"http://img.hackhome.com/img2017/3/17/2017031757695017_APP.png"}},{"ID":99361,"ObjID":258389,"Index":"4","User":"星降之夜","UserID":"54905","UserPic":"http://img2.hackhome.com/user/api/654905.jpg","UserType":"0","UserCrad":"0","UserSing":"","UserCity":"","UserSex":"False","Good":"2","Bad":"0","Time":"2017-9-7 12:51:38","Content":"好处","AtName":"null","AtID":"null","Phone":"乐视 乐2","PalyMinute":"0","Re":[],"UserApp":{"id":691595,"title":"逃离实验室汉化版","pic":"http://img.hackhome.com/img2017/8/9/13/54128475_APP.png"}},{"ID":99360,"ObjID":258389,"Index":"5","User":"星降之夜","UserID":"54905","UserPic":"http://img2.hackhome.com/user/api/654905.jpg","UserType":"0","UserCrad":"0","UserSing":"","UserCity":"","UserSex":"False","Good":"0","Bad":"0","Time":"2017-9-7 12:51:31","Content":"喜欢","AtName":"null","AtID":"null","Phone":"乐视 乐2","PalyMinute":"0","Re":[],"UserApp":{"id":691595,"title":"逃离实验室汉化版","pic":"http://img.hackhome.com/img2017/8/9/13/54128475_APP.png"}}]
     */

    private boolean success;
    private int size;
    private int page;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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
         * ID : 100348
         * ObjID : 258389
         * Index : 2
         * User : 伟雄
         * UserID : 56143
         * UserPic : http://img2.hackhome.com/user/api/656143.jpg
         * UserType : 0
         * UserCrad : 0
         * UserSing :
         * UserCity :
         * UserSex : False
         * Good : 1
         * Bad : 1
         * Time : 2017-9-11 14:23:52
         * Content : 天天遇坑，都掉黄金了
         * AtName : null
         * AtID : null
         * Phone : 努比亚 Z9
         * PalyMinute : 0
         * Re : [{"ID":100350,"Index":"1","AdminLink":"","User":"伟雄","UserID":"56143","UserPic":"http://img2.hackhome.com/user/api/656143.jpg","ReUserID":"56143","ReUserName":"伟雄","Good":"0","Bad":"0","Time":"2017-9-11 14:27:09","Content":"农药很贵","AtName":"null","AtID":"null","Phone":"努比亚 Z9"},{"ID":100420,"Index":"2","AdminLink":"","User":"__灰色的世界","UserID":"54273","UserPic":"http://img2.hackhome.com/user/api/654273.jpg","ReUserID":"56143","ReUserName":"伟雄","Good":"0","Bad":"0","Time":"2017-9-11 19:51:36","Content":"你自己不就是吗？","AtName":"null","AtID":"null","Phone":"vivo X9"}]
         * UserApp : {"id":0,"title":"","pic":""}
         */

        private int ID;
        private int ObjID;
        private String Index;
        private String User;
        private String UserID;
        private String UserPic;
        private String Icon;
        private String UserType;
        private String UserCrad;
        private String UserSing;
        private String UserCity;
        private boolean UserSex;
        private String Good;
        private String Bad;
        private String Time;
        private String Content;
        private String AtName;
        private String AtID;
        private String Phone;
        private String PalyMinute;
        private UserAppBean UserApp;
        private List<ReBean> Re;
        private boolean isDig;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getObjID() {
            return ObjID;
        }

        public void setObjID(int ObjID) {
            this.ObjID = ObjID;
        }

        public String getIndex() {
            return Index;
        }

        public void setIndex(String Index) {
            this.Index = Index;
        }

        public String getUser() {
            return User;
        }

        public void setUser(String User) {
            this.User = User;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getUserPic() {
            return UserPic;
        }

        public void setUserPic(String UserPic) {
            this.UserPic = UserPic;
        }

        public String getIcon() {
            return Icon;
        }

        public void setIcon(String icon) {
            Icon = icon;
        }

        public String getUserType() {
            return UserType;
        }

        public void setUserType(String UserType) {
            this.UserType = UserType;
        }

        public String getUserCrad() {
            return UserCrad;
        }

        public void setUserCrad(String UserCrad) {
            this.UserCrad = UserCrad;
        }

        public String getUserSing() {
            return UserSing;
        }

        public void setUserSing(String UserSing) {
            this.UserSing = UserSing;
        }

        public String getUserCity() {
            return UserCity;
        }

        public void setUserCity(String UserCity) {
            this.UserCity = UserCity;
        }

        public boolean isUserSex() {
            return UserSex;
        }

        public void setUserSex(boolean UserSex) {
            this.UserSex = UserSex;
        }

        public String getGood() {
            return Good;
        }

        public void setGood(String Good) {
            this.Good = Good;
        }

        public String getBad() {
            return Bad;
        }

        public void setBad(String Bad) {
            this.Bad = Bad;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public String getAtName() {
            return AtName;
        }

        public void setAtName(String AtName) {
            this.AtName = AtName;
        }

        public String getAtID() {
            return AtID;
        }

        public void setAtID(String AtID) {
            this.AtID = AtID;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getPalyMinute() {
            return PalyMinute;
        }

        public void setPalyMinute(String PalyMinute) {
            this.PalyMinute = PalyMinute;
        }

        public UserAppBean getUserApp() {
            return UserApp;
        }

        public void setUserApp(UserAppBean UserApp) {
            this.UserApp = UserApp;
        }

        public boolean isDig() {
            return isDig;
        }

        public void setDig(boolean dig) {
            isDig = dig;
        }

        public List<ReBean> getRe() {
            return Re;
        }

        public void setRe(List<ReBean> Re) {
            this.Re = Re;
        }

        public static class UserAppBean {
            /**
             * id : 0
             * title :
             * pic :
             */

            private int id;
            private String title;
            private String pic;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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
        }

        public static class ReBean {
            /**
             * ID : 100350
             * Index : 1
             * AdminLink :
             * User : 伟雄
             * UserID : 56143
             * UserPic : http://img2.hackhome.com/user/api/656143.jpg
             * ReUserID : 56143
             * ReUserName : 伟雄
             * Good : 0
             * Bad : 0
             * Time : 2017-9-11 14:27:09
             * Content : 农药很贵
             * AtName : null
             * AtID : null
             * Phone : 努比亚 Z9
             */

            private int ID;
            private String Index;
            private String AdminLink;
            private String User;
            private String UserID;
            private String UserPic;
            private String ReUserID;
            private String ReUserName;
            private String Good;
            private String Bad;
            private String Time;
            private String Content;
            private String AtName;
            private String AtID;
            private String Phone;

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getIndex() {
                return Index;
            }

            public void setIndex(String Index) {
                this.Index = Index;
            }

            public String getAdminLink() {
                return AdminLink;
            }

            public void setAdminLink(String AdminLink) {
                this.AdminLink = AdminLink;
            }

            public String getUser() {
                return User;
            }

            public void setUser(String User) {
                this.User = User;
            }

            public String getUserID() {
                return UserID;
            }

            public void setUserID(String UserID) {
                this.UserID = UserID;
            }

            public String getUserPic() {
                return UserPic;
            }

            public void setUserPic(String UserPic) {
                this.UserPic = UserPic;
            }

            public String getReUserID() {
                return ReUserID;
            }

            public void setReUserID(String ReUserID) {
                this.ReUserID = ReUserID;
            }

            public String getReUserName() {
                return ReUserName;
            }

            public void setReUserName(String ReUserName) {
                this.ReUserName = ReUserName;
            }

            public String getGood() {
                return Good;
            }

            public void setGood(String Good) {
                this.Good = Good;
            }

            public String getBad() {
                return Bad;
            }

            public void setBad(String Bad) {
                this.Bad = Bad;
            }

            public String getTime() {
                return Time;
            }

            public void setTime(String Time) {
                this.Time = Time;
            }

            public String getContent() {
                return Content;
            }

            public void setContent(String Content) {
                this.Content = Content;
            }

            public String getAtName() {
                return AtName;
            }

            public void setAtName(String AtName) {
                this.AtName = AtName;
            }

            public String getAtID() {
                return AtID;
            }

            public void setAtID(String AtID) {
                this.AtID = AtID;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String Phone) {
                this.Phone = Phone;
            }
        }
    }
}
