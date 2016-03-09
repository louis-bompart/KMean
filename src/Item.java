import java.util.*;

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

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(Item.class))
        for (Variable variable :
                category.getVariables()) {
                if (!Objects.equals(((Item) obj).get(variable), values.get(variable))) {
                    return false;
                }
            }
        return ((Item)obj).getCategory().equals(category);
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

}
