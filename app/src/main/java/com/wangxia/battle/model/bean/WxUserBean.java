package com.wangxia.battle.model.bean;

/**
 * Created by 昝奥博 on 2017/11/20 0020
 * Email:18772833900@163.com
 * Explain：微信用户
 */
public class WxUserBean {

    /**
     * access_token : 4_5fL89w2vgrMfFv1qswz4952w3L9-JL8HPFu-J3P_0ai4-Tj9pZbyWdhHplMPMcV53UWmCTIH0niCUMT9ZBfJmE1IZcMqXQM4KB3-gFsO50E
     * expires_in : 7200
     * refresh_token : 4_kAq3wnZXbSDDkHREE0ceDNdzDs-ZZiBJyuiOPQQnRHdRrDzS-ptTxYtjzVnQST1F2zuIMkabBvIAMpJdGN0HEZHBrcQp1FwVTbcPDkiWd7c
     * openid : oS9i30WsW1muA8joTUyKlVVnccE0
     * scope : snsapi_userinfo
     * unionid : obCyp00L-QKbezAzuC__2EKn5sR8
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
