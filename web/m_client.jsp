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

            $('#selectAll').click(function(event) {
                if(this.checked) {
                    $('.checkbox1').each(function() {
                        this.checked = true;
                    });
                }else{
                    $('.checkbox1').each(function() {
                        this.checked = false;
                    });
                }
            });

            $('.checkbox1').click(function(event) {
                if(!this.checked){
                    $('#selectAll').each(function() {
                        this.checked = false;
                    });
                } else if ($('.checkbox1:checked').length == $('.checkbox1').length) {
                    $('#selectAll').each(function() {
                        this.checked = true;
                    });
                }
            });
        });
    </script>
</head>
<body>
<jsp:include page="m_navigation.jsp" />
<div id="content" class="SITE_STRUCTURE content">
    <div style="display: block;" class="tabs_item" id="select_main">
        <div id="message">
                <%
                    ArrayList<AccountBean> abs = new ArrayList<AccountBean>();
                    if(request.getAttribute("accounts")!=null){
                        abs = (ArrayList<AccountBean>) request.getAttribute("accounts");
                        if(abs.size()!=0){
                            out.println("<form method='post' action='m_client?action=save'>");
                            out.println("<table>");
                            out.println("<colgroup><col style='width:10%'><col style='width:10%'><col style='width:20'>");
                            out.println("<col style='width:10%'><col style='width:40%'><col style='width:10%'></colgroup>");
                            out.println("<tr><th>ID</th><th>Password</th><th>Name</th><th>Tel</th><th>Address</th><th>Validation<input type='checkbox' id='selectAll'/></th></tr>");
                            for(int i=0; i<abs.size(); i++) {
                                out.println("<tr><td>" + abs.get(i).getId() + "</td>");
                                out.println("<td>" + abs.get(i).getPassword() + "</td>");
                                out.println("<td>" + abs.get(i).getName() + "</td>");
                                out.println("<td>" + abs.get(i).getTel() + "</td>");
                                out.println("<td>" + abs.get(i).getAddress() + "</td>");
                                out.println("<td><input type='checkbox' class='checkbox1' name='ValidationID' value='"+abs.get(i).getId()+"'/></td>");
                            }
                            out.println("</table>");
                            out.println("<div id='btn'>");
                                out.println("<input type='submit' value='Save'/>");
                                out.println("<input type='reset' value='Cancel'/>");
                            out.println("</div>");
                        } else {
                            out.println("<h1>No account is waited for verify.</h1>");
                        }
                    }
                    if(request.getAttribute("update")!=null) {
                        if ("Y".equalsIgnoreCase((String) request.getAttribute("update"))) {
                            out.println("<h1>Update Successfully !</h1>");
                        } else if ("N".equalsIgnoreCase((String) request.getAttribute("update"))) {
                            out.println("<h1>Update Fail !</h1>");
                        } else {
                            out.println("<h1>Please select at least one account!</h1>");
                        }
                    }
                %>
        </div>
    </div>
</div>
</body>
</html>
