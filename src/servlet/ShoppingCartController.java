package servlet;

import bean.ProductBean;
import bean.ShoppingCartBean;
import db.AccountDB;

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
        String action = req.getParameter("action");
        if (action.equals("add")) {
            add(req, resp);
        } else if (action.equals("remove")) {
            remove(req, resp);
        } else if (action.equals("edit")) {
            edit(req, resp);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            ShoppingCartBean shop = (ShoppingCartBean) session.getAttribute("shoppingCart");
            if (shop == null) {
                ProductBean pb = new ProductBean();
                pb.setQty(Integer.parseInt(req.getParameter("qty")));
                pb.setProductID(req.getParameter("itemID"));
                pb.setPrice(Integer.parseInt(req.getParameter("price")));
                pb.setName(req.getParameter("name"));
                ShoppingCartBean scb = new ShoppingCartBean();
                scb.addBean(pb);
                session.setAttribute("shoppingCart", scb);
            } else {
                ProductBean addProduct = new ProductBean();
                addProduct.setName(req.getParameter("name"));
                addProduct.setQty(Integer.parseInt(req.getParameter("qty")));
                addProduct.setProductID(req.getParameter("itemID"));
                addProduct.setPrice(Integer.parseInt(req.getParameter("price")));
                ShoppingCartBean scbExist = (ShoppingCartBean) session.getAttribute("shoppingCart");
                if (checkValid(scbExist.getX(), addProduct) == false) {
                    scbExist.addBean(addProduct);
                }
            }
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/ProductList?action=all");
            rd.forward(req, res);
        }
    }

    private boolean checkValid(ArrayList<ProductBean> pb, ProductBean pb2) {
        boolean isValid = false;
        for (int i = 0; i < pb.size(); i++) {
            if (pb.get(i).getProductID().equals(pb2.getProductID())) {
                isValid = true;
                pb.get(i).setQty(pb2.getQty() + pb.get(i).getQty());
                break;
            }
        }
        return isValid;
    }

    private void remove(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String itemID = req.getParameter("itemID");
        HttpSession session = req.getSession(false);
        if (session != null) {
            ShoppingCartBean shop = (ShoppingCartBean) session.getAttribute("shoppingCart");
            if (shop != null) {
                ShoppingCartBean scbExist = (ShoppingCartBean) session.getAttribute("shoppingCart");
                scbExist.removeBean(itemID);
            }
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/shoppingCart.jsp");
        rd.forward(req, res);
    }

    private void edit(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String itemID = req.getParameter("itemID");
        String qty = req.getParameter("qty");
        HttpSession session = req.getSession(false);
        if (session != null) {
            ShoppingCartBean shop = (ShoppingCartBean) session.getAttribute("shoppingCart");
            if (shop != null) {
                ShoppingCartBean scbExist = (ShoppingCartBean) session.getAttribute("shoppingCart");
                scbExist.updateProduct(itemID,Integer.parseInt(qty));
            }
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/shoppingCart.jsp");
        rd.forward(req, res);
    }
}

