package web.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <h3>URM</h3>
 * <p>增加类型权限的管理页面加载</p>
 * 获取标签表中对应的页面标签和提交的action
 * @author : 李雷
 * @date : 2020-11-25 10:00
 **/
@WebServlet("/AddPageServlet")
public class AddPageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);
    }
}
