<%@ page import="bean.ProductBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Maintain product</title>
    <link href="css/manager.css" rel="stylesheet" type="text/css">
    <script src="js/jquery-1.11.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#main").removeClass('tabs pageActive tab_current');
            $("#client").removeClass('tabs pageActive tab_current');
            $("#product").addClass('tabs pageActive tab_current');
            $("#order").removeClass('tabs pageActive tab_current');
            $("#editMessage").hide();

            $("#Price").blur(function(){
                if($("#Price").val()<0)

            });
        });

        function showEditMessage(productID, picturePath, name, categoryName, Description, Price) {
            $("#editMessage").show();
            $("#productID").val(productID);
            $("#picturePath").val(picturePath);
            $("#name").val(name);
            $("#categoryName").val(categoryName);
            $("#Description").val(Description);
            $("#Price").val(Price);
            $("#message").hide();
        }

        function showMessage() {
            $("#editMessage").hide();
            $("#productID").val("");
            $("#picturePath").val("");
            $("#name").val("");
            $("#categoryName").val("");
            $("#Description").val("");
            $("#Price").val("0");
            $("#message").show();
        }
    </script>
</head>
<body>
<jsp:include page="m_navigation.jsp" />
<div id="content" class="SITE_STRUCTURE content">
    <div style="display: block;" class="tabs_item" id="select_main">
        <div id="editMessage">
            <form method="post" action="edit">
                <table id="edit_table">
                    <tr><td class="edit">Product ID:</td><td class="edit"><input type="text" id="productID" value=""/></td></tr>
                    <tr><td class="edit">Picture:</td><td class="edit"><input type="text" id="picturePath" value=""/></td></tr>
                    <tr><td class="edit">Name:</td><td class="edit"><input type="text" id="name" value=""/></td></tr>
                    <tr><td class="edit">Category name:</td><td class="edit"><input type="text" id="categoryName" value=""/></td></tr>
                    <tr><td class="edit">Description:</td><td class="edit"><input type="text" id="Description" value=""/></td></tr>
                    <tr><td class="edit">Price:</td><td class="edit"><input type="text" id="Price" value=""/></td></tr>
                    <tr><td colspan="2"><input class="myButton" style="display: block; width: 100%;" type="submit" value="Edit"/></td></tr>
                    <tr><td colspan="2"><input class="myButton"  style="display: block; width: 100%;" type="button" onclick="showMessage()" value="Cancel"/></td></tr>
                </table>
            </form>
        </div>
        <div id="message">
            <%
                ArrayList<ProductBean> pbs = new ArrayList<ProductBean>();
                if(request.getAttribute("products")!=null){
                    pbs = (ArrayList<ProductBean>) request.getAttribute("products");
                    if(pbs.size()!=0){
                        out.println("<form method='post' action='m_client?action=save'>");
                        out.println("<table id='message_table'>");
                        out.println("<colgroup><col style='width:7%'><col style='width:20%'><col style='width:15'><col style='width:12%'>");
                        out.println("<col style='width:25%'><col style='width:7%'><col style='width:7%'><col style='width:7%'></colgroup>");
                        out.println("<tr><th>ID</th><th>Picture</th><th>Name</th><th>Category</th><th>Description</th><th>Price</th></tr>");
                        for(int i=0; i<pbs.size(); i++) {
                            out.println("<tr><td>" + pbs.get(i).getProductID() + "</td>");
                            out.println("<td>" + pbs.get(i).getPicturePath() + "</td>");
                            out.println("<td>" + pbs.get(i).getName() + "</td>");
                            out.println("<td>" + pbs.get(i).getCategoryID().getName() + "</td>");
                            out.println("<td>" + pbs.get(i).getDescriptions() + "</td>");
                            out.println("<td>" + pbs.get(i).getPrice() + "</td>");
                            out.println("<td><a onclick='showEditMessage(" +
                                    "\"" + pbs.get(i).getProductID() + "\"," +
                                    "\"" + pbs.get(i).getPicturePath() + "\"," +
                                    "\"" + pbs.get(i).getName() + "\"," +
                                    "\"" + pbs.get(i).getCategoryID().getName() + "\"," +
                                    "\"" + pbs.get(i).getDescriptions() + "\"," +
                                    "\"" + pbs.get(i).getPrice() +
                                    "\")'><img src='img/b_edit.png'/>edit</a></td>");
                            out.println("<td><a onclick='return confirm(\"Are you sure?\")' href='?action=delete&productID="
                                    +pbs.get(i).getProductID()+"'><img src='img/b_drop.png'/>delete</td>");
                        }
                        out.println("</table>");
                    } else {
                        out.println("<h1>No product in database.</h1>");
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
