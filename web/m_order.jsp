<%@ page import="javax.persistence.criteria.Order" %>
<%@ page import="bean.OrdersBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Maintain order</title>
    <link href="css/manager.css" rel="stylesheet" type="text/css">
    <script src="js/jquery-1.11.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#main").removeClass('tabs pageActive tab_current');
            $("#client").removeClass('tabs pageActive tab_current');
            $("#product").removeClass('tabs pageActive tab_current');
            $("#order").addClass('tabs pageActive tab_current');
        });
    </script>
</head>
<body>
<jsp:include page="m_navigation.jsp" />
<div id="content" class="SITE_STRUCTURE content">
    <div style="display: block;" class="tabs_item" id="select_main">
        <div id="editMessage">
            <form method="post" action="m_order?action=edit">
                <table id="edit_table">
                    <tr><td class="edit">Product ID:</td><td class="edit"><input type="text" name="productID" id="productID"/></td></tr>
                    <tr><td class="edit">Picture:</td><td class="edit"><input type="text" name="picturePath" id="picturePath"/></td></tr>
                    <tr><td class="edit">Name:</td><td class="edit"><input type="text" name="name" id="name"/></td></tr>
                    <tr><td class="edit">Category name:</td><td class="edit"><input type="text" name="categoryName" id="categoryName"/></td></tr>
                    <tr><td class="edit">Description:</td><td class="edit"><input type="text" name="description" id="description"/></td></tr>
                    <tr><td class="edit">Price:</td><td class="edit"><input type="text" name="price" id="price"/></td></tr>
                    <tr><td colspan="2"><input class="myButton" style="display: block; width: 100%;" type="submit" onclick='return confirm("Are you sure?")' value="Edit"/></td></tr>
                    <tr><td colspan="2"><input class="myButton"  style="display: block; width: 100%;" type="button" onclick="showMessage()" value="Cancel"/></td></tr>
                </table>
            </form>
        </div>
        <div id="message">
            <h1 align="center">Product List</h1>
            <%
                ArrayList<OrdersBean> obs = new ArrayList<OrdersBean>();
                if(request.getAttribute("orders")!=null){
                    obs = (ArrayList<OrdersBean>) request.getAttribute("orders");
                    if(obs.size()!=0){
                        out.println("<form method='post' action='m_client?action=save'>");
                        out.println("<table id='message_table'>");
                        out.println("<colgroup><col style='width:7%'><col style='width:20%'><col style='width:15'><col style='width:12%'>");
                        out.println("<col style='width:25%'><col style='width:7%'><col style='width:7%'><col style='width:7%'></colgroup>");
                        out.println("<tr><th>ID</th><th>Client Name</th><th>Date</th><th>Status</th><th>Total Amount</th>");
                        out.println("<th>Delivery Address</th><th>Pick-up Time</th><th>Cancelled</th></tr>");
                        for(int i=0; i<obs.size(); i++) {
                            out.println("<tr><td>" + obs.get(i).getOrderID() + "</td>");
                            out.println("<td>" + obs.get(i).getClient().getName() + "</td>");
                            out.println("<td>" + obs.get(i).getOrderDate() + "</td>");
                            out.println("<td>" + obs.get(i).getStatus() + "</td>");
                            out.println("<td>" + obs.get(i).getTotalAmount() + "</td>");
                            out.println("<td>" + obs.get(i).getDeliveryAddress() + "</td>");
                            out.println("<td>" + obs.get(i).getPickupTime() + "</td>");
                            out.println("<td>" + obs.get(i).getCancelled() + "</td>");
                        }
                        out.println("</table>");
                    } else {
                        out.println("<h1>No order in database.</h1>");
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
