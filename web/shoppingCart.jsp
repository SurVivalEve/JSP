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
    <title>Shopping Cart</title>
    <link rel="stylesheet" type="text/css" href="css/cart.css">
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <script type="text/javascript">
        function addressNoShow() {
            var target = document.querySelector(".c-address");
            target.style.visibility = " hidden";
            var target = document.querySelector(".c-address > div:nth-child(2) > input:nth-child(1)");
            target.value=null;
        }

        function addressShow() {
            var target = document.querySelector(".c-address");
            target.style.visibility = "visible";
        }
    </script>
</head>
<body>
<%
    ShoppingCartBean scb = null;
    try {
        scb = (ShoppingCartBean) session.getAttribute("shoppingCart");
    } catch (NullPointerException ex) {
        ex.printStackTrace();
    }

    if (scb == null) {
        String redirectURL = "ProductList?action=all";
        response.sendRedirect(redirectURL);
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
                for (int i = 0; i < scb.getX().size(); i++) {
                    out.println("<tr>" +

                            "<form method=\"get\" action=\"ShoppingCart\"><td>" + scb.getX().get(i).getName() + "</td>" +
                            "<td>" + scb.getX().get(i).getProductID() + "</td>" +
                            "<td>" + scb.getX().get(i).getPrice() + "</td>" +
                            "<td><input type=\"text\" name=\"qty\" value=\"" + scb.getX().get(i).getQty() + "\"></td>" +
                            "<td>" + scb.getX().get(i).getQty() * scb.getX().get(i).getPrice() + "</td>" +
                            "<td class=\"icon-edit\"><button type=\"submit\"><img src=\"img/b_edit.png\"></button></td>" +
                            "<td class=\"icon\"><a href=\"ShoppingCart?action=remove&itemID=" + scb.getX().get(i).getProductID() + "\"><img src=\"img/b_drop.png\"" + "/></a></td>" +
                            "<input type=\"hidden\" name=\"itemID\" value=\"" + scb.getX().get(i).getProductID() + "\">" +
                            "<input type=\"hidden\" name=\"action\" value=\"edit\">" +
                            "</form></tr>");
                }
            }
        %>
    </table>
    <form method="get" action="CreateOrder">
        <div class="c-total">

            <div class="d-option">
                <div id="op1">Self-Pick<input type="radio" value="self-pick" name="deliveryOption"
                                              onchange="addressNoShow();"></div>
                <div id="op2">Delivery<input type="radio" value="delivery" name="deliveryOption"
                                             onchange="addressShow()" checked="checked">
                </div>
                <div id="upDate">
                    <button type="submit">Update Shopping Cart</button>
                </div>
            </div>
            <%
                if (scb != null) {
                    int tA = 0;
                    for (int i = 0; i < scb.getX().size(); i++) {
                        tA += (scb.getX().get(i).getPrice() * scb.getX().get(i).getQty());
                    }
                    out.println("<div id=\"total\">Total :$ " + tA + "</div>");
                }
            %>
        </div>
        <hr id="nono">
        <div class="c-address">
            <div>Address :</div>
            <div #="12345"><input type="text" name="dAddress"></div>
        </div>
        <div><input type="submit" value="Create Order"></div>
    </form>
</div>

</body>
</html>
