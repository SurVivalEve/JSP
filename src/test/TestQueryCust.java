package test;

import bean.*;
import db.*;

import java.util.ArrayList;

public class TestQueryCust {
    
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/jsp";
        String username = "jsp";
        String password = "jsp";
        CategoryDB db = new CategoryDB(url, username, password);
    }
}
