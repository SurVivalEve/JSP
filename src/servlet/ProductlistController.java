package servlet;

import bean.CategoryBean;
import bean.ProductBean;
import db.AccountDB;
import db.CategoryDB;
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
    private CategoryDB cb;

    public void init() throws ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new ProductDB(dbUrl, dbUser, dbPassword);
        cb = new CategoryDB(dbUrl, dbUser, dbPassword);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("all".equals(action)) {
            showAll(req, res);
        } else if ("search".equals(action)) {

            if (req.getParameter("selectedType") != null && req.getParameter("range") != null) {
                //if(!(req.getParameter("selectedType").equals("")) && !(req.getParameter("range").equals(""))){
                    searchFunction3(req,res);
                //}
            } else if (req.getParameter("selectedType") != null) {
                if (!(req.getParameter("selectedType").equals(""))) {
                    searchFunction1(req, res);
                }
            } else if (req.getParameter("selectedType") == null && req.getParameter("range") != null)
                if (!(req.getParameter("range").equals("")))
                    searchFunction2(req, res);
        }
    }

    private void showAll(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ArrayList<ProductBean> pbArrayList = db.qeuryProduct();
        req.setAttribute("pbArrayList", pbArrayList);
        ArrayList<CategoryBean> cbArrayList = cb.queryCategory();
        req.setAttribute("cbArrayList", cbArrayList);
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/searchProduct.jsp");
        rd.forward(req, res);
    }

    private void searchFunction1(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ArrayList<CategoryBean> cbArrayList = cb.queryCategory();
        req.setAttribute("cbArrayList", cbArrayList);
        ArrayList<ProductBean> pbArrayList = db.queryByCategory(req.getParameter("selectedType"));
        req.setAttribute("pbArrayList", pbArrayList);
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/searchProduct.jsp");
        rd.forward(req, res);
    }

    private void searchFunction2(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ArrayList<CategoryBean> cbArrayList = cb.queryCategory();
        req.setAttribute("cbArrayList", cbArrayList);
        ArrayList<ProductBean> pbArrayList = db.queryByRange(req.getParameter("range"));
        req.setAttribute("pbArrayList", pbArrayList);
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/searchProduct.jsp");
        rd.forward(req, res);
    }

    private void searchFunction3(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ArrayList<CategoryBean> cbArrayList = cb.queryCategory();
        req.setAttribute("cbArrayList", cbArrayList);
        ArrayList<ProductBean> pbArrayList = db.queryForS3(req.getParameter("selectedType"),req.getParameter("range"));
        req.setAttribute("pbArrayList", pbArrayList);
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/searchProduct.jsp");
        rd.forward(req, res);
    }
}
