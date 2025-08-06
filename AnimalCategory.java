import java.util.ArrayList;

// AnimalCategory.java
// This is my overridden method
public class AnimalCategory extends WordCategory {

    public AnimalCategory() {
        super(new ArrayList<String>() {{
            add("ELEPHANT");
            add("TIGER");
            add("GIRAFFE");
            add("KANGAROO");
            add("DOLPHIN");
        }});
    }

    @Override
    public String getHint() {
        return "It's an animal.";
    }
}
