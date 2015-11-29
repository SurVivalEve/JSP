<%@ page import="bean.AccountBean" %>
<%--
  Created by IntelliJ IDEA.
  User: Sur.Vival
  Date: 30/11/2015
  Time: 2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%@ page errorPage="notLoggedInYet.jsp" %>
<%
  AccountBean client = (AccountBean) session.getAttribute("userInfo");
%>
<h1>Personal Details</h1>
<div>Name<input type="text" name="name" value="<%=client.getName()%>"></div>
<div>Tel<input type="tel" name="tel" value="<%=client.getTel()%>"></div>
<div>Address<input type="text" name="address" value="<%=client.getAddress()%>"> </div>
<div>New Password<input type="password" name="password"></div>
</body>
</html>
