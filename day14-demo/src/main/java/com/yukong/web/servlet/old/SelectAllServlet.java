package com.yukong.web.servlet.old;

import com.alibaba.fastjson.JSON;
import com.yukong.pojo.Brand;
import com.yukong.service.BrandService;
import com.yukong.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处理post请求乱码

        // 1.调用Service查询
        List<Brand> brands = brandService.selectAll();
        // 2.转为JSON
        String jsonString = JSON.toJSONString(brands);
        // 3.响应数据
        // 设置浏览器解析格式和编码
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
