import java.io.InputStream;

/**
 * Created by cioni on 16/02/17.
 */
public class PropertiesLoader {
    String filename="config.properties";
    public InputStream getPropAsStream(){
        return getClass().getClassLoader().getResourceAsStream(filename);

    }
}
