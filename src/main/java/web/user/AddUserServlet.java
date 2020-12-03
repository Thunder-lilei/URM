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
 * <p>新增用户</p>
 * 获取填写的用户信息
 * 密码使用md5加密
 * @author : 李雷
 * @date : 2020-11-24 17:13
 **/
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String message = "添加成功！";
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setNickname(request.getParameter("nickName"));
        if(userService.addUser(user).equals(0)) {
            message = "添加失败！尝试更换用户名";
        }
        request.setAttribute("message",message);
        request.getSession().setAttribute("pageNow",1);
        request.setAttribute("UserControlPage",true);
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
