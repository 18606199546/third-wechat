package com.wechat.entity.wechat;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取访问用户身份返回值
 *
 * @author: wx
 * @date: 2021/12/31
 */
@NoArgsConstructor
@Data
public class WechatUserInfo {
    private Integer errcode;
    private String errmsg;
    private String userId;
    private UserInfo user_info;
    private CorpInfo corp_info;
    private Integer status;

    public static class UserInfo{
        private String userid;
        private String open_userid;
        private String name;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getOpen_userid() {
            return open_userid;
        }

        public void setOpen_userid(String open_userid) {
            this.open_userid = open_userid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class CorpInfo{
        private String corpid;

        public String getCorpid() {
            return corpid;
        }

        public void setCorpid(String corpid) {
            this.corpid = corpid;
        }
    }
}
