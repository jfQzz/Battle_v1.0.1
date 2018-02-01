package com.wangxia.battle.model.bean;

/**
 * Created by 昝奥博 on 2018/1/8 0008
 * Email:18772833900@163.com
 * Explain：抓取的资源的图片
 */
public class PicBean {

    private boolean status;
    private String pathdict;
    private boolean success;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPathdict() {
        return pathdict;
    }

    public void setPathdict(String pathdict) {
        this.pathdict = pathdict;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
