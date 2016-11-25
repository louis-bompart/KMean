import java.util.ArrayList;

/**
 * Return distance following the euclidean method.
 * Created by louis on 02/03/2016.
 */
public class EuclideanDistance implements Distance {
    /**
     * @param item One of the item of the dataset
     * @param center The center to measure distance.
     * @return the distance.
     */
    @Override
    public double computeDistance(Item item, Item center) {
        ArrayList<Variable> variables = center.getCluster().getVariables();
        double distance=0;
        for (Variable var :
                variables) {
                distance =Math.pow((item.get(var)-center.get(var)),2);
        }
        return Math.sqrt(distance);
    }
}
