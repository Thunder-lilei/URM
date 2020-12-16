package web.role.user;

import po.User;
import service.impl.role.user.RoleUserServiceImpl;
import service.impl.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>URM</h3>
 * <p>撤销用户角色</p>
 * 根据选中的角色ID移除用户角色
 * @author : 李雷
 * @date : 2020-12-01 10:30
 **/
@WebServlet("/DeleteRoleUserServlet")
public class DeleteRoleUserServlet extends HttpServlet {
    RoleUserServiceImpl roleUserService = new RoleUserServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("id");
        User user = userService.selectById(Long.parseLong(idString));
        String[] roleId = request.getParameterValues("roleId");
        if (roleId != null) {
            int deleteRoleUserSize = 0;
            for (String s : roleId) {
                if (!roleUserService.deleteRoleUser(Long.parseLong(s),user.getId()).equals(0)) {
                    ++deleteRoleUserSize;
                }
            }
            request.setAttribute("message","成功移除"+user.getNickname()+deleteRoleUserSize+"个角色!");
        }else {
            request.setAttribute("message","该用户没有角色!");
        }

        request.setAttribute("UserControlPage",true);
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
