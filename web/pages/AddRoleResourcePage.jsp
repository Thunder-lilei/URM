<%@ page import="po.Resource" %>
<%@ page import="java.util.List" %>
<%@ page import="serviceImpl.resource.ResourceServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/11/30
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String roleId = null;
    String deleteRoleResourceId = (String) request.getSession().getAttribute("deleteRoleResourceId");
%>
<div style="padding: 100px 100px 10px;width: 50%;">
    <%
        if(deleteRoleResourceId!=null) {
            %>
    <h1><span class="label label-info">撤销角色权限</span></h1>
    <h1><span class="label label-warning">请勾选需要撤销的权限</span></h1>
    <br/>
    <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/DeleteRoleResourceServlet">
            <%
            roleId = (String) request.getSession().getAttribute("deleteRoleResourceId");
        }else {;
            %>
    <h1><span class="label label-info">角色授权</span></h1>
    <br/>
    <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/UpdateRoleResourceServlet">
            <%
            roleId = (String) request.getSession().getAttribute("roleId");
        };
        ResourceServiceImpl resourceService = new ResourceServiceImpl();
        List<Resource> menuResourceList = resourceService.getAllMenuResource();
    %>
        <%
            for (Resource menuResource : menuResourceList) {
        %>
        <h2><span class="label label-info"><%=menuResource.getResourceName()%></span></h2>
        <%
            List<Resource> btnResourceList = resourceService.getBtnResourceByMenuResourceId(menuResource.getId());
            List<Integer> btnResourceId = resourceService.selectBtnResourcesIdByRoleIdAndMenuResourceId
                    (Integer.parseInt(roleId),menuResource.getId());
            for (Resource btnResource : btnResourceList) {
        %>
        <div style="height: 5%;" class="form-check">
            <label style="width: 100%;height: 100%;" class="form-check-label">
                <input name="btnResourceId" style="height: 25px;width: 25px;" type="checkbox" class="form-check-input" value="<%=btnResource.getId()%>"
                    <%
                                    if (btnResourceId.contains(btnResource.getId()) && request.getSession().getAttribute("roleId") != null) {
                                        %>
                       checked="checked"
                                        <%
                                    }
                                    %>
                >
                <span style="margin: 0 0 0 5% ;font-size: 20px;" class="label label-info"><%=btnResource.getResourceName()%></span>
            </label>
        </div>
        <%
                };
            };
        %>
        <br/>
        <div class="btn-group">
            <button type="submit" class="btn btn-default">提交</button>
        </div>
    </form>
</div>
