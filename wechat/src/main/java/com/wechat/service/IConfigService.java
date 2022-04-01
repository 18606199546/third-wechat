package com.wechat.service;

import javax.servlet.ServletInputStream;

/**
 * 企业微信第三方服务service
 */
public interface IConfigService {

    /**
     * 验证通用开发参数及应用回调
     * @returns: java.lang.String
     */
    String doGetCallback(String msgSignature, String timestamp, String nonce, String echoStr);

    /**
     * 获取SuiteTicket，AuthCode
     */
    String doPostCallback(String msgSignature, String timestamp, String nonce, String type, String corpId, ServletInputStream in);


}
