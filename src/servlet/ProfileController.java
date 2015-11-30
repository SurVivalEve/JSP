package servlet;


import bean.AccountBean;
import bean.OrdersBean;
import db.AccountDB;
import db.OrdersDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Sur.Vival on 30/11/2015.
 */
@WebServlet(name = "ProfileController", urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {
    private AccountDB accountDB;
    private OrdersDB ordersDB;

    @Override
    public void init() throws ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        accountDB = new AccountDB(dbUrl, dbUser, dbPassword);
        ordersDB = new OrdersDB(dbUrl, dbUser, dbPassword);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("updateDetails".equalsIgnoreCase(action)) {
            doChange(req,resp);
        } else if ("showOrder".equalsIgnoreCase(action)) {
            showOrders(req,resp);
        } else if ("changeStatus".equalsIgnoreCase(action)) {
            changeStatus(req,resp);
        } else if ("orderHistory".equalsIgnoreCase(action)) {
            showOrdersHistory(req, resp);
        } else if ("orderDetails".equalsIgnoreCase(action)) {
            showOrdersDetails(req, resp);
        }
    }

    private void showOrdersDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderID = request.getParameter("orderID");
        OrdersBean ordersBean =  ordersDB.queryByID(orderID);
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.setAttribute("order", ordersBean);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/orderProductList.jsp");
            rd.forward(request, response);
        }
    }

    private void changeStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderID = request.getParameter("orderID");
        OrdersBean order = ordersDB.queryByID(orderID);
        order.setCancelled(true);
        ordersDB.editRecord(order);
        response.sendRedirect("orderManage.jsp");
    }

    private void showOrdersHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientID = request.getParameter("clientID");
        ArrayList<OrdersBean> orderList =  ordersDB.queryMyOrdersHistory(clientID);
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.setAttribute("orderHistory", orderList);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/orderHistory.jsp");
            rd.forward(request, response);
        }
    }

    private void showOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientID = request.getParameter("clientID");
        ArrayList<OrdersBean> orderList =  ordersDB.queryMyOrders(clientID);
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.setAttribute("orderList", orderList);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/orderManage.jsp");
            rd.forward(request, response);
        }
    }

    private void doChange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(false);
        if (session != null) {
            if(accountDB.updatePersonalDetails(id,name,tel,address,password)) {
                PrintWriter out = response.getWriter();
                out.println("<b>Account details has been updated!</b>");
                String redirectTarget = "<script type=\"text/javascript\">setTimeout(function(){\ntop.location.href = \"loginCheck?action=logout\";},1500);\n</script>";
                response.getWriter().print(redirectTarget);
                response.flushBuffer();
            }
        }

    }
}
