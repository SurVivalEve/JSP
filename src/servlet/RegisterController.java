package servlet;
// Tin

import bean.AccountBean;
import db.AccountDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by Sur.Vival on 25/11/2015.
 */
@WebServlet(name = "LoginController", urlPatterns = {"/registerCheck"})
public class RegisterController extends HttpServlet {
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
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("register".equalsIgnoreCase(action)) {
            doRegister(req, resp);
        } else {
            PrintWriter out = resp.getWriter();
            out.print("No such action Found!!!");
        }

    }

    public void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String address = request.getParameter("deliveryAddress");
        String payment = request.getParameter("payment");

        if(db.addUserInfo(name,tel,address)) {
            response.sendRedirect("registerSuccess.jsp");
        } else {
            PrintWriter out = response.getWriter();
            out.println("Register fail!!!");

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/register");
            rd.forward(request,response);
        }
    }


}
