package com.wechat.config;

import com.wechat.service.IWeChatThirdTokenService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定义切面
 */
@Slf4j
@Aspect
@Component
public class WeChatAop {
    @Autowired
    private IWeChatThirdTokenService weChatThirdTokenService;

    /**
     * 定义切入点，切入点为com.wechat.service.impl.WeChatServiceImpl所有方法
     */
    @Pointcut("execution(public * com.wechat.service.impl.WeChatServiceImpl..*.*(..))")
    public void method(){}

    /**
     * 前置通知：在连接点之前执行的通知
     */
    @Before(value = "method()")
    public void getToken(){
        weChatThirdTokenService.getSuiteToken();
        weChatThirdTokenService.getProviderToken();
    }

    /**
     * 处理完后返回内存
     */
    @AfterReturning(returning = "ret",pointcut = "method()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.debug("RESPONSE:" + ret);
    }
}
