package web.roleUser;

import po.User;
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
 * 判断用户名是否正确
 * 记录本次赋予的角色数量传递给前段
 * @author : 李雷
 * @date : 2020-11-26 09:17
 **/
@WebServlet("/AddRoleUserServlet")
public class AddRoleUserServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();
    RoleUserServiceImpl roleUserService = new RoleUserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User user = userService.selectByUsername(request.getParameter("userName"));
        if (user == null) {
            request.setAttribute("message","请填写正确的用户名!");
            request.getRequestDispatcher("pages/control.jsp").forward(request,response);
        }
        String[] roleIds = request.getParameterValues("roleId");
        Integer addRoleSize = 0;
        for(String s : roleIds) {
            if (!roleUserService.insertRoleUser(Integer.parseInt(s),user.getId()).equals(0)) {
                ++addRoleSize;
            }
        }
        request.setAttribute("message","成功赋予"+user.getNickname()+addRoleSize+"个角色");
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
