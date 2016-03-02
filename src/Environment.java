import java.util.ArrayList;

/**
 * Created by louis on 02/03/2016.
 */

public class Environment {

    /**
     * List of used categories for the run of K-means.
     */
    private ArrayList<Category> categories;
    /**
     * Data set used for the run of K-means.
     */
    private ArrayList<Item> dataSet;

    /**
     * Standard constructor.
     * Just initialize Arrays properly.
     */
    public Environment() {
        categories = new ArrayList<>();
        dataSet = new ArrayList<>();
    }

    //TODO: Implements delegate methods of categories and dataSet as needed.
}
