package com.wechat.entity.wechat;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取访问用户身份
 * 网页登录
 *
 * @author: wx
 * @date: 2022/2/23
 */
@NoArgsConstructor
@Data
public class WeChatUserinfo3rd {
    private Integer errcode;
    private String errmsg;
    private String CorpId;
    private String UserId;
    private String DeviceId;
    private String user_ticket;
    private String open_userid;
    private Integer status;
}
