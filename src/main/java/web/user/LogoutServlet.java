package web.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>UserPowerControl</h3>
 * <p>${登出}</p>
 * session中查询user
 * @author : 李雷
 * @date : 2020-11-24 10:16
 **/
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final String SESSION_USER = "user";
    private static final String REQUEST_MESSAGE = "message";
    private static final String REQUEST_ERROR_LOGIN = "请登录";
    private static final String PAGES_LOGIN = "user/login.jsp";
    private static final String PAGES_INDEX = "index.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getSession().getAttribute(SESSION_USER) !=null) {
            request.getSession().invalidate();
            response.sendRedirect(PAGES_INDEX);
        }else {
            request.setAttribute(REQUEST_MESSAGE,REQUEST_ERROR_LOGIN);
            request.getRequestDispatcher(PAGES_LOGIN).forward(request,response);
        }
    }
}
