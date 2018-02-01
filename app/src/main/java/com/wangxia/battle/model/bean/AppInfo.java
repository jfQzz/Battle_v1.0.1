package com.wangxia.battle.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 昝奥博 on 2017/9/14 0014
 * Email:18772833900@163.com
 * Explain：游戏详情，包含几张大图，推荐游戏等等
 */
public class AppInfo implements Serializable {
    private int ID;
    private String SoftName;
    private String AppTitle;
    private String AppName;
    private String CatalogName;
    private String AppText;
    private String Labels;
    private String packageName;
    private String VerCode;
    private String Ver;
    private String Rank;
    private String Size;
    private String Hits;
    private String Time;
    private String Ico;
    private String Remarks;
    private String Text;
    private String Qkey;
    private String LableSplit;
    private String HtmlUrl;
    private String NewsCount;
    private String LibaoCount;
    private String HdCount;
    private String PlCount;
    private List<PicBean> Pic;
    private List<DownBean> Down;
    private List<AppListBean> AppList;
    private List<EditorBean> Editor;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSoftName() {
        return SoftName;
    }

    public void setSoftName(String SoftName) {
        this.SoftName = SoftName;
    }

    public String getAppTitle() {
        return AppTitle;
    }

    public void setAppTitle(String AppTitle) {
        this.AppTitle = AppTitle;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String AppName) {
        this.AppName = AppName;
    }

    public String getCatalogName() {
        return CatalogName;
    }

    public void setCatalogName(String CatalogName) {
        this.CatalogName = CatalogName;
    }

    public String getAppText() {
        return AppText;
    }

    public void setAppText(String AppText) {
        this.AppText = AppText;
    }

    public String getLabels() {
        return Labels;
    }

    public void setLabels(String Labels) {
        this.Labels = Labels;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVerCode() {
        return VerCode;
    }

    public void setVerCode(String VerCode) {
        this.VerCode = VerCode;
    }

    public String getVer() {
        return Ver;
    }

    public void setVer(String Ver) {
        this.Ver = Ver;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String Rank) {
        this.Rank = Rank;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public String getHits() {
        return Hits;
    }

    public void setHits(String Hits) {
        this.Hits = Hits;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getIco() {
        return Ico;
    }

    public void setIco(String Ico) {
        this.Ico = Ico;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String Remarks) {
        this.Remarks = Remarks;
    }

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public String getQkey() {
        return Qkey;
    }

    public void setQkey(String Qkey) {
        this.Qkey = Qkey;
    }

    public String getLableSplit() {
        return LableSplit;
    }

    public void setLableSplit(String LableSplit) {
        this.LableSplit = LableSplit;
    }

    public String getHtmlUrl() {
        return HtmlUrl;
    }

    public void setHtmlUrl(String HtmlUrl) {
        this.HtmlUrl = HtmlUrl;
    }

    public String getNewsCount() {
        return NewsCount;
    }

    public void setNewsCount(String NewsCount) {
        this.NewsCount = NewsCount;
    }

    public String getLibaoCount() {
        return LibaoCount;
    }

    public void setLibaoCount(String LibaoCount) {
        this.LibaoCount = LibaoCount;
    }

    public String getHdCount() {
        return HdCount;
    }

    public void setHdCount(String HdCount) {
        this.HdCount = HdCount;
    }

    public String getPlCount() {
        return PlCount;
    }

    public void setPlCount(String PlCount) {
        this.PlCount = PlCount;
    }

    public List<PicBean> getPic() {
        return Pic;
    }

    public void setPic(List<PicBean> Pic) {
        this.Pic = Pic;
    }

    public List<DownBean> getDown() {
        return Down;
    }

    public void setDown(List<DownBean> Down) {
        this.Down = Down;
    }

    public List<AppListBean> getAppList() {
        return AppList;
    }

    public void setAppList(List<AppListBean> AppList) {
        this.AppList = AppList;
    }

    public List<EditorBean> getEditor() {
        return Editor;
    }

    public void setEditor(List<EditorBean> Editor) {
        this.Editor = Editor;
    }


    public static class PicBean implements Serializable{
        /**
         * i : 1
         * name : http://img2.hackhome.com/img2017/9/7/16/381398552.jpg
         */

        private int i;
        private String name;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class DownBean implements Serializable{
        /**
         * i : 1
         * name : 立即下载
         * url :
         */

        private int i;
        private String name;
        private String url;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class AppListBean implements Serializable{
        /**
         * ID : 585268
         * title : 大汉皇朝公益服BT变态版
         * apptitle : 大汉皇朝变态版
         * appname : 大汉皇朝
         * desc : 大汉皇朝变态版是一款情缘双修游戏，纵横大汉皇朝，没有佳人相伴，如何彰显你的霸气。实时语言系统，随心畅聊，打造你的社交圈。跨服争霸，实力悍斗，只为王者无敌。大汉皇朝变态版特色：1、即使语言系统，轻松畅聊；2、家园系统，打造自己完美后花园；3、...
         * remark : 成为无敌的代表。
         * image : http://img.hackhome.com/img2017/3/20/2017032031183689_APP.png
         * viewimg : http://img.hackhome.com/img2017/3/20/2017032031106329.jpg
         * hits : 9
         * pcatalog : 263
         * catalog : 301
         * catalogname : 角色扮演
         * labels : RPG,pk,变态版,公益服
         * softsize : 144384
         * downurl : http://syb.yasnk.cn/h14/lp/dahanhuangchao.apk
         * libaonum : 0
         * time : 2017-3-20 8:56:00
         * packageName : com.jxwy.sq.wl
         * VerCode : 0
         * SoftVer : v1.00.10
         * GameType : 1
         * AppUpCount : 3
         * status : 0
         * progress  : 100
         */

        private int ID;
        private String title;
        private String apptitle;
        private String appname;
        private String desc;
        private String remark;
        private String image;
        private String viewimg;
        private String hits;
        private int pcatalog;
        private int catalog;
        private String catalogname;
        private String labels;
        private String softsize;
        private String downurl;
        private String libaonum;
        private String time;
        private String packageName;
        private String VerCode;
        private String SoftVer;
        private String GameType;
        private String AppUpCount;
        private int status;
        private int progress;

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

        public String getApptitle() {
            return apptitle;
        }

        public void setApptitle(String apptitle) {
            this.apptitle = apptitle;
        }

        public String getAppname() {
            return appname;
        }

        public void setAppname(String appname) {
            this.appname = appname;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getViewimg() {
            return viewimg;
        }

        public void setViewimg(String viewimg) {
            this.viewimg = viewimg;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public int getPcatalog() {
            return pcatalog;
        }

        public void setPcatalog(int pcatalog) {
            this.pcatalog = pcatalog;
        }

        public int getCatalog() {
            return catalog;
        }

        public void setCatalog(int catalog) {
            this.catalog = catalog;
        }

        public String getCatalogname() {
            return catalogname;
        }

        public void setCatalogname(String catalogname) {
            this.catalogname = catalogname;
        }

        public String getLabels() {
            return labels;
        }

        public void setLabels(String labels) {
            this.labels = labels;
        }

        public String getSoftsize() {
            return softsize;
        }

        public void setSoftsize(String softsize) {
            this.softsize = softsize;
        }

        public String getDownurl() {
            return downurl;
        }

        public void setDownurl(String downurl) {
            this.downurl = downurl;
        }

        public String getLibaonum() {
            return libaonum;
        }

        public void setLibaonum(String libaonum) {
            this.libaonum = libaonum;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getVerCode() {
            return VerCode;
        }

        public void setVerCode(String VerCode) {
            this.VerCode = VerCode;
        }

        public String getSoftVer() {
            return SoftVer;
        }

        public void setSoftVer(String SoftVer) {
            this.SoftVer = SoftVer;
        }

        public String getGameType() {
            return GameType;
        }

        public void setGameType(String GameType) {
            this.GameType = GameType;
        }

        public String getAppUpCount() {
            return AppUpCount;
        }

        public void setAppUpCount(String AppUpCount) {
            this.AppUpCount = AppUpCount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }
    }

    public static class EditorBean implements Serializable{
        /**
         * UserID : 10463
         * NewUserID : 610463
         * UserType : 3
         * UserCard : 1
         * UserNike : 游戏界扛把子
         * UserPic :
         */

        private String UserID;
        private String NewUserID;
        private String UserType;
        private String UserCard;
        private String UserNike;
        private String UserPic;

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getNewUserID() {
            return NewUserID;
        }

        public void setNewUserID(String NewUserID) {
            this.NewUserID = NewUserID;
        }

        public String getUserType() {
            return UserType;
        }

        public void setUserType(String UserType) {
            this.UserType = UserType;
        }

        public String getUserCard() {
            return UserCard;
        }

        public void setUserCard(String UserCard) {
            this.UserCard = UserCard;
        }

        public String getUserNike() {
            return UserNike;
        }

        public void setUserNike(String UserNike) {
            this.UserNike = UserNike;
        }

        public String getUserPic() {
            return UserPic;
        }

        public void setUserPic(String UserPic) {
            this.UserPic = UserPic;
        }
    }
}
