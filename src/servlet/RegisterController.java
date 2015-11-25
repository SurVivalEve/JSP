package servlet;

import bean.AccountBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Sur.Vival on 25/11/2015.
 */
@WebServlet(name = "LoginController", urlPatterns = {"/registerCheck"})
public class RegisterController extends HttpServlet {
    private AccountBean db;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String name = req.getParameter("name");
        String tel = req.getParameter("tel");
        String delivery = req.getParameter("delivery");
        String address = req.getParameter("address");

        if (!existCheck()) {

        } else {

        }

    }

    public boolean existCheck() {
        boolean alreadyExist = false;

        return alreadyExist;
    }

    public void doRegister(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

    }


}
