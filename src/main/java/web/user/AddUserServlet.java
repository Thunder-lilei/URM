package web.user;

import po.User;
import service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>UserPowerControl</h3>
 * <p>${description}</p>
 *
 * @author : 李雷
 * @date : 2020-11-24 17:13
 **/
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String NICKNAME = "nickname";
    private static final String REQUEST_MESSAGE = "message";
    private static final String REQUEST_LIST = "list";
    private static final String REQUEST_ACTION = "action";
    private static final String PAGES_CONTROL = "pages/control.jsp";
    private static final String REQUEST_CHARACTER = "utf-8";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(REQUEST_CHARACTER);
        User user = new User();
        user.setUserName(request.getParameter(USERNAME));
        user.setPassword(request.getParameter(PASSWORD));
        user.setNickname(request.getParameter(NICKNAME));
        request.setAttribute(REQUEST_MESSAGE,UserService.addUser(user));
        request.getSession().removeAttribute(REQUEST_LIST);
        request.getSession().removeAttribute(REQUEST_ACTION);
        request.getRequestDispatcher(PAGES_CONTROL).forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
