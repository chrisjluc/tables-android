package tables.android.models;

import com.parse.ParseFile;
import com.parse.ParseObject;

public class MenuCategory {
    private String id;
    private String categoryName;
    private String categoryDescription;
    private ParseFile categoryImage;

    public MenuCategory(ParseObject parseObject){
        id = parseObject.getObjectId();
        categoryName = parseObject.getString("categoryName");
        categoryImage = parseObject.getParseFile("categoryImage");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ParseFile getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(ParseFile categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
