package PlanningApp.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Category implements Serializable {
    private static HashMap<String,Integer> categories ;
    public static void initcategory(){
        Category cate = new Category() ;
    }
    private Category(){
        // init Category hash map
        categories = new HashMap<String,Integer>() ;
        categories.put("Work",0);
        categories.put("Study",0);
        categories.put("Sport",0);
    }
    public static void addCategory(String category){
        categories.put(category,0);
    }
    public static void addtoCategory(String category){ categories.put(category,categories.get(category)+1); }
    public static HashMap<String,Integer> getCategories(){
        return categories;
    }
    public static int getnumcategory(String category){ return categories.get(category); }

}
