package com.wechat.service.impl;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.wechat.common.WeChatConstants;
import com.wechat.common.WeChatUtils;
import com.wechat.common.cache.CacheData;
import com.wechat.entity.wechat.WeChatLoginUrl;
import com.wechat.entity.wechat.WeChatPermanentCodeReturn;
import com.wechat.entity.wechat.WeChatUserinfo3rd;
import com.wechat.entity.wechat.WechatUserInfo;
import com.wechat.service.IWeChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 第三方应用服务层
 */
@Slf4j
@Service
public class WeChatServiceImpl implements IWeChatService {

    /**
     * 构造扫码登录链接
     */
    @Override
    public WeChatLoginUrl thirdLoginUrl() {
        WeChatLoginUrl login = new WeChatLoginUrl();
        // 企业微信的CorpID
        String corpId = WeChatConstants.CORP_ID;
        // 重定向url
        String redirectUrl = WeChatConstants.REDIRECT_URL;
        log.debug("登录地址url:"+redirectUrl+"企业微信corpId->"+corpId);
        // 重定向地址
        String redirectUri = "";
        try {
            redirectUri = URLEncoder.encode((redirectUrl), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        // 获取扫码登录链接
        String getWechatLogin = WeChatUtils.THIRD_BUS_WECHAT_LOGIN;
        // 转换成登录地址
        String loginUrl = getWechatLogin.replace(WeChatConstants.CORP_ID, corpId).replace(WeChatConstants.REDIRECT_URI,redirectUri);
        login.setLoginUrl(loginUrl);
        log.debug("重定向后登录地址url:"+login);
        return login;
    }

    /**
     * 构造企业微信内登录链接
     */
    @Override
    public WeChatLoginUrl wechatLoginUrl() {
        log.debug("wechatLogin->start");
        WeChatLoginUrl login = new WeChatLoginUrl();
        // 	第三方应用id（即ww或wx开头的suite_id）。
        String suiteId = WeChatConstants.SUITE_ID;
        // 重定向地址
        String redirectUrl = WeChatConstants.REDIRECT_URL;
        log.debug("suiteId:"+suiteId+"==redirectUrl:"+redirectUrl);
        // 重定向地址
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        //第三方 构造网页授权链接
        String getWechatLogin = WeChatUtils.THIRD_BUS_WECHAT_AUTHORIZE_URL;
        // 转换成登录地址
        String loginUrl = getWechatLogin.replace(WeChatConstants.APP_ID, suiteId).replace(WeChatConstants.REDIRECT_URI,redirectUrl);
        login.setLoginUrl(loginUrl);
        log.debug("企业微信内登录重定向url:"+loginUrl);
        return login;
    }

    /**
     * 获取企业永久码
     */
    @Override
    public void getPermanentCode(String authCode) {
        // 永久授权码
        log.debug("获取企业永久授权码->getPermanentCode->start");
        // 	第三方应用access_token
        String suiteToken = (String)CacheData.get("SUITE_TOKEN");
        // 获取企业永久授权码url
        String accessTokenUrl = WeChatUtils.THIRD_BUS_WECHAT_ACCESS_TOKEN;
        // 替换值
        accessTokenUrl = accessTokenUrl.replace("SUITE_ACCESS_TOKEN", suiteToken);
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("auth_code", authCode);
            log.debug("获取企业永久授权码accessTokenUrl:"+accessTokenUrl+"->auth_code:"+authCode);
            String body = HttpRequest.post(accessTokenUrl).body(JSONUtil.toJsonStr(map), ContentType.JSON.getValue()).execute().body();
            WeChatPermanentCodeReturn weChat = JSONUtil.toBean(body, WeChatPermanentCodeReturn.class);
            log.debug("获取企业永久授权码转换成bean->weChat:"+weChat);
            //授权方（企业）access_token
            String accessToken = weChat.getAccess_token();
            //授权方企业微信id
            String corpId = weChat.getAuth_corp_info().getCorpid();
            //授权方企业微信名称
            String corpName = weChat.getAuth_corp_info().getCorp_name();
            //授权方应用id
            Long agentId = weChat.getAuth_info().getAgent().get(0).getAgentid();
            //用户id
            String userId = weChat.getAuth_user_info().getUserid();
            //企业永久授权码
            String permanentCode = weChat.getPermanent_code();
            //存放到cache中
            CacheData.put(WeChatConstants.ACCESS_TOKEN, accessToken);
            //授权方企业微信id
            CacheData.put(WeChatConstants.AUTH_CORPID, corpId);
            //授权方企业微信名称
            CacheData.put(WeChatConstants.CORP_NAME, corpName);
            //授权方应用id
            CacheData.put(WeChatConstants.AGENT_ID, agentId);
            //用户id
            CacheData.put(WeChatConstants.USER_ID, userId);
            //获取企业永久授权码
            CacheData.put(WeChatConstants.PERMANENT_CODE, permanentCode);
            log.debug("获取企业永久授权码->PERMANENT_CODE:"+permanentCode);
        } catch (Exception e) {
            log.debug("获取accessToken失败errcode");
            throw new RuntimeException();
        }
        log.debug("获取企业永久授权码->getPermanentCode->end");
    }

    /**
     * 获取用户信息
     */
    @Override
    public WechatUserInfo getUserInfo(String code) {
        // 	授权登录服务商的网站时，使用应用提供商的provider_access_toke
        String providerSccessToken = (String) CacheData.get(WeChatConstants. PROVIDER_ACCESS_TOKEN);
        // 获取扫码登录链接url
        String getUserInfo = WeChatUtils.THIRD_BUS_WECHAT_GET_LOGIN_INFO;
        // 获取登录用户信息
        String getUserInfoUrl = getUserInfo.replace(WeChatConstants.PROVIDER_ACCESS_TOKEN, providerSccessToken);
        log.debug("getUserInfo->获取登录用户信息Url->"+getUserInfoUrl);
        // 使用http请求调用
        Map<String, Object> mapCode = new HashMap<>();
        mapCode.put("auth_code", code);
        String body = HttpRequest.post(getUserInfoUrl).body(JSONUtil.toJsonStr(mapCode), ContentType.JSON.getValue()).execute().body();
        WechatUserInfo userInfo = null;
        String userId = "";
        try {
            // 获取用户信息
            userInfo = JSONUtil.toBean(body, WechatUserInfo.class);
            log.debug("getUserInfo->获取用户信息转换成bean:"+JSONUtil.toJsonStr(userInfo));
            if(userInfo.getErrcode() == null || userInfo.getErrcode() == 0){
                // 用户id
                userId = userInfo.getUser_info().getUserid();
                userInfo.setUserId(userId);
            } else{
                throw new RuntimeException(userInfo.getErrmsg());
            }
            log.debug("获取访问用户身份成功");
        } catch (Exception e) {
            log.debug("获取访问用户身份失败");
            throw new RuntimeException(userInfo.getErrmsg());
        }
        log.debug("getUserInfo->end->userInfo:"+JSONUtil.toJsonStr(userInfo));
        return userInfo;
    }

    /**
     * 网页授权登录-获取用户信息
     */
    @Override
    public WeChatUserinfo3rd getUserinfo3rd(String code) {
        //授权登录服务商的网站时，第三方应用access_token
        String suiteToken = (String) CacheData.get(WeChatConstants.SUITE_TOKEN);
        // 获取扫码登录链接url
        String getUserinfo3rdUrl = WeChatUtils.THIRD_BUS_WECHAT_GET_USER_INFO;
        // 替换
        getUserinfo3rdUrl = getUserinfo3rdUrl.replace(WeChatConstants.SUITE_TOKEN, suiteToken).replace(WeChatConstants.CODE, code);
        log.debug("获取访问用户身份url:"+getUserinfo3rdUrl);
        // 使用http请求调用
        String body = HttpRequest.get(getUserinfo3rdUrl).execute().body();
        WeChatUserinfo3rd userInfo = null;
        String userId = "";
        try {
            // 取部门列表信息
            userInfo = JSONUtil.toBean(body, WeChatUserinfo3rd.class);
            log.debug("获取访问用户身份userInfo转换成bean:"+JSONUtil.toJsonStr(userInfo));
            if(userInfo.getErrcode() == null || userInfo.getErrcode() == 0){
                // 用户id
                userId = userInfo.getUserId();
                userInfo.setUserId(userId);
            } else{
                throw new RuntimeException(userInfo.getErrmsg());
            }
            String success = String.format("获取访问用户身份成功", userId ,userInfo.getErrcode());
            log.debug(success);
        } catch (Exception e) {
            String error = String.format("获取访问用户身份失败", userInfo.getErrcode() ,userInfo.getErrmsg());
            log.debug(error);
            throw new RuntimeException(userInfo.getErrmsg());
        }
        log.debug("getUserinfo3rd->end:"+JSONUtil.toJsonStr(userInfo));
        return userInfo;
    }
}
