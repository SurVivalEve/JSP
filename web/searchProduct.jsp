<%--
  Created by IntelliJ IDEA.
  User: Sur.Vival
  Date: 23/11/2015
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="db.ProductDB" %>
<%@ page import="bean.ProductBean" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/productlayout_css.css">
</head>
<body>
<jsp:include page="navigation.jsp"></jsp:include>
<%
    ArrayList<ProductBean> pb = (ArrayList) request.getAttribute("pbArrayList");
%>
<div class="p-search">
</div>
<div class="c-product">
    <ul>
        <%
            for (int i = 0; i < pb.size(); i++) {
                out.println("<li>\n" +
                        "            <div class=\"c-detail\">\n" +
                        "                <div class=\"detail-top\">" + pb.get(i).getPicturePath() + "</div>\n" +
                        "                <div class=\"detail-mid\">" + pb.get(i).getName() + "</div>\n" +
                        "                <div class=\"detail-down\">" + "$" + pb.get(i).getPrice() + "</div>\n" +
                        "                <div>a link</div>\n" +
                        "            </div>\n" +
                        "        </li>");
            }
        %>
    </ul>
</div>
</body>
</html>
