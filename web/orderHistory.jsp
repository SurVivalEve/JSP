<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="bean.OrdersBean" %>
<%@ page import="java.util.ArrayList" %>
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
    <title>Order History</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="css/profile.css">
<html>
<head>
  <title></title>
</head>
<%
  ArrayList<OrdersBean> orderList = (ArrayList<OrdersBean>) session.getAttribute("orderHistory");
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
    <%
      SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
      SimpleDateFormat sf2 = new SimpleDateFormat("dd-MM-yyyy");
      for (int i = 0; i < orderList.size(); i++) {
        out.println("<tr>");
        out.println("<td class=\"tg-jsj9\">"+orderList.get(i).getOrderID()+"</td>");
        out.println("<td class=\"tg-jsj9\">"+orderList.get(i).getClient().getName()+"</td>");
        out.println("<td class=\"tg-jsj9\">"+sf.format(orderList.get(i).getOrderDate())+"</td>");
        out.println("<td class=\"tg-jsj9\">"+orderList.get(i).getStatus()+"</td>");

        if(orderList.get(i).getDeliveryAddress()==null)
          out.println("<td class=\"tg-jsj9\"></td>");
        else
          out.println("<td class=\"tg-jsj9\">"+orderList.get(i).getDeliveryAddress()+"</td>");

        if(orderList.get(i).getPickupTime()==null)
          out.println("<td class=\"tg-jsj9\"></td>");
        else
          out.println("<td class=\"tg-jsj9\">"+sf2.format(orderList.get(i).getPickupTime())+"</td>");

        out.println("<td class=\"tg-jsj9\">"+orderList.get(i).getCancelled()+"</td>");
      }
    %>
  </table>
</div>
</body>
</html>
