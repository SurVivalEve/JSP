<%@ page import="bean.GiftBean" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Sur.Vival
  Date: 23/11/2015
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="css/productlayout.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<style>
    .c-product li img {
        width: 182px;
        height: 100px;
    }
    .c-detail a img {
        width: 197px;
        height: 69px;
    }
</style>
<html>
<head>
    <title></title>
</head>
<body>
<body>
<jsp:include page="navigation.jsp"></jsp:include>
<%
    ArrayList<GiftBean> giftList = (ArrayList) request.getAttribute("giftList");
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
        <div class="a-clear"><a href="redeem?action=all">
            <button>Show All</button>
        </a></div>
        <div class="c-product">
            <ul>
                <%
                    for (int i = 0; i < giftList.size(); i++) {
                        out.println("<li>\n" +
                                "            <div class=\"c-detail\">\n" +
                                "                <div class=\"detail-top\">" + "<img src=\"" + giftList.get(i).getPicturePath() + "\"" + ">" + "</div>\n" +
                                "                <div class=\"detail-mid\">" + giftList.get(i).getName() + "</div>\n" +
                                "                <div class=\"detail-down\">" + "Requir Bonus" + giftList.get(i).getRequireBonus() + "</div>\n" +
                                "                <div><a href=\"redeem?action=redeemProcess&giftID="+giftList.get(i).getId()+"\"><img src=\"img/gifts.jpg\"></a></div>\n" +
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
