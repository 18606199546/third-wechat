package com.wechat.service.impl;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.support.json.JSONUtils;
import com.wechat.common.StringUtils;
import com.wechat.common.WeChatConstants;
import com.wechat.common.WeChatUtils;
import com.wechat.common.cache.CacheData;
import com.wechat.entity.wechat.WeChatProviderAccessToken;
import com.wechat.entity.wechat.WeChatReturn;
import com.wechat.entity.wechat.WeChatSuiteReturn;
import com.wechat.service.IWeChatThirdTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取token
 */
@Slf4j
@Service
public class WeChatThirdTokenServiceImpl implements IWeChatThirdTokenService {

    /**
     * 获取第三方应用凭证
     */
    @Override
    public void getSuiteToken() {
        // 获取第三方应用凭证url
        String suiteTokenUrl = WeChatUtils.THIRD_BUS_WECHAT_SUITE_TOKEN;
        // 	第三方应用access_token
        String suiteToken = "";
        try {
            Map<String,Object> map = new HashMap<>();
            //以ww或wx开头应用id
            map.put("suite_id", WeChatConstants.SUITE_ID);
            //应用secret
            map.put("suite_secret", WeChatConstants.SUITE_SECRET);
            //企业微信后台推送的ticket
            map.put("suite_ticket", CacheData.get(WeChatConstants.SUITE_TICKET));
            log.debug("getSuiteToken获取第三方应用凭证url入参:"+ JSONUtil.toJsonStr(map));
            String body = HttpRequest.post(suiteTokenUrl).body(JSONUtil.toJsonStr(map), ContentType.JSON.getValue()).execute().body();
            log.debug("getSuiteToken获取第三方应用凭证出参:"+JSONUtil.toJsonStr(body));
            WeChatSuiteReturn weChat = JSONUtil.toBean(body, WeChatSuiteReturn.class);
            log.debug("getSuiteToken获取第三方应用凭证出参转换成bea:"+JSONUtil.toJsonStr(weChat));
            if(weChat.getErrcode() == null || weChat.getErrcode() == 0){
                suiteToken = weChat.getSuite_access_token();
                CacheData.put(WeChatConstants.SUITE_TOKEN, suiteToken);
            }
            // 打印消息
            log.debug("获取suite token成功:"+suiteToken);
        } catch (Exception e) {
            log.debug("获取suite token失败errcode:"+suiteToken);
            throw new RuntimeException();
        }
    }

    /**
     * 服务商的token
     */
    @Override
    public void getProviderToken() {
        // 服务商的secret，在服务商管理后台可见
        String providerSecret = WeChatConstants.PROVIDER_SECRET;
        // 服务商的corpid
        String corpId = WeChatConstants.CORP_ID;
        // 获取服务商的tokenurl
        String providerTokenUrl = WeChatUtils.THIRD_BUS_WECHAT_GET_PROVIDER_TOKEN;
        String providerAccessToken = null;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("corpid", corpId);
            map.put("provider_secret", providerSecret);
            log.debug("getProviderToken入参:"+ JSONUtils.toJSONString(map));
            String body = HttpRequest.post(providerTokenUrl).body(JSONUtil.toJsonStr(map), ContentType.JSON.getValue()).execute().body();
            log.debug("getProviderToken出参"+body);
            WeChatProviderAccessToken weChat = JSONUtil.toBean(body, WeChatProviderAccessToken.class);
            if(weChat.getErrcode() == null || weChat.getErrcode() == 0){
                providerAccessToken = weChat.getProvider_access_token();
                CacheData.put("PROVIDER_ACCESS_TOKEN",providerAccessToken);
            }
            // 打印消息
            log.debug("获取providerAccessTokenn成功:"+ providerAccessToken);
        } catch (Exception e) {
            log.error("获取providerAccessToken失败:"+ providerAccessToken);
            throw new RuntimeException();
        }
    }

    /**
     * 如果企业凭证到期后
     * 根据授权方corpid，企业永久码获取获取企业凭证
     */
    @Override
    public void getCorpToken() {
        log.debug("获取企业凭证getCorpToken==========start============");
        //永久码
        String permanentCode =  (String)CacheData.get(WeChatConstants.PERMANENT_CODE);
        //第三方应用access_token
        String suiteAccessToken = (String) CacheData.get(WeChatConstants.SUITE_TOKEN);
        //应用企业corpid
        String authCorpId = (String)CacheData.get(WeChatConstants.AUTH_CORPID);
        //获取企业凭证
        String corpTokenUrl = WeChatUtils.THIRD_BUS_WECHAT_GET_CORP_TOKEN;
        corpTokenUrl = corpTokenUrl.replace("SUITE_ACCESS_TOKEN", suiteAccessToken);
        //授权方（企业）access_token
        String accessToken = null;
        try {
            Map<String, Object> map = new HashMap<>();
            //授权方corpid
            map.put("auth_corpid", authCorpId);
            //永久授权码
            map.put("permanent_code", permanentCode);
            log.debug("获取企业凭证 getCorpToken 入参："+suiteAccessToken+"==map："+JSONUtils.toJSONString(map));
            String body = HttpRequest.post(corpTokenUrl).body(JSONUtil.toJsonStr(map), ContentType.JSON.getValue()).execute().body();
            WeChatReturn weChat = JSONUtil.toBean(body, WeChatReturn.class);
            log.debug("获取企业凭证 getCorpToken 出参转换成bean=="+JSONUtil.toJsonStr(weChat));
            accessToken = weChat.getAccess_token();
            CacheData.put(WeChatConstants.ACCESS_TOKEN,accessToken);
            CacheData.put(WeChatConstants.AUTH_CORPID,authCorpId);
            //打印消息
            log.debug("获取accessToken成功:" + accessToken);
        } catch (Exception e) {
            log.debug("获取paccessToken失败:" + accessToken);
            throw new RuntimeException();
        }
        log.debug("获取企业凭证getCorpToken==========end============");
    }
}
