<%@ page import="javax.persistence.criteria.Order" %>
<%@ page import="bean.OrdersBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="bean.AccountBean" %>
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
            $('#update_order').hide();
            $('#obtain_report').hide();

            $('#item_client').click(function(){
                $(".select_main").hide();
                $('#update_order').show();
                $('#obtain_report').hide();
            });

            $('#item_product').click(function(){
                $(".select_main").hide();
                $('#update_order').hide();
                $('#obtain_report').show();
            });
        });

        function showSelectMessage(){
            $(".select_main").show();
            $('#update_order').hide();
            $('#obtain_report').hide();
        }

    </script>
</head>
<body>
<%
    AccountBean ab = (AccountBean) session.getAttribute("adminInfo");
    if(ab == null) {
        response.sendRedirect("notLoggedInYet.jsp");
    }
%>
<jsp:include page="m_navigation.jsp" />
<div id="content" class="SITE_STRUCTURE content">
    <div style="display: block;" class="tabs_item" id="select_main">
        <div class="select_main">
            <div class="item_div2" id="item_client">
                <div class="item_border">
                    <div class="item_word_border">
                        <div class="item_word2">
                            <h2 style="font-size: 30px;" >Update order status</h2>
                        </div>
                    </div>
                </div>
            </div>
            <div class="item_div2" id="item_product">
                <div class="item_border">
                    <div class="item_word_border">
                        <div class="item_word2">
                            <h2 style="font-size: 25px;" id="sp">Obtain a report for<br/> incomplete orders</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="display: block;" class="tabs_item" id="update_order">
        <div id="message">
            <h1 align="center">Order List</h1>
            <%
                ArrayList<OrdersBean> obs = new ArrayList<OrdersBean>();
                SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                SimpleDateFormat sf2 = new SimpleDateFormat("dd-MM-yyyy");
                if(request.getAttribute("orders")!=null){
                    obs = (ArrayList<OrdersBean>) request.getAttribute("orders");
                    if(obs.size()!=0){
                        out.println("<form method='post' action='m_order?action=save'>");
                        out.println("<table id='message_table'>");
                        out.println("<colgroup><col style='width:7%'><col style='width:12%'><col style='width:12'><col style='width:12%'>");
                        out.println("<col style='width:10%'><col style='width:25%'><col style='width:12%'><col style='width:10%'></colgroup>");
                        out.println("<tr><th>ID</th><th>Client Name</th><th>Date</th><th>Status</th><th>Total Amount</th>");
                        out.println("<th>Delivery Address</th><th>Pick-up Time</th><th>Cancelled</th></tr>");
                        for(int i=0; i<obs.size(); i++) {
                            out.println("<tr><td><a href='m_showOrderDetail?action=show&orderID="+obs.get(i).getOrderID()+"'/>" + obs.get(i).getOrderID() + "</td>");
                            out.println("<td>" + obs.get(i).getClient().getName() + "</td>");
                            out.println("<td>" + sf.format(obs.get(i).getOrderDate()) + "</td>");
                            if ("Process".equalsIgnoreCase(obs.get(i).getStatus())){
                                out.println("<td><select name='status'>" +
                                        "<option name='Process' value='Process' selected='selected'>Process</option>" +
                                        "<option name='Delivered' value='Delivered'>Delivered</option>" +
                                        "<option name='Picked-up' value='Picked-up'>Picked-up</option>" +
                                        "<option name='Cancel' value='Cancel'>Cancel</option>" +
                                        "</select><input type='hidden' name='orderID' value='"+obs.get(i).getOrderID()+"'/></td>");
                            } else if ("Delivered".equalsIgnoreCase(obs.get(i).getStatus())){
                                out.println("<td><select name='status'>" +
                                        "<option name='Process' value='Process'>Process</option>" +
                                        "<option name='Delivered' value='Delivered' selected='selected'>Delivered</option>" +
                                        "<option name='Picked-up' value='Picked-up'>Picked-up</option>" +
                                        "<option name='Cancel' value='Cancel'>Cancel</option>" +
                                        "</select><input type='hidden' name='orderID' value='"+obs.get(i).getOrderID()+"'/></td>");
                            } else if ("Picked-up".equalsIgnoreCase(obs.get(i).getStatus())){
                                out.println("<td><select name='status'>" +
                                        "<option name='Process' value='Process'>Process</option>" +
                                        "<option name='Delivered' value='Delivered'>Delivered</option>" +
                                        "<option name='Picked-up' value='Picked-up' selected='selected'>Picked-up</option>" +
                                        "<option name='Cancel' value='Cancel'>Cancel</option>" +
                                        "</select><input type='hidden' name='orderID' value='"+obs.get(i).getOrderID()+"'/></td>");
                            } else if ("Cancel".equalsIgnoreCase(obs.get(i).getStatus())){
                                out.println("<td><select name='status'>" +
                                        "<option value='Process' selected='selected'>Process</option>" +
                                        "<option value='Delivered'>Delivered</option>" +
                                        "<option value='Picked-up'>Picked-up</option>" +
                                        "<option value='Cancel' selected='selected'>Cancel</option>" +
                                        "</select><input type='hidden' name='orderID' value='"+obs.get(i).getOrderID()+"'/></td>");
                            }
                            out.println("<td>$" + obs.get(i).getTotalAmount() + "</td>");
                            if(obs.get(i).getDeliveryAddress()==null)
                                out.println("<td></td>");
                            else
                                out.println("<td>" + obs.get(i).getDeliveryAddress() + "</td>");
                            if(obs.get(i).getPickupTime()==null)
                                out.println("<td></td>");
                            else
                                out.println("<td>" + sf2.format(obs.get(i).getPickupTime()) + "</td>");
                            if (obs.get(i).getCancelled()==true)
                                out.println("<td><input type='checkbox' class='checkbox1' checked='checked' name='cancelled' value='"+obs.get(i).getOrderID()+"'/></td>");
                            else
                                out.println("<td><input type='checkbox' class='checkbox1' name='cancelled' value='"+obs.get(i).getOrderID()+"'/></td>");
                        }
                        out.println("</table>");
                        out.println("<p><div id='btn'>");
                        out.println("<input class='myButton' type='submit' value='Save'/>");
                        out.println("<input class='myButton' type='reset' value='reset'/>");
                        out.println("<input class='myButton' type='reset' onClick='showSelectMessage()' value='Cancel'/>");
                        out.println("</div></p>");
                        out.println("</form>");
                    } else {
                        out.println("<h1>No order in database.</h1>");
                    }
                }
            %>
        </div>
    </div>
    <div style="display: block;" class="tabs_item" id="obtain_report">
        <div id="message2">
            <h1 align="center">Incomplete orders List</h1>
            <%
                if(request.getAttribute("orders")!=null){
                    obs = (ArrayList<OrdersBean>) request.getAttribute("orders");
                    if(obs.size()!=0){
                        out.println("<form method='post' action='m_order?action=save'>");
                        out.println("<table id='message_table'>");
                        out.println("<colgroup><col style='width:7%'><col style='width:12%'><col style='width:12'><col style='width:12%'>");
                        out.println("<col style='width:10%'><col style='width:25%'><col style='width:12%'><col style='width:10%'></colgroup>");
                        out.println("<tr><th>ID</th><th>Client Name</th><th>Date</th><th>Status</th><th>Total Amount</th>");
                        out.println("<th>Delivery Address</th><th>Pick-up Time</th><th>Cancelled</th></tr>");
                        for(int i=0; i<obs.size(); i++) {
                            if ("Process".equalsIgnoreCase(obs.get(i).getStatus())) {
                                out.println("<tr><td><a href='m_showOrderDetail?action=show&orderID="+obs.get(i).getOrderID()+"'/>" + obs.get(i).getOrderID() + "</td>");
                                out.println("<td>" + obs.get(i).getClient().getName() + "</td>");
                                out.println("<td>" + sf.format(obs.get(i).getOrderDate()) + "</td>");
                                out.println("<td><select name='status'>" +
                                        "<option name='Process' value='Process' selected='selected'>Process</option>" +
                                        "<option name='Delivered' value='Delivered'>Delivered</option>" +
                                        "<option name='Picked-up' value='Picked-up'>Picked-up</option>" +
                                        "<option name='Cancel' value='Cancel'>Cancel</option>" +
                                        "</select><input type='hidden' name='orderID' value='"+obs.get(i).getOrderID()+"'/></td>");
                                out.println("<td>$" + obs.get(i).getTotalAmount() + "</td>");
                                if (obs.get(i).getDeliveryAddress() == null)
                                    out.println("<td></td>");
                                else
                                    out.println("<td>" + obs.get(i).getDeliveryAddress() + "</td>");
                                if (obs.get(i).getPickupTime() == null)
                                    out.println("<td></td>");
                                else
                                    out.println("<td>" + sf2.format(obs.get(i).getPickupTime()) + "</td>");
                                if (obs.get(i).getCancelled() == true)
                                    out.println("<td><input type='checkbox' class='checkbox1' checked='checked' name='cancelled' value='" + obs.get(i).getOrderID() + "'/></td>");
                                else
                                    out.println("<td><input type='checkbox' class='checkbox1' name='cancelled' value='" + obs.get(i).getOrderID() + "'/></td>");
                            }
                        }
                        out.println("</table>");
                        out.println("<p><div id='btn'>");
                        out.println("<input class='myButton' type='submit' value='Save'/>");
                        out.println("<input class='myButton' type='reset' value='reset'/>");
                        out.println("<input class='myButton' type='reset' onClick='showSelectMessage()' value='Cancel'/>");
                        out.println("</div></p>");
                        out.println("</form>");
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
