package web.user;

import constant.PageUrlConstant;
import constant.RequestConstant;
import service.impl.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>URM</h3>
 * <p>删除用户</p>
 * Long.parseLong
 * @author : 李雷
 * @date : 2020-11-30 17:38
 **/
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] userId = request.getParameterValues("userId");

        int deleteSize = 0;
        for (String s : userId) {
            if (!userService.deleteUser(Long.parseLong(s)).equals(0)) {
                ++deleteSize;
            }
        }
        request.setAttribute(RequestConstant.MESSAGE,"成功移除"+deleteSize+"个用户！");
        request.setAttribute(RequestConstant.PAGE_NOW,1);
        request.setAttribute(RequestConstant.USER_CONTROL_PAGE,true);
        request.getRequestDispatcher(PageUrlConstant.CONTROL_PAGE).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
