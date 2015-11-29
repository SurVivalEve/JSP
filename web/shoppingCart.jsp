<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.ShoppingCartBean" %><%--
  Created by IntelliJ IDEA.
  User: Sur.Vival
  Date: 23/11/2015
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/cart.css">
    <link rel="stylesheet" type="text/css" href="css/common.css">
</head>
<body>
<%
    ArrayList<ShoppingCartBean> scb = null;
    try {
        scb = (ArrayList) session.getAttribute("shoppingCart");
    } catch (NullPointerException ex) {
        ex.printStackTrace();
    }
%>
<jsp:include page="navigation.jsp"></jsp:include>
<div id="space-control">
    <hr>
</div>
<div class="c-table">
    <table>
        <tr>
            <th>Product Name</th>
            <th>Porduct ID</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Amount</th>
        </tr>
        <%
            if (scb != null) {
                for (int i = 0; i < scb.size(); i++) {
                    out.println("<tr>" +
                            "<td>" + scb.get(i).getX().get(i).getProductID() + "</td>" +
                            "<td>" + scb.get(i).getX().get(i).getName() + "</td>" +
                            "<td>" + scb.get(i).getX().get(i).getPrice() + "</td>" +
                            "<td><input type=\"text\" name=\"qty\" value=\"" + scb.get(i).getX().get(i).getQty() + "\"></td>" +
                            "<td>" + "</td>" +
                            "</tr>");
                }
            }
        %>
        <tr>
            <td>1</td>
            <td>2</td>
            <td>3</td>
            <td><input type="text" name="qty[]" value="4"></td>
            <td>NULL</td>
            <input type="hidden" name="id[]" value="123456"/>
        </tr>
    </table>

    <div class="c-total">
        <div class="d-option">
            <div id="op1">Self-Pick<input type="radio" value="self-pick" name="deliveryOption"></div>
            <div id="op2">Delivery<input type="radio" value="delivery" name="deliveryOption" checked="checked"></div>
            <div id="upDate">
                <button type="submit">Update Shopping Cart</button>
            </div>
        </div>
        <div id="total">Total:<input type="text" readonly></div>
    </div>
</div>
</body>
</html>
