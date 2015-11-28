<%--
  Created by IntelliJ IDEA.
  User: Sur.Vival
  Date: 23/11/2015
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/layout.css">
    <script type="text/javascript">
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
        <img src="img/pencil_PNG3860.png" height="100px">
    </div>
    <div class="nav-btn">
        <div class="a-logout">
            <img src="img/mobile_interface-01-512.png">
        </div>
        <div class="left-side">
            <ul>
                <li>Profile</li>
                <li><a href="searchProduct.jsp">Products</a></li>
                <li>Gifts</li>
                <li><a href="shoppingCart.jsp">Shopping Cart</a></li>
                <li>Test 1</li>
            </ul>
        </div>
        <div class="right-side">
            <button onclick="loginOverlay();">Login</button>
            <button onclick="overLay('register.jsp')">Register</button>
        </div>
    </div>
</div>

<div class="c-main">
    <div class="c-left">
    </div>
    <div class="c-right"></div>
</div>
</body>
</html>
