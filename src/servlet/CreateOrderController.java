package servlet;

import bean.AccountBean;
import bean.ShoppingCartBean;
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

/**
 * Created by matthew on 30/11/2015.
 */
@WebServlet(name = "CreateOrderController", urlPatterns = {"/CreateOrder"})
public class CreateOrderController extends HttpServlet {

    OrdersDB odb;
    AccountDB Dadb;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        odb = new OrdersDB(dbUrl, dbUser, dbPassword);
        Dadb = new AccountDB(dbUrl, dbUser, dbPassword);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        createOrder(req, resp);
    }

    private void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        ShoppingCartBean scb = (ShoppingCartBean) session.getAttribute("shoppingCart");
        AccountBean adb = (AccountBean) session.getAttribute("userInfo");
        String id = odb.getLastID();
        int accAmount;
        int totalAmount = 0;
        int bounsPoint;
        int oldBounsPoint;
        for (int i = 0; i < scb.getX().size(); i++) {
            totalAmount += (scb.getX().get(i).getPrice() * scb.getX().get(i).getQty());
        }

        accAmount = Dadb.queryByID(adb.getId()).getAmount();
        if (accAmount >= totalAmount) {
            odb.addRecord(id, adb.getId(), scb.getX(), "Process", totalAmount, req.getParameter("dAddress"), null);
            session.removeAttribute("shoppingCart");

            oldBounsPoint = Dadb.queryByID(adb.getId()).getBounsPoint();
            bounsPoint = 100 * (totalAmount / 1000);

            Dadb.updateAccountAmount2(adb.getId(), (accAmount - totalAmount));
            Dadb.updateAccountBounsPoint(adb.getId(), (bounsPoint + oldBounsPoint));

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/shoppingCart.jsp");
            rd.forward(req, resp);
        } else {
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/createOrderFail.jsp");
            rd.forward(req, resp);
        }

    }
}
