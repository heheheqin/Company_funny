package com.dream.will.company_funny.bean;

import java.util.List;

/**
 * Author：Will on 2016/11/25 09:46
 * Mail：heheheqin.will@gmail.com
 */

public class HomeNewsDetail {

    /**
     * retcode : 0
     * retmsg : 成功
     * id : HSH2016112500631003
     * title : 老小区补短板 上海积极推进基层社会治理创新
     * source : 解放日报
     * time : 2016-11-25 08:20:44
     * url : http://xw.qq.com/house/20161125006310/HSH2016112500631003
     * surl : http://m.house.qq.com/a/HSH2016112500631003/
     * content : [{"type":1,"value":"老旧小区，似乎总伴随着一堆麻烦：硬件老化、软件落后、停车难、维修难，没有物业或是物业来了又走\u2026\u2026 　　如何破解难题？记者最近走访了上海不少老旧小区，发现解决上述难题光靠居委会或街道恐怕不行，就像社区干部所说的，\u201c管头管脚管得再多，总有疏漏，没有居民们一起参与效果好。\u201d 　　今年初，市委、市政府将住宅小区综合治理纳入《创新社会治理加强基层建设\u201c1+6\u201d文件2016年推进落实工作要点》，作为\u201c深入推进基层社会治理创新\u201d的一项重要任务。文件精神延伸到社会毛细血管的最末梢，让越来越多的居民明白了一件事，\u201c政府现在鼓励老百姓一起参与小区治理\u201d。 　　奉贤南桥江海新村，建于上世纪80年代，这个小区没有像样的大门，仅有的6栋居民楼也有些陈旧。但走进小区，踏进每栋楼后，却能在略显斑驳的楼梯扶手背后，感受到新式楼盘里稀缺的那种浓浓人情味。 　　在徐汇斜土街道嘉乐公寓，曾经\u201c扑扑满\u201d的楼道堆物让公寓楼内部\u201c暗无天日\u201d。今年，小区居民们在业委会的带领下，主动与堆放在家门口二三十年的杂物\u201c断舍离\u201d。老公寓楼重新变得宽敞、明亮。居民们说，\u201c楼道亮了，心也跟着亮堂了。\u201d 　　在杨浦区江浦路街道辽昆居民区，为扮美老弄堂，居民们把周边文创园的白领也拉进来，一道涂鸦墙让老弄堂变得优雅、时尚。而居民楼与商务楼的互动，也让社区居民自治有了更多资源，底气也更足了。 　　小区虽老旧，但在居民们心中，这里是住了数十年的温暖的家。虽然没有停车库，没有大面积绿化，甚至外观有些过时，但正因居民们对这里的感情无比深厚，大家参与小区治理的积极性也普遍高涨。记者注意到，这些小区的居民自治，并非一窝蜂、一阵风，更多时候是大家细致商讨、集思广益，形成机制，自治的效果也更加长久。 　　(解放日报)"},{"type":2,"value":"http://inews.gtimg.com/newsapp_bt/0/83261103/640"}]
     */

    private int retcode;
    private String retmsg;
    private String id;
    private String title;
    private String source;
    private String time;
    private String url;
    private String surl;
    private List<ContentBean> content;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * type : 1
         * value : 老旧小区，似乎总伴随着一堆麻烦：硬件老化、软件落后、停车难、维修难，没有物业或是物业来了又走…… 　　如何破解难题？记者最近走访了上海不少老旧小区，发现解决上述难题光靠居委会或街道恐怕不行，就像社区干部所说的，“管头管脚管得再多，总有疏漏，没有居民们一起参与效果好。” 　　今年初，市委、市政府将住宅小区综合治理纳入《创新社会治理加强基层建设“1+6”文件2016年推进落实工作要点》，作为“深入推进基层社会治理创新”的一项重要任务。文件精神延伸到社会毛细血管的最末梢，让越来越多的居民明白了一件事，“政府现在鼓励老百姓一起参与小区治理”。 　　奉贤南桥江海新村，建于上世纪80年代，这个小区没有像样的大门，仅有的6栋居民楼也有些陈旧。但走进小区，踏进每栋楼后，却能在略显斑驳的楼梯扶手背后，感受到新式楼盘里稀缺的那种浓浓人情味。 　　在徐汇斜土街道嘉乐公寓，曾经“扑扑满”的楼道堆物让公寓楼内部“暗无天日”。今年，小区居民们在业委会的带领下，主动与堆放在家门口二三十年的杂物“断舍离”。老公寓楼重新变得宽敞、明亮。居民们说，“楼道亮了，心也跟着亮堂了。” 　　在杨浦区江浦路街道辽昆居民区，为扮美老弄堂，居民们把周边文创园的白领也拉进来，一道涂鸦墙让老弄堂变得优雅、时尚。而居民楼与商务楼的互动，也让社区居民自治有了更多资源，底气也更足了。 　　小区虽老旧，但在居民们心中，这里是住了数十年的温暖的家。虽然没有停车库，没有大面积绿化，甚至外观有些过时，但正因居民们对这里的感情无比深厚，大家参与小区治理的积极性也普遍高涨。记者注意到，这些小区的居民自治，并非一窝蜂、一阵风，更多时候是大家细致商讨、集思广益，形成机制，自治的效果也更加长久。 　　(解放日报)
         */

        private int type;
        private String value;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "ContentBean{" +
                    "type=" + type +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HomeNewsDetail{" +
                "retcode=" + retcode +
                ", retmsg='" + retmsg + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", time='" + time + '\'' +
                ", url='" + url + '\'' +
                ", surl='" + surl + '\'' +
                ", content=" + content +
                '}';
    }
}
