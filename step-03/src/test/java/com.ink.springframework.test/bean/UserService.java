package com.ink.springframework.test.bean;

/**
 * @author MT
 * @date 2021/8/3 11:10
 */
public class UserService {
    private final String name;

    public UserService(String name) {
        this.name = name;
    }

    public void getUserInfo() {
        System.out.println("查询用户信息:" + name);
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }
}
