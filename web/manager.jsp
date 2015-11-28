<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager page</title>
    <link href="css/manager.css" rel="stylesheet" type="text/css">
    <script src="js/jquery-1.11.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#main").addClass('tabs pageActive tab_current');
            $("#client").removeClass('tabs pageActive tab_current');
            $("#product").removeClass('tabs pageActive tab_current');
            $("#order").removeClass('tabs pageActive tab_current');
        });
    </script>
</head>
<body>
<jsp:include page="m_navigation.jsp" />
<div id="content" class="SITE_STRUCTURE content">
    <div class="tab_content">
        <div style="display: block;" class="tabs_item" id="select_main">
            <div class="select_main">
                <a href="m_client?action=verify">
                    <div class="item_div" id="item_client">
                        <div class="item_border">
                            <div class="item_word_border">
                                <div class="item_word">
                                    <h2 style="font-size: 50px;">Client</h2>
                                </div>
                                <div class="item_word item_word_displayTime">
                                    <span>-&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspVerify client&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span><br/>
                                    <span>- approve credit amount</span><br/>
                                    <span>for client</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
                <a href="m_product.jsp">
                    <div class="item_div" id="item_product">
                        <div class="item_border">
                            <div class="item_word_border">
                                <div class="item_word">
                                    <h2 style="font-size: 50px;">Product</h2>
                                </div>
                                <div class="item_word item_word_displayTime">
                                    <span>- &nbsp&nbspAdd product&nbsp&nbsp</span><br/>
                                    <span>- &nbsp&nbspEdit product&nbsp&nbsp</span><br/>
                                    <span>- Delete product</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
                <a href="m_order.jsp">
                    <div class="item_div" id="item_order">
                        <div class="item_border">
                            <div class="item_word_border">
                                <div class="item_word">
                                    <h2 style="font-size: 50px;">Order</h2>
                                </div>
                                <div class="item_word item_word_displayTime">
                                    <span>- Update order status</span><br/>
                                    <span>- &nbspObtain a report for&nbsp</span><br/>
                                    <span>incomplete orders</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
