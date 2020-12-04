package web.resource;

import constant.PageUrlConstant;
import constant.RequestConstant;
import serviceImpl.resource.ResourceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>URM</h3>
 * <p>${description}</p>
 *
 * @author : 李雷
 * @date : 2020-12-04 11:19
 **/
@WebServlet("/DeleteResourceServlet")
public class DeleteResourceServlet extends HttpServlet {
    ResourceServiceImpl resourceService = new ResourceServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        if (resourceService.deleteResource(Integer.parseInt(id)).equals(0)) {
            request.setAttribute(RequestConstant.MESSAGE,"删除失败！");
        }
        request.setAttribute(RequestConstant.MESSAGE,"删除成功！");
        request.setAttribute(RequestConstant.RESOURCE_CONTROL_PAGE,true);
        request.getRequestDispatcher(PageUrlConstant.CONTROL_PAGE).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
