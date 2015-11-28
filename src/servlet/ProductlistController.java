package servlet;

import bean.ProductBean;
import db.AccountDB;
import db.ProductDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by matthew on 28/11/2015.
 */
@WebServlet(name = "ProductlistController", urlPatterns = {"/ProductList"})
public class ProductlistController extends HttpServlet {
    private ProductDB db;

    public void init() throws ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new ProductDB(dbUrl, dbUser, dbPassword);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        doPost(req,res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        showAll(req,res);
    }

    private void showAll(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        ArrayList<ProductBean> pbArrayList = db.qeuryProduct();
        req.setAttribute("pbArrayList", pbArrayList);
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/searchProduct.jsp");
        rd.forward(req,res);
    }

}
