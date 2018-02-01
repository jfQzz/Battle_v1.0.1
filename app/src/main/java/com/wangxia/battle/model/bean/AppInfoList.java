package com.wangxia.battle.model.bean;

import java.util.List;

/**
 * Created by 昝奥博 on 2017/9/13 0013
 * Email:18772833900@163.com
 * Explain：游戏对象
 */
public class AppInfoList {

    private boolean success;
    private int pagesize;
    private int size;
    private int pagecount;
    private int curpage;
    private List<ItemsBean> items;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
}
