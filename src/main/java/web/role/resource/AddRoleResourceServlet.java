package web.role.resource;

import constant.PageUrlConstant;
import constant.RequestConstant;
import service.impl.role.RoleServiceImpl;
import service.impl.role.resource.RoleResourceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>URM</h3>
 * <p>角色授权</p>
 * 根据选中的资源ID授予
 * @author : 李雷
 * @date : 2020-11-30 21:23
 **/
@WebServlet("/AddRoleResourceServlet")
public class AddRoleResourceServlet extends HttpServlet {
    RoleServiceImpl roleService = new RoleServiceImpl();
    RoleResourceServiceImpl roleResourceService = new RoleResourceServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roleId = request.getParameter("id");
        String[] btnResourceId = request.getParameterValues("btnResourceId");
        int addRoleResourceSize = 0;
        if (btnResourceId != null) {
            for (String s : btnResourceId) {
                if (!roleResourceService.insertRoleResource(Long.parseLong(s),Long.parseLong(roleId)).equals(0)) {
                    ++addRoleResourceSize;
                }
            }
        }
        request.getSession().setAttribute("roleId",null);
        request.setAttribute(RequestConstant.MESSAGE,"成功赋予"+roleService.selectNameById(Long.parseLong(roleId))+addRoleResourceSize+"个权限");
        request.setAttribute(RequestConstant.ROLE_CONTROL_PAGE,true);
        request.getRequestDispatcher(PageUrlConstant.CONTROL_PAGE).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
