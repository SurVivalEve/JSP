package db;

import bean.CategoryBean;
import bean.ProductBean;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDB {

    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public ProductDB(String dburl, String dbUser, String dbPassword){
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

    public boolean addRecord(String productID, String name, String description, String categoryID, int price, String picturePath){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO product VALUES (?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, productID);
            pStmnt.setString(2, name);
            pStmnt.setString(3, description);
            pStmnt.setString(4, categoryID);
            pStmnt.setInt(5, price);
            pStmnt.setString(6, picturePath);
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

    public ProductBean queryByID(String productID){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ProductBean pb = null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM product WHERE productID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, productID);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()){
                pb = new ProductBean();
                pb.setProductID(productID);
                pb.setName(rs.getString("name"));
                pb.setDescriptions(rs.getString("descriptions"));
                CategoryDB cateDB = new CategoryDB(dburl,dbUser,dbPassword);
                pb.setCategoryID(cateDB.queryByID(rs.getString("categoryID")));
                pb.setPrice(rs.getInt("price"));
                pb.setPicturePath(rs.getString("picturePath"));
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
        return pb;
    }

    public ArrayList<ProductBean> qeuryProduct(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList<ProductBean> pbs = new ArrayList<ProductBean>();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM product";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();
            while(rs.next()){
                ProductBean pb = new ProductBean();
                pb.setProductID(rs.getString("productID"));
                pb.setName(rs.getString("name"));
                pb.setDescriptions(rs.getString("descriptions"));
                CategoryDB cateDB = new CategoryDB(dburl,dbUser,dbPassword);
                pb.setCategoryID(cateDB.queryByID(rs.getString("categoryID")));
                pb.setPrice(rs.getInt("price"));
                pb.setPicturePath(rs.getString("picturePath"));
                pbs.add(pb);
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
        return pbs;
    }

    public boolean delRecord(String productID){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM product WHERE productID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, productID);
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

    public boolean editRecord(ProductBean pb){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE product SET name=?,descriptions=?,categoryID=?,price=?,picturePath=? WHERE productID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, pb.getName());
            pStmnt.setString(2, pb.getDescriptions());
            pStmnt.setString(3, pb.getCategoryID().getCategoryID());
            pStmnt.setInt(4, pb.getPrice());
            pStmnt.setString(5, pb.getPicturePath());
            pStmnt.setString(6, pb.getProductID());
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

    public ArrayList<ProductBean> queryByCategory(String cid){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList<ProductBean> pbs = new ArrayList<ProductBean>();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM product WHERE categoryID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, cid);
            ResultSet rs = pStmnt.executeQuery();
            while(rs.next()){
                ProductBean pb = new ProductBean();
                pb.setProductID(rs.getString("productID"));
                pb.setName(rs.getString("name"));
                pb.setDescriptions(rs.getString("descriptions"));
                CategoryDB cateDB = new CategoryDB(dburl,dbUser,dbPassword);
                pb.setCategoryID(cateDB.queryByID(rs.getString("categoryID")));
                pb.setPrice(rs.getInt("price"));
                pb.setPicturePath(rs.getString("picturePath"));
                pbs.add(pb);
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
        return pbs;
    }

    public ArrayList<ProductBean> queryByRange(int min, int max){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList<ProductBean> pbs = new ArrayList<ProductBean>();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM product WHERE price BETWEEN ? and ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, min);
            pStmnt.setInt(2, max);
            ResultSet rs = pStmnt.executeQuery();
            while(rs.next()){
                ProductBean pb = new ProductBean();
                pb.setProductID(rs.getString("productID"));
                pb.setName(rs.getString("name"));
                pb.setDescriptions(rs.getString("descriptions"));
                CategoryDB cateDB = new CategoryDB(dburl,dbUser,dbPassword);
                pb.setCategoryID(cateDB.queryByID(rs.getString("categoryID")));
                pb.setPrice(rs.getInt("price"));
                pb.setPicturePath(rs.getString("picturePath"));
                pbs.add(pb);
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
        return pbs;
    }




}
