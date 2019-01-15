/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shuyu.spring.template.utils;

import com.shuyu.spring.template.config.SpringContextHolder;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.List;

/**
 * 缓存工具类
 */
public class CacheUtil {

    private static final Object LOCKER = new Object();

    public static void put(String cacheName, Object key, Object value) {
        getOrAddCache(cacheName).put(new Element(key, value));
    }

    /**
     * @param timeToIdleSeconds 缓存自创建日期起至失效时的间隔时间
     * @param timeToLiveSeconds 缓存创建以后，最后一次访问缓存的日期至失效之时的时间间隔
     */
    public static void put(String cacheName, Object key, Object value, int timeToIdleSeconds, int timeToLiveSeconds) {
        getOrAddCache(cacheName).put(new Element(key, value, timeToIdleSeconds, timeToLiveSeconds));
    }

    @SuppressWarnings("all")
    public static <T> T get(String cacheName, Object key) {
        Element element = getOrAddCache(cacheName).get(key);
        if (element == null) {
            return null;
        } else {
            Object objectValue = element.getObjectValue();
            return (T) objectValue;
        }
    }

    public static List getKeys(String cacheName) {
        return getOrAddCache(cacheName).getKeys();
    }

    public static void remove(String cacheName, Object key) {
        getOrAddCache(cacheName).remove(key);
    }

    public static void removeAll(String cacheName) {
        getOrAddCache(cacheName).removeAll();
    }

    private static Cache getOrAddCache(String cacheName) {
        CacheManager cacheManager = SpringContextHolder.getBean(CacheManager.class);
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            synchronized (LOCKER) {
                cache = cacheManager.getCache(cacheName);
                if (cache == null) {
                    cacheManager.addCacheIfAbsent(cacheName);
                    cache = cacheManager.getCache(cacheName);
                }
            }
        }
        return cache;
    }
}


