package servlet;

import bean.OrdersBean;
import db.CategoryDB;
import db.OrdersDB;
import db.ProductDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "MaintainOrderController", urlPatterns = {"/m_order"})
public class MaintainOrderController extends HttpServlet {
    OrdersDB ordersDB;
    ProductDB prodDB;
    CategoryDB cateDB;

    public void init() throws ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        prodDB = new ProductDB(dbUrl, dbUser, dbPassword);
        cateDB = new CategoryDB(dbUrl, dbUser, dbPassword);
        ordersDB = new OrdersDB(dbUrl, dbUser, dbPassword);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req,res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        ArrayList<OrdersBean> orders = ordersDB.queryOrders();
        if("maintain".equalsIgnoreCase(req.getParameter("action"))) {
            req.setAttribute("orders", orders);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/m_order.jsp");
            rd.forward(req,res);
        } else {
            PrintWriter out = res.getWriter();
            out.println("No such action!!!");
        }
    }
}
