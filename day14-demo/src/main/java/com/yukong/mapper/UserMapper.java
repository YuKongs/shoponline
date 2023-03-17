package com.yukong.mapper;

import com.yukong.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * 根据账号和密码查找用户
     * @param username
     * @param password
     * @return
     */
    User select(@Param("username") String username, @Param("password") String password);

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
