package com.yukong.service.impl;

import com.yukong.mapper.UserMapper;
import com.yukong.pojo.User;
import com.yukong.service.UserService;
import com.yukong.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserServiceImpl implements UserService {
    // 1.创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public User select(String username, String password) {
        // 2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3.获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 4.调用方法
        User user = mapper.select(username, password);
        // 5.关闭资源
        sqlSession.close();
        return user;
    }

    @Override
    public User selectUser(String username) {
        // 2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3.获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 4.调用方法
        User user = mapper.selectUser(username);
        // 5.关闭资源
        sqlSession.close();
        return user;
    }

    @Override
    public void add(User user) {
        // 2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 3.获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 4.调用方法
        mapper.add(user);
        // 提交事务
        sqlSession.commit();
        // 5.关闭资源
        sqlSession.close();
    }
}
