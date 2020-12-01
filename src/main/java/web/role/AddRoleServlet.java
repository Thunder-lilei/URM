package web.role;

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
        request.setCharacterEncoding("utf-8");
        Role role = new Role();
        role.setRole_type(request.getParameter("type"));
        role.setRoleName(request.getParameter("role_name"));
        if (!roleService.addRole(role).equals(0)) {
            request.setAttribute("message","添加失败！");
        }
        request.setAttribute("message","成功添加"+role.getRoleName()+"!");
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
