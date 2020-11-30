<%@ page import="java.util.List" %>
<%@ page import="po.Role" %>
<%@ page import="serviceImpl.role.RoleServiceImpl" %>
<%@ page import="serviceImpl.resource.ResourceServiceImpl" %>
<%@ page import="po.Resource" %>
<%@ page import="service.role.resource.RoleResourceService" %>
<%@ page import="serviceImpl.role.resource.RoleResourceServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/11/30
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RoleServiceImpl roleService = new RoleServiceImpl();
    ResourceServiceImpl resourceService = new ResourceServiceImpl();
    RoleResourceServiceImpl roleResourceService = new RoleResourceServiceImpl();
    List<Role> roleList = roleService.selectAll();
    List<Resource> menuResourceList = resourceService.getAllMenuResource();
%>
<div style="padding: 100px 100px 10px;width: 50%;">
    <h1><span class="label label-info">添加用户信息</span></h1>
    <br/>
    <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/AddRoleResourceServlet">
        <select id="roleId" onchange="changeCheckBox()" name="roleId" class="form-control">
            <option value=0 selected>请选择</option>
            <%
                for (Role role : roleList){
                    %>
            <option value="<%=role.getId()%>"><%=role.getRoleName()%></option>
                    <%
                };
            %>
        </select>
        <%
            for (Resource menuResource : menuResourceList) {
                %>
        <h2><span class="label label-info"><%=menuResource.getResourceName()%></span></h2>
        <%
            List<Resource> btnResourceList = resourceService.getBtnResourceByMenuResourceId(menuResource.getId());
            for (Resource btnResource : btnResourceList) {
                %>
        <div style="height: 5%;" class="form-check">
            <label style="width: 100%;height: 100%;" class="form-check-label">
                <input name="btnResourceId" style="height: 25px;width: 25px;" type="checkbox" class="form-check-input" value="<%=btnResource.getId()%>">
                <span style="margin: 0 0 0 5% ;font-size: 20px;" class="label label-info"><%=btnResource.getResourceName()%></span>
            </label>
        </div>
                <%
            };
        %>
                <%
            };
        %>
        <br/>
        <div class="btn-group">
            <button type="submit" class="btn btn-default">提交</button>
        </div>
    </form>
</div>
<script>
    function changeCheckBox() {
        alert($('#roleId option:selected').val())
    }
</script>