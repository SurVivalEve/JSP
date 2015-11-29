        <%@ page import="bean.AccountBean" %>
<%--
  Created by IntelliJ IDEA.
  User: Sur.Vival
  Date: 27/11/2015
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <script src="js/jquery.payment.js"></script>
    <link rel="stylesheet" type="text/css" href="css/recharge.css">

    <script>
        jQuery(function ($) {
            $('.cc-number').payment('formatCardNumber');
            $('.cc-exp').payment('formatCardExpiry');
            $('.cc-cvc').payment('formatCardCVC');

            $.fn.toggleInputError = function (erred) {
                this.parent('.form-group').toggleClass('has-error', erred);
                return this;
            };



        });
    </script>
</head>
<%@ page language="java" errorPage="notLoggedInYet.jsp" %>
<%
    AccountBean client = (AccountBean) session.getAttribute("userInfo");
%>
<body>
<jsp:include page="navigation.jsp"></jsp:include>
<form action="recharge" method="post">
    <input type="hidden" name="action" value="recharge">
    <legend>Online Recharge</legend>
    Recharge account : <%=client.getId()%>
    <br/><br/>
    Account name : <%=client.getName()%>
    <br/><br/>
    Recharge amount : <input type="number" name="amount" min="500" max="50000"> HKD
    <br/><br/>
    Payment Method :
    <select>
        <option selected="selected">Visa</option>
    </select>
    <br/><br/>

    <div class="container">
        <div class="form-group">
            <label for="cc-number" class="control-label">Card number formatting : </label>
            <input id="cc-number" type="tel" class="input-lg form-control cc-number" autocomplete="cc-number"
                   placeholder="•••• •••• •••• ••••" required>
        </div>
        <br/>

        <div class="form-group">
            <label for="cc-exp" class="control-label">Card expiry formatting : </label>
            <input id="cc-exp" type="tel" class="input-lg form-control cc-exp" autocomplete="cc-exp"
                   placeholder="•• / ••" required>
        </div>
        <br/>

        <div class="form-group">
            <label for="cc-cvc" class="control-label">Card CVC formatting : </label>
            <input id="cc-cvc" type="tel" class="input-lg form-control cc-cvc" autocomplete="off" placeholder="•••"
                   required>
        </div>
        <br/>
    </div>
    <input type="submit" value="Recharge"/>
</form>
</body>
</html>
