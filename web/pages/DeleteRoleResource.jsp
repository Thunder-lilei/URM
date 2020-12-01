<%@ page import="serviceImpl.role.RoleServiceImpl" %>
<%@ page import="po.Role" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/12/1
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RoleServiceImpl roleService = new RoleServiceImpl();
    List<Role> roleList = roleService.selectAll();
%>
<div style="padding: 100px 100px 10px;width: 50%;">
    <h1><span class="label label-info">撤销角色权限</span></h1>
    <br/>
    <form class="bs-example bs-example-form" role="form" method="post" action="${pageContext.request.contextPath}/DeleteRoleResourceSelectServlet">
        <select id="roleId" name="roleId" class="form-control">
            <%
                for (Role role : roleList){
            %>
            <option value="<%=role.getId()%>"><%=role.getRoleName()%></option>
            <%
                };
            %>
        </select>
        <br/>
        <div class="btn-group">
            <button type="submit" class="btn btn-default">提交</button>
        </div>
    </form>
</div>