<%@ page import="bean.AccountBean" %>
<%--
  Created by IntelliJ IDEA.
  User: Sur.Vival
  Date: 27/11/2015
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="css/recharge.css">
</head>
<%
    AccountBean client = (AccountBean) session.getAttribute("userInfo");
%>
<body>
<form id="formCharge" action="recharge" method="post">
    <input type="hidden" name="action" value="recharge">
    <fieldset>
        <legend>Online Recharge</legend>
        Recharge account : <%=client.getId()%>
        <br/><br/>
        Account name : <%=client.getName()%>
        <br/><br/>
        Recharge amount : <input type="number" name="amount" min="500" max="50000"> HKD
        <br/><br/>
        Payment Method :
        <div>
            <ul style="list-style: none; border: 1">
                <li>Visa</li>
                <li>Master</li>
            </ul>
        </div>
        <input type="submit" value="Recharge">
    </fieldset>
</form>
</body>
</html>
