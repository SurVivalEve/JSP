<%@ page import="bean.AccountBean" %>
<%@ page import="db.AccountDB" %>
<%--
  Created by IntelliJ IDEA.
  User: matthew
  Date: 29/11/2015
  Time: 0:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/layout.css">
    <script type="text/javascript">

        function disableAfterLogin() {
            var target = document.getElementsByClassName("right-side");
            target.style().visibility = "hidden";
        }

        function overLay(srcData) {
            var s = document.querySelector("#overlay-content > iframe:nth-child(2)");
            s.src = srcData;
            var target = document.getElementById("overlay");
            target.style.visibility = " visible";
            var target = document.getElementById("overlay-content");
            target.style.visibility = " visible";
        }

        function quitOverLay() {
            var target = document.getElementById("overlay");
            target.style.visibility = " hidden";
            var target = document.getElementById("overlay-content");
            target.style.visibility = " hidden";
            var target = document.getElementById("login-content");
            target.style.visibility = " hidden";
        }

        function loginOverlay() {
            var target = document.getElementById("overlay");
            target.style.visibility = " visible";
            var target = document.getElementById("login-content");
            target.style.visibility = " visible";
        }

        function submit1() {
            document.getElementById("l-sub").submit();
        }
    </script>

    <%
        AccountBean client = null;
        AccountDB db = null;
        AccountBean newClient = null;
        try {
             client = (AccountBean) session.getAttribute("userInfo");
             db = new AccountDB("jdbc:mysql://kazechan.ddns.net:3306/jsp","jsp","jsp");
             newClient = db.queryByID(client.getId());
        } catch (NullPointerException ex) {

        }
    %>
</head>
<body>
<div class="nav-bar">
    <div id="overlay" onclick="quitOverLay();">
    </div>
    <form method="post" action="loginCheck" id="l-sub">
        <div id="login-content">
            <input type="hidden" name="action" value="authenticate"/>

            <div id="l-top">
                WELCOME
            </div>
            <div id="l-username">USERNAME :<input type="text" maxlength="10" size="15" name="username"></div>
            <div id="l-password">PASSWORD :<input type="password" name="password" maxlength="10" size="15"></div>
            <div id="l-submit">
                <button type="submit">Login</button>
            </div>
        </div>
    </form>

    <div id="overlay-content">
        <div class="top">
            <div class="left">
                <div class="non-close"></div>
            </div>
            <div class="right">
                <div class="close" onclick="quitOverLay()"><img
                        src="img/070816-glossy-black-icon-alphanumeric-circled-x2.png" height="50px"></div>
            </div>
        </div>
        <iframe id="f-con"></iframe>
    </div>

    <div class="logo">
        <a href="index.jsp"><img src="img/pencil_PNG3860.png" height="100px"></a>

        <div class="c-client">
            <%
                try {
                    if (client != null)
                        out.println("<div> Welcome - " + client.getName() + "</div>");
                        out.println("<div>Account amount - " + newClient.getAmount() + "</div>");
                        out.println("<div>Bouns Point - " + newClient.getBounsPoint() + "</div>");
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
            %>
        </div>
    </div>
    <div class="nav-btn">
        <div class="a-logout">
            <a href="loginCheck?action=logout"> <img src="img/mobile_interface-01-512.png"> </a>
        </div>
        <div class="left-side">
            <ul>
                <li>Profile</li>
                <li><a href="ProductList?action=all">Products</a></li>
                <li><a href="redeem?action=all">RedeemGift</a></li>
                <li><a href="shoppingCart.jsp">Shopping Cart</a></li>
                <%
                    try {
                        if (client != null)
                            out.print("<li><a href=\"recharge.jsp\">Recharge Funds</a></li>");
                            out.println("<script>disableAfterLogin();</script>");
                    } catch (NullPointerException ex) {
                        ex.printStackTrace();
                    }
                %>
            </ul>
        </div>
        <div class="right-side">
            <button onclick="loginOverlay();">Login</button>
            <button onclick="overLay('register.jsp')">Register</button>
        </div>
    </div>
</div>
</body>
</html>
