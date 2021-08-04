package com.ink.springframework.test.bean;

/**
 * @author MT
 * @date 2021/8/3 11:10
 */
public class UserService {
    private String uId;
    private UserDao userDao;

    public void getUserInfo() {
        System.out.println("查询用户信息:" + userDao.queryUserName(uId));
    }

    //public String getuId() {
    //    return uId;
    //}
    //
    //public void setuId(String uId) {
    //    this.uId = uId;
    //}
    //
    //public UserDao getUserDao() {
    //    return userDao;
    //}
    //
    //public void setUserDao(UserDao userDao) {
    //    this.userDao = userDao;
    //}
}
