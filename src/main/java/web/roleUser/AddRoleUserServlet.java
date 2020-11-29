package web.roleUser;

import serviceImpl.role.RoleServiceImpl;
import serviceImpl.role.user.RoleUserServiceImpl;
import serviceImpl.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>URM</h3>
 * <p>为用户赋予角色</p>
 * 获取用户名和角色id
 * 记录本次赋予的角色数量传递给前段
 * 移除本次的页面标签session
 * @author : 李雷
 * @date : 2020-11-26 09:17
 **/
@WebServlet("/AddRoleUserServlet")
public class AddRoleUserServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();
    RoleServiceImpl roleService = new RoleServiceImpl();
    RoleUserServiceImpl roleUserService = new RoleUserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Integer userId = userService.selectIdByUsername(request.getParameter("name"));
        String[] checkbox = request.getParameterValues("checkbox");
        Integer addRoleSize = 0;
        for(String s : checkbox) {
            Integer roleId = roleService.selectIdByName(s);
            if (!roleId.equals(0) && !userId.equals(0)) {
                if (!roleUserService.insertRoleUser(roleId,userId).equals(0)) {
                    ++addRoleSize;
                }
            }
        }
        request.setAttribute("message","成功赋予"+addRoleSize+"个角色");
        request.getSession().removeAttribute("list");
        request.getSession().removeAttribute("action");
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
