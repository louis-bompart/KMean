import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Class which handle the reading of dataset
 * Created by juhel on 09/03/2016.
 */
public class Reader {

    public Reader(String path, Environment environment) throws IOException {
        String line;
        InputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(isr);
        ArrayList<Variable> variables = new ArrayList<>();
        boolean initialized = false;
        while ((line = br.readLine()) != null) {
            String[] input = line.split(",");
            Item item = new Item();
            for(int i = 0; i < input.length; i++){
                if(!initialized){
                    variables.add(new Variable(i));
                }
                item.put(variables.get(i), Double.parseDouble(input[i]));
            }
            if(!initialized){
                initialized = true;
            }
            environment.add(item);
        }
    }
}
