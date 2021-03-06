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
    private ArrayList<Cluster> categories;

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
    private Map<Cluster,Set<Item>> itemsByCategory;

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
        currentIterationID=1;
    }

    public boolean add(Item item) {
        return dataSet.add(item);
    }
//TODO: Implements delegate methods of categories and dataSet as needed.

    /**
     * The magical function. It does everything (well running the KMean algorithm.)
     */
    public void computeKMean(int nbIt, int nbCat) {
        while (currentIterationID<nbIt+1) {
            clearer();
            generateRandomCategories(currentIterationID);
            //generateRandomCenter();
            for (Cluster cluster :
                    categories) {
                itemsByCategory.put(cluster, new HashSet<>());
            }
            randomlyAffectAllItems();
            findGravityCenter();
            boolean done = false;
            while (!done) {
                affectAllItems();
                //RemoveFurthests();
                done = findGravityCenter();
            }
            computeVariance();
            currentIterationID++;
        }
        displayResult();
    }
    private void clearer() {
        itemsByCategory.clear();
        categories.clear();
    }
    private void generateRandomCategories(int nbCat) {
        for (int i = 0; i < nbCat; i++) {
            categories.add((new Cluster()));

            categories.get(i).addAll(dataSet.get(0).keySet());
        }
    }

    /**
     * Randomly initialize the centers of the clusters.
     */
    private void generateRandomCenter() {
        Random rnd = new Random();
        Set<Item> tmp = new HashSet<>();
        Set<Item> centers = new HashSet<>();
        for (Cluster cluster :
                categories) {
            Item item = dataSet.get(rnd.nextInt(dataSet.size()));
            item.setCluster(cluster);
            dataSet.remove(item);//Remove temporary the center of the dataSet to avoid having two centers for the same item.
            tmp.add(item);
            cluster.setBarycenter(item);
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
            item.setCluster(categories.get(rnd.nextInt(categories.size())));
            itemsByCategory.get(item.getCluster()).add(item);
        }

        initialDataSets.put(currentIterationID, new ArrayList<>(dataSet));
    }

    /**
     * Compute the variance of the beginning.
     */
    private void computeVariance() {
        double variance=0d;
        for (Cluster cluster :
                categories) {
            for (Item item :
                    itemsByCategory.get(cluster)) {
                variance+=varianceDistance.computeDistance(item, cluster.getBarycenter());
            }
        }
        finalVariance.put(currentIterationID-1,variance);
    }

    /**
     * Affect all items in the right category.
     */
    private void affectAllItems() {
        for (Item item :
                dataSet) {
            Cluster nearest = null;
            double min = Double.MAX_VALUE;
            for (Cluster cluster :
                    categories) {
                double tmp = distance.computeDistance(cluster.getBarycenter(),item);
                if(tmp<min) {
                    nearest = cluster;
                }
            }
            itemsByCategory.get(nearest).add(item);
        }
    }

    /**
     * @return Return true if the centers doesn't changed, false otherwise.
     */
    private boolean findGravityCenter() {
        boolean isComplete = true;
        for (Cluster cluster :
                categories) {
            boolean tmp = cluster.computeBarycenter(itemsByCategory.get(cluster));
            isComplete= isComplete && tmp;
        }
        return isComplete;
    }

    private void displayResult() {
        for (Integer i: finalVariance.keySet()
             ) {
            System.out.print("For iteration #" + (i+1));
            System.out.println(": "+ finalVariance.get(i));

        }
//       System.out.println(getMin() + " - " + getMax());
    }

    private Double getMax(){
        int i = 0;
        Double max = finalVariance.get(0);
        while(i<currentIterationID){
            if(finalVariance.get(i)>max){
                max = finalVariance.get(i);
            }
            i++;
        }
        return max;
    }


    private Double getMin(){
        int i = 0;
        Double min = finalVariance.get(0);
        while(i<currentIterationID){
            if(finalVariance.get(i)<min){
                min = finalVariance.get(i);
            }
            i++;
        }
        return min;
    }
}
