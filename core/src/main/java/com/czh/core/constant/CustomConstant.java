package com.czh.core.constant;

/**
 * @author xfan
 * @date Created on 2018/2/7 -- 15:51
 * @desc Custom Constant
 */
public class CustomConstant {
    private CustomConstant() {
    }

    /*------------------------------------------------------------------------ 礼卡相关 -----------------------------------------------------------------*/

    public static final String GIFT_CARD_AVAILABLE = "正常";
    public static final String GIFT_CARD_UNAVAILABLE = "已失效";
    public static final String RECHARGE_GIFT_CARD = "recharge";
    public static final String USE_GIFT_CARD = "use_paper";
    public static final String INVITE_GIFT_CARD = "invite";

    /*------------------------------------------------------------------------ Http Status Code Constants -----------------------------------------------------------------*/

    public static final String RESPONSE_UNAUTHORIZED = "401";

     /*------------------------------------------------------------------------ WX Login Constants -----------------------------------------------------------------*/

    public static final String OPEN_ID = "openid";
    public static final String UNION_ID = "unionid";
    public static final String LOGIN_TYPE_WX = "wx";
    public static final String LOGIN_TYPE_PHONE = "phone";

    /*------------------------------------------------------------------------ Token Type -----------------------------------------------------------------*/

    public static final String MINI_TOKEN = "mini_token";
    public static final String CMS_TOKEN = "cms_token";

     /*------------------------------------------------------------------------ Message -----------------------------------------------------------------*/

    public static final String AFTER_RECHARGE_MSG = "“￥{money}/{paper}张纸巾”充值成功";
    public static final String AFTER_RECHARGE_MSG_TITLE = "充值成功";
    public static final String GET_GIFT_CARD_MSG = "恭喜您获得礼卡“{content}”一张，可在【我的礼卡】查看";
    public static final String GET_GIFT_CARD_TITLE = "获得礼卡";
    public static final String MSG_SEND_BY_SYSTEM = "system";
    public static final String MSG_SEND_BY_BACKSTAGE = "backstage";
}
