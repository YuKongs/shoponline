package com.yukong.web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet("/baseServlet")
public class BaseServlet extends HttpServlet {

    // 根据请求路径最后一段来进行分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取请求路径
        String uri = req.getRequestURI();  // /shop/brand/selectAll
        // 2.获取最后一段路径，方法名 selectAll
        int index = uri.lastIndexOf('/');  // 获取传入字符的最后一个下标  11
        String methodName = uri.substring(index + 1);  // 从下标开始往后截取，包前不包后

        // 3.执行方法
        // 3.1 获取字节码对象
        Class<? extends BaseServlet> cls = this.getClass();// 谁调用我(this 所在的方法)，我(this)代表谁
        // 3.2 获取Method对象
        try {
            Method method = cls.getMethod(methodName,HttpServletRequest.class, HttpServletResponse.class);
            // 3.3 执行方法
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
