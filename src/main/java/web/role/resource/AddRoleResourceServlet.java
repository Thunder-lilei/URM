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
 * <p>${description}</p>
 *
 * @author : 李雷
 * @date : 2020-11-30 21:23
 **/
@WebServlet("/AddRoleResourceServlet")
public class AddRoleResourceServlet extends HttpServlet {
    RoleServiceImpl roleService = new RoleServiceImpl();
    RoleResourceServiceImpl roleResourceService = new RoleResourceServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String roleId = request.getParameter("id");
        String[] btnResourceId = request.getParameterValues("btnResourceId");
        Integer addRoleResourceSize = 0;
        if (btnResourceId != null) {
            for (String s : btnResourceId) {
                if (!roleResourceService.insertRoleResource(Integer.parseInt(s),Integer.parseInt(roleId)).equals(0)) {
                    ++addRoleResourceSize;
                }
            }
        }
        request.getSession().setAttribute("roleId",null);
        request.setAttribute("message","成功赋予"+roleService.selectNameById(Integer.parseInt(roleId))+addRoleResourceSize+"个权限");
        request.setAttribute("RoleControlPage",true);
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
