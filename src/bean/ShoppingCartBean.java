package bean;

import java.util.ArrayList;

/**
 * Created by matthew on 29/11/2015.
 */
public class ShoppingCartBean {
    private ArrayList<ProductBean> x = new ArrayList();

    public ShoppingCartBean() {
    }

    public ArrayList<ProductBean> getX() {
        return x;
    }

    public void setX(ArrayList<ProductBean> x) {
        this.x = x;
    }

    public void removeBean(String pID) {
        for (int i = 0; i < x.size(); i++) {
            if (x.get(i).getProductID().equals(pID)) {
                x.remove(i);
            }
        }
    }

    public void addBean(ProductBean pb) {
        x.add(pb);
    }


}
