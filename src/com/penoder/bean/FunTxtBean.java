package com.penoder.bean;

import java.util.List;

/**
 * Created by Penoder on 2017/5/13.
 */

public class FunTxtBean {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"allPages":1140,"ret_code":0,"contentlist":[{"id":"5916b0856e36d651f8cb828d","title":"\u201c我是高中生侦探工藤...","text":"\u201c我是高中生侦探工藤新一，当我跟青梅竹马的同学毛利兰一起到游乐园游玩时，却目击了黑暗组织的交易现场。当时我只顾着偷看交易，却忽略了从背后而来的另一个同伙。我被那个人强灌了毒药，等我醒来时，我的声优已经换人了。\u201d","type":1,"ct":"2017-05-13 15:06:45.947"},{"id":"5916a28f6e36d651f8cb54b9","title":"某妇抱怨丈夫：\u201c你真...","text":"某妇抱怨丈夫：\u201c你真窝囊，哪方面都没有人家好。\u201d\r\n夫道：\u201c非常正确，尤其是老婆！\u201d","type":1,"ct":"2017-05-13 14:07:11.531"},{"id":"591694596e36d651f8cb28b4","title":"女：\u201c你干嘛一直打哈...","text":"女：\u201c你干嘛一直打哈欠？\u201d\r\n男：\u201c我听人家说，打哈欠是会传染的。\u201d\r\n女：\u201c那你干嘛要传染我这个？\u201d\r\n男：\u201c因为我想和你睡觉嘛。\u201d","type":1,"ct":"2017-05-13 13:06:33.928"},{"id":"591694596e36d651f8cb28b2","title":"过年了一堆朋友相约去...","text":"过年了一堆朋友相约去广场放孔明灯。大家把心愿写好，点上灯，灯飞了大概两层楼高的时候。一位（大龄宅女一枚）冲天大喊\u201c老天爷啊，赐给我一个男人吧！\u201d然后\u2026\u2026她的灯就掉下来了。再然后笑疯了一群人\u2026\u2026","type":1,"ct":"2017-05-13 13:06:33.890"},{"id":"59167dbb6e36d651f8cad3c6","title":"你看过了许多美景，你...","text":"你看过了许多美景，你看过了许多美女，你跪烂了家里面每一块废弃的主板。","type":1,"ct":"2017-05-13 11:30:03.833"},{"id":"591661a56e36d651f8ca7dbf","title":"女：\u201c我需要一个男友...","text":"女：\u201c我需要一个男友。\u201d\r\n男：\u201c我帮你找，我宿舍有个兄弟还不错。\u201d\r\n女：\u201c我跟他在一起，你不会心疼吗？\u201d\r\n男：\u201c微小说看多了吧？我跟他没什么的，你放心好了。\u201d","type":1,"ct":"2017-05-13 09:30:13.766"},{"id":"591661a56e36d651f8ca7dbd","title":"有一同事早上走路去上...","text":"有一同事早上走路去上班，遇一男人抢劫，把她的手提袋抢了就跑，我那同事就在后面追啊，还我手提袋，还我手提袋，我里面什么都没有，只有几个姨妈巾，你给我抢走了，我今天怎么过啊。","type":1,"ct":"2017-05-13 09:30:13.645"},{"id":"59165bc76e36d651f8ca75cf","title":"今天去上课的路上碰见...","text":"今天去上课的路上碰见我们的女导员，顺手把我准备带去上课吃的香蕉递给她一根。\r\n她连忙说：谢谢\u2026不用不用真不用\u2026\r\n然后我特么也不知道怎么脑子一热，就冒出一句：不用\u2026你可以吃啊！","type":1,"ct":"2017-05-13 09:05:11.690"},{"id":"59164e4c6e36d651f8ca4e31","title":"和老公走在马路上，打...","text":"和老公走在马路上，打闹，生气，大骂。前面两男士回头看，妻子马上装小鸟依人。\r\n老公说：\u201c别装了，他们没看你，他们是看哪个男人这么窝囊。\u201d","type":1,"ct":"2017-05-13 08:07:40.077"},{"id":"59164e4c6e36d651f8ca4e30","title":"表弟小时候不爱学习。...","text":"表弟小时候不爱学习。\r\n5以内的加减法都学不会。\r\n于是我姨夫就很生气的亲自教他：\u201c你左手5个手指头，咔嚓一刀剁掉3个，还有几个？\u201d\r\n于是我表弟扑通一声就跪下抱着我姨夫的腿嚎哭：\u201c爸爸，别杀我\u2026\u2026\u201c","type":1,"ct":"2017-05-13 08:07:40.075"}],"currentPage":1,"allNum":11394,"maxResult":10}
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
         * allPages : 1140
         * ret_code : 0
         * contentlist : [{"id":"5916b0856e36d651f8cb828d","title":"\u201c我是高中生侦探工藤...","text":"\u201c我是高中生侦探工藤新一，当我跟青梅竹马的同学毛利兰一起到游乐园游玩时，却目击了黑暗组织的交易现场。当时我只顾着偷看交易，却忽略了从背后而来的另一个同伙。我被那个人强灌了毒药，等我醒来时，我的声优已经换人了。\u201d","type":1,"ct":"2017-05-13 15:06:45.947"},{"id":"5916a28f6e36d651f8cb54b9","title":"某妇抱怨丈夫：\u201c你真...","text":"某妇抱怨丈夫：\u201c你真窝囊，哪方面都没有人家好。\u201d\r\n夫道：\u201c非常正确，尤其是老婆！\u201d","type":1,"ct":"2017-05-13 14:07:11.531"},{"id":"591694596e36d651f8cb28b4","title":"女：\u201c你干嘛一直打哈...","text":"女：\u201c你干嘛一直打哈欠？\u201d\r\n男：\u201c我听人家说，打哈欠是会传染的。\u201d\r\n女：\u201c那你干嘛要传染我这个？\u201d\r\n男：\u201c因为我想和你睡觉嘛。\u201d","type":1,"ct":"2017-05-13 13:06:33.928"},{"id":"591694596e36d651f8cb28b2","title":"过年了一堆朋友相约去...","text":"过年了一堆朋友相约去广场放孔明灯。大家把心愿写好，点上灯，灯飞了大概两层楼高的时候。一位（大龄宅女一枚）冲天大喊\u201c老天爷啊，赐给我一个男人吧！\u201d然后\u2026\u2026她的灯就掉下来了。再然后笑疯了一群人\u2026\u2026","type":1,"ct":"2017-05-13 13:06:33.890"},{"id":"59167dbb6e36d651f8cad3c6","title":"你看过了许多美景，你...","text":"你看过了许多美景，你看过了许多美女，你跪烂了家里面每一块废弃的主板。","type":1,"ct":"2017-05-13 11:30:03.833"},{"id":"591661a56e36d651f8ca7dbf","title":"女：\u201c我需要一个男友...","text":"女：\u201c我需要一个男友。\u201d\r\n男：\u201c我帮你找，我宿舍有个兄弟还不错。\u201d\r\n女：\u201c我跟他在一起，你不会心疼吗？\u201d\r\n男：\u201c微小说看多了吧？我跟他没什么的，你放心好了。\u201d","type":1,"ct":"2017-05-13 09:30:13.766"},{"id":"591661a56e36d651f8ca7dbd","title":"有一同事早上走路去上...","text":"有一同事早上走路去上班，遇一男人抢劫，把她的手提袋抢了就跑，我那同事就在后面追啊，还我手提袋，还我手提袋，我里面什么都没有，只有几个姨妈巾，你给我抢走了，我今天怎么过啊。","type":1,"ct":"2017-05-13 09:30:13.645"},{"id":"59165bc76e36d651f8ca75cf","title":"今天去上课的路上碰见...","text":"今天去上课的路上碰见我们的女导员，顺手把我准备带去上课吃的香蕉递给她一根。\r\n她连忙说：谢谢\u2026不用不用真不用\u2026\r\n然后我特么也不知道怎么脑子一热，就冒出一句：不用\u2026你可以吃啊！","type":1,"ct":"2017-05-13 09:05:11.690"},{"id":"59164e4c6e36d651f8ca4e31","title":"和老公走在马路上，打...","text":"和老公走在马路上，打闹，生气，大骂。前面两男士回头看，妻子马上装小鸟依人。\r\n老公说：\u201c别装了，他们没看你，他们是看哪个男人这么窝囊。\u201d","type":1,"ct":"2017-05-13 08:07:40.077"},{"id":"59164e4c6e36d651f8ca4e30","title":"表弟小时候不爱学习。...","text":"表弟小时候不爱学习。\r\n5以内的加减法都学不会。\r\n于是我姨夫就很生气的亲自教他：\u201c你左手5个手指头，咔嚓一刀剁掉3个，还有几个？\u201d\r\n于是我表弟扑通一声就跪下抱着我姨夫的腿嚎哭：\u201c爸爸，别杀我\u2026\u2026\u201c","type":1,"ct":"2017-05-13 08:07:40.075"}]
         * currentPage : 1
         * allNum : 11394
         * maxResult : 10
         */

        private int allPages;
        private int ret_code;
        private int currentPage;
        private int allNum;
        private int maxResult;
        private List<TxtBean> contentlist;

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

        public List<TxtBean> getContentlist() {
            return contentlist;
        }

        public void setContentlist(List<TxtBean> contentlist) {
            this.contentlist = contentlist;
        }
    }
}
