package com.wechat.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.wechat.common.StringUtils;
import com.wechat.common.WeChatConstants;
import com.wechat.common.WxUtil;
import com.wechat.common.cache.CacheData;
import com.wechat.entity.aes.AesException;
import com.wechat.entity.aes.WXBizMsgCrypt;
import com.wechat.service.IConfigService;
import com.wechat.service.IWeChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * 回调service
 */
@Slf4j
@Service
public class ConfigServiceImpl implements IConfigService {

    @Autowired
    private IWeChatService weChatService;

    /**
     * 验证通用开发参数及应用回调
     * @returns: java.lang.String
     */
    @Override
    public String doGetCallback(String msgSignature, String timestamp, String nonce, String echoStr) {
        //需要返回的明文
        String sEchoStr="";
        try {
            log.debug(WeChatConstants.TOKENS, WeChatConstants.ENCODING_AES_KEY, WeChatConstants.CORP_ID);
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(WeChatConstants.TOKENS, WeChatConstants.ENCODING_AES_KEY, WeChatConstants.CORP_ID);
            sEchoStr = wxcpt.VerifyURL(msgSignature, timestamp, nonce, echoStr);
        } catch (AesException e) {
            e.printStackTrace();
        }
        return sEchoStr;
    }

    /**
     * 获取SuiteTicket，AuthCode
     * @param: msgSignature 微信加密签名
     * @param: timestamp 时间戳
     * @param: nonce  随机数
     * @param: type 类型
     * @param: corpId 企业id
     * @param: in
     * @returns: java.lang.String
     */
    @Override
    public String doPostCallback(String msgSignature, String timestamp, String nonce, String type, String corpId, ServletInputStream in) {
        String id = "";
        // 访问应用和企业回调传不同的ID
        if(!StringUtils.isNull(type) && type.equals("data")){
            id = corpId;
            log.debug("======corpId==="+id);
        } else {
            id = WeChatConstants.SUITE_ID;
            log.debug("======SuiteId===" + id);
        }
        try {
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(WeChatConstants.TOKENS, WeChatConstants.ENCODING_AES_KEY, id);
            String postData="";   // 密文，对应POST请求的数据
            //1.获取加密的请求消息：使用输入流获得加密请求消息postData
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String tempStr = "";   //作为输出字符串的临时串，用于判断是否读取完毕
            while(null != (tempStr=reader.readLine())){
                postData+=tempStr;
            }
            log.debug("====msg_signature===="+msgSignature+"====timestamp==="+timestamp+"====nonce==="+nonce+"====postData==="+postData);
            String suiteXml = wxcpt.DecryptMsg(msgSignature, timestamp, nonce, postData);
            log.debug("suiteXml: " + suiteXml);
            Map suiteMap = WxUtil.parseXml(suiteXml);
            log.debug("==suiteMap=="+ JSONUtils.toJSONString(suiteMap));
            if(suiteMap.get("SuiteTicket") != null) {
                String suiteTicket = (String) suiteMap.get("SuiteTicket");
                CacheData.put(WeChatConstants.SUITE_TICKET, suiteTicket);
                log.debug("====SuiteTicket=====" + suiteTicket);
            } else if(suiteMap.get("AuthCode") != null){
                String authCode = (String) suiteMap.get("AuthCode");
                log.debug("doPostValid->AuthCode:" + authCode);
                //根据authcode获取企业永久授权码
                weChatService.getPermanentCode(authCode);
                CacheData.put(WeChatConstants.AUTH_CODE, authCode);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}
