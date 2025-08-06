import java.util.ArrayList;

// CountryCategory.java
// This is my overridden method
public class CountryCategory extends WordCategory {

    public CountryCategory() {
        super(new ArrayList<String>() {{
            add("CANADA");
            add("BRAZIL");
            add("JAPAN");
            add("FRANCE");
            add("INDIA");
        }});
    }

    @Override
    public String getHint() {
        return "It's a country.";
    }
}
