<%--
  Created by IntelliJ IDEA.
  User: Sur.Vival
  Date: 23/11/2015
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form method="get" action="registerCheck">
    <input type="hidden" name="action" value="register"/>
    <table border="0">
        <td><p align="right">Name:</p></td>
        <td><p><input type="text" name="name" maxlength="10" size="15"></p></td>
        </tr>
        <tr>
            <td><p align="right">Tel:</p></td>
            <td><p><input type="text" name="tel" maxlength="10" size="15"></p></td>
        </tr>
        <tr>
            <td><p align="right">Delivery Address:</p></td>
            <td><p><input type="text" name="deliveryAddress" maxlength="10" size="15"></p></td>
        </tr>
        <tr>
            <td><p align="right">Payment amount:</p></td>
            <td><p><input type="number" name="payment" maxlength="10" size="15"></p></td>
        </tr>
        <tr>
            <td colspan="2"><p align="center"><input type="submit" value="Login"></p></td>
        </tr>
    </table>
</form>
</body>
</html>
