<%@ page import="bean.ProductBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Maintain product</title>
    <link href="css/manager.css" rel="stylesheet" type="text/css">
    <script src="js/jquery-1.11.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#main").removeClass('tabs pageActive tab_current');
            $("#client").removeClass('tabs pageActive tab_current');
            $("#product").addClass('tabs pageActive tab_current');
            $("#order").removeClass('tabs pageActive tab_current');
        });
    </script>
</head>
<body>
<jsp:include page="m_navigation.jsp" />
<div id="content" class="SITE_STRUCTURE content">
    <div style="display: block;" class="tabs_item" id="select_main">
        <div id="message">
            <%
                ArrayList<ProductBean> pbs = new ArrayList<ProductBean>();
                if(request.getAttribute("products")!=null){
                    pbs = (ArrayList<ProductBean>) request.getAttribute("products");
                    if(pbs.size()!=0){
                        out.println("<form method='post' action='m_client?action=save'>");
                        out.println("<table>");
                        out.println("<colgroup><col style='width:7%'><col style='width:20%'><col style='width:15'><col style='width:12%'>");
                        out.println("<col style='width:25%'><col style='width:7%'><col style='width:7%'><col style='width:7%'></colgroup>");
                        out.println("<tr><th>ID</th><th>Picture</th><th>Name</th><th>Category</th><th>Description</th><th>Price</th></tr>");
                        for(int i=0; i<pbs.size(); i++) {
                            out.println("<tr><td>" + pbs.get(i).getProductID() + "</td>");
                            out.println("<td>" + pbs.get(i).getPicturePath() + "</td>");
                            out.println("<td>" + pbs.get(i).getName() + "</td>");
                            out.println("<td>" + pbs.get(i).getCategoryID().getName() + "</td>");
                            out.println("<td>" + pbs.get(i).getDescriptions() + "</td>");
                            out.println("<td>" + pbs.get(i).getPrice() + "</td>");
                            out.println("<td><a href='?action=edit&productID="+pbs.get(i).getProductID()+"'>edit</a></td>");
                            out.println("<td><a onclick='return confirm(\"Are you sure?\")' href='?action=delete&productID="+pbs.get(i).getProductID()+"'>delete</td>");
                        }
                        out.println("</table>");
                    } else {
                        out.println("<h1>No product in database.</h1>");
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
