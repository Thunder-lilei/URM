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
 * <p>查询用户</p>
 * 按照用户名进行查询
 * @author : 李雷
 * @date : 2020-11-30 16:40
 **/
@WebServlet("/SelectUserServlet")
public class SelectUserServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        List<User> userList = userService.selectUserByKeyWord(request.getParameter("keyWord"));
        if (userList!=null) {
            request.getSession().setAttribute("selectUserList",userList);
        }else {
            request.setAttribute("message","请检查关键字!");
        }
        request.setAttribute("UserControlPage",true);
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
