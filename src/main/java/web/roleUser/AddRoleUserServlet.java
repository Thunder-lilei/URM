package web.roleUser;

import service.role.RoleService;
import service.role.user.RoleUserService;
import service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>UserPowerControl</h3>
 * <p>${description}</p>
 * 角色id不能为0 用户id不能为0
 * @author : 李雷
 * @date : 2020-11-26 09:17
 **/
@WebServlet("/AddRoleUserServlet")
public class AddRoleUserServlet extends HttpServlet {
    private static final String NAME = "name";
    private static final String CHECKBOX = "checkbox";
    private static final Integer ADD_ROLE_SIZE_0 = 0;
    private static final Integer ROLE_ID_0 = 0;
    private static final Integer USER_ID_0 = 0;
    private static final String REQUEST_MESSAGE = "message";
    private static final String REQUEST_MESSAGE_SUCCESS_ADD = "成功赋予";
    private static final String REQUEST_MESSAGE_SIZE_ROLE = "个角色";
    private static final String REQUEST_LIST = "list";
    private static final String REQUEST_ACTION = "action";
    private static final String PAGES_CONTROL = "pages/control.jsp";
    private static final String REQUEST_CHARACTER = "utf-8";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(REQUEST_CHARACTER);
        Integer usertId = UserService.selectIdByUsername(request.getParameter(NAME));
        String[] checkbox = request.getParameterValues(CHECKBOX);
        Integer addRoleSize = ADD_ROLE_SIZE_0;
        for(String s : checkbox) {
            Integer roleId = RoleService.selectIdByName(s);
            if (!roleId.equals(ROLE_ID_0) && !usertId.equals(USER_ID_0)) {
                if (!RoleUserService.insertRoleUser(roleId,usertId).equals(ADD_ROLE_SIZE_0)) {
                    ++addRoleSize;
                }
            }
        }
        request.setAttribute(REQUEST_MESSAGE,REQUEST_MESSAGE_SUCCESS_ADD+addRoleSize+REQUEST_MESSAGE_SIZE_ROLE);
        request.getSession().removeAttribute(REQUEST_LIST);
        request.getSession().removeAttribute(REQUEST_ACTION);
        request.getRequestDispatcher(PAGES_CONTROL).forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
