import java.io.IOException;

/**
 * Created by juhel on 09/03/2016.
 */
public class Main {
    public static void main(String[] Args){
        String path = "Data2.csv";
        int nbCategory = 14;
        int nbIteration = 28;
        Environment environment= new Environment();
        try {
            new Reader(path, environment);
        } catch (IOException e) {
            e.printStackTrace();
        }
        environment.computeKMean(nbIteration, nbCategory);
    }
}
