<%@ page import="serviceImpl.role.RoleServiceImpl" %>
<%@ page import="po.Role" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/12/1
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding: 100px 100px 10px;width: 50%;">
    <h1><span class="label label-info">移除角色</span></h1>
    <br/>
    <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/DeleteRoleServlet">
        <%
            RoleServiceImpl roleService = new RoleServiceImpl();
            List<Role> roleList = roleService.selectAll();
            for (Role role : roleList) {
        %>
        <div style="height: 5%;" class="form-check">
            <label style="width: 100%;height: 100%;" class="form-check-label">
                <input name="roleId" style="height: 25px;width: 25px;" type="checkbox" class="form-check-input" value="<%=role.getId()%>">
                <span style="margin: 0 0 0 5% ;font-size: 20px;" class="label label-info"><%=role.getRoleName()%></span>
            </label>
        </div>
        <%
            };
        %>
        <br/>
        <div class="btn-group">
            <button type="submit" class="btn btn-default">移除</button>
        </div>
    </form>
</div>
