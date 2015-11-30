<%@ page import="bean.OrdersBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="db.OrdersDB" %>
<%@ page import="bean.AccountBean" %>
<%--
  Created by IntelliJ IDEA.
  User: Sur.Vival
  Date: 30/11/2015
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Product List</title>
</head>
<link rel="stylesheet" type="text/css" href="css/orderProductList.css">
<body>
<%
    AccountBean client = (AccountBean) session.getAttribute("userInfo");
    if (client == null)
        throw new NullPointerException();

    OrdersBean order = (OrdersBean) session.getAttribute("order");
%>
<div class="tg-wrap">
    <table class="tg" style="undefined;table-layout: fixed; width: 100%">
        <colgroup>
            <col style="width: 65px">
            <col style="width: 135px">
            <col style="width: 94px">
            <col style="width: 200px">
            <col style="width: 185px">
            <col style="width: 129px">
            <col style="width: 75px">
            <col style="width: 114px">
        </colgroup>
        <tr>
            <th class="tg-wjqe" colspan="8">Order Details<br></th>
        </tr>
        <tr>
            <td class="tg-h1ln">ID<br></td>
            <td class="tg-h1ln">Picture<br></td>
            <td class="tg-h1ln">Name</td>
            <td class="tg-h1ln">Category</td>
            <td class="tg-h1ln">Description<br></td>
            <td class="tg-h1ln">Price<br></td>
            <td class="tg-h1ln">Qty</td>
            <td class="tg-h1ln">Amount</td>
        </tr>
        <%
            for (int i = 0; i < order.getProductBeans().size(); i++) {
                OrdersDB orderDB = new OrdersDB("jdbc:mysql://kazechan.ddns.net:3306/jsp", "jsp", "jsp");
                ArrayList<OrdersBean> newOrderList = orderDB.queryMyOrders(order.getClient().getId());
                out.println("<tr>");
                out.println("<td class=\"tg-jsj9\">" + order.getProductBeans().get(i).getProductID() + "</td>");
                out.println("<td class=\"tg-jsj9\"><img width='100' height='100' src=\"" + order.getProductBeans().get(i).getPicturePath() + "\"></td>");
                out.println("<td class=\"tg-jsj9\">" + order.getProductBeans().get(i).getName() + "</td>");
                out.println("<td class=\"tg-jsj9\">" + order.getProductBeans().get(i).getCategoryID().getName() + "</td>");
                out.println("<td class=\"tg-jsj9\">" + order.getProductBeans().get(i).getDescriptions() + "</td>");
                out.println("<td class=\"tg-jsj9\">" + order.getProductBeans().get(i).getPrice() + "</td>");
                out.println("<td class=\"tg-jsj9\">" + order.getProductBeans().get(i).getQty() + "</td>");
                out.println("<td class=\"tg-jsj9\">$" + order.getProductBeans().get(i).getQty() * order.getProductBeans().get(i).getQty() + "</td>");
            }
        %>
    </table>
    <button id="myButton">Back</button>

    <script type="text/javascript">
        function show(location) {
            document.getElementById("myIframe").src = location;
        }

        document.getElementById("myButton").onclick = function () {
            location.href = "profile?action=showOrder&clientID=<%=client.getId()%>";
        };
    </script>
</div>
</body>
</html>
