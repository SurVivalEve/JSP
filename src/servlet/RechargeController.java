package servlet;

import bean.AccountBean;
import db.AccountDB;

import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Sur.Vival on 28/11/2015.
 */
@WebServlet(name = "RechargeController", urlPatterns = {"/recharge"})
public class RechargeController extends HttpServlet {
    private AccountDB db;

    @Override
    public void init() throws ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new AccountDB(dbUrl, dbUser, dbPassword);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if("recharge".equalsIgnoreCase(action)) {
            doRecharge(req,resp);
        }
    }

    private void doRecharge(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        String amount = request.getParameter("amount");
        String targetURL;
        HttpSession session = request.getSession(false);
        if (session != null) {
            AccountBean bean = (AccountBean) session.getAttribute("userInfo");
            db.updateAccountAmount(bean.getId(), Integer.parseInt(amount));
            targetURL = "rechargeSuccess.jsp";
        } else {
            targetURL = "recharge.jsp";
        }
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request,response);

    }
}
