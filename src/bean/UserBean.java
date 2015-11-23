package bean;

/**
 * Created by Sur.Vival on 23/11/2015.
 */
public class UserBean {
    private String id;
    private String password;
    private String type;

    public UserBean() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
