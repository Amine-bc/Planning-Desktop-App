package PlanningApp.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Category {
    private static HashMap<String,Integer> categories ;
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
    public static HashMap<String,Integer> getCategories(){
        return categories;
    }

}
