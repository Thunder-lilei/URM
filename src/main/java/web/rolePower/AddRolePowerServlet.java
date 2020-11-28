package web.rolePower;

import service.resource.BtnResourceService;
import service.role.RoleService;
import service.role.btn.resource.RoleBtnResourceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>URM</h3>
 * <p>赋予角色权限</p>
 * 获取角色名和权限id
 * 记录本次赋予的权限数量传递给前段
 * 移除本次的页面标签session
 * @author : 李雷
 * @date : 2020-11-26 11:10
 **/
@WebServlet("/AddRolePowerServlet")
public class AddRolePowerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Integer roleId = RoleService.selectIdByName(request.getParameter("name"));
        String[] checkbox = request.getParameterValues("checkbox");
        Integer addRolePowerSize = 0;
        for (String s : checkbox) {
            Integer powerId = BtnResourceService.selectBtnReourceIdByName(s);
            if (!roleId.equals(0) && !powerId.equals(0)) {
                if (!RoleBtnResourceService.addRoleBtnResource(powerId,roleId).equals(0)) {
                    ++addRolePowerSize;
                }
            }
        }
        request.setAttribute("message","成功赋予角色"+addRolePowerSize+"个权限");
        request.getSession().removeAttribute("list");
        request.getSession().removeAttribute("action");
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
