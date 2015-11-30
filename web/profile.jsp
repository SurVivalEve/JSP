<%@ page import="bean.AccountBean" %>
<%--
  Created by IntelliJ IDEA.
  User: Sur.Vival
  Date: 30/11/2015
  Time: 1:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<link rel="stylesheet" type="text/css" href="css/profile.css">
<script src="profile.js" language="javascript"></script>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<%
    AccountBean client = (AccountBean) session.getAttribute("userInfo");
    if(client==null)
        throw new NullPointerException();
%>
<%@page errorPage="notLoggedInYet.jsp" %>
<script>
    function show(location) {
        document.getElementById("myIframe").src = location;
    }
</script>
<div id="header">
    <h1>Profile Setting</h1>
</div>
<div id="nav">
    <a href="index.jsp"><b>Home</b></a><br>
    <a href="javascript:void(0);" onclick="show('profile?action=showOrder&clientID=<%=client.getId()%>')"><b>Order manage</b></a><br>
    <a href="javascript:void(0);" onclick="show('profile?action=orderHistory&clientID=<%=client.getId()%>')"><b>Order History</b></a><br>
    <a href="javascript:void(0);" onclick="show('personalDetails.jsp')"><b>Personal information</b></a><br>

</div>

<div id="section">
    <iframe frameborder="0" id="myIframe">

    </iframe>
</div>

<div id="footer">
    Stationery Station
</div>

</body>
</html>

</html>
