package com.yukong.web.servlet;

import com.alibaba.fastjson.JSON;
import com.yukong.pojo.Brand;
import com.yukong.pojo.User;
import com.yukong.service.UserService;
import com.yukong.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * 登录查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // 处理post请求乱码
        request.setCharacterEncoding("utf-8");

        // 接收记住密码复选框数据
        String checked = request.getParameter("checked");

        // 1.接收账号和密码数据
        BufferedReader br = request.getReader();
        String params = br.readLine();  // 无论页面提交了多少数据，都是只有一行。 数据格式为JSON
        // 转换成User对象
        User user = JSON.parseObject(params, User.class);
        // 获取账号和密码
        String username = user.getUsername();
        String password = user.getPassword();

        // 2.调用方法，查询是否存在该用户
        User u = userService.select(username, password);

        // 3.判断
        if(u != null){
            // 存在，登录成功

            // 判断用户是否勾选记住密码
            if ("true".equals(checked)){
                // 勾选了，发送cookie

                /**
                 * 这三个设置先放这
                 */
                // 需要设置前端请求的url。不支持*
                response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
                // 设置允许跨域的方法
                response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
                // 之后需要设置允许cookie跨域
                response.setHeader("Access-Control-Allow-Credentials", "true");

                // 创建Cookie对象
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);
                // 设置cookie存活时间
                c_username.setMaxAge(7 * 24 * 60 *60);
                c_password.setMaxAge(7 * 24 * 60 * 60);
                // 向客户端发送cookie（并不是响应数据哦）
                response.addCookie(c_username);
                response.addCookie(c_password);


            }

            // 将登录成功后的user对象，存储到session  使用session是因为有安全要求
            HttpSession session = request.getSession();
            session.setAttribute("user",user);

            response.getWriter().write("success");
        }
        // 不存在不用做处理
    }

    /**
     * 根据用户名查询用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // 1.获取账号 get请求
        String username = request.getParameter("username");

        // 2.调用方法
        User u = userService.selectUser(username);

        if (u == null ){
            // 说明可以注册
            // 设置响应数据
            response.getWriter().write("success");
        }

    }

    /**
     * 添加用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // 处理post请求乱码
        request.setCharacterEncoding("utf-8");

        // 1.接收账号和密码数据
        BufferedReader br = request.getReader();
        String params = br.readLine();  // 无论页面提交了多少数据，都是只有一行。 数据格式为JSON
        // 2.转换成User对象
        User user = JSON.parseObject(params, User.class);

        // 3.调用方法
        userService.add(user);
        // 设置响应数据
        response.getWriter().write("success");

    }

    public void rememberCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // 处理post请求乱码
        request.setCharacterEncoding("utf-8");

        Cookie[] cookies = request.getCookies();

        /*for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            System.out.println("key: "+cookie.getName() + " value: " + cookie.getValue());
        }*/

        // 转成JSON字符串
        String jsonString = JSON.toJSONString(cookies);

        // 3.响应数据
        // 设置浏览器解析格式和编码
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }
}
