package servlet;

import bean.AccountBean;
import db.AccountDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "VerifyClientController", urlPatterns = {"/m_client"})
public class VerifyClientController  extends HttpServlet {
    AccountDB db;

    public void init() throws ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new AccountDB(dbUrl, dbUser, dbPassword);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
        doPost(req,res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        ArrayList<AccountBean> accounts = db.queryAccount();
        String abc = "cannot connect to db";
        if(accounts.size()==0)
            req.setAttribute("abc", abc);
        if("verify".equalsIgnoreCase(req.getParameter("action")))
            req.setAttribute("accounts", accounts);
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/m_client.jsp");
        rd.forward(req,res);
    }
}
