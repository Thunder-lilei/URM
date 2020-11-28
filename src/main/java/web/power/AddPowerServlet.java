package web.power;

import pojo.BtnResourcePojo;
import service.resource.BtnResourceService;
import service.resource.value.BtnResourceValueService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>UserPowerControl</h3>
 * <p>${description}</p>
 * 新增power
 * 新增powervalue
 * @author : 李雷
 * @date : 2020-11-26 16:35
 **/
@WebServlet("/AddPowerServlet")
public class AddPowerServlet extends HttpServlet {
    private static final String REQUEST_POWERNAME = "powerName";
    private static final String REQUEST_FATHERPOWERNAME = "fatherPowerName";
    private static final String REQUEST_POWERTYPE = "powerType";
    private static final String REQUEST_POWERNICKNAME = "powerNickName";
    private static final String POWER_TYPE_ADD = "add";
    private static final String POWER_TYPE_DELETE = "delete";
    private static final String POWER_TYPE_UPDATE = "update";
    private static final String POWER_TYPE_SELECT = "select";
    private static final String VALUE_TYPE_INPUT = "input";
    private static final String VALUE_TYPE_ACTION = "action";
    private static final String POWER_TYPE_ADD_SERVLET = "/AddPageServlet";
    private static final String POWER_TYPE_DELETE_SERVLET = "/DeletePageServlet";
    private static final String POWER_TYPE_UPDATE_SERVLET = "/UpdatePageServlet";
    private static final String POWER_TYPE_SELECT_SERVLET = "/SelectPageServlet";
    private static final Integer RESULT_0 = 0;
    private static final String REQUEST_MESSAGE = "message";
    private static final String REQUEST_MESSAGE_LOSE = "添加权限失败";
    private static final String REQUEST_LIST = "list";
    private static final String REQUEST_ACTION = "action";
    private static final String PAGES_CONTROL = "pages/control.jsp";
    private static final String REQUEST_CHARACTER = "utf-8";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(REQUEST_CHARACTER);
        BtnResourcePojo btnResourcePojo = new BtnResourcePojo();
        btnResourcePojo.setBtnResourceName(request.getParameter(REQUEST_POWERNAME));
        btnResourcePojo.setMenuResourceName(request.getParameter(REQUEST_FATHERPOWERNAME));
        btnResourcePojo.setBtnResourceType(request.getParameter(REQUEST_POWERTYPE));
        btnResourcePojo.setBtnResourceNickname(request.getParameter(REQUEST_POWERNICKNAME));
        String url = null;
        switch (btnResourcePojo.getBtnResourceType()) {
            case POWER_TYPE_ADD:
                url = POWER_TYPE_ADD_SERVLET;
                break;
            case POWER_TYPE_DELETE:
                url = POWER_TYPE_DELETE_SERVLET;
                break;
            case POWER_TYPE_UPDATE:
                url = POWER_TYPE_UPDATE_SERVLET;
                break;
            case POWER_TYPE_SELECT:
                url = POWER_TYPE_SELECT_SERVLET;
                break;
            default:
                break;
        }
        btnResourcePojo.setUrl(url);
        if (!BtnResourceService.addBtnResource(btnResourcePojo).equals(RESULT_0)) {
            Integer forSize = RESULT_0;
            switch (btnResourcePojo.getBtnResourceType()) {
                case POWER_TYPE_ADD:
                    break;
                case POWER_TYPE_DELETE:
                    BtnResourceValueService.insertBtnResourceValueDelete(btnResourcePojo.getBtnResourceName(),VALUE_TYPE_INPUT);
                    BtnResourceValueService.insertBtnResourceValueDelete(btnResourcePojo.getBtnResourceName(),VALUE_TYPE_ACTION);
                    break;
                case POWER_TYPE_UPDATE:
                    break;
                case POWER_TYPE_SELECT:
                    break;
                default:
                    break;
            }
            BtnResourceValueService.addBtnResourceValue(btnResourcePojo.getBtnResourceName(),btnResourcePojo.getBtnResourceNickname());
        }else {
            request.setAttribute(REQUEST_MESSAGE,REQUEST_MESSAGE_LOSE);
        }
        request.getSession().removeAttribute(REQUEST_LIST);
        request.getSession().removeAttribute(REQUEST_ACTION);
        request.getRequestDispatcher(PAGES_CONTROL).forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
