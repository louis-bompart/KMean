import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Test of category.
 * Created by louis on 02/03/2016.
 */
public class ClusterTest {

    @Test
    public void testComputeBarycenter() throws Exception {
        Cluster cluster = new Cluster();
        Set<Item> items = new HashSet<>();

        //Create some variables
        int nbVar = 4;
        Set<Variable> variables = new HashSet<>();
        for (int i = 0; i < nbVar; i++) {
            variables.add(new Variable(i));
        }

        //Create two items
        Item a = new Item();
        Item b = new Item();
        for (Variable var :
                variables) {
            a.put(var, 9d);
            b.put(var, 7d);
        }

        items.add(a);
        items.add(b);

        cluster.addAll(a.keySet());
        cluster.computeBarycenter(items);
        Item barycenter =  cluster.getBarycenter();
        for (Variable var :
                variables) {
            assert (barycenter.get(var) == 8d);
        }
        assert (barycenter.getCluster().equals(cluster));
    }
}