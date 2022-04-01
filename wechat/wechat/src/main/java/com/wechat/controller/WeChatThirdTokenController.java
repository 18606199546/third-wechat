package com.wechat.controller;

import com.wechat.service.IWeChatThirdTokenService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第三方应用操作
 *
 * @author: wx
 * @date: 2022/4/1
 */
@Slf4j
@RestController
@RequestMapping(value = "wechatToken")
public class WeChatThirdTokenController {

    @Autowired
    private IWeChatThirdTokenService weChatThirdTokenService;


    /**
     * 获取扫码登录地址
     */
    @ApiOperation(value = "获取扫码登录地址")
    @PostMapping(value = "getSuiteToken")
    public void getSuiteToken(){
        //获取扫码登录地址
        weChatThirdTokenService.getSuiteToken();
    }

    /**
     * 企业微信内登录地址
     */
    @ApiOperation(value = "企业微信内登录")
    @PostMapping(value = "getProviderToken")
    public void getProviderToken(){
        //获取企业微信内登录地址
        weChatThirdTokenService.getProviderToken();
    }

    /**
     * 前端回调->获取访问用户身份
     */
    @ApiOperation(value = "获取访问用户身份")
    @PostMapping(value = "getCorpToken")
    public void getCorpToken(){
        weChatThirdTokenService.getCorpToken();
    }
}
