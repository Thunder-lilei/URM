package web.role.resource;

import constant.PageUrlConstant;
import constant.RequestConstant;
import po.Resource;
import serviceImpl.resource.ResourceServiceImpl;
import serviceImpl.role.RoleServiceImpl;
import serviceImpl.role.resource.RoleResourceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <h3>URM</h3>
 * <p>撤销角色权限</p>
 * 接收勾选的权限id
 * 如果菜单资源的按钮资源都被清空同时移除菜单资源与角色的关联
 * @author : 李雷
 * @date : 2020-12-01 11:01
 **/
@WebServlet("/DeleteRoleResourceServlet")
public class DeleteRoleResourceServlet extends HttpServlet {
    RoleResourceServiceImpl roleResourceService = new RoleResourceServiceImpl();
    ResourceServiceImpl resourceService = new ResourceServiceImpl();
    RoleServiceImpl roleService = new RoleServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] btnResourceId = request.getParameterValues("btnResourceId");
        String roleId = request.getParameter("id");
        Integer deleteRoleResourceSize = 0;

        for (String s : btnResourceId) {
            if(!roleResourceService.deleteRoleResource(Integer.parseInt(s),Integer.parseInt(roleId)).equals(0)) {
                ++deleteRoleResourceSize;
            }
            Resource menuResource = resourceService.getResourceById(resourceService.getResourceById(Integer.parseInt(s)).getMenuResourceId());
            List<Integer> btnResourceIdList = resourceService.selectBtnResourcesIdByRoleIdAndMenuResourceId(Integer.parseInt(roleId),menuResource.getId());
            if (btnResourceIdList.isEmpty()) {
                roleResourceService.deleteRoleResource(menuResource.getId(),Integer.parseInt(roleId));
            }
        }
        request.setAttribute(RequestConstant.MESSAGE,
                "成功移除"+roleService.selectNameById(Integer.parseInt(roleId))+deleteRoleResourceSize+"个权限！");
        request.getSession().setAttribute("deleteRoleResourceId",null);
        request.setAttribute(RequestConstant.ROLE_CONTROL_PAGE,true);
        request.getRequestDispatcher(PageUrlConstant.CONTROL_PAGE).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
