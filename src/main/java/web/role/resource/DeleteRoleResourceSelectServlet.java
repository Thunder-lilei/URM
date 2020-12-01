package web.role.resource;

import serviceImpl.role.RoleServiceImpl;
import serviceImpl.role.resource.RoleResourceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>URM</h3>
 * <p>赋予角色权限角色查询</p>
 * 获取角色id
 * @author : 李雷
 * @date : 2020-12-01 10:49
 **/
@WebServlet("/DeleteRoleResourceSelectServlet")
public class DeleteRoleResourceSelectServlet extends HttpServlet {
    RoleServiceImpl roleService = new RoleServiceImpl();
    RoleResourceServiceImpl roleResourceService = new RoleResourceServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        request.getSession().setAttribute("deleteRoleResourceId",request.getParameter("roleId"));
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
