<%--
  Created by IntelliJ IDEA.
  User: Sur.Vival
  Date: 27/11/2015
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<p><b>Incorrect Password or Non-Verifies Account</b></p>
<p>
  <% out.println("<a href=\""+ request.getContextPath()+"/loginCheck\">Login again</a>");%>
</p>
</body>
</html>
