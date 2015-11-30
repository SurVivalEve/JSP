<%@ page import="bean.GiftBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="db.GiftDB" %>
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
<html>
<head>
    <title>RedeemGift</title>
</head>
<body>
<body>
<jsp:include page="navigation.jsp"></jsp:include>
<%
    ArrayList<GiftBean> gb = (ArrayList<GiftBean>) request.getAttribute("g1234");
%>
<div id="space-control">
    <hr>
</div>
<div class="c-main">
    <div class="c-left">
        <form action="redeem" method="get">
            <div class="c-panel">
                <div class="s-panel"></div>
                <input type="hidden" value="search" name="action">
                <div class="c-range">
                    Point Range:
                    <select name="range">
                        <option selected="" disabled="" value="" hidden=""></option>
                        <option value="between 1 and 500">1 ~ 500</option>
                        <option value="between 501 and 1000">501 ~ 1000</option>
                        <option value="between 1001 and 1500">1001 ~ 1500</option>
                        <option value="between 1501 and 2000">1501 ~ 2000</option>
                        <option value=">2000">above 2000</option>
                    </select>
                </div>
                <div>
                    <a>
                        <button type="submit">Search</button>
                    </a>
                    <button type="reset">Reset</button>
                </div>


            </div>
        </form>
        <div class="a-clear">
            <a href="redeem?action=all">
                <button>Show All</button>
            </a>
        </div>
        <div class="c-product">
            <ul>
                <%
                    for (int i = 0; i < gb.size(); i++) {
                        out.println("<li>\n" +
                                "            <div class=\"c-detail\">\n" +
                                "                <div class=\"detail-top\">" + "<img src=\"" + gb.get(i).getPicturePath() + "\"" + ">" + "</div>\n" +
                                "                <div class=\"detail-mid\">" + gb.get(i).getName() + "</div>\n" +
                                "                <div class=\"detail-down\">" + "Requir Bonus" + gb.get(i).getRequireBonus() + "</div>\n" +
                                "                <div><a href=\"redeem?action=redeemProcess&giftID=" + gb.get(i).getId() + "\"><img src=\"img/gifts.jpg\"></a></div>\n" +
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





