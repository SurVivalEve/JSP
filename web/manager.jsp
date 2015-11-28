<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager page</title>
    <link href="css/manager.css" rel="stylesheet" type="text/css">
    <script src="js/jquery-1.11.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#maintain_client").hide();
            $("#maintain_product").hide();
            $("#maintain_order").hide();

            $("#main").click(function(){
                $("#main").addClass('tabs pageActive tab_current');
                $("#client").removeClass('tabs pageActive tab_current');
                $("#product").removeClass('tabs pageActive tab_current');
                $("#order").removeClass('tabs pageActive tab_current');
                $("#select_main").show();
                $("#maintain_client").hide();
                $("#maintain_product").hide();
                $("#maintain_order").hide();
            });

            $("#client").click(function(){
                $("#main").removeClass('tabs pageActive tab_current');
                $("#client").addClass('tabs pageActive tab_current');
                $("#product").removeClass('tabs pageActive tab_current');
                $("#order").removeClass('tabs pageActive tab_current');
                $("#select_main").hide();
                $("#maintain_client").show();
                $("#maintain_product").hide();
                $("#maintain_order").hide();
            });

            $("#product").click(function(){
                $("#main").removeClass('tabs pageActive tab_current');
                $("#client").removeClass('tabs pageActive tab_current');
                $("#product").addClass('tabs pageActive tab_current');
                $("#order").removeClass('tabs pageActive tab_current');
                $("#select_main").hide();
                $("#maintain_client").hide();
                $("#maintain_product").show();
                $("#maintain_order").hide();
            });

            $("#order").click(function(){
                $("#main").removeClass('tabs pageActive tab_current');
                $("#client").removeClass('tabs pageActive tab_current');
                $("#product").removeClass('tabs pageActive tab_current');
                $("#order").addClass('tabs pageActive tab_current');
                $("#select_main").hide();
                $("#maintain_client").hide();
                $("#maintain_product").hide();
                $("#maintain_order").show();
            });

            $("#item_client").click(function(){
                $("#main").removeClass('tabs pageActive tab_current');
                $("#client").addClass('tabs pageActive tab_current');
                $("#product").removeClass('tabs pageActive tab_current');
                $("#order").removeClass('tabs pageActive tab_current');
                $("#select_main").hide();
                $("#maintain_client").show();
                $("#maintain_product").hide();
                $("#maintain_order").hide();
            });

            $("#item_product").click(function(){
                $("#main").removeClass('tabs pageActive tab_current');
                $("#client").removeClass('tabs pageActive tab_current');
                $("#product").addClass('tabs pageActive tab_current');
                $("#order").removeClass('tabs pageActive tab_current');
                $("#select_main").hide();
                $("#maintain_client").hide();
                $("#maintain_product").show();
                $("#maintain_order").hide();
            });

            $("#item_order").click(function(){
                $("#main").removeClass('tabs pageActive tab_current');
                $("#client").removeClass('tabs pageActive tab_current');
                $("#product").removeClass('tabs pageActive tab_current');
                $("#order").addClass('tabs pageActive tab_current');
                $("#select_main").hide();
                $("#maintain_client").hide();
                $("#maintain_product").hide();
                $("#maintain_order").show();
            });
        });
    </script>
</head>
<body>
<div id="topDIV_div">
    <ul id="topDIV_ul" class="SITE_STRUCTURE">
        <li class="web_title">Manager Page</li>
    </ul>
</div>
<div style="position: absolute;" id="top_nav">
    <ul id="nav_bar" class="SITE_STRUCTURE">
        <li style="float: right;">
            <ul id="nav_items">
                <li class="tabs pageActive tab_current" id="main">
                    <div class="nav_word"><a>Main</a></div>
                </li>
                <li class="tabs pageActive tab_other" id="client">
                    <div class="nav_word"><a>Client</a></div>
                </li>
                <li class="tabs pageActive tab_other" id="product">
                    <div class="nav_word"><a>Product</a></div>
                </li>
                <li class="tabs pageActive tab_other" id="order">
                    <div class="nav_word"><a>Order</a></div>
                </li>
                <li class="tabs pageActive tab_other" id="logout">
                    <div class="nav_word"><a>Logout</a></div>
                </li>
            </ul>
        </li>
    </ul>
</div>
<div id="content" class="SITE_STRUCTURE content">
    <div class="tab_content">
        <div style="display: block;" class="tabs_item" id="select_main">
            <div class="select_main">
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
            </div>
        </div>
        <div style="display: block;" class="tabs_item" id="maintain_client">
            <div class="maintain_client">
                maintain client
            </div>
        </div>
        <div style="display: block;" class="tabs_item" id="maintain_product">
            <div class="maintain_product">
                maintain product
            </div>
        </div>
        <div style="display: block;" class="tabs_item" id="maintain_order">
            <div class="maintain_order">
                maintain order
            </div>
        </div>
    </div>
</div>
</body>
</html>
