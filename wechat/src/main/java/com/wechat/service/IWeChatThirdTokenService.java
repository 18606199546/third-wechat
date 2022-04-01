package com.wechat.service;

/**
 * 获取token
 */
public interface IWeChatThirdTokenService {

    /**
     * 获取第三方应用凭证
     */
    void getSuiteToken();

    /**
     * 服务商的token
     */
    void getProviderToken();

    /**
     * 获取企业凭证
     */
    void getCorpToken();
}
