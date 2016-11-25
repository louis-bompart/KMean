import java.util.*;

/**
 * An item of a data set.
 * Created by louis on 02/03/2016.
 */
public class Item {
    private Cluster cluster;
    private Map<Variable,Double> values;


    public Set<Variable> keySet() {
        return values.keySet();
    }

    public Double get(Variable key) {
        //noinspection SuspiciousMethodCalls
        return values.get(key);
    }

    public void replaceValue(Variable key, double value) {
        values.replace(key,values.get(key)+value);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null) {
            return false;
        }
        if(obj.getClass().equals(Item.class))
        for (Variable variable :
                keySet()) {
                if (!Objects.equals(((Item) obj).get(variable), values.get(variable))) {
                    return false;
                }
            }
        return true;
        //return ((Item)obj).getCluster().equals(cluster);
    }

    /**
     * Standard constructor.
     * Just initialize class' variables.
     */
    public Item() {
        cluster = null;
        values = new HashMap<>();
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public Double put(Variable key, Double value) {
        return values.put(key, value);
    }

}
