package servlet;

import bean.AccountBean;
import bean.GiftBean;
import com.sun.corba.se.spi.activation.LocatorPackage.ServerLocation;
import db.AccountDB;
import db.GiftDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Sur.Vival on 29/11/2015.
 */
@WebServlet(name = "RedeemController", urlPatterns = {"/redeem"})
public class RedeemController extends HttpServlet {
    private GiftDB giftDB;
    private AccountDB accountDB;

    @Override
    public void init() throws ServletException {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        giftDB = new GiftDB(dbUrl, dbUser, dbPassword);
        accountDB = new AccountDB(dbUrl, dbUser, dbPassword);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            if ("all".equalsIgnoreCase(action)) {
                showAllGift(req, resp);
            } else if ("search".equalsIgnoreCase(action)) {
                searchGift(req, resp);
            } else if ("redeem".equalsIgnoreCase(action)) {
                doRedeem(req, resp);
            } else if ("redeemProcess".equalsIgnoreCase(action)) {
                redeemProcess(req, resp);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void showAllGift(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<GiftBean> giftList = giftDB.queryGift();
        request.setAttribute("g1234", giftList);
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/redeemGift.jsp");
        rd.forward(request, response);
    }

    private void searchGift(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<GiftBean> gbArrayList = giftDB.searchFunction(request.getParameter("range"));
        request.setAttribute("g1234", gbArrayList);
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/redeemGift.jsp");
        rd.forward(request, response);
    }

    private void redeemProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String giftID = request.getParameter("giftID");
        GiftBean giftBean = giftDB.queryByID(giftID);
        request.setAttribute("giftInfo", giftBean);
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/redeemProcess.jsp");
        rd.forward(request, response);
    }

    private void doRedeem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String giftID = request.getParameter("giftID");
        String targetURL = null;
        HttpSession session = request.getSession(false);
        if (session != null) {
            AccountBean loginClient = (AccountBean) session.getAttribute("userInfo");
            int o = accountDB.queryByID(loginClient.getId()).getBounsPoint();
            int n = giftDB.queryByID(giftID).getRequireBonus();
            int checkBonus = o - n;
            if (checkBonus < 0) {
                targetURL = "redeemFail.jsp";
            } else {
                accountDB.updateAccountBounsPoint(loginClient.getId(), checkBonus);
                targetURL = "redeemSuccess.jsp";
            }
        } else {
            targetURL = "notLoggedInYet.jsp";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }
}
