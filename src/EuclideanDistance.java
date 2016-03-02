import java.util.ArrayList;

/**
 * Return distance following the euclidean method.
 * Created by louis on 02/03/2016.
 */
public class EuclideanDistance implements Distance {
    @Override
    public double computeDistance(Item a, Item b) {
        ArrayList<Variable> variables = new ArrayList<>(a.keySet());
        double distance=0;
        for (Variable var :
                variables) {
            distance =Math.pow((a.get(var)-b.get(var)),2);
        }
        return Math.sqrt(distance);
    }
}
