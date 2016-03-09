import java.util.*;

/**
 * Class where all the magic happens, the KMean algorithm is executed right here.
 * Created by louis on 02/03/2016.
 */

public class Environment {

    /**
     * The distance used to compute.
     */
    private final Distance distance = new EuclideanDistance();

    /**
     * The distance used to compute variance.
     */
    private final Distance varianceDistance = new SquaredEuclideanDistance();

    /**
     * List of used categories for the run of K-means.
     */
    private ArrayList<Category> categories;

    /**
     * Data set used for the run of K-means.
     */
    private ArrayList<Item> dataSet;

    /**
     * Collection of initial data sets, identified by id.
     */
    private Map<Integer,ArrayList<Item>> initialDataSets;

    /**
     * Collection of final variance, identified by id (same as up).
     */
    private Map<Integer,Double> finalVariance;

    /**
     * Store item in different collection and by category.
     */
    private Map<Category,Set<Item>> itemsByCategory;

    /**
     * The current ID.
     */
    private int currentIterationID;

    /**
     * Standard constructor.
     * Just initialize Arrays properly.
     */
    public Environment() {
        categories = new ArrayList<>();
        dataSet = new ArrayList<>();
        itemsByCategory = new HashMap<>();
        initialDataSets = new HashMap<>();
        finalVariance = new HashMap<>();
        currentIterationID=0;
    }

    //TODO: Implements delegate methods of categories and dataSet as needed.

    /**
     * The magical function. It does everything (well running the KMean algorithm.)
     */
    public void computeKMean(int nbIt) {
        while (currentIterationID<nbIt) {
            //generateRandomCenter();
            for (Category category :
                    categories) {
                itemsByCategory.put(category, new HashSet<>());
            }
            randomlyAffectAllItems();
            findGravityCenter();
            boolean done = false;
            while (!done) {
                affectAllItems();
                done = findGravityCenter();
            }
            computeVariance();
            currentIterationID++;
        }
    }

    /**
     * Randomly initialize the centers of the clusters.
     */
    private void generateRandomCenter() {
        Random rnd = new Random();
        Set<Item> tmp = new HashSet<>();
        Set<Item> centers = new HashSet<>();
        for (Category category :
                categories) {
            Item item = dataSet.get(rnd.nextInt(dataSet.size()));
            item.setCategory(category);
            dataSet.remove(item);//Remove temporary the center of the dataSet to avoid having two centers for the same item.
            tmp.add(item);
            category.setBarycenter(item);
        }
        dataSet.addAll(tmp);
    }

    /**
     * Randomly affect all items.
     */
    private void randomlyAffectAllItems() {
        Random rnd = new Random();
        for (Item item :
                dataSet) {
            item.setCategory(categories.get(rnd.nextInt(categories.size())));
            itemsByCategory.get(item.getCategory()).add(item);
        }

        initialDataSets.put(currentIterationID, new ArrayList<>(dataSet));
    }

    /**
     * Compute the variance of the beginning.
     */
    private void computeVariance() {
        double variance=0d;
        for (Category category :
                categories) {
            for (Item item :
                    itemsByCategory.get(category)) {
                variance+=varianceDistance.computeDistance(item,category.getBarycenter());
            }
        }
        finalVariance.put(currentIterationID,variance);
    }

    /**
     * Affect all items in the right category.
     */
    private void affectAllItems() {
        for (Item item :
                dataSet) {
            Item nearest = null;
            double min = Double.MAX_VALUE;
            for (Category category:
                    categories) {
                double tmp = distance.computeDistance(category.getBarycenter(),item);
                if(tmp<min) {
                    item.setCategory(category);
                }
            }
            itemsByCategory.get(item.getCategory()).add(item);
        }
    }

    /**
     * @return Return true if the centers doesn't changed, false otherwise.
     */
    private boolean findGravityCenter() {
        boolean isComplete = true;
        for (Category category :
                categories) {
            isComplete= isComplete && category.computeBarycenter(itemsByCategory.get(category));
        }
        return isComplete;
    }

    /*
    *   First thought, but implementing this in affectAllItems avoid to
    *   loop on dataSet once more
    private void findGravityCenter() {
        //Initialization
        Map<Category,Set<Item>> itemsByCategory = new HashMap<>();
        for (Category category :
                categories) {
            itemsByCategory.put(category,new HashSet<>());
        }

        for (Item item :
                dataSet) {
            itemsByCategory.get(item.getCategory()).add(item);
        }
    }*/
}
