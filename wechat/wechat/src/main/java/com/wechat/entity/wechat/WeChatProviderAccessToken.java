package com.wechat.entity.wechat;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务商的access_toke
 *
 * @author: wx
 * @date: 2022/2/21
 */
@NoArgsConstructor
@Data
public class WeChatProviderAccessToken {
    private Integer errcode;
    private String errmsg;
    private String provider_access_token;
    private Long expires_in;
}
