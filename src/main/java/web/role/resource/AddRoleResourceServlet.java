package web.role.resource;

import serviceImpl.resource.ResourceServiceImpl;
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
 * <p>赋予角色权限</p>
 * 获取角色名和权限id
 * 记录本次赋予的权限数量传递给前段
 * @author : 李雷
 * @date : 2020-11-26 11:10
 **/
@WebServlet("/AddRoleResourceServlet")
public class AddRoleResourceServlet extends HttpServlet {
    RoleServiceImpl roleService = new RoleServiceImpl();
    RoleResourceServiceImpl roleResourceService = new RoleResourceServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Integer roleId = roleService.selectIdByName(request.getParameter("roleId"));
        String[] btnResourceId = request.getParameterValues("btnResourceId");
        Integer addRoleResourceSize = 0;
        if (btnResourceId != null) {
            for (String s : btnResourceId) {
                if (!roleResourceService.insertRoleResource(roleId,Integer.parseInt(s)).equals(0)) {
                    ++addRoleResourceSize;
                }
            }
        }
        request.setAttribute("message","成功赋予角色"+addRoleResourceSize+"个权限");
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
