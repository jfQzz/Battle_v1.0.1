package com.wangxia.battle.model.bean;

/**
 * Created by 昝奥博 on 2017/9/21 0021
 * Email:18772833900@163.com
 * Explain：app更新对象
 */
public class AppUpdateBean {

    /**
     * ver : 1.0.0
     * down : https://apk.apk.hz155.com/down/chuanqi.apk
     * size : 1M
     * desc : 1111111111111111111
     22222222222
     3333333333
     */

    private String ver;
    private String down;
    private String size;
    private String desc;


    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getDown() {
        return down;
    }

    public void setDown(String down) {
        this.down = down;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "AppUpdateBean{" +
                "ver='" + ver + '\'' +
                ", down='" + down + '\'' +
                ", size='" + size + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }


}
