package web.user;

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
 * <p>更新用户信息</p>
 *
 * @author : 李雷
 * @date : 2020-11-30 16:58
 **/
@WebServlet("/UpdateUserSelectServlet")
public class UpdateUserSelectServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User user = userService.selectByUsername(request.getParameter("userName"));
        if (user!=null) {
            request.getSession().setAttribute("updateUser",user);
        }else {
            request.setAttribute("message","请检查用户名!");
        }
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
