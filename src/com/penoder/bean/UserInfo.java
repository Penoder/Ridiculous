package com.penoder.bean;

/**
 * QQ 登录可以获取到的字段，当然不至于全部都用到
 *
 * Created by asus on 2017/6/2.
 */

public class UserInfo {

	public int id;
	
//    public int ret;                     // 0
    public String openid;               // "068D1591868CA2442EA526C146E9C111"   (唯一标识)
    public String access_token;         // "945E457135F6C2C825AD195068BD4B87"
//    public String pay_token;            // "9EBC86931FF7D4BCDF1B8E4884DC4597"
//    public int expires_in;              // 7776000
//    public String pf;                   // "desktop_m_qq-10000144-android-2002-"
//    public String pfkey;                // "565a46cb2436c4b661350d75abf0e109"
//    public String msg;                  // ""
//    public int login_cost;              // 149
//    public int query_authority_cost;    // 286
//    public int authority_cost;          // 0


//    public int is_lost;                 // 0
    public String nickname;             // "Penoder"
    public String gender;               // "男"
    public String province;             // ""
    public String city;                 // ""
//    public String figureurl;            // "http://qzapp.qlogo.cn/qzapp/1106420736/068D1591868CA2442EA526C146E9C111/30"
//    public String figureurl_1;          // "http://qzapp.qlogo.cn/qzapp/1106420736/068D1591868CA2442EA526C146E9C111/50
//    public String figureurl_2;          // "http://qzapp.qlogo.cn/qzapp/1106420736/068D1591868CA2442EA526C146E9C111/100"
//    public String figureurl_qq_1;       // "http://q.qlogo.cn/qqapp/1106420736/068D1591868CA2442EA526C146E9C111/40"
    public String figureurl_qq_2;       // "http://q.qlogo.cn/qqapp/1106420736/068D1591868CA2442EA526C146E9C111/100"


    public String session;
    public String registerTime;
    public String loginTime;

    /**
     * {"nameValuePairs":{
     *      "ret":0,
     *      "msg":"",
     *      "is_lost":0,
     *      "nickname":"Penoder",
     *      "gender":"男",
     *      "province":"",
     *      "city":"",
     *      "figureurl":"http://qzapp.qlogo.cn/qzapp/1106420736/068D1591868CA2442EA526C146E9C111/30",
     *      "figureurl_1":"http://qzapp.qlogo.cn/qzapp/1106420736/068D1591868CA2442EA526C146E9C111/50",
     *      "figureurl_2":"http://qzapp.qlogo.cn/qzapp/1106420736/068D1591868CA2442EA526C146E9C111/100",
     *      "figureurl_qq_1":"http://q.qlogo.cn/qqapp/1106420736/068D1591868CA2442EA526C146E9C111/40",
     *      "figureurl_qq_2":"http://q.qlogo.cn/qqapp/1106420736/068D1591868CA2442EA526C146E9C111/100",
     *      "is_yellow_vip":"0",
     *      "vip":"0",
     *      "yellow_vip_level":"0",
     *      "level":"0",
     *      "is_yellow_year_vip":"0"
     *  }
     * }
     */

/**
 * {
 *      "ret":0,
 *      "openid":"068D1591868CA2442EA526C146E9C111",
 *      "access_token":"945E457135F6C2C825AD195068BD4B87",
 *      "pay_token":"9EBC86931FF7D4BCDF1B8E4884DC4597",
 *      "expires_in":7776000,
 *      "pf":"desktop_m_qq-10000144-android-2002-",
 *      "pfkey":"565a46cb2436c4b661350d75abf0e109",
 *      "msg":"",
 *      "login_cost":149,
 *      "query_authority_cost":286,
 *      "authority_cost":0
 *  }
 */
}
