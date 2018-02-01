package com.wangxia.battle.model.bean;

import java.util.List;

/**
 * Created by 昝奥博 on 2018/1/3 0003
 * Email:18772833900@163.com
 * Explain：
 */
public class HeroBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * u5f0fu795eu6807u7b7e : 爆发/控制
         * u5f0fu795eu5b9au4f4d : ["巫"]
         * cvu540du5b57 : ["前野智昭","夏磊"]
         * u63a8u8350u5206u8def : 中
         * u5f0fu795eu653bu7565 : 式神定位：巫#r推荐路线：中路#r使用技巧：尽量保证自身有3技能的护盾时进行战斗；利用连续技能命中触发被动伤害和减速效果消耗敌人；在对手被控制的情况下，让其难以轻松离开大招的范围，受到巨额的伤害。
         * u8bc4u5206 : {"u8f93u51fa":5,"u751fu5b58":3,"u63a7u5236":5,"u589eu76ca":1,"u654fu6377":3,"u96beu5ea6":2}
         * u5f0fu795eu5c5eu6027u6210u957f : {"u9b54u6cd5u56deu590d":0.8,"u9b54u6cd5u4e0au9650":50,"u9b54u6297":1.5,"u653bu901fu52a0u6210":0.003,"u7269u7406u4f24u5bb3":4.5,"u751fu547du6062u590d":0.82,"u62a4u7532":6.5,"u751fu547du503c":151}
         * u63a8u8350u52a0u70b9u987au5e8f : [2,3,2,1,2,4,2,3,2,3,4,3,3,1,1,4,1,1]
         * u5f0fu795eu57fau7840u5c5eu6027 : {"u653bu51fbu901fu5ea6":0.625,"u9b54u6cd5u56deu590d":6,"u9b54u6cd5u4e0au9650":360,"u9b54u6297":60,"u653bu901fu52a0u6210":0.376,"u7269u7406u4f24u5bb3":76,"u751fu547du6062u590d":9,"u653bu51fbu540eu6447":21,"u62a4u7532":48,"u79fbu52a8u901fu5ea6":34,"u751fu547du503c":800}
         * u63a8u8350u9634u9633u672f : [5,11,18,24,26,32,40,48,49]
         * u5f0fu795eID : 1003
         * u5f0fu795eu540du79f0 : 大天狗
         * u5f0fu795eu6280u80fd : {"u5929u751fu88abu52a8":{"u56feu6807u8defu5f84":"gui/res/skill/1003000.png","u6280u80fdID":100351,"u6280u80fdu63cfu8ff0":"被动效果：大天狗的技能每次命中敌方式神都会为其添加1层[天狗道]效果,每层[天狗道]会降低目标式神5%法术抗性，持续8秒。#r当[天狗道]叠加至3层时，目标式神会立即受到22#c5591B7(+15.0*等级)#n#c30C066(+30%法强)#n点#U法术伤害#n和减速效果，并减少目标20%的法术抗性，持续4秒。","u6280u80fdu540du79f0":"天狗道","u6280u80fdu6210u957f":{"u51b7u5374":[4],"u6d88u8017":[null]}},"u4e8cu6280u80fd":{"u56feu6807u8defu5f84":"gui/res/skill/1003001.png","u6280u80fdID":100321,"u6280u80fdu63cfu8ff0":"大天狗向指定方向发射一道羽矢，对路径上的敌人造成120#c30C066(+55%法强)#n点#U法术伤害#n；#r如果命中敌方式神，会在敌方式神脚下的地面形成一个阵法，短暂延迟后，这个阵法范围内的所有敌人将会再次受到40#c30C066(+15%法强)#n点#U法术伤害#n，每个敌人最多受到1次阵法的伤害。","u6280u80fdu540du79f0":"羽矢","u6280u80fdu6210u957f":{"u51b7u5374":[7,6,6,5,5],"u9635u6cd5u57fau7840u4f24u5bb3":["40","50","60","70","80"],"u7fbdu77e2u57fau7840u4f24u5bb3":["120","165","210","255","300"],"u6d88u8017":[40,50,60,70,80]}},"u56dbu6280u80fd":{"u56feu6807u8defu5f84":"gui/res/skill/1003004.png","u6280u80fdID":100341,"u6280u80fdu63cfu8ff0":"大天狗振翅施法，在指定范围内生成一个持续3秒的羽刃暴风，暴风每0.5秒对敌人造成一次#U法术伤害#n，每次伤害90#c30C066(+35%法强)#n点，同时对敌人造成20%减速。#r羽刃暴风会在持续时间内扩大暴风的影响范围并缓慢向施法方向移动。","u6280u80fdu540du79f0":"羽刃暴风","u6280u80fdu6210u957f":{"u51cfu901fu6548u679c":["20%","25%","30%"],"u51b7u5374":[50,45,40],"u6d88u8017":[100,100,100],"u57fau7840u4f24u5bb3":["90","150","210"]}},"u4e00u6280u80fd":{"u56feu6807u8defu5f84":"gui/res/skill/1003003.png","u6280u80fdID":100311,"u6280u80fdu63cfu8ff0":"被动效果：永久大天狗增加自身法术强度12.0点。#r被动触发：若持续24秒没有受到来自式神的伤害，大天狗的双翼会打开一个护盾。无懈之翼能够抵抗一次来自敌方式神的技能伤害，同时在抵抗生效后提升大天狗20%移动速度，持续2秒。","u6280u80fdu540du79f0":"无懈之翼","u6280u80fdu6210u957f":{"u51b7u5374":[],"u89e6u53d1u95f4u9694":["24","21","18","15","12"],"u6d88u8017":[null,null,null,null,null],"u589eu52a0u6cd5u5f3a":["12.0","24.0","36.0","48.0","60.0"]}},"u4e09u6280u80fd":{"u56feu6807u8defu5f84":"gui/res/skill/1003002.png","u6280u80fdID":100331,"u6280u80fdu63cfu8ff0":"大天狗对指定目标释放疾风咒，对目标及其周围的敌人造成60#c30C066(+40%法强)#n点#U法术伤害#n和0.5秒的击飞效果。","u6280u80fdu540du79f0":"疾风咒","u6280u80fdu6210u957f":{"u51fbu98deu65f6u95f4":["0.5","0.6","0.7","0.8","0.9"],"u51b7u5374":[10,10,10,10,10],"u6d88u8017":[60,65,70,75,80],"u57fau7840u4f24u5bb3":["60","110","160","210","260"]}}}
         * u63a8u8350u88c5u5907 : {"u63a8u8350u88c5u5907u51fau95e8u88c5":["疾行短靴","古事残页","人鱼魂晶","凝魂符咒","晴明印"],"u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481u8bf4u660e":"爆发","u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483u8bf4u660e":"生存、消耗","u63a8u8350u88c5u5907u9632u5fa1":["杀生石之甲","毗沙门天鼓","缠魂袖爪","月读尊衣","彭侯大铠","金装武神铠","九天穹之翼","繁盛绯衣","三途天光","水月无相","结魂八咫镜"],"u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482u8bf4u660e":"消耗","u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481":["曼荼罗密经","鬼袭之靴","伊邪那神意","出云之章","太阴·太极","山吹花烬"],"u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482":["曼荼罗密经","清心之靴","山吹花烬","伊邪那神意","出云之章","太阴·太极"],"u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483":["七面天女","清心之靴","山吹花烬","天之尾羽张","冥夜星辰","太阴·太极"],"u63a8u8350u88c5u5907u8fdbu653b":["七面天女","曼荼罗密经","伊邪那神意","天沼望月","出云之章","山吹花烬","太阴·太极","冥夜星辰","灵刀·千代","古琴·须臾","妖琴·赫风","天之尾羽张"]}
         * u5f0fu795eu65b9u5934u50cf : gui/res/head_fang/1003.png
         * u63a8u8350u7075u5492 : [10005,10009]
         */

        private String u5f0fu795eu6807u7b7e;
        private String u63a8u8350u5206u8def;
        private String u5f0fu795eu653bu7565;
        private U8bc4u5206Bean u8bc4u5206;
        private U5f0fu795eu5c5eu6027u6210u957fBean u5f0fu795eu5c5eu6027u6210u957f;
        private U5f0fu795eu57fau7840u5c5eu6027Bean u5f0fu795eu57fau7840u5c5eu6027;
        private int u5f0fu795eID;
        private String u5f0fu795eu540du79f0;
        private U5f0fu795eu6280u80fdBean u5f0fu795eu6280u80fd;
        private U63a8u8350u88c5u5907Bean u63a8u8350u88c5u5907;
        private String u5f0fu795eu65b9u5934u50cf;
        private List<String> u5f0fu795eu5b9au4f4d;
        private List<String> cvu540du5b57;
        private List<Integer> u63a8u8350u52a0u70b9u987au5e8f;
        private List<Integer> u63a8u8350u9634u9633u672f;
        private List<Integer> u63a8u8350u7075u5492;

        public String getU5f0fu795eu6807u7b7e() {
            return u5f0fu795eu6807u7b7e;
        }

        public void setU5f0fu795eu6807u7b7e(String u5f0fu795eu6807u7b7e) {
            this.u5f0fu795eu6807u7b7e = u5f0fu795eu6807u7b7e;
        }

        public String getU63a8u8350u5206u8def() {
            return u63a8u8350u5206u8def;
        }

        public void setU63a8u8350u5206u8def(String u63a8u8350u5206u8def) {
            this.u63a8u8350u5206u8def = u63a8u8350u5206u8def;
        }

        public String getU5f0fu795eu653bu7565() {
            return u5f0fu795eu653bu7565;
        }

        public void setU5f0fu795eu653bu7565(String u5f0fu795eu653bu7565) {
            this.u5f0fu795eu653bu7565 = u5f0fu795eu653bu7565;
        }

        public U8bc4u5206Bean getU8bc4u5206() {
            return u8bc4u5206;
        }

        public void setU8bc4u5206(U8bc4u5206Bean u8bc4u5206) {
            this.u8bc4u5206 = u8bc4u5206;
        }

        public U5f0fu795eu5c5eu6027u6210u957fBean getU5f0fu795eu5c5eu6027u6210u957f() {
            return u5f0fu795eu5c5eu6027u6210u957f;
        }

        public void setU5f0fu795eu5c5eu6027u6210u957f(U5f0fu795eu5c5eu6027u6210u957fBean u5f0fu795eu5c5eu6027u6210u957f) {
            this.u5f0fu795eu5c5eu6027u6210u957f = u5f0fu795eu5c5eu6027u6210u957f;
        }

        public U5f0fu795eu57fau7840u5c5eu6027Bean getU5f0fu795eu57fau7840u5c5eu6027() {
            return u5f0fu795eu57fau7840u5c5eu6027;
        }

        public void setU5f0fu795eu57fau7840u5c5eu6027(U5f0fu795eu57fau7840u5c5eu6027Bean u5f0fu795eu57fau7840u5c5eu6027) {
            this.u5f0fu795eu57fau7840u5c5eu6027 = u5f0fu795eu57fau7840u5c5eu6027;
        }

        public int getU5f0fu795eID() {
            return u5f0fu795eID;
        }

        public void setU5f0fu795eID(int u5f0fu795eID) {
            this.u5f0fu795eID = u5f0fu795eID;
        }

        public String getU5f0fu795eu540du79f0() {
            return u5f0fu795eu540du79f0;
        }

        public void setU5f0fu795eu540du79f0(String u5f0fu795eu540du79f0) {
            this.u5f0fu795eu540du79f0 = u5f0fu795eu540du79f0;
        }

        public U5f0fu795eu6280u80fdBean getU5f0fu795eu6280u80fd() {
            return u5f0fu795eu6280u80fd;
        }

        public void setU5f0fu795eu6280u80fd(U5f0fu795eu6280u80fdBean u5f0fu795eu6280u80fd) {
            this.u5f0fu795eu6280u80fd = u5f0fu795eu6280u80fd;
        }

        public U63a8u8350u88c5u5907Bean getU63a8u8350u88c5u5907() {
            return u63a8u8350u88c5u5907;
        }

        public void setU63a8u8350u88c5u5907(U63a8u8350u88c5u5907Bean u63a8u8350u88c5u5907) {
            this.u63a8u8350u88c5u5907 = u63a8u8350u88c5u5907;
        }

        public String getU5f0fu795eu65b9u5934u50cf() {
            return u5f0fu795eu65b9u5934u50cf;
        }

        public void setU5f0fu795eu65b9u5934u50cf(String u5f0fu795eu65b9u5934u50cf) {
            this.u5f0fu795eu65b9u5934u50cf = u5f0fu795eu65b9u5934u50cf;
        }

        public List<String> getU5f0fu795eu5b9au4f4d() {
            return u5f0fu795eu5b9au4f4d;
        }

        public void setU5f0fu795eu5b9au4f4d(List<String> u5f0fu795eu5b9au4f4d) {
            this.u5f0fu795eu5b9au4f4d = u5f0fu795eu5b9au4f4d;
        }

        public List<String> getCvu540du5b57() {
            return cvu540du5b57;
        }

        public void setCvu540du5b57(List<String> cvu540du5b57) {
            this.cvu540du5b57 = cvu540du5b57;
        }

        public List<Integer> getU63a8u8350u52a0u70b9u987au5e8f() {
            return u63a8u8350u52a0u70b9u987au5e8f;
        }

        public void setU63a8u8350u52a0u70b9u987au5e8f(List<Integer> u63a8u8350u52a0u70b9u987au5e8f) {
            this.u63a8u8350u52a0u70b9u987au5e8f = u63a8u8350u52a0u70b9u987au5e8f;
        }

        public List<Integer> getU63a8u8350u9634u9633u672f() {
            return u63a8u8350u9634u9633u672f;
        }

        public void setU63a8u8350u9634u9633u672f(List<Integer> u63a8u8350u9634u9633u672f) {
            this.u63a8u8350u9634u9633u672f = u63a8u8350u9634u9633u672f;
        }

        public List<Integer> getU63a8u8350u7075u5492() {
            return u63a8u8350u7075u5492;
        }

        public void setU63a8u8350u7075u5492(List<Integer> u63a8u8350u7075u5492) {
            this.u63a8u8350u7075u5492 = u63a8u8350u7075u5492;
        }

        public static class U8bc4u5206Bean {
            /**
             * u8f93u51fa : 5
             * u751fu5b58 : 3
             * u63a7u5236 : 5
             * u589eu76ca : 1
             * u654fu6377 : 3
             * u96beu5ea6 : 2
             */

            private int u8f93u51fa;
            private int u751fu5b58;
            private int u63a7u5236;
            private int u589eu76ca;
            private int u654fu6377;
            private int u96beu5ea6;

            public int getU8f93u51fa() {
                return u8f93u51fa;
            }

            public void setU8f93u51fa(int u8f93u51fa) {
                this.u8f93u51fa = u8f93u51fa;
            }

            public int getU751fu5b58() {
                return u751fu5b58;
            }

            public void setU751fu5b58(int u751fu5b58) {
                this.u751fu5b58 = u751fu5b58;
            }

            public int getU63a7u5236() {
                return u63a7u5236;
            }

            public void setU63a7u5236(int u63a7u5236) {
                this.u63a7u5236 = u63a7u5236;
            }

            public int getU589eu76ca() {
                return u589eu76ca;
            }

            public void setU589eu76ca(int u589eu76ca) {
                this.u589eu76ca = u589eu76ca;
            }

            public int getU654fu6377() {
                return u654fu6377;
            }

            public void setU654fu6377(int u654fu6377) {
                this.u654fu6377 = u654fu6377;
            }

            public int getU96beu5ea6() {
                return u96beu5ea6;
            }

            public void setU96beu5ea6(int u96beu5ea6) {
                this.u96beu5ea6 = u96beu5ea6;
            }
        }

        public static class U5f0fu795eu5c5eu6027u6210u957fBean {
            /**
             * u9b54u6cd5u56deu590d : 0.8
             * u9b54u6cd5u4e0au9650 : 50
             * u9b54u6297 : 1.5
             * u653bu901fu52a0u6210 : 0.003
             * u7269u7406u4f24u5bb3 : 4.5
             * u751fu547du6062u590d : 0.82
             * u62a4u7532 : 6.5
             * u751fu547du503c : 151
             */

            private double u9b54u6cd5u56deu590d;
            private int u9b54u6cd5u4e0au9650;
            private double u9b54u6297;
            private double u653bu901fu52a0u6210;
            private double u7269u7406u4f24u5bb3;
            private double u751fu547du6062u590d;
            private double u62a4u7532;
            private float u751fu547du503c;

            public double getU9b54u6cd5u56deu590d() {
                return u9b54u6cd5u56deu590d;
            }

            public void setU9b54u6cd5u56deu590d(double u9b54u6cd5u56deu590d) {
                this.u9b54u6cd5u56deu590d = u9b54u6cd5u56deu590d;
            }

            public int getU9b54u6cd5u4e0au9650() {
                return u9b54u6cd5u4e0au9650;
            }

            public void setU9b54u6cd5u4e0au9650(int u9b54u6cd5u4e0au9650) {
                this.u9b54u6cd5u4e0au9650 = u9b54u6cd5u4e0au9650;
            }

            public double getU9b54u6297() {
                return u9b54u6297;
            }

            public void setU9b54u6297(double u9b54u6297) {
                this.u9b54u6297 = u9b54u6297;
            }

            public double getU653bu901fu52a0u6210() {
                return u653bu901fu52a0u6210;
            }

            public void setU653bu901fu52a0u6210(double u653bu901fu52a0u6210) {
                this.u653bu901fu52a0u6210 = u653bu901fu52a0u6210;
            }

            public double getU7269u7406u4f24u5bb3() {
                return u7269u7406u4f24u5bb3;
            }

            public void setU7269u7406u4f24u5bb3(double u7269u7406u4f24u5bb3) {
                this.u7269u7406u4f24u5bb3 = u7269u7406u4f24u5bb3;
            }

            public double getU751fu547du6062u590d() {
                return u751fu547du6062u590d;
            }

            public void setU751fu547du6062u590d(double u751fu547du6062u590d) {
                this.u751fu547du6062u590d = u751fu547du6062u590d;
            }

            public double getU62a4u7532() {
                return u62a4u7532;
            }

            public void setU62a4u7532(double u62a4u7532) {
                this.u62a4u7532 = u62a4u7532;
            }

            public float getU751fu547du503c() {
                return u751fu547du503c;
            }

            public void setU751fu547du503c(float u751fu547du503c) {
                this.u751fu547du503c = u751fu547du503c;
            }
        }

        public static class U5f0fu795eu57fau7840u5c5eu6027Bean {
            /**
             * u653bu51fbu901fu5ea6 : 0.625
             * u9b54u6cd5u56deu590d : 6
             * u9b54u6cd5u4e0au9650 : 360
             * u9b54u6297 : 60
             * u653bu901fu52a0u6210 : 0.376
             * u7269u7406u4f24u5bb3 : 76
             * u751fu547du6062u590d : 9
             * u653bu51fbu540eu6447 : 21
             * u62a4u7532 : 48
             * u79fbu52a8u901fu5ea6 : 34
             * u751fu547du503c : 800
             */

            private double u653bu51fbu901fu5ea6;
            private float u9b54u6cd5u56deu590d;
            private int u9b54u6cd5u4e0au9650;
            private int u9b54u6297;
            private double u653bu901fu52a0u6210;
            private float u7269u7406u4f24u5bb3;
            private int u751fu547du6062u590d;
            private int u653bu51fbu540eu6447;
            private int u62a4u7532;
            private float u79fbu52a8u901fu5ea6;
            private float u751fu547du503c;

            public double getU653bu51fbu901fu5ea6() {
                return u653bu51fbu901fu5ea6;
            }

            public void setU653bu51fbu901fu5ea6(double u653bu51fbu901fu5ea6) {
                this.u653bu51fbu901fu5ea6 = u653bu51fbu901fu5ea6;
            }

            public float getU9b54u6cd5u56deu590d() {
                return u9b54u6cd5u56deu590d;
            }

            public void setU9b54u6cd5u56deu590d(int u9b54u6cd5u56deu590d) {
                this.u9b54u6cd5u56deu590d = u9b54u6cd5u56deu590d;
            }

            public int getU9b54u6cd5u4e0au9650() {
                return u9b54u6cd5u4e0au9650;
            }

            public void setU9b54u6cd5u4e0au9650(int u9b54u6cd5u4e0au9650) {
                this.u9b54u6cd5u4e0au9650 = u9b54u6cd5u4e0au9650;
            }

            public int getU9b54u6297() {
                return u9b54u6297;
            }

            public void setU9b54u6297(int u9b54u6297) {
                this.u9b54u6297 = u9b54u6297;
            }

            public double getU653bu901fu52a0u6210() {
                return u653bu901fu52a0u6210;
            }

            public void setU653bu901fu52a0u6210(double u653bu901fu52a0u6210) {
                this.u653bu901fu52a0u6210 = u653bu901fu52a0u6210;
            }

            public float getU7269u7406u4f24u5bb3() {
                return u7269u7406u4f24u5bb3;
            }

            public void setU7269u7406u4f24u5bb3(int u7269u7406u4f24u5bb3) {
                this.u7269u7406u4f24u5bb3 = u7269u7406u4f24u5bb3;
            }

            public int getU751fu547du6062u590d() {
                return u751fu547du6062u590d;
            }

            public void setU751fu547du6062u590d(int u751fu547du6062u590d) {
                this.u751fu547du6062u590d = u751fu547du6062u590d;
            }

            public int getU653bu51fbu540eu6447() {
                return u653bu51fbu540eu6447;
            }

            public void setU653bu51fbu540eu6447(int u653bu51fbu540eu6447) {
                this.u653bu51fbu540eu6447 = u653bu51fbu540eu6447;
            }

            public int getU62a4u7532() {
                return u62a4u7532;
            }

            public void setU62a4u7532(int u62a4u7532) {
                this.u62a4u7532 = u62a4u7532;
            }

            public float getU79fbu52a8u901fu5ea6() {
                return u79fbu52a8u901fu5ea6;
            }

            public void setU79fbu52a8u901fu5ea6(float u79fbu52a8u901fu5ea6) {
                this.u79fbu52a8u901fu5ea6 = u79fbu52a8u901fu5ea6;
            }

            public float getU751fu547du503c() {
                return u751fu547du503c;
            }

            public void setU751fu547du503c(int u751fu547du503c) {
                this.u751fu547du503c = u751fu547du503c;
            }
        }

        public static class U5f0fu795eu6280u80fdBean {
            /**
             * u5929u751fu88abu52a8 : {"u56feu6807u8defu5f84":"gui/res/skill/1003000.png","u6280u80fdID":100351,"u6280u80fdu63cfu8ff0":"被动效果：大天狗的技能每次命中敌方式神都会为其添加1层[天狗道]效果,每层[天狗道]会降低目标式神5%法术抗性，持续8秒。#r当[天狗道]叠加至3层时，目标式神会立即受到22#c5591B7(+15.0*等级)#n#c30C066(+30%法强)#n点#U法术伤害#n和减速效果，并减少目标20%的法术抗性，持续4秒。","u6280u80fdu540du79f0":"天狗道","u6280u80fdu6210u957f":{"u51b7u5374":[4],"u6d88u8017":[null]}}
             * u4e8cu6280u80fd : {"u56feu6807u8defu5f84":"gui/res/skill/1003001.png","u6280u80fdID":100321,"u6280u80fdu63cfu8ff0":"大天狗向指定方向发射一道羽矢，对路径上的敌人造成120#c30C066(+55%法强)#n点#U法术伤害#n；#r如果命中敌方式神，会在敌方式神脚下的地面形成一个阵法，短暂延迟后，这个阵法范围内的所有敌人将会再次受到40#c30C066(+15%法强)#n点#U法术伤害#n，每个敌人最多受到1次阵法的伤害。","u6280u80fdu540du79f0":"羽矢","u6280u80fdu6210u957f":{"u51b7u5374":[7,6,6,5,5],"u9635u6cd5u57fau7840u4f24u5bb3":["40","50","60","70","80"],"u7fbdu77e2u57fau7840u4f24u5bb3":["120","165","210","255","300"],"u6d88u8017":[40,50,60,70,80]}}
             * u56dbu6280u80fd : {"u56feu6807u8defu5f84":"gui/res/skill/1003004.png","u6280u80fdID":100341,"u6280u80fdu63cfu8ff0":"大天狗振翅施法，在指定范围内生成一个持续3秒的羽刃暴风，暴风每0.5秒对敌人造成一次#U法术伤害#n，每次伤害90#c30C066(+35%法强)#n点，同时对敌人造成20%减速。#r羽刃暴风会在持续时间内扩大暴风的影响范围并缓慢向施法方向移动。","u6280u80fdu540du79f0":"羽刃暴风","u6280u80fdu6210u957f":{"u51cfu901fu6548u679c":["20%","25%","30%"],"u51b7u5374":[50,45,40],"u6d88u8017":[100,100,100],"u57fau7840u4f24u5bb3":["90","150","210"]}}
             * u4e00u6280u80fd : {"u56feu6807u8defu5f84":"gui/res/skill/1003003.png","u6280u80fdID":100311,"u6280u80fdu63cfu8ff0":"被动效果：永久大天狗增加自身法术强度12.0点。#r被动触发：若持续24秒没有受到来自式神的伤害，大天狗的双翼会打开一个护盾。无懈之翼能够抵抗一次来自敌方式神的技能伤害，同时在抵抗生效后提升大天狗20%移动速度，持续2秒。","u6280u80fdu540du79f0":"无懈之翼","u6280u80fdu6210u957f":{"u51b7u5374":[],"u89e6u53d1u95f4u9694":["24","21","18","15","12"],"u6d88u8017":[null,null,null,null,null],"u589eu52a0u6cd5u5f3a":["12.0","24.0","36.0","48.0","60.0"]}}
             * u4e09u6280u80fd : {"u56feu6807u8defu5f84":"gui/res/skill/1003002.png","u6280u80fdID":100331,"u6280u80fdu63cfu8ff0":"大天狗对指定目标释放疾风咒，对目标及其周围的敌人造成60#c30C066(+40%法强)#n点#U法术伤害#n和0.5秒的击飞效果。","u6280u80fdu540du79f0":"疾风咒","u6280u80fdu6210u957f":{"u51fbu98deu65f6u95f4":["0.5","0.6","0.7","0.8","0.9"],"u51b7u5374":[10,10,10,10,10],"u6d88u8017":[60,65,70,75,80],"u57fau7840u4f24u5bb3":["60","110","160","210","260"]}}
             */

            private U5929u751fu88abu52a8Bean u5929u751fu88abu52a8;
            private U4e8cu6280u80fdBean u4e8cu6280u80fd;
            private U56dbu6280u80fdBean u56dbu6280u80fd;
            private U4e00u6280u80fdBean u4e00u6280u80fd;
            private U4e09u6280u80fdBean u4e09u6280u80fd;

            public U5929u751fu88abu52a8Bean getU5929u751fu88abu52a8() {
                return u5929u751fu88abu52a8;
            }

            public void setU5929u751fu88abu52a8(U5929u751fu88abu52a8Bean u5929u751fu88abu52a8) {
                this.u5929u751fu88abu52a8 = u5929u751fu88abu52a8;
            }

            public U4e8cu6280u80fdBean getU4e8cu6280u80fd() {
                return u4e8cu6280u80fd;
            }

            public void setU4e8cu6280u80fd(U4e8cu6280u80fdBean u4e8cu6280u80fd) {
                this.u4e8cu6280u80fd = u4e8cu6280u80fd;
            }

            public U56dbu6280u80fdBean getU56dbu6280u80fd() {
                return u56dbu6280u80fd;
            }

            public void setU56dbu6280u80fd(U56dbu6280u80fdBean u56dbu6280u80fd) {
                this.u56dbu6280u80fd = u56dbu6280u80fd;
            }

            public U4e00u6280u80fdBean getU4e00u6280u80fd() {
                return u4e00u6280u80fd;
            }

            public void setU4e00u6280u80fd(U4e00u6280u80fdBean u4e00u6280u80fd) {
                this.u4e00u6280u80fd = u4e00u6280u80fd;
            }

            public U4e09u6280u80fdBean getU4e09u6280u80fd() {
                return u4e09u6280u80fd;
            }

            public void setU4e09u6280u80fd(U4e09u6280u80fdBean u4e09u6280u80fd) {
                this.u4e09u6280u80fd = u4e09u6280u80fd;
            }

            public static class U5929u751fu88abu52a8Bean {
                /**
                 * u56feu6807u8defu5f84 : gui/res/skill/1003000.png
                 * u6280u80fdID : 100351
                 * u6280u80fdu63cfu8ff0 : 被动效果：大天狗的技能每次命中敌方式神都会为其添加1层[天狗道]效果,每层[天狗道]会降低目标式神5%法术抗性，持续8秒。#r当[天狗道]叠加至3层时，目标式神会立即受到22#c5591B7(+15.0*等级)#n#c30C066(+30%法强)#n点#U法术伤害#n和减速效果，并减少目标20%的法术抗性，持续4秒。
                 * u6280u80fdu540du79f0 : 天狗道
                 * u6280u80fdu6210u957f : {"u51b7u5374":[4],"u6d88u8017":[null]}
                 */

                private String u56feu6807u8defu5f84;
                private int u6280u80fdID;
                private String u6280u80fdu63cfu8ff0;
                private String u6280u80fdu540du79f0;
                private U6280u80fdu6210u957fBean u6280u80fdu6210u957f;

                public String getU56feu6807u8defu5f84() {
                    return u56feu6807u8defu5f84;
                }

                public void setU56feu6807u8defu5f84(String u56feu6807u8defu5f84) {
                    this.u56feu6807u8defu5f84 = u56feu6807u8defu5f84;
                }

                public int getU6280u80fdID() {
                    return u6280u80fdID;
                }

                public void setU6280u80fdID(int u6280u80fdID) {
                    this.u6280u80fdID = u6280u80fdID;
                }

                public String getU6280u80fdu63cfu8ff0() {
                    return u6280u80fdu63cfu8ff0;
                }

                public void setU6280u80fdu63cfu8ff0(String u6280u80fdu63cfu8ff0) {
                    this.u6280u80fdu63cfu8ff0 = u6280u80fdu63cfu8ff0;
                }

                public String getU6280u80fdu540du79f0() {
                    return u6280u80fdu540du79f0;
                }

                public void setU6280u80fdu540du79f0(String u6280u80fdu540du79f0) {
                    this.u6280u80fdu540du79f0 = u6280u80fdu540du79f0;
                }

                public U6280u80fdu6210u957fBean getU6280u80fdu6210u957f() {
                    return u6280u80fdu6210u957f;
                }

                public void setU6280u80fdu6210u957f(U6280u80fdu6210u957fBean u6280u80fdu6210u957f) {
                    this.u6280u80fdu6210u957f = u6280u80fdu6210u957f;
                }

                public static class U6280u80fdu6210u957fBean {
                    private List<Integer> u51b7u5374;
                    private List<Integer> u6d88u8017;

                    public List<Integer> getU51b7u5374() {
                        return u51b7u5374;
                    }

                    public void setU51b7u5374(List<Integer> u51b7u5374) {
                        this.u51b7u5374 = u51b7u5374;
                    }

                    public List<Integer> getU6d88u8017() {
                        return u6d88u8017;
                    }

                    public void setU6d88u8017(List<Integer> u6d88u8017) {
                        this.u6d88u8017 = u6d88u8017;
                    }
                }
            }

            public static class U4e8cu6280u80fdBean {
                /**
                 * u56feu6807u8defu5f84 : gui/res/skill/1003001.png
                 * u6280u80fdID : 100321
                 * u6280u80fdu63cfu8ff0 : 大天狗向指定方向发射一道羽矢，对路径上的敌人造成120#c30C066(+55%法强)#n点#U法术伤害#n；#r如果命中敌方式神，会在敌方式神脚下的地面形成一个阵法，短暂延迟后，这个阵法范围内的所有敌人将会再次受到40#c30C066(+15%法强)#n点#U法术伤害#n，每个敌人最多受到1次阵法的伤害。
                 * u6280u80fdu540du79f0 : 羽矢
                 * u6280u80fdu6210u957f : {"u51b7u5374":[7,6,6,5,5],"u9635u6cd5u57fau7840u4f24u5bb3":["40","50","60","70","80"],"u7fbdu77e2u57fau7840u4f24u5bb3":["120","165","210","255","300"],"u6d88u8017":[40,50,60,70,80]}
                 */

                private String u56feu6807u8defu5f84;
                private int u6280u80fdID;
                private String u6280u80fdu63cfu8ff0;
                private String u6280u80fdu540du79f0;
                private U6280u80fdu6210u957fBeanX u6280u80fdu6210u957f;

                public String getU56feu6807u8defu5f84() {
                    return u56feu6807u8defu5f84;
                }

                public void setU56feu6807u8defu5f84(String u56feu6807u8defu5f84) {
                    this.u56feu6807u8defu5f84 = u56feu6807u8defu5f84;
                }

                public int getU6280u80fdID() {
                    return u6280u80fdID;
                }

                public void setU6280u80fdID(int u6280u80fdID) {
                    this.u6280u80fdID = u6280u80fdID;
                }

                public String getU6280u80fdu63cfu8ff0() {
                    return u6280u80fdu63cfu8ff0;
                }

                public void setU6280u80fdu63cfu8ff0(String u6280u80fdu63cfu8ff0) {
                    this.u6280u80fdu63cfu8ff0 = u6280u80fdu63cfu8ff0;
                }

                public String getU6280u80fdu540du79f0() {
                    return u6280u80fdu540du79f0;
                }

                public void setU6280u80fdu540du79f0(String u6280u80fdu540du79f0) {
                    this.u6280u80fdu540du79f0 = u6280u80fdu540du79f0;
                }

                public U6280u80fdu6210u957fBeanX getU6280u80fdu6210u957f() {
                    return u6280u80fdu6210u957f;
                }

                public void setU6280u80fdu6210u957f(U6280u80fdu6210u957fBeanX u6280u80fdu6210u957f) {
                    this.u6280u80fdu6210u957f = u6280u80fdu6210u957f;
                }

                public static class U6280u80fdu6210u957fBeanX {
                    private List<Integer> u51b7u5374;
                    private List<String> u9635u6cd5u57fau7840u4f24u5bb3;
                    private List<String> u7fbdu77e2u57fau7840u4f24u5bb3;
                    private List<Integer> u6d88u8017;

                    public List<Integer> getU51b7u5374() {
                        return u51b7u5374;
                    }

                    public void setU51b7u5374(List<Integer> u51b7u5374) {
                        this.u51b7u5374 = u51b7u5374;
                    }

                    public List<String> getU9635u6cd5u57fau7840u4f24u5bb3() {
                        return u9635u6cd5u57fau7840u4f24u5bb3;
                    }

                    public void setU9635u6cd5u57fau7840u4f24u5bb3(List<String> u9635u6cd5u57fau7840u4f24u5bb3) {
                        this.u9635u6cd5u57fau7840u4f24u5bb3 = u9635u6cd5u57fau7840u4f24u5bb3;
                    }

                    public List<String> getU7fbdu77e2u57fau7840u4f24u5bb3() {
                        return u7fbdu77e2u57fau7840u4f24u5bb3;
                    }

                    public void setU7fbdu77e2u57fau7840u4f24u5bb3(List<String> u7fbdu77e2u57fau7840u4f24u5bb3) {
                        this.u7fbdu77e2u57fau7840u4f24u5bb3 = u7fbdu77e2u57fau7840u4f24u5bb3;
                    }

                    public List<Integer> getU6d88u8017() {
                        return u6d88u8017;
                    }

                    public void setU6d88u8017(List<Integer> u6d88u8017) {
                        this.u6d88u8017 = u6d88u8017;
                    }
                }
            }

            public static class U56dbu6280u80fdBean {
                /**
                 * u56feu6807u8defu5f84 : gui/res/skill/1003004.png
                 * u6280u80fdID : 100341
                 * u6280u80fdu63cfu8ff0 : 大天狗振翅施法，在指定范围内生成一个持续3秒的羽刃暴风，暴风每0.5秒对敌人造成一次#U法术伤害#n，每次伤害90#c30C066(+35%法强)#n点，同时对敌人造成20%减速。#r羽刃暴风会在持续时间内扩大暴风的影响范围并缓慢向施法方向移动。
                 * u6280u80fdu540du79f0 : 羽刃暴风
                 * u6280u80fdu6210u957f : {"u51cfu901fu6548u679c":["20%","25%","30%"],"u51b7u5374":[50,45,40],"u6d88u8017":[100,100,100],"u57fau7840u4f24u5bb3":["90","150","210"]}
                 */

                private String u56feu6807u8defu5f84;
                private int u6280u80fdID;
                private String u6280u80fdu63cfu8ff0;
                private String u6280u80fdu540du79f0;
                private U6280u80fdu6210u957fBeanXX u6280u80fdu6210u957f;

                public String getU56feu6807u8defu5f84() {
                    return u56feu6807u8defu5f84;
                }

                public void setU56feu6807u8defu5f84(String u56feu6807u8defu5f84) {
                    this.u56feu6807u8defu5f84 = u56feu6807u8defu5f84;
                }

                public int getU6280u80fdID() {
                    return u6280u80fdID;
                }

                public void setU6280u80fdID(int u6280u80fdID) {
                    this.u6280u80fdID = u6280u80fdID;
                }

                public String getU6280u80fdu63cfu8ff0() {
                    return u6280u80fdu63cfu8ff0;
                }

                public void setU6280u80fdu63cfu8ff0(String u6280u80fdu63cfu8ff0) {
                    this.u6280u80fdu63cfu8ff0 = u6280u80fdu63cfu8ff0;
                }

                public String getU6280u80fdu540du79f0() {
                    return u6280u80fdu540du79f0;
                }

                public void setU6280u80fdu540du79f0(String u6280u80fdu540du79f0) {
                    this.u6280u80fdu540du79f0 = u6280u80fdu540du79f0;
                }

                public U6280u80fdu6210u957fBeanXX getU6280u80fdu6210u957f() {
                    return u6280u80fdu6210u957f;
                }

                public void setU6280u80fdu6210u957f(U6280u80fdu6210u957fBeanXX u6280u80fdu6210u957f) {
                    this.u6280u80fdu6210u957f = u6280u80fdu6210u957f;
                }

                public static class U6280u80fdu6210u957fBeanXX {
                    private List<String> u51cfu901fu6548u679c;
                    private List<Integer> u51b7u5374;
                    private List<Integer> u6d88u8017;
                    private List<String> u57fau7840u4f24u5bb3;

                    public List<String> getU51cfu901fu6548u679c() {
                        return u51cfu901fu6548u679c;
                    }

                    public void setU51cfu901fu6548u679c(List<String> u51cfu901fu6548u679c) {
                        this.u51cfu901fu6548u679c = u51cfu901fu6548u679c;
                    }

                    public List<Integer> getU51b7u5374() {
                        return u51b7u5374;
                    }

                    public void setU51b7u5374(List<Integer> u51b7u5374) {
                        this.u51b7u5374 = u51b7u5374;
                    }

                    public List<Integer> getU6d88u8017() {
                        return u6d88u8017;
                    }

                    public void setU6d88u8017(List<Integer> u6d88u8017) {
                        this.u6d88u8017 = u6d88u8017;
                    }

                    public List<String> getU57fau7840u4f24u5bb3() {
                        return u57fau7840u4f24u5bb3;
                    }

                    public void setU57fau7840u4f24u5bb3(List<String> u57fau7840u4f24u5bb3) {
                        this.u57fau7840u4f24u5bb3 = u57fau7840u4f24u5bb3;
                    }
                }
            }

            public static class U4e00u6280u80fdBean {
                /**
                 * u56feu6807u8defu5f84 : gui/res/skill/1003003.png
                 * u6280u80fdID : 100311
                 * u6280u80fdu63cfu8ff0 : 被动效果：永久大天狗增加自身法术强度12.0点。#r被动触发：若持续24秒没有受到来自式神的伤害，大天狗的双翼会打开一个护盾。无懈之翼能够抵抗一次来自敌方式神的技能伤害，同时在抵抗生效后提升大天狗20%移动速度，持续2秒。
                 * u6280u80fdu540du79f0 : 无懈之翼
                 * u6280u80fdu6210u957f : {"u51b7u5374":[],"u89e6u53d1u95f4u9694":["24","21","18","15","12"],"u6d88u8017":[null,null,null,null,null],"u589eu52a0u6cd5u5f3a":["12.0","24.0","36.0","48.0","60.0"]}
                 */

                private String u56feu6807u8defu5f84;
                private int u6280u80fdID;
                private String u6280u80fdu63cfu8ff0;
                private String u6280u80fdu540du79f0;
                private U6280u80fdu6210u957fBeanXXX u6280u80fdu6210u957f;

                public String getU56feu6807u8defu5f84() {
                    return u56feu6807u8defu5f84;
                }

                public void setU56feu6807u8defu5f84(String u56feu6807u8defu5f84) {
                    this.u56feu6807u8defu5f84 = u56feu6807u8defu5f84;
                }

                public int getU6280u80fdID() {
                    return u6280u80fdID;
                }

                public void setU6280u80fdID(int u6280u80fdID) {
                    this.u6280u80fdID = u6280u80fdID;
                }

                public String getU6280u80fdu63cfu8ff0() {
                    return u6280u80fdu63cfu8ff0;
                }

                public void setU6280u80fdu63cfu8ff0(String u6280u80fdu63cfu8ff0) {
                    this.u6280u80fdu63cfu8ff0 = u6280u80fdu63cfu8ff0;
                }

                public String getU6280u80fdu540du79f0() {
                    return u6280u80fdu540du79f0;
                }

                public void setU6280u80fdu540du79f0(String u6280u80fdu540du79f0) {
                    this.u6280u80fdu540du79f0 = u6280u80fdu540du79f0;
                }

                public U6280u80fdu6210u957fBeanXXX getU6280u80fdu6210u957f() {
                    return u6280u80fdu6210u957f;
                }

                public void setU6280u80fdu6210u957f(U6280u80fdu6210u957fBeanXXX u6280u80fdu6210u957f) {
                    this.u6280u80fdu6210u957f = u6280u80fdu6210u957f;
                }

                public static class U6280u80fdu6210u957fBeanXXX {
                    private List<?> u51b7u5374;
                    private List<String> u89e6u53d1u95f4u9694;
                    private List<Integer> u6d88u8017;
                    private List<String> u589eu52a0u6cd5u5f3a;

                    public List<?> getU51b7u5374() {
                        return u51b7u5374;
                    }

                    public void setU51b7u5374(List<?> u51b7u5374) {
                        this.u51b7u5374 = u51b7u5374;
                    }

                    public List<String> getU89e6u53d1u95f4u9694() {
                        return u89e6u53d1u95f4u9694;
                    }

                    public void setU89e6u53d1u95f4u9694(List<String> u89e6u53d1u95f4u9694) {
                        this.u89e6u53d1u95f4u9694 = u89e6u53d1u95f4u9694;
                    }

                    public List<Integer> getU6d88u8017() {
                        return u6d88u8017;
                    }

                    public void setU6d88u8017(List<Integer> u6d88u8017) {
                        this.u6d88u8017 = u6d88u8017;
                    }

                    public List<String> getU589eu52a0u6cd5u5f3a() {
                        return u589eu52a0u6cd5u5f3a;
                    }

                    public void setU589eu52a0u6cd5u5f3a(List<String> u589eu52a0u6cd5u5f3a) {
                        this.u589eu52a0u6cd5u5f3a = u589eu52a0u6cd5u5f3a;
                    }
                }
            }

            public static class U4e09u6280u80fdBean {
                /**
                 * u56feu6807u8defu5f84 : gui/res/skill/1003002.png
                 * u6280u80fdID : 100331
                 * u6280u80fdu63cfu8ff0 : 大天狗对指定目标释放疾风咒，对目标及其周围的敌人造成60#c30C066(+40%法强)#n点#U法术伤害#n和0.5秒的击飞效果。
                 * u6280u80fdu540du79f0 : 疾风咒
                 * u6280u80fdu6210u957f : {"u51fbu98deu65f6u95f4":["0.5","0.6","0.7","0.8","0.9"],"u51b7u5374":[10,10,10,10,10],"u6d88u8017":[60,65,70,75,80],"u57fau7840u4f24u5bb3":["60","110","160","210","260"]}
                 */

                private String u56feu6807u8defu5f84;
                private int u6280u80fdID;
                private String u6280u80fdu63cfu8ff0;
                private String u6280u80fdu540du79f0;
                private U6280u80fdu6210u957fBeanXXXX u6280u80fdu6210u957f;

                public String getU56feu6807u8defu5f84() {
                    return u56feu6807u8defu5f84;
                }

                public void setU56feu6807u8defu5f84(String u56feu6807u8defu5f84) {
                    this.u56feu6807u8defu5f84 = u56feu6807u8defu5f84;
                }

                public int getU6280u80fdID() {
                    return u6280u80fdID;
                }

                public void setU6280u80fdID(int u6280u80fdID) {
                    this.u6280u80fdID = u6280u80fdID;
                }

                public String getU6280u80fdu63cfu8ff0() {
                    return u6280u80fdu63cfu8ff0;
                }

                public void setU6280u80fdu63cfu8ff0(String u6280u80fdu63cfu8ff0) {
                    this.u6280u80fdu63cfu8ff0 = u6280u80fdu63cfu8ff0;
                }

                public String getU6280u80fdu540du79f0() {
                    return u6280u80fdu540du79f0;
                }

                public void setU6280u80fdu540du79f0(String u6280u80fdu540du79f0) {
                    this.u6280u80fdu540du79f0 = u6280u80fdu540du79f0;
                }

                public U6280u80fdu6210u957fBeanXXXX getU6280u80fdu6210u957f() {
                    return u6280u80fdu6210u957f;
                }

                public void setU6280u80fdu6210u957f(U6280u80fdu6210u957fBeanXXXX u6280u80fdu6210u957f) {
                    this.u6280u80fdu6210u957f = u6280u80fdu6210u957f;
                }

                public static class U6280u80fdu6210u957fBeanXXXX {
                    private List<String> u51fbu98deu65f6u95f4;
                    private List<Integer> u51b7u5374;
                    private List<Integer> u6d88u8017;
                    private List<String> u57fau7840u4f24u5bb3;

                    public List<String> getU51fbu98deu65f6u95f4() {
                        return u51fbu98deu65f6u95f4;
                    }

                    public void setU51fbu98deu65f6u95f4(List<String> u51fbu98deu65f6u95f4) {
                        this.u51fbu98deu65f6u95f4 = u51fbu98deu65f6u95f4;
                    }

                    public List<Integer> getU51b7u5374() {
                        return u51b7u5374;
                    }

                    public void setU51b7u5374(List<Integer> u51b7u5374) {
                        this.u51b7u5374 = u51b7u5374;
                    }

                    public List<Integer> getU6d88u8017() {
                        return u6d88u8017;
                    }

                    public void setU6d88u8017(List<Integer> u6d88u8017) {
                        this.u6d88u8017 = u6d88u8017;
                    }

                    public List<String> getU57fau7840u4f24u5bb3() {
                        return u57fau7840u4f24u5bb3;
                    }

                    public void setU57fau7840u4f24u5bb3(List<String> u57fau7840u4f24u5bb3) {
                        this.u57fau7840u4f24u5bb3 = u57fau7840u4f24u5bb3;
                    }
                }
            }
        }

        public static class U63a8u8350u88c5u5907Bean {
            /**
             * u63a8u8350u88c5u5907u51fau95e8u88c5 : ["疾行短靴","古事残页","人鱼魂晶","凝魂符咒","晴明印"]
             * u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481u8bf4u660e : 爆发
             * u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483u8bf4u660e : 生存、消耗
             * u63a8u8350u88c5u5907u9632u5fa1 : ["杀生石之甲","毗沙门天鼓","缠魂袖爪","月读尊衣","彭侯大铠","金装武神铠","九天穹之翼","繁盛绯衣","三途天光","水月无相","结魂八咫镜"]
             * u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482u8bf4u660e : 消耗
             * u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481 : ["曼荼罗密经","鬼袭之靴","伊邪那神意","出云之章","太阴·太极","山吹花烬"]
             * u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482 : ["曼荼罗密经","清心之靴","山吹花烬","伊邪那神意","出云之章","太阴·太极"]
             * u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483 : ["七面天女","清心之靴","山吹花烬","天之尾羽张","冥夜星辰","太阴·太极"]
             * u63a8u8350u88c5u5907u8fdbu653b : ["七面天女","曼荼罗密经","伊邪那神意","天沼望月","出云之章","山吹花烬","太阴·太极","冥夜星辰","灵刀·千代","古琴·须臾","妖琴·赫风","天之尾羽张"]
             */

            private String u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481u8bf4u660e;
            private String u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483u8bf4u660e;
            private String u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482u8bf4u660e;
            private List<String> u63a8u8350u88c5u5907u51fau95e8u88c5;
            private List<String> u63a8u8350u88c5u5907u9632u5fa1;
            private List<String> u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481;
            private List<String> u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482;
            private List<String> u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483;
            private List<String> u63a8u8350u88c5u5907u8fdbu653b;

            public String getU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481u8bf4u660e() {
                return u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481u8bf4u660e;
            }

            public void setU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481u8bf4u660e(String u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481u8bf4u660e) {
                this.u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481u8bf4u660e = u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481u8bf4u660e;
            }

            public String getU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483u8bf4u660e() {
                return u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483u8bf4u660e;
            }

            public void setU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483u8bf4u660e(String u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483u8bf4u660e) {
                this.u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483u8bf4u660e = u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483u8bf4u660e;
            }

            public String getU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482u8bf4u660e() {
                return u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482u8bf4u660e;
            }

            public void setU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482u8bf4u660e(String u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482u8bf4u660e) {
                this.u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482u8bf4u660e = u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482u8bf4u660e;
            }

            public List<String> getU63a8u8350u88c5u5907u51fau95e8u88c5() {
                return u63a8u8350u88c5u5907u51fau95e8u88c5;
            }

            public void setU63a8u8350u88c5u5907u51fau95e8u88c5(List<String> u63a8u8350u88c5u5907u51fau95e8u88c5) {
                this.u63a8u8350u88c5u5907u51fau95e8u88c5 = u63a8u8350u88c5u5907u51fau95e8u88c5;
            }

            public List<String> getU63a8u8350u88c5u5907u9632u5fa1() {
                return u63a8u8350u88c5u5907u9632u5fa1;
            }

            public void setU63a8u8350u88c5u5907u9632u5fa1(List<String> u63a8u8350u88c5u5907u9632u5fa1) {
                this.u63a8u8350u88c5u5907u9632u5fa1 = u63a8u8350u88c5u5907u9632u5fa1;
            }

            public List<String> getU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481() {
                return u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481;
            }

            public void setU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481(List<String> u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481) {
                this.u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481 = u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68481;
            }

            public List<String> getU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482() {
                return u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482;
            }

            public void setU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482(List<String> u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482) {
                this.u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482 = u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68482;
            }

            public List<String> getU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483() {
                return u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483;
            }

            public void setU63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483(List<String> u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483) {
                this.u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483 = u63a8u8350u88c5u5907u9ed8u8ba4u65b9u68483;
            }

            public List<String> getU63a8u8350u88c5u5907u8fdbu653b() {
                return u63a8u8350u88c5u5907u8fdbu653b;
            }

            public void setU63a8u8350u88c5u5907u8fdbu653b(List<String> u63a8u8350u88c5u5907u8fdbu653b) {
                this.u63a8u8350u88c5u5907u8fdbu653b = u63a8u8350u88c5u5907u8fdbu653b;
            }
        }
    }
}
