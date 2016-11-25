import java.io.IOException;

/**
 * Created by juhel on 09/03/2016.
 */
public class Main {
    public static void main(String[] Args){
        String path = "data.csv";
        int nbCategory = 3;
        int nbIteration = 30;
        Environment environment= new Environment();
        try {
            new Reader(path, environment);
        } catch (IOException e) {
            e.printStackTrace();
        }
        environment.computeKMean(nbIteration, nbCategory);
    }
}
