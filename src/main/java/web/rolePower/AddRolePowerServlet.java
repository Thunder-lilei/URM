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
 * <h3>UserPowerControl</h3>
 * <p>${description}</p>
 *
 * @author : 李雷
 * @date : 2020-11-26 11:10
 **/
@WebServlet("/AddRolePowerServlet")
public class AddRolePowerServlet extends HttpServlet {
    private static final String NAME = "name";
    private static final String CHECKBOX = "checkbox";
    private static final Integer ADD_ROLE_SIZE_0 = 0;
    private static final Integer ROLE_ID_0 = 0;
    private static final Integer POWER_ID_0 = 0;
    private static final String REQUEST_MESSAGE = "message";
    private static final String REQUEST_MESSAGE_SUCCESS_ADD = "成功赋予角色";
    private static final String REQUEST_MESSAGE_SIZE_ROLEPOWER = "个权限";
    private static final String REQUEST_LIST = "list";
    private static final String REQUEST_ACTION = "action";
    private static final String PAGES_CONTROL = "pages/control.jsp";
    private static final String REQUEST_CHARACTER = "utf-8";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(REQUEST_CHARACTER);
        Integer roleId = RoleService.selectIdByName(request.getParameter(NAME));
        String[] checkbox = request.getParameterValues(CHECKBOX);
        Integer addRolePowerSize = ADD_ROLE_SIZE_0;
        for (String s : checkbox) {
            Integer powerId = BtnResourceService.selectBtnReourceIdByName(s);
            if (!roleId.equals(ROLE_ID_0) && !powerId.equals(POWER_ID_0)) {
                if (!RoleBtnResourceService.addRoleBtnResource(powerId,roleId).equals(ADD_ROLE_SIZE_0)) {
                    ++addRolePowerSize;
                }
            }
        }
        request.setAttribute(REQUEST_MESSAGE,REQUEST_MESSAGE_SUCCESS_ADD+addRolePowerSize+REQUEST_MESSAGE_SIZE_ROLEPOWER);
        request.getSession().removeAttribute(REQUEST_LIST);
        request.getSession().removeAttribute(REQUEST_ACTION);
        request.getRequestDispatcher(PAGES_CONTROL).forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
