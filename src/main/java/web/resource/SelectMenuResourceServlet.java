package web.resource;

import constant.PageUrlConstant;
import constant.RequestConstant;
import po.Resource;
import po.User;
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
 * <p>查询资源</p>
 * 按照关键字进行查询
 * @author : 李雷
 * @date : 2020-12-04 12:02
 **/
@WebServlet("/SelectMenuResourceServlet")
public class SelectMenuResourceServlet extends HttpServlet {
    ResourceServiceImpl resourceService = new ResourceServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Resource> resourceList = resourceService.selectMenuResourceByKeyWord(request.getParameter("keyWord"));
        if (resourceList!=null) {
            request.getSession().setAttribute("selectResourceList",resourceList);
        }else {
            request.setAttribute(RequestConstant.MESSAGE,"请检查关键字!");
        }
        request.setAttribute(RequestConstant.RESOURCE_CONTROL_PAGE,true);
        request.getRequestDispatcher(PageUrlConstant.CONTROL_PAGE).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
