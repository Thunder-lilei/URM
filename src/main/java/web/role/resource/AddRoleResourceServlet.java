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
 * 移除本次的页面标签session
 * @author : 李雷
 * @date : 2020-11-26 11:10
 **/
@WebServlet(name = "AddRoleResourceServlet")
public class AddRoleResourceServlet extends HttpServlet {
    RoleServiceImpl roleService = new RoleServiceImpl();
    ResourceServiceImpl resourceService = new ResourceServiceImpl();
    RoleResourceServiceImpl roleResourceService = new RoleResourceServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Integer roleId = roleService.selectIdByName(request.getParameter("name"));
        String[] checkbox = request.getParameterValues("checkbox");
        Integer addRoleResourceSize = 0;
        for (String s : checkbox) {
            Integer resourceId = resourceService.selectBtnReourceIdByName(s);
            if (!roleId.equals(0) && !resourceId.equals(0)) {
                if (!roleResourceService.insertRoleResource(roleId,resourceId).equals(0)) {
                    ++addRoleResourceSize;
                }
            }
        }
        request.setAttribute("message","成功赋予角色"+addRoleResourceSize+"个权限");
        request.getSession().removeAttribute("list");
        request.getSession().removeAttribute("action");
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
