package web.role;

import serviceImpl.role.RoleServiceImpl;

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
 * @date : 2020-12-01 09:41
 **/
@WebServlet("/DeleteRoleServlet")
public class DeleteRoleServlet extends HttpServlet {
    RoleServiceImpl roleService = new RoleServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String[] roleId = request.getParameterValues("roleId");
        Integer deleteSize = 0;
        for (String s : roleId) {
            if (!roleService.deleteRole(Integer.parseInt(s)).equals(0)) {
                ++deleteSize;
            }
        }
        request.setAttribute("message","成功移除"+deleteSize+"个角色！");
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
