package com.wangxia.battle.model.bean;

/**
 * Created by 昝奥博 on 2017/11/15 0015
 * Email:18772833900@163.com
 * Explain：用户信息
 */
public class UserInfo {

    /**
     * info : ok
     * status : 200
     * ispass : True
     * userid : 100007
     * usernike : 匿名100007
     * userpic : http://img3.hackhome.com/images/user/def.png
     * usersex : 0
     * userreip : 119.99.143.225
     * userretime : 2017-11-15 8:17:13
     */

    private String info;
    private String status;
    private String ispass;
    private String userid;
    private String usernike;
    private String userpic;
    private String usersex;
    private String userreip;
    private String userretime;

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

    public String getIspass() {
        return ispass;
    }

    public void setIspass(String ispass) {
        this.ispass = ispass;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsernike() {
        return usernike;
    }

    public void setUsernike(String usernike) {
        this.usernike = usernike;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getUserreip() {
        return userreip;
    }

    public void setUserreip(String userreip) {
        this.userreip = userreip;
    }

    public String getUserretime() {
        return userretime;
    }

    public void setUserretime(String userretime) {
        this.userretime = userretime;
    }
}
