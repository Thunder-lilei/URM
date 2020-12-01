package web.roleUser;

import po.User;
import serviceImpl.role.user.RoleUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>URM</h3>
 * <p>移除用户角色</p>
 *
 * @author : 李雷
 * @date : 2020-12-01 10:30
 **/
@WebServlet("/DeleteRoleUserServlet")
public class DeleteRoleUserServlet extends HttpServlet {
    RoleUserServiceImpl roleUserService = new RoleUserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User user = (User) request.getSession().getAttribute("deleteRoleUser");
        String[] roleId = request.getParameterValues("roleId");
        Integer deleteRoleUserSize = 0;
        for (String s : roleId) {
            if (!roleUserService.deleteRoleUser(Integer.parseInt(s),user.getId()).equals(0)) {
                ++deleteRoleUserSize;
            }
        }
        request.setAttribute("message","成功移除"+user.getNickname()+deleteRoleUserSize+"个角色!");
        request.getSession().setAttribute("deleteRoleUser",null);
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
