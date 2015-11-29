<%@ page import="bean.AccountBean" %>
<%@ page import="bean.OrdersBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.ProductBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
    <div style="display: block;" class="tabs_item" id="order_detail">
        <div id="message3">
            <%
                OrdersBean ob = (OrdersBean) request.getAttribute("orders");
            %>
            <h1 align="center">Order <%=ob.getOrderID()%> product list</h1>
            <%
                ArrayList<ProductBean> pbs = ob.getProductBeans();
                if(pbs.size()!=0){
                    out.println("<form method='post' action='m_order?action=maintain'>");
                    out.println("<table id='message_table'>");
                    out.println("<colgroup><col style='width:7%'><col style='width:20%'><col style='width:15%'><col style='width:12%'>");
                    out.println("<col style='width:25%'><col style='width:7%'><col style='width:7%'><col style='width:7%'></colgroup>");
                    out.println("<tr><th>ID</th><th>Picture</th><th>Name</th><th>Category</th><th>Description</th><th>Price</th><th>qty</th><th>Amount</th></tr>");
                    for(int i=0; i<pbs.size(); i++) {
                        out.println("<tr><td>" + pbs.get(i).getProductID() + "</td>");
                        out.println("<td><img height='80' width='80' src='"+pbs.get(i).getPicturePath()+"'/></td>");
                        out.println("<td>" + pbs.get(i).getName() + "</td>");
                        out.println("<td>" + pbs.get(i).getCategoryID().getName() + "</td>");
                        out.println("<td>" + pbs.get(i).getDescriptions() + "</td>");
                        out.println("<td>$" + pbs.get(i).getPrice() + "</td>");
                        out.println("<td>" + pbs.get(i).getQty() + "</td>");
                        out.println("<td>$" + pbs.get(i).getPrice()*pbs.get(i).getQty() + "</td>");
                    }
                    out.println("</table>");
                }
                out.println("<p><div id='btn'>");
                out.println("<input class='myButton' type='submit' value='Back'/>");
                out.println("</div></p>");
                out.println("</form>");
            %>
        </div>
    </div>
</div>
</body>
</html>
