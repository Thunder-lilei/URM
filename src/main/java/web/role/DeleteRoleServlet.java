package web.role;

import constant.PageUrlConstant;
import constant.RequestConstant;
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
        String[] roleId = request.getParameterValues("roleId");
        Integer deleteSize = 0;
        for (String s : roleId) {
            if (!roleService.deleteRole(Integer.parseInt(s)).equals(0)) {
                ++deleteSize;
            }
        }
        request.setAttribute(RequestConstant.MESSAGE,"成功移除"+deleteSize+"个角色！");
        request.setAttribute(RequestConstant.ROLE_CONTROL_PAGE,true);
        request.getRequestDispatcher(PageUrlConstant.CONTROL_PAGE).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
