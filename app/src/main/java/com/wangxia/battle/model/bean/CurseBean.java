package com.wangxia.battle.model.bean;

import java.util.List;

/**
 * Created by 昝奥博 on 2018/1/9 0009
 * Email:18772833900@163.com
 * Explain：灵咒对象
 */
public class CurseBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * u89e3u9501u7b49u7ea7 : 1
         * u56feu6807u8defu5f84 : gui/res/skill/dunshou.png
         * u7075u5492ID : 10004
         * u6280u80fdu51b7u5374 : 90
         * u7075u5492u6280u80fdu63cfu8ff0 : 保护自己和附近一名生命垂危的友方式神，分别生成一个可吸收125#c5591B7(+25.0*等级)#n点伤害的护盾，持续3秒。15秒内再次受到盾守效果将减半。
         * u9884u89c8u56feu7247u8defu5f84 : gui/res/lingzhou/10004.png
         * u7075u5492u540du79f0 : 盾守
         */

        private int u89e3u9501u7b49u7ea7;
        private String u56feu6807u8defu5f84;
        private int u7075u5492ID;
        private int u6280u80fdu51b7u5374;
        private String u7075u5492u6280u80fdu63cfu8ff0;
        private String u9884u89c8u56feu7247u8defu5f84;
        private String u7075u5492u540du79f0;

        public int getU89e3u9501u7b49u7ea7() {
            return u89e3u9501u7b49u7ea7;
        }

        public void setU89e3u9501u7b49u7ea7(int u89e3u9501u7b49u7ea7) {
            this.u89e3u9501u7b49u7ea7 = u89e3u9501u7b49u7ea7;
        }

        public String getU56feu6807u8defu5f84() {
            return u56feu6807u8defu5f84;
        }

        public void setU56feu6807u8defu5f84(String u56feu6807u8defu5f84) {
            this.u56feu6807u8defu5f84 = u56feu6807u8defu5f84;
        }

        public int getU7075u5492ID() {
            return u7075u5492ID;
        }

        public void setU7075u5492ID(int u7075u5492ID) {
            this.u7075u5492ID = u7075u5492ID;
        }

        public int getU6280u80fdu51b7u5374() {
            return u6280u80fdu51b7u5374;
        }

        public void setU6280u80fdu51b7u5374(int u6280u80fdu51b7u5374) {
            this.u6280u80fdu51b7u5374 = u6280u80fdu51b7u5374;
        }

        public String getU7075u5492u6280u80fdu63cfu8ff0() {
            return u7075u5492u6280u80fdu63cfu8ff0;
        }

        public void setU7075u5492u6280u80fdu63cfu8ff0(String u7075u5492u6280u80fdu63cfu8ff0) {
            this.u7075u5492u6280u80fdu63cfu8ff0 = u7075u5492u6280u80fdu63cfu8ff0;
        }

        public String getU9884u89c8u56feu7247u8defu5f84() {
            return u9884u89c8u56feu7247u8defu5f84;
        }

        public void setU9884u89c8u56feu7247u8defu5f84(String u9884u89c8u56feu7247u8defu5f84) {
            this.u9884u89c8u56feu7247u8defu5f84 = u9884u89c8u56feu7247u8defu5f84;
        }

        public String getU7075u5492u540du79f0() {
            return u7075u5492u540du79f0;
        }

        public void setU7075u5492u540du79f0(String u7075u5492u540du79f0) {
            this.u7075u5492u540du79f0 = u7075u5492u540du79f0;
        }
    }
}
