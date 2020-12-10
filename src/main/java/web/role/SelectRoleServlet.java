package web.role;

import constant.PageUrlConstant;
import constant.RequestConstant;
import po.Role;
import service.impl.role.RoleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <h3>URM</h3>
 * <p>查询角色</p>
 * 按照关键字进行查询
 * @author : 李雷
 * @date : 2020-12-04 12:02
 **/
@WebServlet("/SelectRoleServlet")
public class SelectRoleServlet extends HttpServlet {
    RoleServiceImpl roleService = new RoleServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Role> roleList = roleService.selectRoleByKeyWord(request.getParameter("keyWord"));
        if (roleList!=null) {
            request.getSession().setAttribute("selectRoleList",roleList);
        }else {
            request.setAttribute(RequestConstant.MESSAGE,"请检查关键字!");
        }
        request.setAttribute(RequestConstant.ROLE_CONTROL_PAGE,true);
        request.getRequestDispatcher(PageUrlConstant.CONTROL_PAGE).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
