package web.user;

import constant.PageUrlConstant;
import constant.RequestConstant;
import po.User;
import service.impl.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <h3>URM</h3>
 * <p>用户信息分页查询</p>
 * 获取当前页码及每页最大数
 * 保存当前页码 返回用户列表
 * 设置最小最大页码
 * @author : 李雷
 * @date : 2020-12-02 11:52
 **/
@WebServlet("/SelectUserPageServlet")
public class SelectUserPageServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNow = request.getParameter("pageNow");
        int pageNowInteger = Integer.parseInt(pageNow);
        String pageSize = request.getParameter("pageSize");
        Integer pageSizeInteger = Integer.parseInt(pageSize);
        //最小最大页码
        int minPageSize = 1;
        int maxPageSize = (userService.countUser()+pageSizeInteger-1)/pageSizeInteger;

        if (pageNowInteger < minPageSize) {pageNowInteger = minPageSize;}
        if (pageNowInteger > maxPageSize) {pageNowInteger = maxPageSize;}
        List<User> userList = userService.selectUserByPage(pageNowInteger,pageSizeInteger);
        request.getSession().setAttribute("userPage",userList);
        request.getSession().setAttribute(RequestConstant.PAGE_NOW,pageNowInteger);
        request.setAttribute(RequestConstant.USER_CONTROL_PAGE,true);
        request.getRequestDispatcher(PageUrlConstant.CONTROL_PAGE).forward(request,response);
    }
}
