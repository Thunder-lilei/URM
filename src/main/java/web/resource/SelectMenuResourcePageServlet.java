package web.resource;

import constant.PageUrlConstant;
import constant.RequestConstant;
import po.Resource;
import po.Role;
import serviceImpl.resource.ResourceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <h3>URM</h3>
 * <p>资源信息分页查询</p>
 * 获取当前页码及每页最大数
 * 保存当前页码 返回资源列表
 * 设置最小最大页码
 * @author : 李雷
 * @date : 2020-12-04 13:38
 **/
@WebServlet("/SelectMenuResourcePageServlet")
public class SelectMenuResourcePageServlet extends HttpServlet {
    ResourceServiceImpl resourceService = new ResourceServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String pageNow = request.getParameter("pageNow");
        int pageNowInteger = Integer.parseInt(pageNow);
        String pageSize = request.getParameter("pageSize");
        Integer pageSizeInteger = Integer.parseInt(pageSize);
        //最小最大页码
        int minPageSize = 1;
        int maxPageSize = (resourceService.countMenuResource()+pageSizeInteger-1)/pageSizeInteger;

        if (pageNowInteger < minPageSize) {pageNowInteger = minPageSize;}
        if (pageNowInteger > maxPageSize) {pageNowInteger = maxPageSize;}
        List<Resource> resourceList = resourceService.selectMenuResourceByPage(pageNowInteger,pageSizeInteger);
        request.getSession().setAttribute("resourcePage",resourceList);
        request.getSession().setAttribute(RequestConstant.PAGE_NOW,pageNowInteger);
        request.setAttribute(RequestConstant.RESOURCE_CONTROL_PAGE,true);
        request.getRequestDispatcher(PageUrlConstant.CONTROL_PAGE).forward(request,response);
    }
}
