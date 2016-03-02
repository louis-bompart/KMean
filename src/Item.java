import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by louis on 02/03/2016.
 */
public class Item {
    private Category category;
    private Map<Variable,Double> values;

    /**
     * Standard constructor.
     * Just initialize class' variables.
     */
    public Item() {
        category = null;
        values = new HashMap<>();
    }

    //TODO: Implements delegate methods of values as needed.
}
