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
 * <p>为用户赋予角色</p>
 * 获取用户id和角色id
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
        String idString = request.getParameter("id");
        User user = userService.selectById(Long.parseLong(idString));
        int addRoleSize = 0;
        if (user != null) {
            String[] roleIds = request.getParameterValues("roleId");
            for(String s : roleIds) {
                if (!roleUserService.insertRoleUser(Long.parseLong(s),user.getId()).equals(0)) {
                    ++addRoleSize;
                }
            }
            request.setAttribute("message","成功赋予"+user.getNickname()+addRoleSize+"个角色");
        }else {
            request.setAttribute("message","用户异常!");
        }
        request.setAttribute("UserControlPage",true);
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
