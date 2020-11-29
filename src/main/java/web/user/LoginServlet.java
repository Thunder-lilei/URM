package web.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>URM</h3>
 * <p>${登录}</p>
 * 根据用户名搜索用户
 * 对比密码正确后，扣除密码存储session
 * 登陆失败携带错误信息返回登录页面
 * @author : 李雷
 * @date : 2020-11-21 22:07
 **/
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        UserPojo userPojo = UserService.selectByUsername(request.getParameter("username"));
        if(userPojo !=null) {
            if(userPojo.getPassword().equals(request.getParameter("password"))) {
                userPojo.setPassword(null);
                request.getSession().setAttribute("user",userPojo);
                response.sendRedirect("pages/control.jsp");
            }else {
                request.setAttribute("message","密码错误");
                request.getRequestDispatcher("user/login.jsp").forward(request,response);
            }
        }else {
            request.setAttribute("message","用户名错误");
            request.getRequestDispatcher("user/login.jsp").forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}
