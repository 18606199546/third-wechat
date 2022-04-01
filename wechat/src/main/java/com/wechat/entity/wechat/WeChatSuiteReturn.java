package com.wechat.entity.wechat;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取第三方应用凭证
 *
 * @author: wx
 * @date: 2022/2/17
 */
@NoArgsConstructor
@Data
public class WeChatSuiteReturn {
    private Integer errcode;
    private String errmsg;
    private String suite_access_token;
    private Long expires_in;
}
