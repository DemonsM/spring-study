package com.ink.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MT
 * @date 2021/8/4 15:14
 */
public class UserDao {
    private static final Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "张三");
        hashMap.put("10002", "李四");
        hashMap.put("10003", "王五");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
