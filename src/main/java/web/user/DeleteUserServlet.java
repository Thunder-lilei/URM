package web.user;

import serviceImpl.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>URM</h3>
 * <p>${description}</p>
 *
 * @author : 李雷
 * @date : 2020-11-30 17:38
 **/
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] userId = request.getParameterValues("userId");
        Integer deleteSize = 0;
        for (String s : userId) {
            if (!userService.deleteUser(Integer.parseInt(s)).equals(0)) {
                ++deleteSize;
            }
        }
        request.setAttribute("message","成功移除"+deleteSize+"个用户！");
        request.setAttribute("UserControlPage",true);
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
