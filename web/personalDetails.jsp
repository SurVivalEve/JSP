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
<link rel="stylesheet" type="text/css" href="css/profile.css">
<%
    AccountBean client = (AccountBean) session.getAttribute("userInfo");
%>
<fieldset>
    <legend>Change Personal Details</legend>
    <div class="Data-Content">
        <div class="Data-Title">
            <div class="AlignRight">
                <label for="name">Name：</label><br/>
                <label for="tel">Tel：</label><br/>
                <label for="address">Address：</label><br/>
                <label for="password">New Password：</label><br/>
            </div>
        </div>
        <form action="profile" method="post">
            <input type="hidden" name="action" value="updateDetails">
            <div class="Data-Items">
                <input type="text" id="name" name="name" value="<%=client.getName()%>"/><br/>
                <input type="tel" id="tel" name="tel" value="<%=client.getTel()%>"/><br/>
                <input type="text" id="address" name="address" value="<%=client.getAddress()%>"/><br/>
                <input type="password" name="password" id="password"/><br/>
                <input type="hidden" name="id" value="<%=client.getId()%>">
                <input type="submit" value="Change">
            </div>
        </form>
    </div>
</fieldset>
</body>
</html>
