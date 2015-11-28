package db;

import bean.AccountBean;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
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

    public boolean addUserInfo(String name, String tel, String deliveryAddress, String amount) {
        Connection connection = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            connection = getConnection();
            String preQueryStatement = "INSERT INTO account VALUES (?,?,?,?,?,?,?,?,?)";
            pStmnt = connection.prepareStatement(preQueryStatement);
            pStmnt.setString(1, String.valueOf(clientCount));
            pStmnt.setString(2, createRandomPassword());
            pStmnt.setString(3, "Client");
            pStmnt.setString(4, name);
            pStmnt.setString(5, amount);
            pStmnt.setString(6, tel);
            pStmnt.setString(7, deliveryAddress);
            pStmnt.setInt(8, 0);
            pStmnt.setString(9, "N");
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

    protected String createRandomPassword() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 9) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public boolean identityCheck(String username, String password) {
        boolean isClient = false;
        Connection connection = null;
        PreparedStatement pStmnt = null;
        try{
            connection = getConnection();
            String preQueryStatement = "SELECT * FROM account WHERE id = ? and password = ? and userType = 'client' and validation = 'Y' ";
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

    public AccountBean queryByID(String id) {
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

    public ArrayList<AccountBean> queryAccount() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<AccountBean> abs = new ArrayList<AccountBean>();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM category";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while(rs.next()){
                AccountBean ab = new AccountBean();
                ab.setId(rs.getString("id"));
                ab.setPassword(rs.getString("password"));
                ab.setUserType(rs.getString("userType"));
                ab.setName(rs.getString("name"));
                ab.setAmount(rs.getInt("amount"));
                ab.setTel(rs.getString("tel"));
                ab.setAddress(rs.getString("address"));
                ab.setBounsPoint(rs.getInt("bounsPoint"));
                ab.setValidation(rs.getString("validation"));
                abs.add(ab);
            }
            pStmnt.close();
            cnnct.close();
        }catch(SQLException ex) {
            while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }catch(IOException ex) {
            ex.printStackTrace();
        }
        return abs;
    }
}
