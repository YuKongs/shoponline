package com.yukong.web.servlet.old;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yukong.pojo.Brand;
import com.yukong.service.BrandService;
import com.yukong.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处理post请求乱码
        request.setCharacterEncoding("utf-8");

        // 1.接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();  // 无论页面提交了多少数据，都是只有一行。 数据格式为JSON
        // 转换成Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        // 2.调用方法
        brandService.add(brand);
        // 3.响应成功标识
        response.getWriter().write("success");




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
