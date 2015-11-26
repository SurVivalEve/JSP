package db;

import bean.AccountBean;
import bean.OrdersBean;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrdersDB {

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public OrdersDB(String dburl, String dbUser, String dbPassword){
        this.dburl = dburl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            Logger.getLogger(AccountDB.class.getName()).log(Level.SEVERE, null, e);
        }
        return DriverManager.getConnection(dburl, dbUser, dbPassword);
    }

    public boolean addRecord(String orderID, AccountBean client, String status, String deliveryAddress, Date pickupTime){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO orders VALUES (?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, orderID);
            pStmnt.setString(2, client.getId());
            pStmnt.setString(3, status);
            pStmnt.setString(4, deliveryAddress);
            pStmnt.setDate(5, new java.sql.Date(pickupTime.getTime()));
            pStmnt.setString(6, "N");
            int rowCount = pStmnt.executeUpdate();
            if (rowCount>=1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public OrdersBean queryByID(String orderID){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        OrdersBean ob = null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM orders WHERE orderID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, orderID);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()){
                ob = new OrdersBean();
                ob.setOrderID(orderID);
                //ob.setClient(rs.getString("clientID"));
                ob.setClient(new AccountBean());
                ob.setStatus(rs.getString("status"));
                ob.setDeliveryAddress(rs.getString("deliveryAddress"));
                ob.setPickupTime(new java.util.Date(rs.getDate("pickupTime").getTime()));
                if(rs.getString("cancelled").equalsIgnoreCase("Y"))
                    ob.setCancelled(true);
                else
                    ob.setCancelled(false);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ob;
    }

    public ArrayList<OrdersBean> queryOrders(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList<OrdersBean> obs = new ArrayList<OrdersBean>();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM orders";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while(rs.next()){
                OrdersBean ob = new OrdersBean();
                ob.setOrderID(rs.getString("orderID"));
                //ob.setClient(rs.getString("clientID"));
                ob.setClient(new AccountBean());
                ob.setStatus(rs.getString("status"));
                ob.setDeliveryAddress(rs.getString("deliveryAddress"));
                ob.setPickupTime(new java.util.Date(rs.getDate("pickupTime").getTime()));
                if(rs.getString("cancelled").equalsIgnoreCase("Y"))
                    ob.setCancelled(true);
                else
                    ob.setCancelled(false);
                obs.add(ob);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return obs;
    }

    public boolean delRecord(String orderID){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM orders WHERE orderID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, orderID);
            if (pStmnt.execute()){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean editRecord(OrdersBean ob){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE orders SET clientID=?, status=?, deliveryAddress=?, pickupTime=?, cancelled=? WHERE orderID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, ob.getClient().getId());
            pStmnt.setString(2, ob.getStatus());
            pStmnt.setString(3, ob.getDeliveryAddress());
            pStmnt.setDate(4, new java.sql.Date(ob.getPickupTime().getTime()));
            if (ob.getCancelled()==true)
                pStmnt.setString(5, "Y");
            else
                pStmnt.setString(5, "N");
            pStmnt.setString(6, ob.getOrderID());
            if (pStmnt.execute()){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
}
