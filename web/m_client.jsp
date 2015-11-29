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
<div id="content" class="SITE_STRUCTURE content">
    <div style="display: block;" class="tabs_item" id="select_main">
        <div id="message">
            <form method="post" action="m_client?action=save">
                <%
                    ArrayList<AccountBean> abs = (ArrayList<AccountBean>) request.getAttribute("accounts");
                    if(abs.size()!=0){
                        out.println("<table>");
                        out.println("<colgroup><col style='width:10%'><col style='width:10%'><col style='width:20'>");
                        out.println("<col style='width:10%'><col style='width:40%'><col style='width:10%'></colgroup>");
                        out.println("<tr><th>ID</th><th>Password</th><th>Name</th><th>Tel</th><th>Address</th><th>Validation</th></tr>");
                        for(int i=0; i<abs.size(); i++) {
                            if(abs.get(i).isValidation()!=null){
                                if (abs.get(i).isValidation().equalsIgnoreCase("N")) {
                                    out.println("<tr><td>" + abs.get(i).getId() + "</td>");
                                    out.println("<td>" + abs.get(i).getPassword() + "</td>");
                                    out.println("<td>" + abs.get(i).getName() + "</td>");
                                    out.println("<td>" + abs.get(i).getTel() + "</td>");
                                    out.println("<td>" + abs.get(i).getAddress() + "</td>");
                                    out.println("<td><input type='checkbox' name='Validation' value='"+abs.get(i).getId()+"'/></td>");
                                }
                            }
                        }
                        out.println("</table>");
                    } else {
                        out.println("<h1>No account is waited for verify.</h1>");
                    }
                %>
                <input type="submit" value="Save"/>
                <input type="reset" value="Cancel"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>
