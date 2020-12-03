package web.resource;

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
 *
 * @author : 李雷
 * @date : 2020-12-03 18:55
 **/
@WebServlet("/AddResourceServlet")
public class AddResourceServlet extends HttpServlet {
    ResourceServiceImpl resourceService = new ResourceServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Resource resource = new Resource();
        String menuResourceName = request.getParameter("menuResourceName");
        Integer menuResourceId = 0,resourceId = null;
        if (!menuResourceName.equals("")) {
            resourceId = resourceService.selectResourceIdByName(menuResourceName);
            if (resourceId.equals(0)) {
                request.setAttribute("message","请检查父级菜单名称！");
                request.setAttribute("ResourceControlPage",true);
                request.getRequestDispatcher("pages/control.jsp").forward(request,response);
            }
                menuResourceId = resourceId;
        }
        resource.setResourceName(request.getParameter("resourceName"));
        resource.setResourceType(request.getParameter("resourceType"));
        resource.setMenuResourceId(menuResourceId);
        request.setAttribute("message","添加失败！");
        assert resourceId != null;
        if (!resourceId.equals(0) && !resourceService.addResource(resource).equals(0)) {
            request.setAttribute("message","成功添加菜单:"+resource.getResourceName());
        }
        request.setAttribute("ResourceControlPage",true);
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
