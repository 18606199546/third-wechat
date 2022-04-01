package com.wechat.entity.wechat;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 获取企业永久授权码
 *
 * @author: wx
 * @date: 2022/2/17
 */
@NoArgsConstructor
@Data
public class WeChatPermanentCodeReturn {
    private Integer errcode;
    private String errmsg;
    private String access_token;
    private Long expires_in;
    private String permanent_code;

    private AuthCorpInfo auth_corp_info;

    public static class AuthCorpInfo{
        private String corpid;
        private String corp_name;

        public String getCorpid() {
            return corpid;
        }

        public void setCorpid(String corpid) {
            this.corpid = corpid;
        }

        public String getCorp_name() {
            return corp_name;
        }

        public void setCorp_name(String corp_name) {
            this.corp_name = corp_name;
        }
    }

    private AuthInfo auth_info;

    public static class AuthInfo{
        private List<Agent> agent;

        public List<Agent> getAgent() {
            return agent;
        }

        public void setAgent(List<Agent> agent) {
            this.agent = agent;
        }

        public static class Agent{
            private Long agentid;
            private String name;

            public Long getAgentid() {
                return agentid;
            }

            public void setAgentid(Long agentid) {
                this.agentid = agentid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    private AuthUserInfo auth_user_info;

    public static class AuthUserInfo{
        private String userid;
        private String name;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public String getPermanent_code() {
        return permanent_code;
    }

    public void setPermanent_code(String permanent_code) {
        this.permanent_code = permanent_code;
    }

    public AuthCorpInfo getAuth_corp_info() {
        return auth_corp_info;
    }

    public void setAuth_corp_info(AuthCorpInfo auth_corp_info) {
        this.auth_corp_info = auth_corp_info;
    }

    public AuthInfo getAuth_info() {
        return auth_info;
    }

    public void setAuth_info(AuthInfo auth_info) {
        this.auth_info = auth_info;
    }

    public AuthUserInfo getAuth_user_info() {
        return auth_user_info;
    }

    public void setAuth_user_info(AuthUserInfo auth_user_info) {
        this.auth_user_info = auth_user_info;
    }

    @Override
    public String toString() {
        return "WeChatPermanentCodeReturn{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                ", permanent_code='" + permanent_code + '\'' +
                ", auth_corp_info=" + auth_corp_info +
                ", auth_info=" + auth_info +
                ", auth_user_info=" + auth_user_info +
                '}';
    }
}

