<%@ page import="bean.OrdersBean" %>
<%--
  Created by IntelliJ IDEA.
  User: Sur.Vival
  Date: 30/11/2015
  Time: 2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="css/profile.css">
<html>
<head>
    <title></title>
</head>
<%
    OrdersBean orderList = (OrdersBean) session.getAttribute("orderList");
%>
<body>
<div class="tg-wrap">
    <table class="tg" style="undefined;table-layout: fixed; width: 100%">
        <colgroup>
            <col style="width: 65px">
            <col style="width: 135px">
            <col style="width: 94px">
            <col style="width: 200px">
            <col style="width: 185px">
            <col style="width: 129px">
            <col style="width: 92px">
        </colgroup>
        <tr>
            <th class="tg-wjqe" colspan="7">Order Details<br></th>
        </tr>
        <tr>
            <td class="tg-h1ln">ID<br></td>
            <td class="tg-h1ln">Client Name<br></td>
            <td class="tg-h1ln">Date</td>
            <td class="tg-h1ln">Status</td>
            <td class="tg-h1ln">Delivery Address<br></td>
            <td class="tg-h1ln">Pick-up Time<br></td>
            <td class="tg-h1ln">Cancelled</td>
        </tr>
        <tr>
            <td class="tg-saa1"><br></td>
            <td class="tg-saa1"><br></td>
            <td class="tg-ry0e"></td>
            <td class="tg-ry0e"></td>
            <td class="tg-ry0e"></td>
            <td class="tg-ry0e"></td>
            <td class="tg-saa1"></td>
        </tr>
    </table>
</div>
</body>
</html>
