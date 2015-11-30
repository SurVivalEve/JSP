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
            pStmnt.setString(3, "client");
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

    public boolean identityCheckManager(String username, String password) {
        boolean isClient = false;
        Connection connection = null;
        PreparedStatement pStmnt = null;
        try{
            connection = getConnection();
            String preQueryStatement = "SELECT * FROM account WHERE id = ? and password = ? and userType = 'manager'";
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

    public boolean identityCheckClient(String username, String password) {
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

    public boolean identityCheckUnVerifyClient(String username, String password) {
        boolean isClient = false;
        Connection connection = null;
        PreparedStatement pStmnt = null;
        try{
            connection = getConnection();
            String preQueryStatement = "SELECT * FROM account WHERE id = ? and password = ? and userType = 'client' and validation = 'N' ";
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

    public boolean updateAccountAmount(String id, int amount) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            AccountBean client = queryByID(id);
            String preQueryStatement = "UPDATE account SET amount = ? WHERE id = ? ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1,client.getAmount()+amount);
            pStmnt.setString(2,id);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

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

    public boolean updateAccountAmount2(String id, int amount) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            AccountBean client = queryByID(id);
            String preQueryStatement = "UPDATE account SET amount = ? WHERE id = ? ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1,amount);
            pStmnt.setString(2,id);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

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

    public boolean updateAccountBounsPoint(String id, int bounsPoint) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE account SET bounsPoint = ? WHERE id = ? ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1,bounsPoint);
            pStmnt.setString(2,id);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

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

    public boolean updatePersonalDetails(String id, String name, String tel, String address, String password) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            AccountBean client = queryByID(id);
            String preQueryStatement = "UPDATE account SET name = ? , tel = ? , address = ? ,  password = ? WHERE id = ? ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1,name);
            pStmnt.setString(2,tel);
            pStmnt.setString(3,address);
            pStmnt.setString(4,password);
            pStmnt.setString(5,id);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

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

    public ArrayList<AccountBean> queryNonVerifyAccount(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<AccountBean> abs = new ArrayList<AccountBean>();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM account where validation=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1,"N");
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

    public boolean verifyAccount(String[] id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement;
            for(int i=0; i<id.length; i++) {
                preQueryStatement = "UPDATE account SET validation = ? WHERE id = ? ";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, "Y");
                pStmnt.setString(2, id[i]);
                pStmnt.executeUpdate();
            }
            isSuccess = true;
            pStmnt.close();
            cnnct.close();

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

    public ArrayList<AccountBean> queryAccount() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<AccountBean> abs = new ArrayList<AccountBean>();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM account";
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
