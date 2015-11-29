package db;

import bean.CategoryBean;
import bean.GiftBean;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GiftDB {
    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public GiftDB(String dburl, String dbUser, String dbPassword){
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

    public boolean addRecord(String id, String name, String picturePath, int requireBonus){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO gift VALUES (?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            pStmnt.setString(2, name);
            pStmnt.setString(3, picturePath);
            pStmnt.setInt(4, requireBonus);
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

    public GiftBean queryByID(String id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        GiftBean gb = null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM gift WHERE giftID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                gb = new GiftBean();
                gb.setId(id);
                gb.setName(rs.getString("name"));
                gb.setPicturePath(rs.getString("picturePath"));
                gb.setRequireBonus(rs.getInt("requireBonus"));
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
        return gb;
    }

    public ArrayList<GiftBean> queryGift(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        ArrayList<GiftBean> gbs = new ArrayList<GiftBean>();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM gift";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while(rs.next()){
                GiftBean gb = new GiftBean();
                gb.setId(rs.getString("giftID"));
                gb.setName(rs.getString("name"));
                gb.setPicturePath(rs.getString("picturePath"));
                gb.setRequireBonus(rs.getInt("requireBonus"));
                gbs.add(gb);
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
        return gbs;
    }

    public boolean delRecord(String id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM gift WHERE giftID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
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

    public boolean editRecord(GiftBean gb){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE gift SET name=?, picturePath=?, requireBonus=? WHERE giftID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, gb.getName());
            pStmnt.setString(2, gb.getPicturePath());
            pStmnt.setInt(3, gb.getRequireBonus());
            pStmnt.setString(4, gb.getId());
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
