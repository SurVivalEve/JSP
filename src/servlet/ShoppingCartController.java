package servlet;

import bean.ProductBean;
import bean.ShoppingCartBean;
import db.AccountDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by matthew on 29/11/2015.
 */
@WebServlet(name = "ShoppingCartController", urlPatterns = {"/ShoppingCart"})
public class ShoppingCartController extends HttpServlet {
    //private ArrayList<ShoppingCartBean> scbArrayList = new ArrayList<>();

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void add(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(true);
        if (session.getAttribute("shopCar") == null) {
            ProductBean pb = new ProductBean();
            pb.setQty(Integer.parseInt(req.getParameter("qty")));
            pb.setProductID(req.getParameter("id"));
            pb.setPrice(Integer.parseInt(req.getParameter("price")));
            ArrayList<ShoppingCartBean> scbArrayList = new ArrayList<>();
            ShoppingCartBean x = new ShoppingCartBean();
            x.addBean(new ProductBean());
        }
    }
}
