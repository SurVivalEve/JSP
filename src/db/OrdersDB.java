package db;

import bean.AccountBean;
import bean.OrdersBean;
import bean.ProductBean;

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

    public boolean addRecord(String orderID, String clientID, ArrayList<ProductBean> products, String status, String deliveryAddress, Date pickupTime){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            //add record to orders table
            if (products.size() > 0){
                String preQueryStatement = "INSERT INTO orders VALUES (?,?,?,?,?,?,?)";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, orderID);
                pStmnt.setString(2, clientID);
                pStmnt.setTimestamp(3, new java.sql.Timestamp((new Date()).getTime()));
                pStmnt.setString(4, status);
                if(!deliveryAddress.equals(""))
                    pStmnt.setString(5, null);
                else
                    pStmnt.setString(5, deliveryAddress);
                if(pickupTime==null)
                    pStmnt.setNull(6, java.sql.Types.DATE);
                else
                    pStmnt.setDate(6, new java.sql.Date(pickupTime.getTime()));
                pStmnt.setString(7, "N");
                pStmnt.executeUpdate();

                //add record to orderline table
                for(int i=0; i<products.size(); i++) {
                    preQueryStatement = "INSERT INTO orderline VALUES (?,?,?)";
                    pStmnt = cnnct.prepareStatement(preQueryStatement);
                    pStmnt.setString(1, orderID);
                    pStmnt.setString(2, products.get(i).getProductID());
                    pStmnt.setInt(3, products.get(i).getQty());
                    pStmnt.executeUpdate();
                }
                pStmnt.close();
            }
            cnnct.close();
            isSuccess = true;
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
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()){
                ob = new OrdersBean();
                ob.setOrderID(orderID);

                //set account detail
                AccountBean ab = new AccountBean();
                ab.setId(rs.getString("clientID"));
                ob.setClient(ab);

                ob.setStatus(rs.getString("status"));
                ob.setDeliveryAddress(rs.getString("deliveryAddress"));
                if (rs.getDate("pickupTime") != null)
                    ob.setPickupTime(new java.util.Date(rs.getDate("pickupTime").getTime()));
                else
                    ob.setPickupTime(null);
                if(rs.getString("cancelled").equalsIgnoreCase("Y"))
                    ob.setCancelled(true);
                else
                    ob.setCancelled(false);
            }

            preQueryStatement = "SELECT * FROM orderline WHERE orderID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, orderID);
            rs = pStmnt.executeQuery();
            ArrayList<ProductBean> products = new ArrayList<ProductBean>();
            ProductDB prodDB = new ProductDB(dburl,dbUser,dbPassword);
            while (rs.next()) {
                //set productBean
                ProductBean pb = prodDB.queryByID(rs.getString("productID"));
                pb.setQty(rs.getInt("qty"));
                products.add(pb);
            }
            ob.setProducts(products);
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

                //set account detail
                AccountBean ab = new AccountBean();
                ab.setId(rs.getString("clientID"));
                ob.setClient(ab);

                ob.setStatus(rs.getString("status"));
                ob.setDeliveryAddress(rs.getString("deliveryAddress"));
                if (rs.getDate("pickupTime") != null)
                    ob.setPickupTime(new java.util.Date(rs.getDate("pickupTime").getTime()));
                else
                    ob.setPickupTime(null);
                if(rs.getString("cancelled").equalsIgnoreCase("Y"))
                    ob.setCancelled(true);
                else
                    ob.setCancelled(false);

                String preQueryStatement2 = "SELECT * FROM orderline WHERE orderID=?";
                PreparedStatement pStmnt2 = cnnct.prepareStatement(preQueryStatement2);
                pStmnt2.setString(1, rs.getString("orderID"));
                ResultSet rs2 = pStmnt2.executeQuery();
                ArrayList<ProductBean> products = new ArrayList<ProductBean>();
                ProductDB prodDB = new ProductDB(dburl,dbUser,dbPassword);
                while (rs2.next()) {
                    //set productBean
                    ProductBean pb = prodDB.queryByID(rs2.getString("productID"));
                    pb.setQty(rs2.getInt("qty"));
                    products.add(pb);
                }
                ob.setProducts(products);
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
            String preQueryStatement="";
            preQueryStatement = "DELETE FROM orderline WHERE orderID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, orderID);
            pStmnt.execute();

            preQueryStatement = "DELETE FROM orders WHERE orderID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, orderID);
            pStmnt.execute();

            isSuccess = true;
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
            //update orders table
            String preQueryStatement = "UPDATE orders SET clientID=?, status=?, deliveryAddress=?, pickupTime=?, cancelled=? WHERE orderID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, ob.getClient().getId());
            pStmnt.setString(2, ob.getStatus());
            if(ob.getDeliveryAddress().equals(""))
                pStmnt.setNull(3, java.sql.Types.VARCHAR);
            else
                pStmnt.setString(3, ob.getDeliveryAddress());
            if(ob.getPickupTime()==null)
                pStmnt.setNull(4, java.sql.Types.DATE);
            else
                pStmnt.setDate(4, new java.sql.Date(ob.getPickupTime().getTime()));
            if (ob.getCancelled()==true)
                pStmnt.setString(5, "Y");
            else
                pStmnt.setString(5, "N");
            pStmnt.setString(6, ob.getOrderID());
            pStmnt.execute();

            //update orderline table
            preQueryStatement = "DELETE FROM orderline WHERE orderID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, ob.getOrderID());
            pStmnt.execute();
            for(int i=0; i<ob.getProductBeans().size(); i++) {
                preQueryStatement = "INSERT INTO orderline VALUES (?,?,?)";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, ob.getOrderID());
                pStmnt.setString(2, ob.getProductBeans().get(i).getProductID());
                pStmnt.setInt(3, ob.getProductBeans().get(i).getQty());
                pStmnt.executeUpdate();
            }

            isSuccess = true;
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
