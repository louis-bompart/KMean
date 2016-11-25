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
     * List of used clusters for the run of K-means.
     */
    private ArrayList<Cluster> clusters;

    /**
     * Data set used for the run of K-means.
     */
    private ArrayList<Item> dataSet;

    /**
     * Collection of initial data sets, identified by id.
     */
    private Map<Integer, ArrayList<Item>> initialDataSets;

    /**
     * Collection of final variance, identified by id (same as up).
     */
    private Map<Integer, Double> finalVariance;


    private ArrayList<Item> unaffectedItems;

    /**
     * The current ID.
     */
    private int currentIterationID;

    /**
     * Standard constructor.
     * Just initialize Arrays properly.
     */
    public Environment() {
        clusters = new ArrayList<>();
        dataSet = new ArrayList<>();
        unaffectedItems = new ArrayList<>();
        initialDataSets = new HashMap<>();
        finalVariance = new HashMap<>();
        currentIterationID = 1;
    }

    public boolean add(Item item) {
        return dataSet.add(item);
    }
//TODO: Implements delegate methods of clusters and dataSet as needed.

    /**
     * The magical function. It does everything (well running the KMean algorithm.)
     */
    public void computeKMean(int nbIt, int nbPoints) {
        //generateRandomCategories(nbPoints);
        while (currentIterationID < nbIt + 1) {
            //generateRandomCenter();
//            for (Cluster cluster :
//                    clusters) {
//                itemsByCategory.put(cluster, new HashSet<>());
//            }
//            randomlyAffectAllItems();
            unaffectedItems.clear();
            unaffectedItems.addAll(dataSet);
            while (!unaffectedItems.isEmpty()) {
                Cluster currentCluster = generateCluster();
                currentCluster.computeBarycenter(unaffectedItems);
                List<Item> clusterItem = RemoveFurthests(currentCluster, nbPoints);
                AffectItems(clusterItem,currentCluster);
            }
            computeVariance();
            displayResult();
            currentIterationID++;
        }
    }

    private Cluster generateCluster() {
        Cluster currentCluster = new Cluster();
        currentCluster.addAll(dataSet.get(0).keySet());
        clusters.add(currentCluster);
        return currentCluster;
    }

    /**
     * Compute the variance of the beginning.
     */
    private void computeVariance() {
        double variance = 0d;
        for (Cluster cluster :
                clusters) {
            for (Item item :
                    dataSet) {
                variance += varianceDistance.computeDistance(item, item.getCluster().getBarycenter());
            }
        }
        finalVariance.put(currentIterationID - 1, variance);
    }

    private float computeThing(Variable variable, Item barycenter) {
        float toReturn = 0;
        for (Item item: unaffectedItems
             ) {
            toReturn+=variable.distance(item,barycenter);
        }
        return toReturn/unaffectedItems.size();
    }

    /**
     * Remove the furthest item in a category
     */
    private List<Item> RemoveFurthests(Cluster cluster,int nbPoints) {
        ArrayList<Item> clusterItems = new ArrayList<>(unaffectedItems);
        while (clusterItems.size() > nbPoints) {
            Item toRemove = null;
            double max = Double.MIN_VALUE;
            for (Item item : clusterItems
                    ) {
                double tmp = distance.computeDistance(item,cluster.getBarycenter());
                if (tmp > max) {
                    toRemove = item;
                    max = tmp;
                }
            }
            clusterItems.remove(toRemove);
            cluster.computeBarycenter(clusterItems);
        }
        AffectItems(clusterItems,cluster);
        unaffectedItems.removeAll(clusterItems);
        return clusterItems;
    }

    private void AffectItems(Collection<Item> items, Cluster cluster) {
        for (Item item: items
             ) {
            item.setCluster(cluster);
        }
    }

    private void findGravityCenter(Cluster cluster) {
        cluster.computeBarycenter(unaffectedItems);
    }

    private void displayResult() {
        for (int i = 0; i < currentIterationID; i++) {
            System.out.print("For iteration #" + i);
            System.out.println(": " + finalVariance.get(i));
        }
    }
}
