/**
 * A variable, used for better understanding in other classes than a simple integer.
 * Created by louis on 02/03/2016.
 */
public class Variable {
    private int id;

    public Variable(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double distance(Item a, Item b) {
        return Math.pow((a.get(this)-b.get(this)),2);
    }
}
