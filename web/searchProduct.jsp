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
<%@ page import="bean.CategoryBean" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/productlayout.css">
    <link rel="stylesheet" type="text/css" href="css/common.css">
</head>
<body>
<jsp:include page="navigation.jsp"></jsp:include>
<%
    ArrayList<ProductBean> pb = (ArrayList) request.getAttribute("pbArrayList");
    ArrayList<CategoryBean> cb = (ArrayList) request.getAttribute("cbArrayList");
%>
<div id="space-control">
    <hr>
</div>
<div class="c-main">
    <div class="c-left">
        <form method="get" action="ProductList">
            <div class="c-panel">
                <div class="s-panel">
                    <input type="hidden" value="search" name="action">
                    <select name="selectedType">
                        <option selected disabled hidden value=''></option>
                        <%
                            for (int i = 0; i < cb.size(); i++) {
                                out.println("<option value=\"" + cb.get(i).getCategoryID()+ "\">" + cb.get(i).getName() + "</option>");
                            }
                        %>
                    </select>
                </div>
                <div class="c-range">
                    $Min:<input type="text" name="minPrice">
                    $Max:<input type="text" name="maxPrice">
                </div>
                <div>
                    <a>
                        <button type="submit">Search</button>
                    </a>
                    <button type="reset">Reset</button>

                </div>
            </div>
        </form>
        <div class="a-clear"><a href="ProductList?action=all"><button>Show All</button></a></div>
        <div class="c-product">
            <ul>
                <%
                    for (int i = 0; i < pb.size(); i++) {
                        out.println("<li>\n" +
                                "            <div class=\"c-detail\">\n" +
                                "                <div class=\"detail-top\">" + "<img src=\"" + pb.get(i).getPicturePath() + "\"" + ">" + "</div>\n" +
                                "                <div class=\"detail-mid\">" + pb.get(i).getName() + "</div>\n" +
                                "                <div class=\"detail-qty\">Qty:<input type=\"text\" name=\"qty[]\" value=\"1\"></div>\n" +
                                "                <div class=\"detail-id\"><input type=\"hidden\" name=\"id[]\" value=\"" + pb.get(i).getProductID() + "\"></div>\n" +
                                "                <div class=\"detail-down\">" + "$" + pb.get(i).getPrice() + "</div>\n" +
                                "                <div><a><img src=\"img/buy-xxl.png\" width=\"50px\" height=\"50px\"></a></div>\n" +
                                "            </div>\n" +
                                "        </li>");
                    }
                %>
            </ul>
        </div>
    </div>
    <div class="c-right"></div>
</div>

</body>
</html>
