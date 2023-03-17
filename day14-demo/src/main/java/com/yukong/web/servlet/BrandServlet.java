package com.yukong.web.servlet;

import com.alibaba.fastjson.JSON;
import com.yukong.pojo.Brand;
import com.yukong.pojo.PageBean;
import com.yukong.service.BrandService;
import com.yukong.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService = new BrandServiceImpl();

    /**
     * 查询所有
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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

    /**
     * 添加数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 处理post请求乱码
        request.setCharacterEncoding("utf-8");  // 没有用到

        // 1.接收数据   [1,2,3]
        BufferedReader br = request.getReader();
        String params = br.readLine();  // 无论页面提交了多少数据，都是只有一行。 数据格式为JSON
        // 转换成int[] 数组
        int[] ids = JSON.parseObject(params, int[].class);

        // 2.调用方法
        brandService.deleteByIds(ids);
        // 3.响应成功标识
        response.getWriter().write("success");
    }

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 处理post请求乱码

        // 接收 当前页码 和 每页展示条数 使用get方式 url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 1.调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);
        // 2.转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        // 3.响应数据
        // 设置浏览器解析格式和编码
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 分页条件查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 处理post请求乱码
        request.setCharacterEncoding("utf-8");

        // 接收 当前页码 和 每页展示条数 使用get方式 url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();  // 无论页面提交了多少数据，都是只有一行。 数据格式为JSON
        // 转换成Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        // 1.调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize,brand);
        // 2.转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        // 3.响应数据
        // 设置浏览器解析格式和编码
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 根据id删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 处理post请求乱码
        request.setCharacterEncoding("utf-8");  // 没有用到

        // 1.接收数据   id
        BufferedReader br = request.getReader();
        String params = br.readLine();  // 无论页面提交了多少数据，都是只有一行。 数据格式为JSON
        // 转换成int 数据
        int id = JSON.parseObject(params, int.class);

        // 2.调用方法
        brandService.deleteById(id);
        // 3.响应成功标识
        response.getWriter().write("success");
    }

    /**
     * 根据id查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 处理post请求乱码
        request.setCharacterEncoding("utf-8");  // 没有用到

        // 1.接收数据   id
        BufferedReader br = request.getReader();
        String params = br.readLine();  // 无论页面提交了多少数据，都是只有一行。 数据格式为JSON
        // 转换成int 数据
        int id = JSON.parseObject(params, int.class);

        // 2.调用方法
        Brand brand = brandService.selectById(id);

        // 2.转为JSON
        String jsonString = JSON.toJSONString(brand);
        // 3.响应数据
        // 设置浏览器解析格式和编码
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 处理post请求乱码
        request.setCharacterEncoding("utf-8");

        // 1.接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();  // 无论页面提交了多少数据，都是只有一行。 数据格式为JSON
        // 转换成Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        // 2.调用方法
        brandService.update(brand);
        // 3.响应成功标识
        response.getWriter().write("success");
    }
}
