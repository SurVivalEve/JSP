<%@ page import="bean.GiftBean" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Sur.Vival
  Date: 29/11/2015
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<link rel="stylesheet" type="text/css" href="css/productlayout.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<%
  GiftBean gift = (GiftBean) request.getAttribute("giftInfo");
%>
<body>
<form action="redeem" method="post">
  <div class="c-detail">
    <div class="detail-top"><img width="50px" height="50px" src=<%=gift.getPicturePath()%> > </div>
    <div class="detail-mid"><%=gift.getName()%> </div>
    <div class="detail-down">Require Bouns:<%=gift.getRequireBonus()%> </div>
    <div><input type="hidden" name="giftID" value="<%=gift.getId()%>"></div>
    <div><input type="hidden" name="action" value="redeem"></div>
    <div><input type="submit" value="Redeem"></div>
  </div>
</form>
</body>
</html>
