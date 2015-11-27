package db;

import bean.AccountBean;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDB {
    private String dburl = "";
    private String dbUser = "";
    private String dbPassword = "";
    private static int clientCount = 1;

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

    public boolean updateUserInfo(String id, String pwd) {
        Connection connection = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            connection = getConnection();
            String preQueryStatement = "UPDATE account SET password = ? WHERE id = ?";
            pStmnt = connection.prepareStatement(preQueryStatement);
            pStmnt.setString(1, pwd);
            pStmnt.setString(2, id);
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

    public boolean addUserInfo(String name, String tel, String deliveryAddress) {
        Connection connection = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            connection = getConnection();
            String preQueryStatement = "INSERT INTO account (id, name, userType, tel, address, bounsPoint, validation)  VALUES (?,?,?,?,?,?,?)";
            pStmnt = connection.prepareStatement(preQueryStatement);
            pStmnt.setString(1, String.valueOf(clientCount));
            pStmnt.setString(2, name);
            pStmnt.setString(3, "client");
            pStmnt.setString(4, tel);
            pStmnt.setString(5, deliveryAddress);
            pStmnt.setInt(6, 0);
            pStmnt.setString(7, "N");
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >= 1) {
                isSuccess = true;
                clientCount++;
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

    public AccountBean queryAconByID(String id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        AccountBean ab = null;
        try{
            connection = getConnection();
            String preQueryStatement = "SELECT * FROM account WHERE id=?";
            preparedStatement = connection.prepareStatement(preQueryStatement);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                ab = new AccountBean();
                ab.setId(rs.getString("id"));
                ab.setPassword(rs.getString("password"));
                ab.setUserType(rs.getString("userType"));
                ab.setName(rs.getString("name"));
                ab.setAmount(rs.getInt("amount"));
                ab.setTel(rs.getString("tel"));
                ab.setAddress(rs.getString("address"));
                ab.setBounsPoint(rs.getInt("bounsPoint"));
                ab.setValidation(rs.getString("validation"));
            }
            preparedStatement.close();
            connection.close();
        }catch(SQLException ex) {
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }catch(IOException ex) {
            ex.printStackTrace();
        }
        return ab;
    }

}
