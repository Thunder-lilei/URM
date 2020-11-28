package web.user;

import pojo.UserPojo;
import service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>UserPowerControl</h3>
 * <p>${登录}</p>
 * 根据用户名搜索用户
 * 对比密码正确后，扣除密码存储session
 * @author : 李雷
 * @date : 2020-11-21 22:07
 **/
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
    private static final String REQUEST_NAME = "username";
    private static final String REQUEST_PASSWORD = "password";
    private static final String REQUEST_MESSAGE = "message";
    private static final String REQUEST_ERROR_PASSWORD = "密码错误";
    private static final String REQUEST_ERROR_USERNAME = "用户名错误";
    private static final String SESSION_USER = "user";
    private static final String PAGES_CONTROL = "pages/control.jsp";
    private static final String PAGES_LOGIN = "user/login.jsp";
    private static final String REQUEST_CHARACTER = "utf-8";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding(REQUEST_CHARACTER);
        UserPojo userPojo = UserService.selectByUsername(request.getParameter(REQUEST_NAME));
        if(userPojo !=null) {
            if(userPojo.getPassword().equals(request.getParameter(REQUEST_PASSWORD))) {
                userPojo.setPassword(null);
                request.getSession().setAttribute(SESSION_USER,userPojo);
                response.sendRedirect(PAGES_CONTROL);
            }else {
                request.setAttribute(REQUEST_MESSAGE,REQUEST_ERROR_PASSWORD);
                request.getRequestDispatcher(PAGES_LOGIN).forward(request,response);
            }
        }else {
            request.setAttribute(REQUEST_MESSAGE,REQUEST_ERROR_USERNAME);
            request.getRequestDispatcher(PAGES_LOGIN).forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}
