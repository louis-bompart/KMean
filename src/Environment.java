import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Class where all the magic happens, the KMean algorithm is executed right here.
 * Created by louis on 02/03/2016.
 */

public class Environment {

    private final Distance distance = new EuclideanDistance();

    private final int RANGE=100;
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

    public void computeKMean() {
        Random rnd = new Random();
        Set<Item> centers = generateRandomCenter();
    }

    public Set<Item> generateRandomCenter() {
        Random rnd = new Random();
        Set<Item> tmp = new HashSet<>();
        Set<Item> centers = new HashSet<>();
        Random random = new Random();
        for (Category category :
                categories) {
            Item item = dataSet.get(rnd.nextInt(dataSet.size()));
            item.setCategory(category);
            dataSet.remove(item);//Remove temporary the center of the dataSet to avoid having two centers for the same item.
            tmp.add(item);
            centers.add(item);
        }
        dataSet.addAll(tmp);
        return centers;
    }

    public void affectAllItems(Set<Item> centers) {
        for (Item item :
                dataSet) {
            Item nearest = null;
            double min = Double.MAX_VALUE;
            for (Item center :
                    centers) {
                double tmp = distance.computeDistance(center,item);
                if(tmp<min) {
                    item.setCategory(center.getCategory());
                }
            }
        }
    }
}
