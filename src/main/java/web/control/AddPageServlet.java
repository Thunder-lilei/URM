package web.control;

import service.resource.value.BtnResourceValueService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <h3>UserPowerControl</h3>
 * <p>${description}</p>
 *
 * @author : 李雷
 * @date : 2020-11-25 10:00
 **/
@WebServlet("/AddPageServlet")
public class AddPageServlet extends HttpServlet {
    private static final String REQUEST_ID = "id";
    private static final String REQUEST_LIST = "list";
    private static final String REQUEST_ACTION = "action";
    private static final String PAGES_CONTROL = "pages/control.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter(REQUEST_ID));
        List<String> list = BtnResourceValueService.selectBtnResourceValueInputByBtnResourceId(id);
        String action = BtnResourceValueService.selectBtnResourceValueActionByBtnResourceId(id);
        request.getSession().setAttribute(REQUEST_LIST,list);
        request.getSession().setAttribute(REQUEST_ACTION,action);
        request.getRequestDispatcher(PAGES_CONTROL).forward(request,response);
    }
}
