package com.penoder.bean;

import java.util.List;

/**
 * Created by Penoder on 2017/5/13.
 */

public class FunImgBean {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"allPages":2334,"ret_code":0,"contentlist":[{"id":"5916945a6e36d651f8cb28b9","title":"妹子这技能一般人做不到","img":"http://sc2.hao123img.com/data/73e1c698fba6a16c730b848c73773540_430","type":2,"ct":"2017-05-13 13:06:34.454"},{"id":"59153a4c6e36d651f8c7001f","title":"我仿佛看到了人生赢家~","img":"http://sc2.hao123img.com/data/8703c19e40dfc8ab796253f98f3d784a_430","type":2,"ct":"2017-05-12 12:30:04.269"},{"id":"59118c196e36ab07d742ad56","title":"穷人的孩子早当￥","img":"http://sc4.hao123img.com/data/3c1a188a63695e8cf4e20a6da81e8121_0","type":2,"ct":"2017-05-09 17:30:01.533"},{"id":"59117e0c6e36ab07d742850e","title":"老婆失踪后，我第一时间到公安局报警。 民警对我说：你先冷静一下， 你这样一直笑没办法做笔录\u2026\u2026","img":"http://sc2.hao123img.com/data/765b691e5fffb643f7c043821fa9bf1b_430","type":2,"ct":"2017-05-09 16:30:04.433"},{"id":"59117e0c6e36ab07d7428506","title":"今天在公司开会，由于BOSS比较忙，","img":"http://sc2.hao123img.com/data/76d7ce49031cd5a2ae9c3d6cb5a18a75_0","type":2,"ct":"2017-05-09 16:30:04.009"},{"id":"59116ffb6e36ab07d74257c7","title":"今天在公司开会，由于BOSS比较忙，会议流程上写的是讲话40分钟，BOSS站在讲话台上说：我40分钟不行，最多10分钟\u2026\u2026然后台","img":"http://sc0.hao123img.com/data/f9e3c96574a18e9922aef88d5682f12b_430","type":2,"ct":"2017-05-09 15:30:03.186"},{"id":"59116ffb6e36ab07d74257c6","title":"今天带孩子去商场，碰到前女友，闲聊了几句她问我","img":"http://sc4.hao123img.com/data/0945844dae5c82fe460b7a237759517e_430","type":2,"ct":"2017-05-09 15:30:03.185"},{"id":"59103a9b6e36e2063f91a787","title":"今天我妈问我，儿子你以后要是找着媳妇了会不会就不管妈了~","img":"http://sc3.hao123img.com/data/1e66ea22c259b551604d4140c5438ccf_430","type":2,"ct":"2017-05-08 17:30:03.319"},{"id":"591034c26e36e2063f91a339","title":"今天我妈问我，儿子你以后要是找着媳妇了会不会就不管妈了!我生气的说到\u201c妈，你把我当什么人了，我是那种轻易能找着老婆的人吗?\u201d\u201c妈","img":"http://sc1.hao123img.com/data/d9c2fb582a2ee161ee8d2ad890066353_430","type":2,"ct":"2017-05-08 17:05:06.371"},{"id":"59102c8b6e36ea527776c439","title":"碰上这样的妹子可还行@","img":"http://sc2.hao123img.com/data/1351b3b99ae792b631f03d72a0ad5b03_0","type":2,"ct":"2017-05-08 16:30:03.805"}],"currentPage":1,"allNum":23338,"maxResult":10}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * allPages : 2334
         * ret_code : 0
         * contentlist : [{"id":"5916945a6e36d651f8cb28b9","title":"妹子这技能一般人做不到","img":"http://sc2.hao123img.com/data/73e1c698fba6a16c730b848c73773540_430","type":2,"ct":"2017-05-13 13:06:34.454"},{"id":"59153a4c6e36d651f8c7001f","title":"我仿佛看到了人生赢家~","img":"http://sc2.hao123img.com/data/8703c19e40dfc8ab796253f98f3d784a_430","type":2,"ct":"2017-05-12 12:30:04.269"},{"id":"59118c196e36ab07d742ad56","title":"穷人的孩子早当￥","img":"http://sc4.hao123img.com/data/3c1a188a63695e8cf4e20a6da81e8121_0","type":2,"ct":"2017-05-09 17:30:01.533"},{"id":"59117e0c6e36ab07d742850e","title":"老婆失踪后，我第一时间到公安局报警。 民警对我说：你先冷静一下， 你这样一直笑没办法做笔录\u2026\u2026","img":"http://sc2.hao123img.com/data/765b691e5fffb643f7c043821fa9bf1b_430","type":2,"ct":"2017-05-09 16:30:04.433"},{"id":"59117e0c6e36ab07d7428506","title":"今天在公司开会，由于BOSS比较忙，","img":"http://sc2.hao123img.com/data/76d7ce49031cd5a2ae9c3d6cb5a18a75_0","type":2,"ct":"2017-05-09 16:30:04.009"},{"id":"59116ffb6e36ab07d74257c7","title":"今天在公司开会，由于BOSS比较忙，会议流程上写的是讲话40分钟，BOSS站在讲话台上说：我40分钟不行，最多10分钟\u2026\u2026然后台","img":"http://sc0.hao123img.com/data/f9e3c96574a18e9922aef88d5682f12b_430","type":2,"ct":"2017-05-09 15:30:03.186"},{"id":"59116ffb6e36ab07d74257c6","title":"今天带孩子去商场，碰到前女友，闲聊了几句她问我","img":"http://sc4.hao123img.com/data/0945844dae5c82fe460b7a237759517e_430","type":2,"ct":"2017-05-09 15:30:03.185"},{"id":"59103a9b6e36e2063f91a787","title":"今天我妈问我，儿子你以后要是找着媳妇了会不会就不管妈了~","img":"http://sc3.hao123img.com/data/1e66ea22c259b551604d4140c5438ccf_430","type":2,"ct":"2017-05-08 17:30:03.319"},{"id":"591034c26e36e2063f91a339","title":"今天我妈问我，儿子你以后要是找着媳妇了会不会就不管妈了!我生气的说到\u201c妈，你把我当什么人了，我是那种轻易能找着老婆的人吗?\u201d\u201c妈","img":"http://sc1.hao123img.com/data/d9c2fb582a2ee161ee8d2ad890066353_430","type":2,"ct":"2017-05-08 17:05:06.371"},{"id":"59102c8b6e36ea527776c439","title":"碰上这样的妹子可还行@","img":"http://sc2.hao123img.com/data/1351b3b99ae792b631f03d72a0ad5b03_0","type":2,"ct":"2017-05-08 16:30:03.805"}]
         * currentPage : 1
         * allNum : 23338
         * maxResult : 10
         */

        private int allPages;
        private int ret_code;
        private int currentPage;
        private int allNum;
        private int maxResult;
        private List<ImgBean> contentlist;

        public int getAllPages() {
            return allPages;
        }

        public void setAllPages(int allPages) {
            this.allPages = allPages;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getAllNum() {
            return allNum;
        }

        public void setAllNum(int allNum) {
            this.allNum = allNum;
        }

        public int getMaxResult() {
            return maxResult;
        }

        public void setMaxResult(int maxResult) {
            this.maxResult = maxResult;
        }

        public List<ImgBean> getContentlist() {
            return contentlist;
        }

        public void setContentlist(List<ImgBean> contentlist) {
            this.contentlist = contentlist;
        }
    }
}
