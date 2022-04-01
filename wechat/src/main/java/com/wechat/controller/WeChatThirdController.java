package com.wechat.controller;

import com.wechat.entity.wechat.WeChatLoginUrl;
import com.wechat.entity.wechat.WeChatUserinfo3rd;
import com.wechat.entity.wechat.WechatUserInfo;
import com.wechat.service.IWeChatService;
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
@RequestMapping(value = "wechat")
public class WeChatThirdController {

    @Autowired
    private IWeChatService weChatService;


    /**
     * 获取扫码登录地址
     */
    @ApiOperation(value = "获取扫码登录地址")
    @PostMapping(value = "login")
    public WeChatLoginUrl login(){
        //获取扫码登录地址
        return weChatService.thirdLoginUrl();
    }

    /**
     * 企业微信内登录地址
     */
    @ApiOperation(value = "企业微信内登录")
    @PostMapping(value = "wechatLogin")
    public WeChatLoginUrl wechatLogin(){
        //获取企业微信内登录地址
        return weChatService.wechatLoginUrl();
    }

    /**
     * 前端回调->扫码授权登录-获取访问用户身份
     */
    @ApiOperation(value = "获取访问用户身份")
    @PostMapping(value = "getUserInfo")
    public WechatUserInfo getUserInfo(String code){
        //获取访问用户身份
        return weChatService.getUserInfo(code);
    }

    /**
     * 前端回调->网页授权登录-获取访问用户身份
     */
    @ApiOperation(value = "获取访问用户身份")
    @PostMapping(value = "getUserinfo3rd")
    public WeChatUserinfo3rd getUserinfo3rd(String code){
        //获取访问用户身份
        return weChatService.getUserinfo3rd(code);
    }
}
