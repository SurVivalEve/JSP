package servlet;


import bean.AccountBean;
import db.AccountDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Sur.Vival on 30/11/2015.
 */
@WebServlet(name = "ProfileController", urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {
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
        if ("updateDetails".equalsIgnoreCase(action)) {
            doChange(req,resp);
        } else if ("showOrder".equalsIgnoreCase(action)) {
            showOrders(req,resp);
        }
    }

    private void showOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void doChange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(false);
        if (session != null) {
            if(db.updatePersonalDetails(id,name,tel,address,password)) {
                PrintWriter out = response.getWriter();
                out.println("<b>Account details has been updated!</b>");
                String redirectTarget = "<script type=\"text/javascript\">setTimeout(function(){\ntop.location.href = \"loginCheck?action=logout\";},1500);\n</script>";
                response.getWriter().print(redirectTarget);
                response.flushBuffer();
            }
        }

    }
}
