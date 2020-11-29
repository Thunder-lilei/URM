package web.power;

import service.resource.value.BtnResourceValueService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>URM</h3>
 * <p>新增权限</p>
 * 获取填写的权限信息
 * 增加至权限表
 * 生成相对应类型的管理页面标签
 * 移除本次的页面标签session
 * @author : 李雷
 * @date : 2020-11-26 16:35
 **/
@WebServlet("/AddPowerServlet")
public class AddPowerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BtnResourcePojo btnResourcePojo = new BtnResourcePojo();
        btnResourcePojo.setBtnResourceName(request.getParameter("powerName"));
        btnResourcePojo.setMenuResourceName(request.getParameter("fatherPowerName"));
        btnResourcePojo.setBtnResourceType(request.getParameter("powerType"));
        btnResourcePojo.setBtnResourceNickname(request.getParameter("powerNickName"));
        String url = null;
        switch (btnResourcePojo.getBtnResourceType()) {
            case "add":
                url = "/AddPageServlet";
                break;
            case "delete":
                url = "/DeletePageServlet";
                break;
            case "update":
                url = "/UpdatePageServlet";
                break;
            case "select":
                url = "/SelectPageServlet";
                break;
            default:
                break;
        }
        btnResourcePojo.setUrl(url);
        if (!BtnResourceService.addBtnResource(btnResourcePojo).equals(0)) {
            Integer forSize = 0;
            switch (btnResourcePojo.getBtnResourceType()) {
                case "add":
                    break;
                case "delete":
                    BtnResourceValueService.insertBtnResourceValueDelete(btnResourcePojo.getBtnResourceName(),"input");
                    BtnResourceValueService.insertBtnResourceValueDelete(btnResourcePojo.getBtnResourceName(),"action");
                    break;
                case "update":
                    break;
                case "select":
                    break;
                default:
                    break;
            }
            BtnResourceValueService.addBtnResourceValue(btnResourcePojo.getBtnResourceName(),btnResourcePojo.getBtnResourceNickname());
        }else {
            request.setAttribute("message","添加权限失败");
        }
        request.getSession().removeAttribute("list");
        request.getSession().removeAttribute("action");
        request.getRequestDispatcher("pages/control.jsp").forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
