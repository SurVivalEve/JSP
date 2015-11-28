<%@ page import="bean.AccountBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Maintain client</title>
    <link href="css/manager.css" rel="stylesheet" type="text/css">
    <script src="js/jquery-1.11.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#main").removeClass('tabs pageActive tab_current');
            $("#client").addClass('tabs pageActive tab_current');
            $("#product").removeClass('tabs pageActive tab_current');
            $("#order").removeClass('tabs pageActive tab_current');
        });
    </script>
</head>
<body>
<jsp:include page="m_navigation.jsp" />
<%
    ArrayList<AccountBean>
%>
<%

%>
</body>
</html>
