package web.resource;

import constant.PageUrlConstant;
import constant.RequestConstant;
import po.Resource;
import serviceImpl.resource.ResourceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>URM</h3>
 * <p>增加菜单资源</p>
 * 获取菜单信息参数
 * 返回添加提示信息message
 * @author : 李雷
 * @date : 2020-12-03 18:55
 **/
@WebServlet("/AddResourceServlet")
public class AddResourceServlet extends HttpServlet {
    ResourceServiceImpl resourceService = new ResourceServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Resource resource = new Resource();
        String menuResourceIdString = request.getParameter("menuResourceId");
        Integer menuResourceId = Integer.parseInt(menuResourceIdString);
        //接收resource参数
        resource.setMenuResourceId(menuResourceId);
        resource.setResourceName(request.getParameter("resourceName"));
        resource.setResourceType(request.getParameter("resourceType"));
        resource.setControlType(request.getParameter("controlType"));

        request.setAttribute(RequestConstant.MESSAGE,"添加失败！");
        if (!resourceService.addResource(resource).equals(0)) {
            request.setAttribute(RequestConstant.MESSAGE,"成功添加菜单:"+resource.getResourceName());
        }
        request.setAttribute(RequestConstant.RESOURCE_CONTROL_PAGE,true);
        request.getRequestDispatcher(PageUrlConstant.CONTROL_PAGE).forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
