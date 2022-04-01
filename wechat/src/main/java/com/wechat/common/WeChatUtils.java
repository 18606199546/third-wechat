package com.wechat.common;

/**
 * 企业微信工具类
 */
public class WeChatUtils {
    /**
     * 第三方应用api start
     */
    // 获取第三方应用凭证
    public final static String THIRD_BUS_WECHAT_SUITE_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/service/get_suite_token";

    // 获取企业永久授权码
    public final static String THIRD_BUS_WECHAT_ACCESS_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/service/get_permanent_code?suite_access_token=SUITE_ACCESS_TOKEN";

    // 第三方 构造扫码登录链接
    public final static String THIRD_BUS_WECHAT_LOGIN = "https://open.work.weixin.qq.com/wwopen/sso/3rd_qrConnect?appid=CORPID&redirect_uri=REDIRECT_URI&state=web_login&usertype=member";

    // 第三方 获取登录用户信息 POST
    public final static String THIRD_BUS_WECHAT_GET_LOGIN_INFO = "https://qyapi.weixin.qq.com/cgi-bin/service/get_login_info?access_token=PROVIDER_ACCESS_TOKEN";

    // 第三方 构造网页授权链接
    public final static String THIRD_BUS_WECHAT_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_privateinfo&state=STATE#wechat_redirect";

    // 第三方 获取访问用户身份 GET
    public final static String THIRD_BUS_WECHAT_GET_USER_INFO = "https://qyapi.weixin.qq.com/cgi-bin/service/getuserinfo3rd?suite_access_token=SUITE_TOKEN&code=CODE";

    // 第三方 获取访问用户敏感信息 post
    public final static String THIRD_BUS_WECHAT_GET_USER_DETAIL3RD = "https://qyapi.weixin.qq.com/cgi-bin/service/getuserdetail3rd?suite_access_token=SUITE_ACCESS_TOKEN";

    // 第三方 获取部门列表
    public final static String THIRD_BUS_WECHAT_DEPART_LIST = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN&id=ID";

    // 第三方 获取部门成员
    public final static String THIRD_BUS_WECHAT_DEPART_USER = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD";

    // 第三方 获取部门成员详情
    public final static String THIRD_BUS_WECHAT_DEPART_USER_DETAIL = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD";

    // 第三方 读取成员 GET
    public final static String THIRD_BUS_WECHAT_GET_USER = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";

    // 服务商的token
    public final static String THIRD_BUS_WECHAT_GET_PROVIDER_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/service/get_provider_token";

    // 获取企业凭证
    public final static String THIRD_BUS_WECHAT_GET_CORP_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/service/get_corp_token?suite_access_token=SUITE_ACCESS_TOKEN";

    // 发送应用消息
    public final static String THIRD_BUS_WECHAT_SEND = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";

    // 获取应用的jsapi_ticket
    public final static String THIRD_BUS_GET_JSAPI_TICKET = "https://qyapi.weixin.qq.com/cgi-bin/ticket/get?access_token=ACCESS_TOKEN&type=agent_config";

    // 获取企业的jsapi_ticket
    public final static String THIRD_BUS_GET_JSAPI_TICKET_BUS = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=ACCESS_TOKEN";
    /**
     * 第三方应用api end
     */

}
