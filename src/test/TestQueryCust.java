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
        /*
        ArrayList<ProductBean> products = new ArrayList<ProductBean>();

        ProductBean prodA = new ProductBean();
        prodA.setProductID("P001");
        prodA.setQty(2);
        products.add(prodA);

        ProductBean prodB = new ProductBean();
        prodB.setProductID("P002");
        prodB.setQty(3);
        products.add(prodB);

        db.addRecord("O006","A001",products,"Process","",null);
        */
        /*
        OrdersBean ob = db.queryByID("O001");
        System.out.println(ob.getOrderID());
        System.out.println(ob.getStatus());
        System.out.println(ob.getPickupTime());
        System.out.println(ob.getDeliveryAddress());
        System.out.println(ob.getCancelled());
        System.out.println(ob.getClient().getId());
        System.out.println(ob.getProductBeans().size());
        System.out.println("-------------------");
        for(int i=0; i<ob.getProductBeans().size();i++){
            System.out.println(ob.getProductBeans().get(i).getProductID());
            System.out.println(ob.getProductBeans().get(i).getName());
            System.out.println(ob.getProductBeans().get(i).getDescriptions());
            System.out.println(ob.getProductBeans().get(i).getCategoryID());
            System.out.println(ob.getProductBeans().get(i).getPrice());
            System.out.println(ob.getProductBeans().get(i).getPicturePath());
            System.out.println(ob.getProductBeans().get(i).getQty());
            System.out.println("-------------------");
        }*/
        /*
        ArrayList<OrdersBean> obs = db.queryOrders();
        for(int i=0; i<obs.size(); i++) {
            System.out.println("--------------------------------------------");
            System.out.println(obs.get(i).getOrderID());
            System.out.println(obs.get(i).getStatus());
            System.out.println(obs.get(i).getPickupTime());
            System.out.println(obs.get(i).getDeliveryAddress());
            System.out.println(obs.get(i).getCancelled());
            System.out.println(obs.get(i).getClient().getId());
            System.out.println(obs.get(i).getProductBeans().size());
            System.out.println("-------------------");

            for (int k = 0; k < obs.get(i).getProductBeans().size(); k++) {
                System.out.println(obs.get(i).getProductBeans().get(k).getProductID());
                System.out.println(obs.get(i).getProductBeans().get(k).getName());
                System.out.println(obs.get(i).getProductBeans().get(k).getDescriptions());
                System.out.println(obs.get(i).getProductBeans().get(k).getCategoryID());
                System.out.println(obs.get(i).getProductBeans().get(k).getPrice());
                System.out.println(obs.get(i).getProductBeans().get(k).getPicturePath());
                System.out.println(obs.get(i).getProductBeans().get(k).getQty());
                System.out.println("-------------------");
            }
        }*/
        /*
        OrdersBean ob = db.queryByID("O001");
        ob.setDeliveryAddress("HK");
        for(int i=0; i<ob.getProductBeans().size(); i++)
            ob.getProductBeans().get(i).setQty(10);
        db.editRecord(ob);
        */
    }
}
