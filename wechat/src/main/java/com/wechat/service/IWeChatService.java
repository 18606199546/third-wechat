package com.wechat.service;

import com.wechat.entity.wechat.WeChatLoginUrl;
import com.wechat.entity.wechat.WeChatUserinfo3rd;
import com.wechat.entity.wechat.WechatUserInfo;

/**
 * 第三方应用服务层
 */
public interface IWeChatService {
    /**
     * 获取企业永久码
     */
    void getPermanentCode(String authCode);

    /**
     * 扫码登录-获取用户信息
     */
    WechatUserInfo getUserInfo(String code);

    /**
     * 网页授权登录-获取用户信息
     */
    WeChatUserinfo3rd getUserinfo3rd(String code);

    /**
     * 获取扫码登录地址
     */
    WeChatLoginUrl thirdLoginUrl();

    /**
     * 获取企业微信内登录地址
     */
    WeChatLoginUrl wechatLoginUrl();
}
