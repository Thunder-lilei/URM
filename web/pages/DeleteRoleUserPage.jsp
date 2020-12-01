<%@ page import="po.User" %>
<%@ page import="serviceImpl.role.RoleServiceImpl" %>
<%@ page import="po.Role" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/12/1
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding: 100px 100px 10px;width: 50%;">
    <h1><span class="label label-info">移除用户角色</span></h1>
    <h1><span class="label label-warning">请选择需要移除的角色</span></h1>
    <br/>
    <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/DeleteRoleUserServlet">
        <%
            User deleteRoleUser = (User) request.getSession().getAttribute("deleteRoleUser");
            RoleServiceImpl roleService = new RoleServiceImpl();
            List<Role> roleList = roleService.selectByUserId(deleteRoleUser.getId());
            for (Role role : roleList) {
        %>
        <div style="height: 5%;" class="form-check">
            <label style="width: 100%;height: 100%;" class="form-check-label">
                <input name="roleId" style="height: 25px;width: 25px;" type="checkbox" class="form-check-input" value="<%=role.getId()%>" checked="checked">
                <span style="margin: 0 0 0 5% ;font-size: 20px;" class="label label-info"><%=role.getRoleName()%></span>
            </label>
        </div>
        <%
            };
        %>
        <div class="btn-group">
            <button type="submit" class="btn btn-default">提交</button>
        </div>
    </form>
</div>