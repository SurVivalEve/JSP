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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Sur.Vival on 25/11/2015.
 */
@WebServlet(name = "LoginController", urlPatterns = {"/loginCheck"})
public class LoginController extends HttpServlet {
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
        if (!isAuthenticated(req) && !("authenticate".equals(action))) {
            doLogin(req,resp);
            return;
        }
        if("authenticate".equals(action)) {
            doAuthenticate(req, resp);
        } else if("logout".equals(action)) {
            doLogout(req,resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }

    private void doAuthenticate(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String id = req.getParameter("username");
        String password = req.getParameter("password");
        String targetURL;
        // har code username and password is abc and 123
        if(db.isValidUser(id,password)) {
            // obtain session from request
            if (db.identityCheck(id, password)) {
                HttpSession clientSession = req.getSession(true);
                AccountBean client = db.queryAconByID(id);
                clientSession.setAttribute("userInfo", client);
            } else {
                HttpSession adminSession = req.getSession(true);
                AccountBean admin = db.queryAconByID(id);
                adminSession.setAttribute("adminInfo",admin);
            }
            // store the userInfo to the session
            targetURL = "/index.jsp";
        } else {
            targetURL = "/loginError.jsp";
        }
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(req,res);
    }

    private boolean isAuthenticated(HttpServletRequest req) {
        boolean result = false;
        HttpSession session = req.getSession();
        // get the UserInfo from session
        if(session.getAttribute("userInfo") != null) {
            result = true;
        }
        return result;
    }

    private void doLogin(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        String targetURL = "login.jsp";
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(req,res);
    }

    private void doLogout(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            // remove the attribute from session
            session.removeAttribute("userInfo");
            //  invalidate the session
            session.invalidate();
        }
        doLogin(req,res);
    }
}
