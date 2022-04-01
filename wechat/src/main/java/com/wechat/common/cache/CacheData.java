package com.wechat.common.cache;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import com.wechat.common.WeChatConstants;

/**
 * 缓存
 */
public class CacheData {
    public static Cache<String, Object> wechat_token_cache = CacheUtil.newTimedCache(WeChatConstants.EXPIRES_IN);

    public static void put(String key, Object obj) {
        wechat_token_cache.put(key, obj);
    }

    public static Object get(String key) {
        return wechat_token_cache.get(key);
    }
}
