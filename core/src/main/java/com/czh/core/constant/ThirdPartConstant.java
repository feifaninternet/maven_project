package com.czh.core.constant;

/**
 * @author xfan
 * @date Created on 2018/2/11 -- 18:04
 * @desc 第三方接口地址
 */
public class ThirdPartConstant {

    /*----------------------------------------------------------------------- 微信相关 -------------------------------------------------------------------*/

    public static final String MINI_APP_ID = "wx65fbfa71fbf0b639";
    private static final String MINI_SECRET = "5ab21044d7f313d2661ef707cb43d654";

    //微信支付

    public static final String PAY_SUCCESS_STR = "SUCCESS";
    public static final String WX_PAY_ID = "1498814312";
    public static final String WX_PAY_KEY = "vnYncWrMQsGHTVvj8ZR76B1d8T32oQZK";
    public static final String WX_PAY_BACK_IP = "120.78.10.31";
    public static final String WX_PAY_BACK_URL = "https://api-czh.dankal.cn/v1/recharge/wx_notify";
    public static final String WX_PAY_TYPE = "JSAPI";
    public static final String ENCODE_TYPE = "utf-8";
    public static final String PAY_ERROR_MSG = "签名错误";
    public static final String PAY_BODY = "众朴科技-充值纸张";
    public static final String PAY_ATTACH = "充值纸张";
    public static final String PAY_DETAIL = "用户充值纸张用于使用";

    /**
     * 小程序获取用户 openid 等
     */
    public static final String MINI_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=" + MINI_APP_ID + "&secret=" + MINI_SECRET +
                                                "&grant_type=authorization_code&js_code=";

    /*----------------------------------------------------------------------- 阿里云相关 -------------------------------------------------------------------*/

    public static final String ALI_ACCESS_KEY_ID = "LTAIMJrCxdWah4Ii";
    public static final String ALI_ACCESS_KEY_SECRET = "Yzx0pe7Ib4uV6xIfQIoDPwsXF74E2A";
    public static final String SMS_CODE_STATE_OK = "OK";
    public static final Integer SMS_CODE_LENGTH = 6;
    public static final String ALI_SMS_MSG_SIGN_NAME = "众朴科技";
    public static final String ALI_SMS_MSG_TEMPLATE_CODE = "SMS_126260271";

    /*----------------------------------------------------------------------- 七牛云相关 -------------------------------------------------------------------*/
    
//    public static final String QI_NIU_ACCESS_KEY = "T-vbxxRWh6cwm_kHRAg52Supa3QaKKjw2PQAJojq";
    public static final String QI_NIU_ACCESS_KEY = "Jyi6Ntprm38nI6n1heGjwXyQmzie8ZjY7l9Cq_Je";
//    public static final String QI_NIU_SECRET_KEY = "UGMkJzkzfIUeGeV0Ko4ljlLOZ5BDTZyITcnNDhMx";
    public static final String QI_NIU_SECRET_KEY = "eBqe82USZuB6gnqvjTsyg_qki6C6HwnkFIvNIH-y";
//    public static final String QI_NIU_BUCKET_NAME = "czh-device-exception";
    public static final String QI_NIU_BUCKET_NAME = "dankal-czh";
//    public static final String QI_NIU_IP_ADDRESS = "p4uu37s23.bkt.clouddn.com";
    public static final String QI_NIU_IP_ADDRESS = "p5d2fqz8j.bkt.clouddn.com";

    /*----------------------------------------------------------------------- 腾讯地图相关 -------------------------------------------------------------------*/

    public static final String TENCENT_MAP_KEY = "YXTBZ-APB3X-NFS43-7C7CR-TFUQF-DUBCU";
    public static final String TENCENT_MAP_RANDOM_LOCATION_URL = "http://apis.map.qq.com/ws/geocoder/v1/?key=" + TENCENT_MAP_KEY + "&location={location}&get_poi=1&poi_options={poi_options}";
    public static final String TENCENT_MAP_ADDRESS_RESOLUTION_URL = "http://apis.map.qq.com/ws/geocoder/v1/?key=" + TENCENT_MAP_KEY + "&address={address}";

}
