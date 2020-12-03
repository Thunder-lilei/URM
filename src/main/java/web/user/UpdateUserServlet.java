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
 * <p>更新用户信息</p>
 *
 * @author : 李雷
 * @date : 2020-11-30 16:58
 **/
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User updateUser = userService.selectById(Integer.parseInt(request.getParameter("userId")));
        if(updateUser!=null) {
            updateUser.setUsername(request.getParameter("username"));
            updateUser.setNickname(request.getParameter("nickname"));
            updateUser.setPassword(request.getParameter("password"));
            if (!userService.updateUser(updateUser).equals(0)) {
                request.setAttribute("message","更新完成！");
            }else {
                request.setAttribute("message","更新失败!尝试更换用户名");
            }
        }else {
            request.setAttribute("message","未能找到需要更新信息的用户！");
        }
        request.getSession().setAttribute("updateUser",null);
        request.getSession().setAttribute("pageNow",1);
        request.setAttribute("UserControlPage",true);
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
