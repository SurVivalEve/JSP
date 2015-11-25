package db;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDB {
    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public AccountDB(String dburl, String dbUser, String dbPassword) {
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

    public boolean addUserInfo(String id, String user, String pwd) {
        Connection connection = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            connection = getConnection();
            String preQueryStatement = "INSERT INTO account VALUES ()";
            pStmnt = connection.prepareStatement(preQueryStatement);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            connection.close();

        } catch (SQLException ex) {
            while(ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean isValidUser(String user, String pwd) {
        boolean isValid = false;
        Connection connection = null;
        PreparedStatement pStmnt = null;
        try{
            connection = getConnection();
            String preQueryStatement = "SELECT * FROM account WHERE id = ? and password = ?";
            pStmnt = connection.prepareStatement(preQueryStatement);
            pStmnt.setString(1,user);
            pStmnt.setString(2,pwd);
            ResultSet rs = pStmnt.executeQuery();
            if(rs.next()){
                isValid = true;
            }
        } catch (SQLException ex) {
            while(ex!=null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isValid;
    }

    public boolean identityCheck(String username, String password) {
        boolean isClient = false;
        Connection connection = null;
        PreparedStatement pStmnt = null;
        try{
            connection = getConnection();
            String preQueryStatement = "SELECT * FROM account WHERE id = ? and password = ? and userType = 'client' ";
            pStmnt = connection.prepareStatement(preQueryStatement);
            pStmnt.setString(1,username);
            pStmnt.setString(2,password);
            ResultSet rs = pStmnt.executeQuery();
            if(rs.next()){
                isClient = true;
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isClient;
    }


}
