package com.shuyu.spring.template.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    public static Object ok() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("errCode", 0);
        obj.put("errMsg", "成功");
        return obj;
    }

    public static Object ok(Object data) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("errCode", 0);
        obj.put("errMsg", "成功");
        obj.put("data", data);
        return obj;
    }

    public static Object ok(String errMsg, Object data) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("errCode", 0);
        obj.put("errMsg", errMsg);
        obj.put("data", data);
        return obj;
    }

    public static Object fail() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("errCode", -1);
        obj.put("errMsg", "错误");
        return obj;
    }

    public static Object fail(int errCode, String errMsg) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("errCode", errCode);
        obj.put("errMsg", errMsg);
        return obj;
    }

    public static Object badArgument() {
        return fail(401, "参数不对");
    }


    public static Object badArgument(String error) {
        return fail(401, error);
    }

    public static Object badArgumentValue() {
        return fail(402, "参数值不对");
    }

    public static Object unlogin() {
        return fail(501, "请登录");
    }

    public static Object serious() {
        return fail(502, "系统内部错误");
    }

    public static Object unsupport() {
        return fail(503, "业务不支持");
    }

    public static Object updatedDateExpired() {
        return fail(504, "更新数据已经失效");
    }

    public static Object updatedDataFailed() {
        return fail(505, "更新数据失败");
    }
}