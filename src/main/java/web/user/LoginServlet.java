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
 * <p>${登录}</p>
 * 根据用户名搜索用户
 * 对比密码正确后，扣除密码存储session
 * 登陆失败携带错误信息返回登录页面
 * @author : 李雷
 * @date : 2020-11-21 22:07
 **/
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        User user = userService.selectByUsername(request.getParameter("username"));
        if(user !=null) {
            if(user.getPassword().equals(request.getParameter("password"))) {
                user.setPassword(null);
                request.getSession().setAttribute("user",user);
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
