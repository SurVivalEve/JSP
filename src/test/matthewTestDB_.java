package test;

import db.CategoryDB;
import db.OrdersDB;
import db.ProductDB;

import java.util.ArrayList;

/**
 * Created by matthew on 29/11/2015.
 */
public class matthewTestDB_ {
    public static void main(String [] argv){
        String url = "jdbc:mysql://kazechan.ddns.net:3306/jsp";
        String username = "jsp";
        String password = "jsp";
        ProductDB db = new ProductDB(url, username, password);
        ArrayList x = db.queryByCategory("C001");
        System.out.println(x.size());
    }
}