package web.role.resource;

import constant.PageUrlConstant;
import constant.RequestConstant;
import po.Resource;
import service.impl.resource.ResourceServiceImpl;
import service.impl.role.RoleServiceImpl;
import service.impl.role.resource.RoleResourceServiceImpl;

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
        int deleteRoleResourceSize = 0;
        if (btnResourceId != null){
            for (String s : btnResourceId) {
                if(!roleResourceService.deleteRoleResource(Long.parseLong(s),Long.parseLong(roleId)).equals(0)) {
                    ++deleteRoleResourceSize;
                }
                Resource menuResource = resourceService.getResourceById(resourceService.getResourceById(Long.parseLong(s)).getMenuResourceId());
                List<Long> btnResourceIdList = resourceService.selectBtnResourcesIdByRoleIdAndMenuResourceId(Long.parseLong(roleId),menuResource.getId());
                if (btnResourceIdList.isEmpty()) {
                    roleResourceService.deleteRoleResource(menuResource.getId(),Long.parseLong(roleId));
                }
            }
        }
        request.setAttribute(RequestConstant.MESSAGE,
                "成功移除"+roleService.selectNameById(Long.parseLong(roleId))+deleteRoleResourceSize+"个权限！");
        request.setAttribute(RequestConstant.ROLE_CONTROL_PAGE,true);
        request.getRequestDispatcher(PageUrlConstant.CONTROL_PAGE).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
