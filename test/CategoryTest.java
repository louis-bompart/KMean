import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Test of category.
 * Created by louis on 02/03/2016.
 */
public class CategoryTest {

    @Test
    public void testComputeBarycenter() throws Exception {
        Category category = new Category();
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

        category.addAll(a.keySet());
        category.computeBarycenter(items);
        Item barycenter =  category.getBarycenter();
        for (Variable var :
                variables) {
            assert (barycenter.get(var) == 8d);
        }
        assert (barycenter.getCategory().equals(category));
    }
}