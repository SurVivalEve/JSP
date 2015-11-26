package test;

import bean.*;
import db.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class TestQueryCust {
    
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/jsp";
        String username = "jsp";
        String password = "jsp";
        OrdersDB db = new OrdersDB(url, username, password);
        SimpleDateFormat sf = new SimpleDateFormat("ss:mm:HH DD-MM-YYYY");

        AccountBean ab = new AccountBean();
        ab.setId("user");
        ab.setAddress("HK");
        db.addRecord("1",ab,"processing",ab.getAddress(), new Date());
    }
}
