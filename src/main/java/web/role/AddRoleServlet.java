package web.role;

import constant.PageUrlConstant;
import constant.RequestConstant;
import po.Role;
import serviceImpl.role.RoleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>URM</h3>
 * <p>新增角色</p>
 * 获取角色信息
 * @author : 李雷
 * @date : 2020-11-25 11:49
 **/
@WebServlet("/AddRoleServlet")
public class AddRoleServlet extends HttpServlet {
    RoleServiceImpl roleService = new RoleServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role role = new Role();
        role.setRoleType(request.getParameter("type"));
        role.setRoleName(request.getParameter("role_name"));
        if (roleService.addRole(role).equals(0)) {
            request.setAttribute(RequestConstant.MESSAGE,"添加失败，请尝试更换信息！");
            request.getRequestDispatcher(PageUrlConstant.CONTROL_PAGE).forward(request,response);
        }
        request.setAttribute(RequestConstant.MESSAGE,"成功添加"+role.getRoleName()+"!");
        request.setAttribute(RequestConstant.ROLE_CONTROL_PAGE,true);
        request.getRequestDispatcher(PageUrlConstant.CONTROL_PAGE).forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
