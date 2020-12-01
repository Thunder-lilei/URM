package web.roleUser;

import po.User;
import serviceImpl.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>URM</h3>
 * <p>查询用户</p>
 * 按照用户名进行查询
 * 查询出结果后清空密码返回给前端
 * @author : 李雷
 * @date : 2020-12-01 10:24
 **/
@WebServlet("/DeleteRoleUserSelectServlet")
public class DeleteRoleUserSelectServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User user = userService.selectByUsername(request.getParameter("userName"));
        if (user != null) {
            user.setPassword(null);
            request.getSession().setAttribute("deleteRoleUser", user);
        } else {
            request.setAttribute("message", "请检查用户名!");
        }
        request.getRequestDispatcher("pages/control.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
