package web.user;

import po.User;
import serviceImpl.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <h3>URM</h3>
 * <p>${description}</p>
 *
 * @author : 李雷
 * @date : 2020-12-02 11:52
 **/
@WebServlet("/SelectUserPageServlet")
public class SelectUserPageServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String page = request.getParameter("page");
        List<User> userList = userService.selectUserByPage(Integer.parseInt(page),10);
        request.getSession().setAttribute("userPage",userList);
        request.setAttribute("UserControlPage",true);
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);
    }
}
