package com.yukong.service;

import com.yukong.pojo.User;

public interface UserService {
    /**
     * 根据账号和密码查询
     * @param username
     * @param password
     * @return
     */
    User select(String username,String password);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User selectUser(String username);

    /**
     * 添加用户
     * @param user
     */
    void add(User user);
}
