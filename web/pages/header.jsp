<%--
  Created by IntelliJ IDEA.
  User: lilei
  Date: 2020/11/30
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div style="display: flex;text-align: center;color: white">
        <div style="width: 50%;">
            <h2>当前登录用户：${sessionScope.user.nickname}</h2>
        </div>
        <div style="width: 50%;text-align: center" class="btn-group btn-group-lg">
            <a style="color: white" href="${pageContext.request.contextPath}/LogoutServlet">
                登出
            </a>
            <br>
            <br>
            <a style="color: white" href="${pageContext.request.contextPath}/index.jsp">
                返回主页
            </a>
        </div>
    </div>
</header>