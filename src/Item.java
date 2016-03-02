import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * An item of a data set.
 * Created by louis on 02/03/2016.
 */
public class Item {
    private Category category;
    private Map<Variable,Double> values;

    public Set<Variable> keySet() {
        return values.keySet();
    }

    public Double get(Object key) {
        //noinspection SuspiciousMethodCalls
        return values.get(key);
    }

    /**
     * Standard constructor.
     * Just initialize class' variables.
     */
    public Item() {
        category = null;
        values = new HashMap<>();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double put(Variable key, Double value) {
        return values.put(key, value);
    }

//TODO: Implements delegate methods of values as needed.
}
