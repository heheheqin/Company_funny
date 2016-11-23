package com.dream.will.company_funny.bean;

import java.util.List;

/**
 * Author：Will on 2016/11/23 16:43
 * Mail：heheheqin.will@gmail.com
 */

public class BannerBean {


    /**
     * data : [{"houseid":"177446","picurl":"http://p.qpic.cn/estate/0/ce2db30ec2b6fa9bc8037a3668a08b84.jpg/0","title":"50-70㎡智慧商务空间","type":"3"},{"houseid":"166048","picurl":"http://p.qpic.cn/estate/0/4a336c64f3abad91056ce5ce6dd090a2.jpg/0","title":"泰禾中央广场荟萃世界的精彩","type":"3"},{"houseid":"172904","picurl":"http://p.qpic.cn/estate/0/8855633d43fd6b844f69bcbe8a897b5b.jpg/0","title":"海淀院落别墅即将发售！","type":"3"}]
     * ehouse_timestamp : 1478612493
     * retcode : 0
     * retmsg : 成功
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * houseid : 177446
         * picurl : http://p.qpic.cn/estate/0/ce2db30ec2b6fa9bc8037a3668a08b84.jpg/0
         * title : 50-70㎡智慧商务空间
         * type : 3
         */

        private String houseid;
        private String picurl;
        private String title;
        private String type;

        public String getHouseid() {
            return houseid;
        }

        public void setHouseid(String houseid) {
            this.houseid = houseid;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
