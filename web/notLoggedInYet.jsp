<%--
  Created by IntelliJ IDEA.
  User: Sur.Vival
  Date: 29/11/2015
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title></title>
    <meta http-equiv="Refresh" content="1.5;url=index.jsp">
</head>
<body>
<%
  if(exception instanceof NullPointerException) {
    out.println("<br/><b>You should loging frist!!");
  }
%>
</body>
</html>
