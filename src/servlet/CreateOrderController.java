package servlet;

import bean.ShoppingCartBean;
import db.AccountDB;
import db.OrdersDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by matthew on 30/11/2015.
 */
@WebServlet(name = "CreateOrderController", urlPatterns = {"/CreateOrder"})
public class CreateOrderController extends HttpServlet {

    OrdersDB odb;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        odb = new OrdersDB(dbUrl, dbUser, dbPassword);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        createOrder(req,resp);
    }

    private void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        ShoppingCartBean scb = (ShoppingCartBean) session.getAttribute("shoppingCart");
        AccountDB adb = (AccountDB) session.getAttribute("userInfo");

    }
}
