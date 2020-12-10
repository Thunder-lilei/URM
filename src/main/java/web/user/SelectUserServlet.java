package web.user;

import constant.PageUrlConstant;
import constant.RequestConstant;
import po.User;
import service.impl.user.UserServiceImpl;

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
 * 按照关键字进行查询
 * @author : 李雷
 * @date : 2020-11-30 16:40
 **/
@WebServlet("/SelectUserServlet")
public class SelectUserServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = userService.selectUserByKeyWord(request.getParameter("keyWord"));
        if (userList!=null) {
            request.getSession().setAttribute("selectUserList",userList);
        }else {
            request.setAttribute(RequestConstant.MESSAGE,"请检查关键字!");
        }
        request.setAttribute(RequestConstant.USER_CONTROL_PAGE,true);
        request.getRequestDispatcher(PageUrlConstant.CONTROL_PAGE).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
