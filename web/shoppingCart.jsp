<%--
  Created by IntelliJ IDEA.
  User: Sur.Vival
  Date: 23/11/2015
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/cart_css.css">
</head>
<body>
<hr>
<div class="c-table">
    <table>
        <tr>
            <th>Product Name</th>
            <th>Porduct ID</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Amount</th>
        </tr>
        <tr>
            <td>1</td>
            <td>2</td>
            <td>3</td>
            <td><input type="text" name="qty[]" value="4"></td>
            <td>NULL</td>
            <input type="hidden" name="id[]" value="123456"/>
        </tr>
        <tr>
            <td>5</td>
            <td>6</td>
            <td>7</td>
            <td><input type="text" value="4"></td>
            <td>NULL</td>
        </tr>
        <tr>
            <td>9</td>
            <td>10</td>
            <td>11</td>
            <td><input type="text" value="4"></td>
            <td>NULL</td>
        </tr>
        <tr>
            <td>13</td>
            <td>14</td>
            <td>15</td>
            <td><input type="text" value="4"></td>
            <td>NULL</td>
        </tr>
        <tr>
            <td>17</td>
            <td>18</td>
            <td>19</td>
            <td><input type="text" value="4"></td>
            <td>NULL</td>
        </tr>
    </table>

    <div class="c-total">
        <div class="d-option">
            <div id="op1">Self-Pick<input type="radio" value="self-pick" name="deliveryOption"></div>
            <div id="op2">Delivery<input type="radio" value="delivery" name="deliveryOption" checked="checked"></div>
            <div id="upDate"><button type="submit">Update Shopping Cart</button></div>
        </div>
        <div id="total">Total:<input type="text" readonly></div>
    </div>
</div>
</body>
</html>
