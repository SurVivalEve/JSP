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

<div class="c-main">
    <div>Username:<input type="text"></div>
    <div>Tel:<input type="text"></div>
    <div>Delivery:<input type="text"></div>
    <div>Address:<input type="text"></div>
    <div>Payment:<input type="text"></div>
    <div><input type="hidden" name="action" value="register"></div>
    <div>
        <button type="submit"  id="a-submit">Confirm</button>
    </div>
</div>
</body>
</html>
