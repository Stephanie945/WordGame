import java.util.ArrayList;

// FoodCategory.java
// This is my overridden method
public class FoodCategory extends WordCategory {

    public FoodCategory() {
        super(new ArrayList<String>() {{
            add("PIZZA");
            add("SUSHI");
            add("BURRITO");
            add("PASTA");
            add("SALAD");
        }});
    }

    @Override
    public String getHint() {
        return "It's a type of food.";
    }
}
