package servlet;

import bean.CategoryBean;
import bean.ProductBean;
import db.CategoryDB;
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
    ProductDB prodDB;
    CategoryDB cateDB;

    public void init() throws ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        cateDB = new CategoryDB(dbUrl, dbUser, dbPassword);
        prodDB = new ProductDB(dbUrl, dbUser, dbPassword);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(!"edit".equalsIgnoreCase(req.getParameter("action")))
            doPost(req,res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        ArrayList<ProductBean> products = prodDB.qeuryProduct();
        if("maintain".equalsIgnoreCase(req.getParameter("action"))) {
            req.setAttribute("products", products);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/m_product.jsp");
            rd.forward(req,res);
        } else if ("delete".equalsIgnoreCase(req.getParameter("action"))){
            prodDB.delRecord(req.getParameter("productID"));
            res.sendRedirect("m_product?action=maintain");
        } else if ("edit".equalsIgnoreCase(req.getParameter("action"))){
            ProductBean pb = new ProductBean();
            pb.setProductID(req.getParameter("productID"));
            pb.setName(req.getParameter("name"));
            pb.setDescriptions(req.getParameter("description"));
            CategoryBean cb = cateDB.queryByName(req.getParameter("categoryName"));
            pb.setCategoryID(cb);
            pb.setPrice(Integer.parseInt(req.getParameter("price")));
            pb.setPicturePath(req.getParameter("picturePath"));
            prodDB.editRecord(pb);
            res.sendRedirect("m_product?action=maintain");
        } else {
            PrintWriter out = res.getWriter();
            out.println("No such action!!!");
        }
    }
}
