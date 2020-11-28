package web.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>UserPowerControl</h3>
 * <p>${description}</p>
 * 获取所有父权限传递给前端
 * @author : 李雷
 * @date : 2020-11-24 10:52
 **/
@WebServlet("/ControlPageServlet")
public class ControlPageServlet extends HttpServlet {
    private static final String REQUEST_LIST = "fatherPowerList";
    private static final String REQUEST_CHARACTER = "utf-8";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding(REQUEST_CHARACTER);

    }
}
