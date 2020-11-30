package web.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>URM</h3>
 * <p>登出</p>
 * 清空session
 * @author : 李雷
 * @date : 2020-11-24 10:16
 **/
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getSession().getAttribute("user") !=null) {
            request.getSession().invalidate();
            response.sendRedirect("index.jsp");
        }else {
            request.setAttribute("message","请登录");
            request.getRequestDispatcher("pages/login.jsp").forward(request,response);
        }
    }
}
