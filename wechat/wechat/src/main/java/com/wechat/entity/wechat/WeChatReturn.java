package com.wechat.entity.wechat;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 企业微信token返回值
 *
 * @author: wx
 * @date: 2021/12/13
 */
@NoArgsConstructor
@Data
public class WeChatReturn {
    private Integer errcode;
    private String errmsg;
    private String access_token;
    private Long expires_in;
}
