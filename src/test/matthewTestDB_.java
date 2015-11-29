package test;

import db.CategoryDB;
import db.GiftDB;
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
        GiftDB db = new GiftDB(url, username, password);
        ArrayList x = db.searchFunction("between 501 and 1000");
        System.out.println(x.size());
    }
}
