<%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/11/30
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header style="background-color: #00B271">
    <%
        if(request.getSession().getAttribute("user")!=null) {
    %>
    <div style="display: flex;text-align: center;color: white">
        <div style="width: 50%;margin: 1.5% 0 0 0">
            <h2>当前登录用户：${sessionScope.user.nickname}</h2>
        </div>
        <div style="width: 50%;text-align: center;" class="btn-group btn-group-lg">
            <br/>

            <a style="color: white;" href="${pageContext.request.contextPath}/LogoutServlet">
                <button style="color: white;font-size: 15px;" type="button" class="btn btn-outline-secondary">登出</button>
            </a>
            <br/>
            <br/>
            <a style="color: white" href="${pageContext.request.contextPath}/index.jsp">
                <button style="color: white;font-size: 15px;" type="button" class="btn btn-outline-secondary">返回主页</button>
            </a>
        </div>
    </div>
    <%
        };
    %>
</header>