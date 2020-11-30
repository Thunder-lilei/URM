<%@ page import="java.util.List" %>
<%@ page import="po.Role" %>
<%@ page import="serviceImpl.role.RoleServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/11/30
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RoleServiceImpl roleService = new RoleServiceImpl();
    List<Role> roleList = null;
%>