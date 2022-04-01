package com.wechat.common;

/**
 * 企业微信
 */
public class WeChatConstants {

    // 企业微信授权码获取时间
    public static final Long EXPIRES_IN = 24 * 60 * 60 * 1000L;
    //24 * 60 * 60 * 1000L 7200L * 1000

    /**
     * 服务商CorpID
     */
    public static final String CORP_ID = "ww14438c6c07a317f2";
    /**
     * 服务商身份的调用凭证
     */
    public static final String PROVIDER_SECRET = "RH7PehRJX3LIcw4axad_H2T9HSUG1finOBEpnLTVIioBrP-zgZrGsqJ9pHVw5vVj";

    /**
     * 应用的唯一身份标识
     */
    public static final String SUITE_ID = "ww4f66fa544a32f920";
    /**
     * 应用的调用身份密钥
     */
    public static final String SUITE_SECRET = "vVv8JzaBlEVCTQkHKqmr57EAMs65AILWiI_4ANc25T4";

    /**
     * 应用的ticket
     */
    public static final String SUITE_TICKET = "SUITE_TICKET";

    /**
     * 应用的auth_code
     */
    public static final String AUTH_CODE = "AUTH_CODE";

    /**
     * 第三方应用凭证token
     */
    public static final String SUITE_TOKEN = "suiteToken";

    /**
     * 授权方（企业）token
     */
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";

    /**
     * 提供商 授权方服务token
     */
    public static final String PROVIDER_ACCESS_TOKEN = "PROVIDER_ACCESS_TOKEN";

    /**
     * 应用企业corpid
     */
    public static final String AUTH_CORPID = "AUTH_CORPID";

    /**
     * 企业名称
     */
    public static final String CORP_NAME = "CORPNAME";

    /**
     * 授权方的网页应用ID，在具体的网页应用中查看
     */
    public static final String AGENT_ID = "AGENTID";

    /**
     * 用户id
     */
    public static final String USER_ID = "userId";

    // 回调相关
    /**
     * 回调/通用开发参数Token, 两者解密算法一样，所以为方便设为一样
     */
    public static final String TOKENS = "E0sOXx4LqeE5BmDvMTAz3x";

    /**
     * 回调/通用开发参数EncodingAESKey, 两者解密算法一样，所以为方便设为一样
     */
    public static final String ENCODING_AES_KEY = "IESLPSyW4vyBB90jkzfwfYRtcMky6LIOevr4SVefz7I";

    public static final String REDIRECT_URI = "REDIRECT_URI";

    /**
     * 重定向地址，自己设置
     */
    public static final String REDIRECT_URL = "www.baidu.com";

    // 第三方应用id（即ww或wx开头的suite_id）
    public static final String APP_ID= "APPID";

    public static final String PERMANENT_CODE = "PERMANENT_CODE";

    // 通过成员授权获取到的code
    public static final String CODE = "CODE";

}
