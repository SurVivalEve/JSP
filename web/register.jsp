<%--
  Created by IntelliJ IDEA.
  User: Sur.Vival
  Date: 23/11/2015
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/register_css.css">
</head>
<body>
<div class="c-top">
    <div id="head">REGISTER</div>
</div>
<form action="registerCheck" method="post">
    <div class="c-main">
        <div>Name:<input type="text" name="name"></div>
        <div>Tel:<input type="text" name="tel"></div>
        <div>Delivery Address:<input type="text" name="deliveryAddress"></div>
        <div>Payment:<input type="text" name="payment"></div>
        <div><input type="hidden" name="action" value="register"></div>
        <div>
            <button type="submit"  id="a-submit">Confirm</button>
        </div>
    </div>
</form>
</body>
</html>
