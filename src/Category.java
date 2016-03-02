import java.util.ArrayList;
import java.util.Set;

/**
 * A category used for the run of KMean algorithm.
 * Created by louis on 02/03/2016.
 */
public class Category {
    /**
     * List of the used variables for the category.
     */
    private ArrayList<Variable> variables;

    /**
     * Standard constructor.
     * Just initialize Arrays properly.
     */
    public Category() {
        this.variables = new ArrayList<>();
    }
    //TODO: Implements delegate methods of variable (e.g:set,get,etc...) as needed.

    /**
     * @param items the data set used in which we want to find the barycenter.
     * @return The barycenter of the class -WARNING : it's a "virtual" item !
     */
    public Item computeBarycenter(Set<Item> items) {
        //Initialization of the barycenter.
        Item barycenter = new Item();
        barycenter.setCategory(this);
        for (Variable var :
                variables) {
            barycenter.put(var, 0d);
        }

        for (Item item :
                items) {
            for (Variable var :
                    variables) {
                barycenter.put(var, barycenter.get(var) + item.get(var));
            }
        }

        for (Variable var :
                variables) {
            barycenter.put(var, barycenter.get(var)/items.size());
        }
        return barycenter;
    }
}
