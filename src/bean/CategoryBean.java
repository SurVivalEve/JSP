package bean;

/**
 * Created by Sur.Vival on 23/11/2015.
 */
public class CategoryBean {
    private String categoryID;
    private String name;
    private String cType;

    public CategoryBean() {
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }
}
