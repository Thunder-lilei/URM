package web.user;

import constant.PageUrlConstant;
import constant.RequestConstant;
import po.User;
import service.impl.user.UserServiceImpl;
import util.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>URM</h3>
 * <p>更新用户信息</p>
 * 表单中的密码如果与原密码不相同则重新加密
 * @author : 李雷
 * @date : 2020-11-30 16:58
 **/
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User updateUser = userService.selectById(Long.parseLong(request.getParameter("userId")));
        if(updateUser!=null) {
            updateUser.setUsername(request.getParameter("username"));
            updateUser.setNickname(request.getParameter("nickname"));
            String password = request.getParameter("password");
            if(!updateUser.getPassword().equals(password)) {
                updateUser.setPassword(BCrypt.hashpw(password,BCrypt.gensalt()));
            }else {
                updateUser.setPassword(request.getParameter("password"));
            }
            if (!userService.updateUser(updateUser).equals(0)) {
                request.setAttribute(RequestConstant.MESSAGE,"更新完成！");
            }else {
                request.setAttribute(RequestConstant.MESSAGE,"更新失败!尝试更换用户名");
            }
        }else {
            request.setAttribute(RequestConstant.MESSAGE,"未能找到需要更新信息的用户！");
        }
        request.getSession().setAttribute(RequestConstant.PAGE_NOW,1);
        request.setAttribute(RequestConstant.USER_CONTROL_PAGE,true);
        request.getRequestDispatcher(PageUrlConstant.CONTROL_PAGE).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
