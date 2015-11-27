package bean;

/**
 * Created by Sur.Vival on 23/11/2015.
 */
public class AccountBean {
    private String id;
    private String password;
    private String userType;
    private String name;
    private int amount;
    private String tel;
    private String address;
    private int bounsPoint;
    private String validation;

    public AccountBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getName() { return name;}

    public void setName(String name) { this.name = name; }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBounsPoint() {
        return bounsPoint;
    }

    public void setBounsPoint(int bounsPoint) {
        this.bounsPoint = bounsPoint;
    }

    public String isValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }
}
