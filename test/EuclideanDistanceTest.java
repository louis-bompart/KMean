import org.junit.Test;

import java.io.Console;
import java.util.HashSet;
import java.util.Set;

/**
 * Test for the computing of the Euclidean Distance
 * Created by louis on 02/03/2016.
 */
public class EuclideanDistanceTest {

    @Test
    public void testComputeDistance() throws Exception {
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

        assert (new EuclideanDistance().computeDistance(a,b)==2d);
    }
}