package com.penoder.bean;

import java.util.List;

/**
 * Created by Penoder on 2017/5/13.
 */

public class FunGifBean {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"allPages":1121,"ret_code":0,"currentPage":1,"allNum":11204,"maxResult":10,"contentlist":[{"id":"585e403c6e36392559c741ef","title":"姿势我已经很老练了，就差个女友了！","img":"http://www.zbjuran.com/uploads/allimg/161224/10-1612241H504D9.gif","type":3,"ct":"2016-12-24 17:30:36.985"},{"id":"585e1cff6e36392559c6c8d8","title":"下次去海边也要试一下","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224144225N6.gif","type":3,"ct":"2016-12-24 15:00:15.867"},{"id":"585e1cff6e36392559c6c8d7","title":"应该让我来帮你们解决啊","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224144641359.gif","type":3,"ct":"2016-12-24 15:00:15.866"},{"id":"585e16056e36392559c6ac51","title":"来，接我一球。","img":"http://www.zbjuran.com/uploads/allimg/161224/10-16122414162IO.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585e16056e36392559c6ac50","title":"妹子好臂力！","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224141T1504.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585e16056e36392559c6ac4f","title":"给你们感受一下男友力","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224141324221.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585e16056e36392559c6ac4e","title":"什么鱼，游泳的速度太快了！","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224142353C2.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585e16056e36392559c6ac4d","title":"你是想体验一下你家松鼠的生活吗！","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224142150505.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585ceec56e36392559c198c8","title":"如何拍出有B格的照片\u2026\u2026","img":"http://www.zbjuran.com/uploads/allimg/161223/10-1612231AS63R.gif","type":3,"ct":"2016-12-23 17:30:45.604"},{"id":"585ccba56e36392559c11ee2","title":"一广东佛山男子两月内三次被抢手机，实在忍无可忍，正好看到抢他","img":"http://www.zbjuran.com/uploads/allimg/161223/10-161223144TOH.gif","type":3,"ct":"2016-12-23 15:00:53.299"}]}
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
         * allPages : 1121
         * ret_code : 0
         * currentPage : 1
         * allNum : 11204
         * maxResult : 10
         * contentlist : [{"id":"585e403c6e36392559c741ef","title":"姿势我已经很老练了，就差个女友了！","img":"http://www.zbjuran.com/uploads/allimg/161224/10-1612241H504D9.gif","type":3,"ct":"2016-12-24 17:30:36.985"},{"id":"585e1cff6e36392559c6c8d8","title":"下次去海边也要试一下","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224144225N6.gif","type":3,"ct":"2016-12-24 15:00:15.867"},{"id":"585e1cff6e36392559c6c8d7","title":"应该让我来帮你们解决啊","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224144641359.gif","type":3,"ct":"2016-12-24 15:00:15.866"},{"id":"585e16056e36392559c6ac51","title":"来，接我一球。","img":"http://www.zbjuran.com/uploads/allimg/161224/10-16122414162IO.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585e16056e36392559c6ac50","title":"妹子好臂力！","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224141T1504.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585e16056e36392559c6ac4f","title":"给你们感受一下男友力","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224141324221.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585e16056e36392559c6ac4e","title":"什么鱼，游泳的速度太快了！","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224142353C2.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585e16056e36392559c6ac4d","title":"你是想体验一下你家松鼠的生活吗！","img":"http://www.zbjuran.com/uploads/allimg/161224/10-161224142150505.gif","type":3,"ct":"2016-12-24 14:30:29.134"},{"id":"585ceec56e36392559c198c8","title":"如何拍出有B格的照片\u2026\u2026","img":"http://www.zbjuran.com/uploads/allimg/161223/10-1612231AS63R.gif","type":3,"ct":"2016-12-23 17:30:45.604"},{"id":"585ccba56e36392559c11ee2","title":"一广东佛山男子两月内三次被抢手机，实在忍无可忍，正好看到抢他","img":"http://www.zbjuran.com/uploads/allimg/161223/10-161223144TOH.gif","type":3,"ct":"2016-12-23 15:00:53.299"}]
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
