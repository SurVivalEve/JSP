package servlet;

import bean.AccountBean;
import bean.ProductBean;
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

@WebServlet(name = "MaintainProductController", urlPatterns = {"/m_product"})
public class MaintainProductController extends HttpServlet {
    ProductDB db;

    public void init() throws ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new ProductDB(dbUrl, dbUser, dbPassword);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req,res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        ArrayList<ProductBean> products = db.qeuryProduct();
        if("maintain".equalsIgnoreCase(req.getParameter("action"))) {
            req.setAttribute("products", products);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/m_product.jsp");
            rd.forward(req,res);
        } else if ("delete".equalsIgnoreCase(req.getParameter("action"))){
            res.sendRedirect("m_product?action=maintain");
        } else {
            PrintWriter out = res.getWriter();
            out.println("No such action!!!");
        }
    }
}
