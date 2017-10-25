package com.wangxia.battle.model.bean;

import java.util.List;

/**
 * Created by 昝奥博 on 2017/9/16 0016
 * Email:18772833900@163.com
 * Explain： 视频列表，采集的数据
 */
public class VideoList {
    private int count;
    private int total;
    private String nextPageUrl;
    private long date;
    private long nextPublishTime;
    private int refreshCount;
    private int lastStartId;
    private List<ItemListBeanX> itemList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getNextPublishTime() {
        return nextPublishTime;
    }

    public void setNextPublishTime(long nextPublishTime) {
        this.nextPublishTime = nextPublishTime;
    }

    public int getRefreshCount() {
        return refreshCount;
    }

    public void setRefreshCount(int refreshCount) {
        this.refreshCount = refreshCount;
    }

    public int getLastStartId() {
        return lastStartId;
    }

    public void setLastStartId(int lastStartId) {
        this.lastStartId = lastStartId;
    }

    public List<ItemListBeanX> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemListBeanX> itemList) {
        this.itemList = itemList;
    }

    public static class ItemListBeanX {
        /**
         * type : video
         * data : {"dataType":"VideoBeanForClient","id":19373,"title":"「激情约会」之夸张修容妆","text":"- Sep. 15, Brunch -","slogan":"女为悦己者容，但如今能让人开心的事有点变化\u2026\u2026","description":"很多直男不喜欢女票浓妆艳抹，不过相机吃妆，为了社交网络上永恒的点赞，怎样都是值得的呀~","image":"http://img.kaiyanapp.com/30e25b9eaedd7dde55402042e8e78dbd.jpeg?imageMogr2/quality/60/format/jpg","actionUrl":"eyepetizer://webview/?title=%E6%97%B6%E5%B0%9A%E9%9D%A0%E8%BE%B9%E7%AB%99%EF%BC%8CTubular%E7%A0%B4%E6%A0%BC%E7%99%","provider":{"name":"定制来源","alias":"CustomSrc","icon":""},"category":"搞笑","author":{"id":720,"icon":"http://img.kaiyanapp.com/f1f1bd0ddd17b2163b63037c87b92c54.jpeg?imageMogr2/quality/60/format/jpg","name":"CollegeHumor","description":"原创搞笑视频 CollegeHumor.com 博主。","link":"","latestReleaseTime":1505523601000,"videoNum":94,"adTrack":null,"follow":{"itemType":"author","itemId":720,"followed":false},"shield":{"itemType":"author","itemId":720,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true},"cover":{"feed":"http://img.kaiyanapp.com/7da3c44a340af1ab111e77cc9fc347c1.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/7da3c44a340af1ab111e77cc9fc347c1.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/d6fd4fd28e16b99ce85b636e9d8a011d.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":"http://img.kaiyanapp.com/7da3c44a340af1ab111e77cc9fc347c1.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim"},"playUrl":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19373&editionType=default&source=qcloud","thumbPlayUrl":null,"duration":132,"webUrl":{"raw":null,"forWeibo":"http://wandou.im/3mugp4"},"releaseTime":1505523601000,"library":"DAILY","playInfo":[{"height":480,"width":854,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19373&editionType=normal&source=qcloud","size":7952052},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19373&editionType=normal&source=ucloud","size":7952052}],"name":"标清","type":"normal","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19373&editionType=normal&source=qcloud"},{"height":720,"width":1280,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19373&editionType=high&source=qcloud","size":14634285},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19373&editionType=high&source=ucloud","size":14634285}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19373&editionType=high&source=qcloud"}],"itemList":[{"type":"video","data":{"dataType":"VideoBeanForClient","id":50689,"title":"爱之愈深 悟之愈彻｜这围棋下的太爽了","slogan":null,"description":"对于下棋人来说，围棋是一生的朋友。                                       \u2014\u2014吴清源","provider":{"name":"PGC","alias":"PGC","icon":""},"category":"记录","author":{"id":1374,"icon":"http://img.kaiyanapp.com/074012d51e266f6d9fa76632a599a678.png?imageMogr2/quality/60/format/jpg","name":"老虎映像","description":"用影像展现那些众所周知和从未发觉的事物","link":"","latestReleaseTime":1505527202000,"videoNum":20,"adTrack":null,"follow":{"itemType":"author","itemId":1374,"followed":false},"shield":{"itemType":"author","itemId":1374,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true},"cover":{"feed":"http://img.kaiyanapp.com/e189f9fa8f2171e8159a95e38673265e.png?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/e189f9fa8f2171e8159a95e38673265e.png?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/8e25d8ea794bc1640f79c59b0771feab.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":null},"playUrl":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=default&source=qcloud","thumbPlayUrl":null,"duration":120,"webUrl":{"raw":"http://www.eyepetizer.net/detail.html?vid=50689","forWeibo":"http://wandou.im/3obg0y"},"releaseTime":1505527202000,"library":"NOT_RECOMMEND","playInfo":[{"height":480,"width":854,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=normal&source=qcloud","size":8686859},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=normal&source=ucloud","size":8686859}],"name":"标清","type":"normal","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=normal&source=qcloud"},{"height":720,"width":1280,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=high&source=qcloud","size":13392908},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=high&source=ucloud","size":13392908}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=high&source=qcloud"}],"consumption":{"collectionCount":0,"shareCount":0,"replyCount":0},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[],"type":"NORMAL","titlePgc":"爱之愈深 悟之愈彻｜这围棋下的太爽了","descriptionPgc":"对于下棋人来说，围棋是一生的朋友。                                       \u2014\u2014吴清源","remark":"围棋  老虎映像","idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1505527202000,"promotion":null,"label":null,"labelList":[],"descriptionEditor":"","collected":false,"played":false,"subtitles":[],"lastViewTime":null,"playlists":null},"tag":null},{"type":"video","data":{"dataType":"VideoBeanForClient","id":51357,"title":"爱尔兰人品尝美式小吃","slogan":null,"description":"你想试试么？","provider":{"name":"定制来源","alias":"CustomSrc","icon":""},"category":"开胃","author":{"id":1837,"icon":"http://img.kaiyanapp.com/5cea103724b47a43e1eae0f22e3f4dae.jpeg?imageMogr2/quality/60/format/jpg","name":"Facts.","description":"分享关于爱尔兰人们尝试没吃过的食物，或没看过的电视节目等等有趣的视频。","link":"","latestReleaseTime":1505524165000,"videoNum":1,"adTrack":null,"follow":{"itemType":"author","itemId":1837,"followed":false},"shield":{"itemType":"author","itemId":1837,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true},"cover":{"feed":"http://img.kaiyanapp.com/c18dfd64074642c2a21a8b2255726f38.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/c18dfd64074642c2a21a8b2255726f38.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/76e6dc14fcc5381d1bf26f8b17677d6a.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":null},"playUrl":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51357&editionType=default&source=qcloud","thumbPlayUrl":null,"duration":195,"webUrl":{"raw":"http://www.eyepetizer.net/detail.html?vid=51357","forWeibo":"http://wandou.im/3obrvm"},"releaseTime":1505524165000,"library":"DEFAULT","playInfo":[{"height":720,"width":1280,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51357&editionType=high&source=qcloud","size":25442181},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51357&editionType=high&source=ucloud","size":25442181}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51357&editionType=high&source=qcloud"}],"consumption":{"collectionCount":2,"shareCount":0,"replyCount":0},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[],"type":"NORMAL","titlePgc":"爱尔兰人品尝美式小吃","descriptionPgc":"你想试试么？","remark":null,"idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1505524165000,"promotion":null,"label":null,"labelList":[],"descriptionEditor":"","collected":false,"played":false,"subtitles":[],"lastViewTime":null,"playlists":null},"tag":null},{"type":"video","data":{"dataType":"VideoBeanForClient","id":51356,"title":"精致蛋糕制作过程集锦","slogan":null,"description":"让人观看时很舒服。","provider":{"name":"定制来源","alias":"CustomSrc","icon":""},"category":"开胃","author":{"id":1833,"icon":"http://img.kaiyanapp.com/c93e92b79c1f909a6bcf5d70f96aec76.jpeg?imageMogr2/quality/60/format/jpg","name":"The Icing Artist","description":"分享精致又迷人的蛋糕创意。","link":"","latestReleaseTime":1505524164000,"videoNum":8,"adTrack":null,"follow":{"itemType":"author","itemId":1833,"followed":false},"shield":{"itemType":"author","itemId":1833,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true},"cover":{"feed":"http://img.kaiyanapp.com/d7ce5401b99ce38997e25048fc6bea65.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/d7ce5401b99ce38997e25048fc6bea65.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/f4fa4546715ab4705cc1d32b558b1402.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":null},"playUrl":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51356&editionType=default&source=qcloud","thumbPlayUrl":null,"duration":501,"webUrl":{"raw":"http://www.eyepetizer.net/detail.html?vid=51356","forWeibo":"http://wandou.im/3obrvl"},"releaseTime":1505524164000,"library":"DEFAULT","playInfo":[{"height":720,"width":1280,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51356&editionType=high&source=qcloud","size":72825145},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51356&editionType=high&source=ucloud","size":72825145}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51356&editionType=high&source=qcloud"}],"consumption":{"collectionCount":0,"shareCount":0,"replyCount":0},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[],"type":"NORMAL","titlePgc":"精致蛋糕制作过程集锦","descriptionPgc":"让人观看时很舒服。","remark":null,"idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1505524164000,"promotion":null,"label":null,"labelList":[],"descriptionEditor":"","collected":false,"played":false,"subtitles":[],"lastViewTime":null,"playlists":null},"tag":null},{"type":"video","data":{"dataType":"VideoBeanForClient","id":51355,"title":"小小茶杯猫从纸巾盒里钻出来","slogan":null,"description":"她在里面睡了几个小时。","provider":{"name":"定制来源","alias":"CustomSrc","icon":""},"category":"萌宠","author":{"id":1777,"icon":"http://img.kaiyanapp.com/978286027e6e83653b4e1c6a2b810b7f.jpeg?imageMogr2/quality/60/format/jpg","name":"sweetfurx4","description":"异国短毛猫和波斯猫的激萌日常，吸猫福利！","link":"","latestReleaseTime":1505524156000,"videoNum":12,"adTrack":null,"follow":{"itemType":"author","itemId":1777,"followed":false},"shield":{"itemType":"author","itemId":1777,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true},"cover":{"feed":"http://img.kaiyanapp.com/813d4bd6d637762a5dc61f65dbbbed3a.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/813d4bd6d637762a5dc61f65dbbbed3a.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/3362eb6a2b50d7ce711f333ea0670336.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":null},"playUrl":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51355&editionType=default&source=qcloud","thumbPlayUrl":null,"duration":33,"webUrl":{"raw":"http://www.eyepetizer.net/detail.html?vid=51355","forWeibo":"http://wandou.im/3obrvj"},"releaseTime":1505524156000,"library":"DEFAULT","playInfo":[{"height":720,"width":1280,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51355&editionType=high&source=qcloud","size":6800749},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51355&editionType=high&source=ucloud","size":6800749}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51355&editionType=high&source=qcloud"}],"consumption":{"collectionCount":2,"shareCount":0,"replyCount":0},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[],"type":"NORMAL","titlePgc":"小小茶杯猫从纸巾盒里钻出来","descriptionPgc":"她在里面睡了几个小时。","remark":null,"idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1505524156000,"promotion":null,"label":null,"labelList":[],"descriptionEditor":"","collected":false,"played":false,"subtitles":[],"lastViewTime":null,"playlists":null},"tag":null},{"type":"video","data":{"dataType":"VideoBeanForClient","id":51354,"title":"法式焦糖布蕾，入口即化","slogan":null,"description":"细腻诱人。","provider":{"name":"定制来源","alias":"CustomSrc","icon":""},"category":"开胃","author":{"id":1769,"icon":"http://img.kaiyanapp.com/876f72c94bcd612a0f1cef43f99f253c.jpeg?imageMogr2/quality/60/format/jpg","name":"汉斯的甜品店","description":"我是一个爱做甜品的韩国小哥，欢迎大家多多关注！","link":"","latestReleaseTime":1505524155000,"videoNum":4,"adTrack":null,"follow":{"itemType":"author","itemId":1769,"followed":false},"shield":{"itemType":"author","itemId":1769,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true},"cover":{"feed":"http://img.kaiyanapp.com/818cabe49826c0b938afa59f13df71f8.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/818cabe49826c0b938afa59f13df71f8.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/2352901e6e47036037678de257da6a78.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":null},"playUrl":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51354&editionType=default&source=qcloud","thumbPlayUrl":null,"duration":179,"webUrl":{"raw":"http://www.eyepetizer.net/detail.html?vid=51354","forWeibo":"http://wandou.im/3obrvg"},"releaseTime":1505524155000,"library":"NOT_RECOMMEND","playInfo":[{"height":720,"width":1280,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51354&editionType=high&source=qcloud","size":32715101},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51354&editionType=high&source=ucloud","size":32715101}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51354&editionType=high&source=qcloud"}],"consumption":{"collectionCount":0,"shareCount":0,"replyCount":0},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[],"type":"NORMAL","titlePgc":"法式焦糖布蕾，入口即化","descriptionPgc":"细腻诱人。","remark":null,"idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1505524155000,"promotion":null,"label":null,"labelList":[],"descriptionEditor":"","collected":false,"played":false,"subtitles":[],"lastViewTime":null,"playlists":null},"tag":null}],"consumption":{"collectionCount":9,"shareCount":2,"replyCount":2},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[{"id":168,"name":"讽刺","actionUrl":"eyepetizer://tag/168/?title=%E8%AE%BD%E5%88%BA","adTrack":null},{"id":140,"name":"搞笑","actionUrl":"eyepetizer://tag/140/?title=%E6%90%9E%E7%AC%91","adTrack":null},{"id":666,"name":"生活","actionUrl":"eyepetizer://tag/666/?title=%E7%94%9F%E6%B4%BB","adTrack":null},{"id":120,"name":"黑色幽默","actionUrl":"eyepetizer://tag/120/?title=%E9%BB%91%E8%89%B2%E5%B9%BD%E9%BB%98","adTrack":null}],"type":"NORMAL","titlePgc":"「激情约会」第七集：夸张修容的妆","descriptionPgc":"女为悦己者容，但是要学会适可而止。","remark":null,"idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1505523600000,"promotion":null,"label":null,"labelList":[],"descriptionEditor":"很多直男不喜欢女票浓妆艳抹，不过相机吃妆，为了社交网络上永恒的点赞，怎样都是值得的呀~","collected":false,"played":false,"subtitles":[],"lastViewTime":null,"playlists":null,"header":{"id":0,"title":"时尚靠边站，Tubular破格登场","font":null,"cover":null,"label":null,"actionUrl":null,"labelList":null,"icon":"http://img.kaiyanapp.com/7055f401de872ca6b0ff81f121e2e354.jpeg?imageMogr2/quality/60/format/jpg","description":"Tubular破格登场，打破时尚禁锢"}}
         * tag : 0
         */

        private String type;
        private DataBeanX data;
        private String tag;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public DataBeanX getData() {
            return data;
        }

        public void setData(DataBeanX data) {
            this.data = data;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public static class DataBeanX {
            /**
             * dataType : VideoBeanForClient
             * id : 19373
             * title : 「激情约会」之夸张修容妆
             * text : - Sep. 15, Brunch -
             * slogan : 女为悦己者容，但如今能让人开心的事有点变化……
             * description : 很多直男不喜欢女票浓妆艳抹，不过相机吃妆，为了社交网络上永恒的点赞，怎样都是值得的呀~
             * image : http://img.kaiyanapp.com/30e25b9eaedd7dde55402042e8e78dbd.jpeg?imageMogr2/quality/60/format/jpg
             * actionUrl : eyepetizer://webview/?title=%E6%97%B6%E5%B0%9A%E9%9D%A0%E8%BE%B9%E7%AB%99%EF%BC%8CTubular%E7%A0%B4%E6%A0%BC%E7%99%
             * provider : {"name":"定制来源","alias":"CustomSrc","icon":""}
             * category : 搞笑
             * author : {"id":720,"icon":"http://img.kaiyanapp.com/f1f1bd0ddd17b2163b63037c87b92c54.jpeg?imageMogr2/quality/60/format/jpg","name":"CollegeHumor","description":"原创搞笑视频 CollegeHumor.com 博主。","link":"","latestReleaseTime":1505523601000,"videoNum":94,"adTrack":null,"follow":{"itemType":"author","itemId":720,"followed":false},"shield":{"itemType":"author","itemId":720,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true}
             * cover : {"feed":"http://img.kaiyanapp.com/7da3c44a340af1ab111e77cc9fc347c1.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/7da3c44a340af1ab111e77cc9fc347c1.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/d6fd4fd28e16b99ce85b636e9d8a011d.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":"http://img.kaiyanapp.com/7da3c44a340af1ab111e77cc9fc347c1.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim"}
             * playUrl : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19373&editionType=default&source=qcloud
             * thumbPlayUrl : null
             * duration : 132
             * webUrl : {"raw":null,"forWeibo":"http://wandou.im/3mugp4"}
             * releaseTime : 1505523601000
             * library : DAILY
             * playInfo : [{"height":480,"width":854,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19373&editionType=normal&source=qcloud","size":7952052},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19373&editionType=normal&source=ucloud","size":7952052}],"name":"标清","type":"normal","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19373&editionType=normal&source=qcloud"},{"height":720,"width":1280,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19373&editionType=high&source=qcloud","size":14634285},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19373&editionType=high&source=ucloud","size":14634285}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19373&editionType=high&source=qcloud"}]
             * itemList : [{"type":"video","data":{"dataType":"VideoBeanForClient","id":50689,"title":"爱之愈深 悟之愈彻｜这围棋下的太爽了","slogan":null,"description":"对于下棋人来说，围棋是一生的朋友。                                       \u2014\u2014吴清源","provider":{"name":"PGC","alias":"PGC","icon":""},"category":"记录","author":{"id":1374,"icon":"http://img.kaiyanapp.com/074012d51e266f6d9fa76632a599a678.png?imageMogr2/quality/60/format/jpg","name":"老虎映像","description":"用影像展现那些众所周知和从未发觉的事物","link":"","latestReleaseTime":1505527202000,"videoNum":20,"adTrack":null,"follow":{"itemType":"author","itemId":1374,"followed":false},"shield":{"itemType":"author","itemId":1374,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true},"cover":{"feed":"http://img.kaiyanapp.com/e189f9fa8f2171e8159a95e38673265e.png?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/e189f9fa8f2171e8159a95e38673265e.png?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/8e25d8ea794bc1640f79c59b0771feab.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":null},"playUrl":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=default&source=qcloud","thumbPlayUrl":null,"duration":120,"webUrl":{"raw":"http://www.eyepetizer.net/detail.html?vid=50689","forWeibo":"http://wandou.im/3obg0y"},"releaseTime":1505527202000,"library":"NOT_RECOMMEND","playInfo":[{"height":480,"width":854,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=normal&source=qcloud","size":8686859},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=normal&source=ucloud","size":8686859}],"name":"标清","type":"normal","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=normal&source=qcloud"},{"height":720,"width":1280,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=high&source=qcloud","size":13392908},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=high&source=ucloud","size":13392908}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=high&source=qcloud"}],"consumption":{"collectionCount":0,"shareCount":0,"replyCount":0},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[],"type":"NORMAL","titlePgc":"爱之愈深 悟之愈彻｜这围棋下的太爽了","descriptionPgc":"对于下棋人来说，围棋是一生的朋友。                                       \u2014\u2014吴清源","remark":"围棋  老虎映像","idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1505527202000,"promotion":null,"label":null,"labelList":[],"descriptionEditor":"","collected":false,"played":false,"subtitles":[],"lastViewTime":null,"playlists":null},"tag":null},{"type":"video","data":{"dataType":"VideoBeanForClient","id":51357,"title":"爱尔兰人品尝美式小吃","slogan":null,"description":"你想试试么？","provider":{"name":"定制来源","alias":"CustomSrc","icon":""},"category":"开胃","author":{"id":1837,"icon":"http://img.kaiyanapp.com/5cea103724b47a43e1eae0f22e3f4dae.jpeg?imageMogr2/quality/60/format/jpg","name":"Facts.","description":"分享关于爱尔兰人们尝试没吃过的食物，或没看过的电视节目等等有趣的视频。","link":"","latestReleaseTime":1505524165000,"videoNum":1,"adTrack":null,"follow":{"itemType":"author","itemId":1837,"followed":false},"shield":{"itemType":"author","itemId":1837,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true},"cover":{"feed":"http://img.kaiyanapp.com/c18dfd64074642c2a21a8b2255726f38.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/c18dfd64074642c2a21a8b2255726f38.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/76e6dc14fcc5381d1bf26f8b17677d6a.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":null},"playUrl":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51357&editionType=default&source=qcloud","thumbPlayUrl":null,"duration":195,"webUrl":{"raw":"http://www.eyepetizer.net/detail.html?vid=51357","forWeibo":"http://wandou.im/3obrvm"},"releaseTime":1505524165000,"library":"DEFAULT","playInfo":[{"height":720,"width":1280,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51357&editionType=high&source=qcloud","size":25442181},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51357&editionType=high&source=ucloud","size":25442181}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51357&editionType=high&source=qcloud"}],"consumption":{"collectionCount":2,"shareCount":0,"replyCount":0},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[],"type":"NORMAL","titlePgc":"爱尔兰人品尝美式小吃","descriptionPgc":"你想试试么？","remark":null,"idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1505524165000,"promotion":null,"label":null,"labelList":[],"descriptionEditor":"","collected":false,"played":false,"subtitles":[],"lastViewTime":null,"playlists":null},"tag":null},{"type":"video","data":{"dataType":"VideoBeanForClient","id":51356,"title":"精致蛋糕制作过程集锦","slogan":null,"description":"让人观看时很舒服。","provider":{"name":"定制来源","alias":"CustomSrc","icon":""},"category":"开胃","author":{"id":1833,"icon":"http://img.kaiyanapp.com/c93e92b79c1f909a6bcf5d70f96aec76.jpeg?imageMogr2/quality/60/format/jpg","name":"The Icing Artist","description":"分享精致又迷人的蛋糕创意。","link":"","latestReleaseTime":1505524164000,"videoNum":8,"adTrack":null,"follow":{"itemType":"author","itemId":1833,"followed":false},"shield":{"itemType":"author","itemId":1833,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true},"cover":{"feed":"http://img.kaiyanapp.com/d7ce5401b99ce38997e25048fc6bea65.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/d7ce5401b99ce38997e25048fc6bea65.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/f4fa4546715ab4705cc1d32b558b1402.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":null},"playUrl":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51356&editionType=default&source=qcloud","thumbPlayUrl":null,"duration":501,"webUrl":{"raw":"http://www.eyepetizer.net/detail.html?vid=51356","forWeibo":"http://wandou.im/3obrvl"},"releaseTime":1505524164000,"library":"DEFAULT","playInfo":[{"height":720,"width":1280,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51356&editionType=high&source=qcloud","size":72825145},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51356&editionType=high&source=ucloud","size":72825145}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51356&editionType=high&source=qcloud"}],"consumption":{"collectionCount":0,"shareCount":0,"replyCount":0},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[],"type":"NORMAL","titlePgc":"精致蛋糕制作过程集锦","descriptionPgc":"让人观看时很舒服。","remark":null,"idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1505524164000,"promotion":null,"label":null,"labelList":[],"descriptionEditor":"","collected":false,"played":false,"subtitles":[],"lastViewTime":null,"playlists":null},"tag":null},{"type":"video","data":{"dataType":"VideoBeanForClient","id":51355,"title":"小小茶杯猫从纸巾盒里钻出来","slogan":null,"description":"她在里面睡了几个小时。","provider":{"name":"定制来源","alias":"CustomSrc","icon":""},"category":"萌宠","author":{"id":1777,"icon":"http://img.kaiyanapp.com/978286027e6e83653b4e1c6a2b810b7f.jpeg?imageMogr2/quality/60/format/jpg","name":"sweetfurx4","description":"异国短毛猫和波斯猫的激萌日常，吸猫福利！","link":"","latestReleaseTime":1505524156000,"videoNum":12,"adTrack":null,"follow":{"itemType":"author","itemId":1777,"followed":false},"shield":{"itemType":"author","itemId":1777,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true},"cover":{"feed":"http://img.kaiyanapp.com/813d4bd6d637762a5dc61f65dbbbed3a.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/813d4bd6d637762a5dc61f65dbbbed3a.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/3362eb6a2b50d7ce711f333ea0670336.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":null},"playUrl":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51355&editionType=default&source=qcloud","thumbPlayUrl":null,"duration":33,"webUrl":{"raw":"http://www.eyepetizer.net/detail.html?vid=51355","forWeibo":"http://wandou.im/3obrvj"},"releaseTime":1505524156000,"library":"DEFAULT","playInfo":[{"height":720,"width":1280,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51355&editionType=high&source=qcloud","size":6800749},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51355&editionType=high&source=ucloud","size":6800749}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51355&editionType=high&source=qcloud"}],"consumption":{"collectionCount":2,"shareCount":0,"replyCount":0},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[],"type":"NORMAL","titlePgc":"小小茶杯猫从纸巾盒里钻出来","descriptionPgc":"她在里面睡了几个小时。","remark":null,"idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1505524156000,"promotion":null,"label":null,"labelList":[],"descriptionEditor":"","collected":false,"played":false,"subtitles":[],"lastViewTime":null,"playlists":null},"tag":null},{"type":"video","data":{"dataType":"VideoBeanForClient","id":51354,"title":"法式焦糖布蕾，入口即化","slogan":null,"description":"细腻诱人。","provider":{"name":"定制来源","alias":"CustomSrc","icon":""},"category":"开胃","author":{"id":1769,"icon":"http://img.kaiyanapp.com/876f72c94bcd612a0f1cef43f99f253c.jpeg?imageMogr2/quality/60/format/jpg","name":"汉斯的甜品店","description":"我是一个爱做甜品的韩国小哥，欢迎大家多多关注！","link":"","latestReleaseTime":1505524155000,"videoNum":4,"adTrack":null,"follow":{"itemType":"author","itemId":1769,"followed":false},"shield":{"itemType":"author","itemId":1769,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true},"cover":{"feed":"http://img.kaiyanapp.com/818cabe49826c0b938afa59f13df71f8.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/818cabe49826c0b938afa59f13df71f8.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/2352901e6e47036037678de257da6a78.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":null},"playUrl":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51354&editionType=default&source=qcloud","thumbPlayUrl":null,"duration":179,"webUrl":{"raw":"http://www.eyepetizer.net/detail.html?vid=51354","forWeibo":"http://wandou.im/3obrvg"},"releaseTime":1505524155000,"library":"NOT_RECOMMEND","playInfo":[{"height":720,"width":1280,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51354&editionType=high&source=qcloud","size":32715101},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51354&editionType=high&source=ucloud","size":32715101}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=51354&editionType=high&source=qcloud"}],"consumption":{"collectionCount":0,"shareCount":0,"replyCount":0},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[],"type":"NORMAL","titlePgc":"法式焦糖布蕾，入口即化","descriptionPgc":"细腻诱人。","remark":null,"idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1505524155000,"promotion":null,"label":null,"labelList":[],"descriptionEditor":"","collected":false,"played":false,"subtitles":[],"lastViewTime":null,"playlists":null},"tag":null}]
             * consumption : {"collectionCount":9,"shareCount":2,"replyCount":2}
             * campaign : null
             * waterMarks : null
             * adTrack : null
             * tags : [{"id":168,"name":"讽刺","actionUrl":"eyepetizer://tag/168/?title=%E8%AE%BD%E5%88%BA","adTrack":null},{"id":140,"name":"搞笑","actionUrl":"eyepetizer://tag/140/?title=%E6%90%9E%E7%AC%91","adTrack":null},{"id":666,"name":"生活","actionUrl":"eyepetizer://tag/666/?title=%E7%94%9F%E6%B4%BB","adTrack":null},{"id":120,"name":"黑色幽默","actionUrl":"eyepetizer://tag/120/?title=%E9%BB%91%E8%89%B2%E5%B9%BD%E9%BB%98","adTrack":null}]
             * type : NORMAL
             * titlePgc : 「激情约会」第七集：夸张修容的妆
             * descriptionPgc : 女为悦己者容，但是要学会适可而止。
             * remark : null
             * idx : 0
             * shareAdTrack : null
             * favoriteAdTrack : null
             * webAdTrack : null
             * date : 1505523600000
             * promotion : null
             * label : null
             * labelList : []
             * descriptionEditor : 很多直男不喜欢女票浓妆艳抹，不过相机吃妆，为了社交网络上永恒的点赞，怎样都是值得的呀~
             * collected : false
             * played : false
             * subtitles : []
             * lastViewTime : null
             * playlists : null
             * header : {"id":0,"title":"时尚靠边站，Tubular破格登场","font":null,"cover":null,"label":null,"actionUrl":null,"labelList":null,"icon":"http://img.kaiyanapp.com/7055f401de872ca6b0ff81f121e2e354.jpeg?imageMogr2/quality/60/format/jpg","description":"Tubular破格登场，打破时尚禁锢"}
             */

            private String dataType;
            private int id;
            private String title;
            private String text;
            private String slogan;
            private String description;
            private String image;
            private String actionUrl;
            private ProviderBean provider;
            private String category;
            private AuthorBean author;
            private CoverBean cover;
            private String playUrl;
            private Object thumbPlayUrl;
            private int duration;
            private WebUrlBean webUrl;
            private long releaseTime;
            private String library;
            private ConsumptionBean consumption;
            private Object campaign;
            private Object waterMarks;
            private Object adTrack;
            private String type;
            private String titlePgc;
            private String descriptionPgc;
            private Object remark;
            private int idx;
            private Object shareAdTrack;
            private Object favoriteAdTrack;
            private Object webAdTrack;
            private long date;
            private Object promotion;
            private Object label;
            private String descriptionEditor;
            private boolean collected;
            private boolean played;
            private Object lastViewTime;
            private Object playlists;
            private HeaderBean header;
            private List<PlayInfoBean> playInfo;
            private List<ItemListBean> itemList;
            private List<TagsBean> tags;
            private List<?> labelList;
            private List<?> subtitles;

            public String getDataType() {
                return dataType;
            }

            public void setDataType(String dataType) {
                this.dataType = dataType;
            }

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

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getSlogan() {
                return slogan;
            }

            public void setSlogan(String slogan) {
                this.slogan = slogan;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getActionUrl() {
                return actionUrl;
            }

            public void setActionUrl(String actionUrl) {
                this.actionUrl = actionUrl;
            }

            public ProviderBean getProvider() {
                return provider;
            }

            public void setProvider(ProviderBean provider) {
                this.provider = provider;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public AuthorBean getAuthor() {
                return author;
            }

            public void setAuthor(AuthorBean author) {
                this.author = author;
            }

            public CoverBean getCover() {
                return cover;
            }

            public void setCover(CoverBean cover) {
                this.cover = cover;
            }

            public String getPlayUrl() {
                return playUrl;
            }

            public void setPlayUrl(String playUrl) {
                this.playUrl = playUrl;
            }

            public Object getThumbPlayUrl() {
                return thumbPlayUrl;
            }

            public void setThumbPlayUrl(Object thumbPlayUrl) {
                this.thumbPlayUrl = thumbPlayUrl;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public WebUrlBean getWebUrl() {
                return webUrl;
            }

            public void setWebUrl(WebUrlBean webUrl) {
                this.webUrl = webUrl;
            }

            public long getReleaseTime() {
                return releaseTime;
            }

            public void setReleaseTime(long releaseTime) {
                this.releaseTime = releaseTime;
            }

            public String getLibrary() {
                return library;
            }

            public void setLibrary(String library) {
                this.library = library;
            }

            public ConsumptionBean getConsumption() {
                return consumption;
            }

            public void setConsumption(ConsumptionBean consumption) {
                this.consumption = consumption;
            }

            public Object getCampaign() {
                return campaign;
            }

            public void setCampaign(Object campaign) {
                this.campaign = campaign;
            }

            public Object getWaterMarks() {
                return waterMarks;
            }

            public void setWaterMarks(Object waterMarks) {
                this.waterMarks = waterMarks;
            }

            public Object getAdTrack() {
                return adTrack;
            }

            public void setAdTrack(Object adTrack) {
                this.adTrack = adTrack;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTitlePgc() {
                return titlePgc;
            }

            public void setTitlePgc(String titlePgc) {
                this.titlePgc = titlePgc;
            }

            public String getDescriptionPgc() {
                return descriptionPgc;
            }

            public void setDescriptionPgc(String descriptionPgc) {
                this.descriptionPgc = descriptionPgc;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public int getIdx() {
                return idx;
            }

            public void setIdx(int idx) {
                this.idx = idx;
            }

            public Object getShareAdTrack() {
                return shareAdTrack;
            }

            public void setShareAdTrack(Object shareAdTrack) {
                this.shareAdTrack = shareAdTrack;
            }

            public Object getFavoriteAdTrack() {
                return favoriteAdTrack;
            }

            public void setFavoriteAdTrack(Object favoriteAdTrack) {
                this.favoriteAdTrack = favoriteAdTrack;
            }

            public Object getWebAdTrack() {
                return webAdTrack;
            }

            public void setWebAdTrack(Object webAdTrack) {
                this.webAdTrack = webAdTrack;
            }

            public long getDate() {
                return date;
            }

            public void setDate(long date) {
                this.date = date;
            }

            public Object getPromotion() {
                return promotion;
            }

            public void setPromotion(Object promotion) {
                this.promotion = promotion;
            }

            public Object getLabel() {
                return label;
            }

            public void setLabel(Object label) {
                this.label = label;
            }

            public String getDescriptionEditor() {
                return descriptionEditor;
            }

            public void setDescriptionEditor(String descriptionEditor) {
                this.descriptionEditor = descriptionEditor;
            }

            public boolean isCollected() {
                return collected;
            }

            public void setCollected(boolean collected) {
                this.collected = collected;
            }

            public boolean isPlayed() {
                return played;
            }

            public void setPlayed(boolean played) {
                this.played = played;
            }

            public Object getLastViewTime() {
                return lastViewTime;
            }

            public void setLastViewTime(Object lastViewTime) {
                this.lastViewTime = lastViewTime;
            }

            public Object getPlaylists() {
                return playlists;
            }

            public void setPlaylists(Object playlists) {
                this.playlists = playlists;
            }

            public HeaderBean getHeader() {
                return header;
            }

            public void setHeader(HeaderBean header) {
                this.header = header;
            }

            public List<PlayInfoBean> getPlayInfo() {
                return playInfo;
            }

            public void setPlayInfo(List<PlayInfoBean> playInfo) {
                this.playInfo = playInfo;
            }

            public List<ItemListBean> getItemList() {
                return itemList;
            }

            public void setItemList(List<ItemListBean> itemList) {
                this.itemList = itemList;
            }

            public List<TagsBean> getTags() {
                return tags;
            }

            public void setTags(List<TagsBean> tags) {
                this.tags = tags;
            }

            public List<?> getLabelList() {
                return labelList;
            }

            public void setLabelList(List<?> labelList) {
                this.labelList = labelList;
            }

            public List<?> getSubtitles() {
                return subtitles;
            }

            public void setSubtitles(List<?> subtitles) {
                this.subtitles = subtitles;
            }

            public static class ProviderBean {
                /**
                 * name : 定制来源
                 * alias : CustomSrc
                 * icon :
                 */

                private String name;
                private String alias;
                private String icon;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }
            }

            public static class AuthorBean {
                /**
                 * id : 720
                 * icon : http://img.kaiyanapp.com/f1f1bd0ddd17b2163b63037c87b92c54.jpeg?imageMogr2/quality/60/format/jpg
                 * name : CollegeHumor
                 * description : 原创搞笑视频 CollegeHumor.com 博主。
                 * link :
                 * latestReleaseTime : 1505523601000
                 * videoNum : 94
                 * adTrack : null
                 * follow : {"itemType":"author","itemId":720,"followed":false}
                 * shield : {"itemType":"author","itemId":720,"shielded":false}
                 * approvedNotReadyVideoCount : 0
                 * ifPgc : true
                 */

                private int id;
                private String icon;
                private String name;
                private String description;
                private String link;
                private long latestReleaseTime;
                private int videoNum;
                private Object adTrack;
                private FollowBean follow;
                private ShieldBean shield;
                private int approvedNotReadyVideoCount;
                private boolean ifPgc;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public long getLatestReleaseTime() {
                    return latestReleaseTime;
                }

                public void setLatestReleaseTime(long latestReleaseTime) {
                    this.latestReleaseTime = latestReleaseTime;
                }

                public int getVideoNum() {
                    return videoNum;
                }

                public void setVideoNum(int videoNum) {
                    this.videoNum = videoNum;
                }

                public Object getAdTrack() {
                    return adTrack;
                }

                public void setAdTrack(Object adTrack) {
                    this.adTrack = adTrack;
                }

                public FollowBean getFollow() {
                    return follow;
                }

                public void setFollow(FollowBean follow) {
                    this.follow = follow;
                }

                public ShieldBean getShield() {
                    return shield;
                }

                public void setShield(ShieldBean shield) {
                    this.shield = shield;
                }

                public int getApprovedNotReadyVideoCount() {
                    return approvedNotReadyVideoCount;
                }

                public void setApprovedNotReadyVideoCount(int approvedNotReadyVideoCount) {
                    this.approvedNotReadyVideoCount = approvedNotReadyVideoCount;
                }

                public boolean isIfPgc() {
                    return ifPgc;
                }

                public void setIfPgc(boolean ifPgc) {
                    this.ifPgc = ifPgc;
                }

                public static class FollowBean {
                    /**
                     * itemType : author
                     * itemId : 720
                     * followed : false
                     */

                    private String itemType;
                    private int itemId;
                    private boolean followed;

                    public String getItemType() {
                        return itemType;
                    }

                    public void setItemType(String itemType) {
                        this.itemType = itemType;
                    }

                    public int getItemId() {
                        return itemId;
                    }

                    public void setItemId(int itemId) {
                        this.itemId = itemId;
                    }

                    public boolean isFollowed() {
                        return followed;
                    }

                    public void setFollowed(boolean followed) {
                        this.followed = followed;
                    }
                }

                public static class ShieldBean {
                    /**
                     * itemType : author
                     * itemId : 720
                     * shielded : false
                     */

                    private String itemType;
                    private int itemId;
                    private boolean shielded;

                    public String getItemType() {
                        return itemType;
                    }

                    public void setItemType(String itemType) {
                        this.itemType = itemType;
                    }

                    public int getItemId() {
                        return itemId;
                    }

                    public void setItemId(int itemId) {
                        this.itemId = itemId;
                    }

                    public boolean isShielded() {
                        return shielded;
                    }

                    public void setShielded(boolean shielded) {
                        this.shielded = shielded;
                    }
                }
            }

            public static class CoverBean {
                /**
                 * feed : http://img.kaiyanapp.com/7da3c44a340af1ab111e77cc9fc347c1.jpeg?imageMogr2/quality/60/format/jpg
                 * detail : http://img.kaiyanapp.com/7da3c44a340af1ab111e77cc9fc347c1.jpeg?imageMogr2/quality/60/format/jpg
                 * blurred : http://img.kaiyanapp.com/d6fd4fd28e16b99ce85b636e9d8a011d.jpeg?imageMogr2/quality/60/format/jpg
                 * sharing : null
                 * homepage : http://img.kaiyanapp.com/7da3c44a340af1ab111e77cc9fc347c1.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
                 */

                private String feed;
                private String detail;
                private String blurred;
                private Object sharing;
                private String homepage;

                public String getFeed() {
                    return feed;
                }

                public void setFeed(String feed) {
                    this.feed = feed;
                }

                public String getDetail() {
                    return detail;
                }

                public void setDetail(String detail) {
                    this.detail = detail;
                }

                public String getBlurred() {
                    return blurred;
                }

                public void setBlurred(String blurred) {
                    this.blurred = blurred;
                }

                public Object getSharing() {
                    return sharing;
                }

                public void setSharing(Object sharing) {
                    this.sharing = sharing;
                }

                public String getHomepage() {
                    return homepage;
                }

                public void setHomepage(String homepage) {
                    this.homepage = homepage;
                }
            }

            public static class WebUrlBean {
                /**
                 * raw : null
                 * forWeibo : http://wandou.im/3mugp4
                 */

                private Object raw;
                private String forWeibo;

                public Object getRaw() {
                    return raw;
                }

                public void setRaw(Object raw) {
                    this.raw = raw;
                }

                public String getForWeibo() {
                    return forWeibo;
                }

                public void setForWeibo(String forWeibo) {
                    this.forWeibo = forWeibo;
                }
            }

            public static class ConsumptionBean {
                /**
                 * collectionCount : 9
                 * shareCount : 2
                 * replyCount : 2
                 */

                private int collectionCount;
                private int shareCount;
                private int replyCount;

                public int getCollectionCount() {
                    return collectionCount;
                }

                public void setCollectionCount(int collectionCount) {
                    this.collectionCount = collectionCount;
                }

                public int getShareCount() {
                    return shareCount;
                }

                public void setShareCount(int shareCount) {
                    this.shareCount = shareCount;
                }

                public int getReplyCount() {
                    return replyCount;
                }

                public void setReplyCount(int replyCount) {
                    this.replyCount = replyCount;
                }
            }

            public static class HeaderBean {
                /**
                 * id : 0
                 * title : 时尚靠边站，Tubular破格登场
                 * font : null
                 * cover : null
                 * label : null
                 * actionUrl : null
                 * labelList : null
                 * icon : http://img.kaiyanapp.com/7055f401de872ca6b0ff81f121e2e354.jpeg?imageMogr2/quality/60/format/jpg
                 * description : Tubular破格登场，打破时尚禁锢
                 */

                private int id;
                private String title;
                private String font;
                private String cover;
                private Object label;
                private String actionUrl;
                private Object labelList;
                private String icon;
                private String description;

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

                public Object getFont() {
                    return font;
                }

                public void setFont(String font) {
                    this.font = font;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public Object getLabel() {
                    return label;
                }

                public void setLabel(Object label) {
                    this.label = label;
                }

                public String getActionUrl() {
                    return actionUrl;
                }

                public void setActionUrl(String actionUrl) {
                    this.actionUrl = actionUrl;
                }

                public Object getLabelList() {
                    return labelList;
                }

                public void setLabelList(Object labelList) {
                    this.labelList = labelList;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }
            }

            public static class PlayInfoBean {
                /**
                 * height : 480
                 * width : 854
                 * urlList : [{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19373&editionType=normal&source=qcloud","size":7952052},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19373&editionType=normal&source=ucloud","size":7952052}]
                 * name : 标清
                 * type : normal
                 * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19373&editionType=normal&source=qcloud
                 */

                private int height;
                private int width;
                private String name;
                private String type;
                private String url;
                private List<UrlListBean> urlList;

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public List<UrlListBean> getUrlList() {
                    return urlList;
                }

                public void setUrlList(List<UrlListBean> urlList) {
                    this.urlList = urlList;
                }

                public static class UrlListBean {
                    /**
                     * name : qcloud
                     * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=19373&editionType=normal&source=qcloud
                     * size : 7952052
                     */

                    private String name;
                    private String url;
                    private int size;

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

                    public int getSize() {
                        return size;
                    }

                    public void setSize(int size) {
                        this.size = size;
                    }
                }
            }

            public static class ItemListBean {
                /**
                 * type : video
                 * data : {"dataType":"VideoBeanForClient","id":50689,"title":"爱之愈深 悟之愈彻｜这围棋下的太爽了","slogan":null,"description":"对于下棋人来说，围棋是一生的朋友。                                       \u2014\u2014吴清源","provider":{"name":"PGC","alias":"PGC","icon":""},"category":"记录","author":{"id":1374,"icon":"http://img.kaiyanapp.com/074012d51e266f6d9fa76632a599a678.png?imageMogr2/quality/60/format/jpg","name":"老虎映像","description":"用影像展现那些众所周知和从未发觉的事物","link":"","latestReleaseTime":1505527202000,"videoNum":20,"adTrack":null,"follow":{"itemType":"author","itemId":1374,"followed":false},"shield":{"itemType":"author","itemId":1374,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true},"cover":{"feed":"http://img.kaiyanapp.com/e189f9fa8f2171e8159a95e38673265e.png?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/e189f9fa8f2171e8159a95e38673265e.png?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/8e25d8ea794bc1640f79c59b0771feab.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":null},"playUrl":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=default&source=qcloud","thumbPlayUrl":null,"duration":120,"webUrl":{"raw":"http://www.eyepetizer.net/detail.html?vid=50689","forWeibo":"http://wandou.im/3obg0y"},"releaseTime":1505527202000,"library":"NOT_RECOMMEND","playInfo":[{"height":480,"width":854,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=normal&source=qcloud","size":8686859},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=normal&source=ucloud","size":8686859}],"name":"标清","type":"normal","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=normal&source=qcloud"},{"height":720,"width":1280,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=high&source=qcloud","size":13392908},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=high&source=ucloud","size":13392908}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=high&source=qcloud"}],"consumption":{"collectionCount":0,"shareCount":0,"replyCount":0},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[],"type":"NORMAL","titlePgc":"爱之愈深 悟之愈彻｜这围棋下的太爽了","descriptionPgc":"对于下棋人来说，围棋是一生的朋友。                                       \u2014\u2014吴清源","remark":"围棋  老虎映像","idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1505527202000,"promotion":null,"label":null,"labelList":[],"descriptionEditor":"","collected":false,"played":false,"subtitles":[],"lastViewTime":null,"playlists":null}
                 * tag : null
                 */

                private String type;
                private DataBean data;
                private Object tag;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public DataBean getData() {
                    return data;
                }

                public void setData(DataBean data) {
                    this.data = data;
                }

                public Object getTag() {
                    return tag;
                }

                public void setTag(Object tag) {
                    this.tag = tag;
                }

                public static class DataBean {
                    /**
                     * dataType : VideoBeanForClient
                     * id : 50689
                     * title : 爱之愈深 悟之愈彻｜这围棋下的太爽了
                     * slogan : null
                     * description : 对于下棋人来说，围棋是一生的朋友。                                       ——吴清源
                     * provider : {"name":"PGC","alias":"PGC","icon":""}
                     * category : 记录
                     * author : {"id":1374,"icon":"http://img.kaiyanapp.com/074012d51e266f6d9fa76632a599a678.png?imageMogr2/quality/60/format/jpg","name":"老虎映像","description":"用影像展现那些众所周知和从未发觉的事物","link":"","latestReleaseTime":1505527202000,"videoNum":20,"adTrack":null,"follow":{"itemType":"author","itemId":1374,"followed":false},"shield":{"itemType":"author","itemId":1374,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true}
                     * cover : {"feed":"http://img.kaiyanapp.com/e189f9fa8f2171e8159a95e38673265e.png?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/e189f9fa8f2171e8159a95e38673265e.png?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/8e25d8ea794bc1640f79c59b0771feab.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":null}
                     * playUrl : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=default&source=qcloud
                     * thumbPlayUrl : null
                     * duration : 120
                     * webUrl : {"raw":"http://www.eyepetizer.net/detail.html?vid=50689","forWeibo":"http://wandou.im/3obg0y"}
                     * releaseTime : 1505527202000
                     * library : NOT_RECOMMEND
                     * playInfo : [{"height":480,"width":854,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=normal&source=qcloud","size":8686859},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=normal&source=ucloud","size":8686859}],"name":"标清","type":"normal","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=normal&source=qcloud"},{"height":720,"width":1280,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=high&source=qcloud","size":13392908},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=high&source=ucloud","size":13392908}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=high&source=qcloud"}]
                     * consumption : {"collectionCount":0,"shareCount":0,"replyCount":0}
                     * campaign : null
                     * waterMarks : null
                     * adTrack : null
                     * tags : []
                     * type : NORMAL
                     * titlePgc : 爱之愈深 悟之愈彻｜这围棋下的太爽了
                     * descriptionPgc : 对于下棋人来说，围棋是一生的朋友。                                       ——吴清源
                     * remark : 围棋  老虎映像
                     * idx : 0
                     * shareAdTrack : null
                     * favoriteAdTrack : null
                     * webAdTrack : null
                     * date : 1505527202000
                     * promotion : null
                     * label : null
                     * labelList : []
                     * descriptionEditor :
                     * collected : false
                     * played : false
                     * subtitles : []
                     * lastViewTime : null
                     * playlists : null
                     */

                    private String dataType;
                    private int id;
                    private String title;
                    private Object slogan;
                    private String description;
                    private ProviderBeanX provider;
                    private String category;
                    private AuthorBeanX author;
                    private CoverBeanX cover;
                    private String playUrl;
                    private Object thumbPlayUrl;
                    private int duration;
                    private WebUrlBeanX webUrl;
                    private long releaseTime;
                    private String library;
                    private ConsumptionBeanX consumption;
                    private Object campaign;
                    private Object waterMarks;
                    private Object adTrack;
                    private String type;
                    private String titlePgc;
                    private String descriptionPgc;
                    private String remark;
                    private int idx;
                    private Object shareAdTrack;
                    private Object favoriteAdTrack;
                    private Object webAdTrack;
                    private long date;
                    private Object promotion;
                    private Object label;
                    private String descriptionEditor;
                    private boolean collected;
                    private boolean played;
                    private Object lastViewTime;
                    private Object playlists;
                    private List<PlayInfoBeanX> playInfo;
                    private List<?> tags;
                    private List<?> labelList;
                    private List<?> subtitles;

                    public String getDataType() {
                        return dataType;
                    }

                    public void setDataType(String dataType) {
                        this.dataType = dataType;
                    }

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

                    public Object getSlogan() {
                        return slogan;
                    }

                    public void setSlogan(Object slogan) {
                        this.slogan = slogan;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public ProviderBeanX getProvider() {
                        return provider;
                    }

                    public void setProvider(ProviderBeanX provider) {
                        this.provider = provider;
                    }

                    public String getCategory() {
                        return category;
                    }

                    public void setCategory(String category) {
                        this.category = category;
                    }

                    public AuthorBeanX getAuthor() {
                        return author;
                    }

                    public void setAuthor(AuthorBeanX author) {
                        this.author = author;
                    }

                    public CoverBeanX getCover() {
                        return cover;
                    }

                    public void setCover(CoverBeanX cover) {
                        this.cover = cover;
                    }

                    public String getPlayUrl() {
                        return playUrl;
                    }

                    public void setPlayUrl(String playUrl) {
                        this.playUrl = playUrl;
                    }

                    public Object getThumbPlayUrl() {
                        return thumbPlayUrl;
                    }

                    public void setThumbPlayUrl(Object thumbPlayUrl) {
                        this.thumbPlayUrl = thumbPlayUrl;
                    }

                    public int getDuration() {
                        return duration;
                    }

                    public void setDuration(int duration) {
                        this.duration = duration;
                    }

                    public WebUrlBeanX getWebUrl() {
                        return webUrl;
                    }

                    public void setWebUrl(WebUrlBeanX webUrl) {
                        this.webUrl = webUrl;
                    }

                    public long getReleaseTime() {
                        return releaseTime;
                    }

                    public void setReleaseTime(long releaseTime) {
                        this.releaseTime = releaseTime;
                    }

                    public String getLibrary() {
                        return library;
                    }

                    public void setLibrary(String library) {
                        this.library = library;
                    }

                    public ConsumptionBeanX getConsumption() {
                        return consumption;
                    }

                    public void setConsumption(ConsumptionBeanX consumption) {
                        this.consumption = consumption;
                    }

                    public Object getCampaign() {
                        return campaign;
                    }

                    public void setCampaign(Object campaign) {
                        this.campaign = campaign;
                    }

                    public Object getWaterMarks() {
                        return waterMarks;
                    }

                    public void setWaterMarks(Object waterMarks) {
                        this.waterMarks = waterMarks;
                    }

                    public Object getAdTrack() {
                        return adTrack;
                    }

                    public void setAdTrack(Object adTrack) {
                        this.adTrack = adTrack;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public String getTitlePgc() {
                        return titlePgc;
                    }

                    public void setTitlePgc(String titlePgc) {
                        this.titlePgc = titlePgc;
                    }

                    public String getDescriptionPgc() {
                        return descriptionPgc;
                    }

                    public void setDescriptionPgc(String descriptionPgc) {
                        this.descriptionPgc = descriptionPgc;
                    }

                    public String getRemark() {
                        return remark;
                    }

                    public void setRemark(String remark) {
                        this.remark = remark;
                    }

                    public int getIdx() {
                        return idx;
                    }

                    public void setIdx(int idx) {
                        this.idx = idx;
                    }

                    public Object getShareAdTrack() {
                        return shareAdTrack;
                    }

                    public void setShareAdTrack(Object shareAdTrack) {
                        this.shareAdTrack = shareAdTrack;
                    }

                    public Object getFavoriteAdTrack() {
                        return favoriteAdTrack;
                    }

                    public void setFavoriteAdTrack(Object favoriteAdTrack) {
                        this.favoriteAdTrack = favoriteAdTrack;
                    }

                    public Object getWebAdTrack() {
                        return webAdTrack;
                    }

                    public void setWebAdTrack(Object webAdTrack) {
                        this.webAdTrack = webAdTrack;
                    }

                    public long getDate() {
                        return date;
                    }

                    public void setDate(long date) {
                        this.date = date;
                    }

                    public Object getPromotion() {
                        return promotion;
                    }

                    public void setPromotion(Object promotion) {
                        this.promotion = promotion;
                    }

                    public Object getLabel() {
                        return label;
                    }

                    public void setLabel(Object label) {
                        this.label = label;
                    }

                    public String getDescriptionEditor() {
                        return descriptionEditor;
                    }

                    public void setDescriptionEditor(String descriptionEditor) {
                        this.descriptionEditor = descriptionEditor;
                    }

                    public boolean isCollected() {
                        return collected;
                    }

                    public void setCollected(boolean collected) {
                        this.collected = collected;
                    }

                    public boolean isPlayed() {
                        return played;
                    }

                    public void setPlayed(boolean played) {
                        this.played = played;
                    }

                    public Object getLastViewTime() {
                        return lastViewTime;
                    }

                    public void setLastViewTime(Object lastViewTime) {
                        this.lastViewTime = lastViewTime;
                    }

                    public Object getPlaylists() {
                        return playlists;
                    }

                    public void setPlaylists(Object playlists) {
                        this.playlists = playlists;
                    }

                    public List<PlayInfoBeanX> getPlayInfo() {
                        return playInfo;
                    }

                    public void setPlayInfo(List<PlayInfoBeanX> playInfo) {
                        this.playInfo = playInfo;
                    }

                    public List<?> getTags() {
                        return tags;
                    }

                    public void setTags(List<?> tags) {
                        this.tags = tags;
                    }

                    public List<?> getLabelList() {
                        return labelList;
                    }

                    public void setLabelList(List<?> labelList) {
                        this.labelList = labelList;
                    }

                    public List<?> getSubtitles() {
                        return subtitles;
                    }

                    public void setSubtitles(List<?> subtitles) {
                        this.subtitles = subtitles;
                    }

                    public static class ProviderBeanX {
                        /**
                         * name : PGC
                         * alias : PGC
                         * icon :
                         */

                        private String name;
                        private String alias;
                        private String icon;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getAlias() {
                            return alias;
                        }

                        public void setAlias(String alias) {
                            this.alias = alias;
                        }

                        public String getIcon() {
                            return icon;
                        }

                        public void setIcon(String icon) {
                            this.icon = icon;
                        }
                    }

                    public static class AuthorBeanX {
                        /**
                         * id : 1374
                         * icon : http://img.kaiyanapp.com/074012d51e266f6d9fa76632a599a678.png?imageMogr2/quality/60/format/jpg
                         * name : 老虎映像
                         * description : 用影像展现那些众所周知和从未发觉的事物
                         * link :
                         * latestReleaseTime : 1505527202000
                         * videoNum : 20
                         * adTrack : null
                         * follow : {"itemType":"author","itemId":1374,"followed":false}
                         * shield : {"itemType":"author","itemId":1374,"shielded":false}
                         * approvedNotReadyVideoCount : 0
                         * ifPgc : true
                         */

                        private int id;
                        private String icon;
                        private String name;
                        private String description;
                        private String link;
                        private long latestReleaseTime;
                        private int videoNum;
                        private Object adTrack;
                        private FollowBeanX follow;
                        private ShieldBeanX shield;
                        private int approvedNotReadyVideoCount;
                        private boolean ifPgc;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getIcon() {
                            return icon;
                        }

                        public void setIcon(String icon) {
                            this.icon = icon;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getDescription() {
                            return description;
                        }

                        public void setDescription(String description) {
                            this.description = description;
                        }

                        public String getLink() {
                            return link;
                        }

                        public void setLink(String link) {
                            this.link = link;
                        }

                        public long getLatestReleaseTime() {
                            return latestReleaseTime;
                        }

                        public void setLatestReleaseTime(long latestReleaseTime) {
                            this.latestReleaseTime = latestReleaseTime;
                        }

                        public int getVideoNum() {
                            return videoNum;
                        }

                        public void setVideoNum(int videoNum) {
                            this.videoNum = videoNum;
                        }

                        public Object getAdTrack() {
                            return adTrack;
                        }

                        public void setAdTrack(Object adTrack) {
                            this.adTrack = adTrack;
                        }

                        public FollowBeanX getFollow() {
                            return follow;
                        }

                        public void setFollow(FollowBeanX follow) {
                            this.follow = follow;
                        }

                        public ShieldBeanX getShield() {
                            return shield;
                        }

                        public void setShield(ShieldBeanX shield) {
                            this.shield = shield;
                        }

                        public int getApprovedNotReadyVideoCount() {
                            return approvedNotReadyVideoCount;
                        }

                        public void setApprovedNotReadyVideoCount(int approvedNotReadyVideoCount) {
                            this.approvedNotReadyVideoCount = approvedNotReadyVideoCount;
                        }

                        public boolean isIfPgc() {
                            return ifPgc;
                        }

                        public void setIfPgc(boolean ifPgc) {
                            this.ifPgc = ifPgc;
                        }

                        public static class FollowBeanX {
                            /**
                             * itemType : author
                             * itemId : 1374
                             * followed : false
                             */

                            private String itemType;
                            private int itemId;
                            private boolean followed;

                            public String getItemType() {
                                return itemType;
                            }

                            public void setItemType(String itemType) {
                                this.itemType = itemType;
                            }

                            public int getItemId() {
                                return itemId;
                            }

                            public void setItemId(int itemId) {
                                this.itemId = itemId;
                            }

                            public boolean isFollowed() {
                                return followed;
                            }

                            public void setFollowed(boolean followed) {
                                this.followed = followed;
                            }
                        }

                        public static class ShieldBeanX {
                            /**
                             * itemType : author
                             * itemId : 1374
                             * shielded : false
                             */

                            private String itemType;
                            private int itemId;
                            private boolean shielded;

                            public String getItemType() {
                                return itemType;
                            }

                            public void setItemType(String itemType) {
                                this.itemType = itemType;
                            }

                            public int getItemId() {
                                return itemId;
                            }

                            public void setItemId(int itemId) {
                                this.itemId = itemId;
                            }

                            public boolean isShielded() {
                                return shielded;
                            }

                            public void setShielded(boolean shielded) {
                                this.shielded = shielded;
                            }
                        }
                    }

                    public static class CoverBeanX {
                        /**
                         * feed : http://img.kaiyanapp.com/e189f9fa8f2171e8159a95e38673265e.png?imageMogr2/quality/60/format/jpg
                         * detail : http://img.kaiyanapp.com/e189f9fa8f2171e8159a95e38673265e.png?imageMogr2/quality/60/format/jpg
                         * blurred : http://img.kaiyanapp.com/8e25d8ea794bc1640f79c59b0771feab.jpeg?imageMogr2/quality/60/format/jpg
                         * sharing : null
                         * homepage : null
                         */

                        private String feed;
                        private String detail;
                        private String blurred;
                        private Object sharing;
                        private Object homepage;

                        public String getFeed() {
                            return feed;
                        }

                        public void setFeed(String feed) {
                            this.feed = feed;
                        }

                        public String getDetail() {
                            return detail;
                        }

                        public void setDetail(String detail) {
                            this.detail = detail;
                        }

                        public String getBlurred() {
                            return blurred;
                        }

                        public void setBlurred(String blurred) {
                            this.blurred = blurred;
                        }

                        public Object getSharing() {
                            return sharing;
                        }

                        public void setSharing(Object sharing) {
                            this.sharing = sharing;
                        }

                        public Object getHomepage() {
                            return homepage;
                        }

                        public void setHomepage(Object homepage) {
                            this.homepage = homepage;
                        }
                    }

                    public static class WebUrlBeanX {
                        /**
                         * raw : http://www.eyepetizer.net/detail.html?vid=50689
                         * forWeibo : http://wandou.im/3obg0y
                         */

                        private String raw;
                        private String forWeibo;

                        public String getRaw() {
                            return raw;
                        }

                        public void setRaw(String raw) {
                            this.raw = raw;
                        }

                        public String getForWeibo() {
                            return forWeibo;
                        }

                        public void setForWeibo(String forWeibo) {
                            this.forWeibo = forWeibo;
                        }
                    }

                    public static class ConsumptionBeanX {
                        /**
                         * collectionCount : 0
                         * shareCount : 0
                         * replyCount : 0
                         */

                        private int collectionCount;
                        private int shareCount;
                        private int replyCount;

                        public int getCollectionCount() {
                            return collectionCount;
                        }

                        public void setCollectionCount(int collectionCount) {
                            this.collectionCount = collectionCount;
                        }

                        public int getShareCount() {
                            return shareCount;
                        }

                        public void setShareCount(int shareCount) {
                            this.shareCount = shareCount;
                        }

                        public int getReplyCount() {
                            return replyCount;
                        }

                        public void setReplyCount(int replyCount) {
                            this.replyCount = replyCount;
                        }
                    }

                    public static class PlayInfoBeanX {
                        /**
                         * height : 480
                         * width : 854
                         * urlList : [{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=normal&source=qcloud","size":8686859},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=normal&source=ucloud","size":8686859}]
                         * name : 标清
                         * type : normal
                         * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=normal&source=qcloud
                         */

                        private int height;
                        private int width;
                        private String name;
                        private String type;
                        private String url;
                        private List<UrlListBeanX> urlList;

                        public int getHeight() {
                            return height;
                        }

                        public void setHeight(int height) {
                            this.height = height;
                        }

                        public int getWidth() {
                            return width;
                        }

                        public void setWidth(int width) {
                            this.width = width;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getType() {
                            return type;
                        }

                        public void setType(String type) {
                            this.type = type;
                        }

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }

                        public List<UrlListBeanX> getUrlList() {
                            return urlList;
                        }

                        public void setUrlList(List<UrlListBeanX> urlList) {
                            this.urlList = urlList;
                        }

                        public static class UrlListBeanX {
                            /**
                             * name : qcloud
                             * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=50689&editionType=normal&source=qcloud
                             * size : 8686859
                             */

                            private String name;
                            private String url;
                            private int size;

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

                            public int getSize() {
                                return size;
                            }

                            public void setSize(int size) {
                                this.size = size;
                            }
                        }
                    }
                }
            }

            public static class TagsBean {
                /**
                 * id : 168
                 * name : 讽刺
                 * actionUrl : eyepetizer://tag/168/?title=%E8%AE%BD%E5%88%BA
                 * adTrack : null
                 */

                private int id;
                private String name;
                private String actionUrl;
                private Object adTrack;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getActionUrl() {
                    return actionUrl;
                }

                public void setActionUrl(String actionUrl) {
                    this.actionUrl = actionUrl;
                }

                public Object getAdTrack() {
                    return adTrack;
                }

                public void setAdTrack(Object adTrack) {
                    this.adTrack = adTrack;
                }
            }
        }
    }
}