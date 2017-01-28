import com.aliasi.classify.ConditionalClassification;
import com.aliasi.classify.LMClassifier;
import com.aliasi.util.AbstractExternalizable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by cioni on 22/01/17.
 */

public class SentimentClassifier {

    LMClassifier clss;
    public SentimentClassifier() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            clss= (LMClassifier)AbstractExternalizable.readObject(new File(prop.getProperty("pathToClassifier")));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String classify(String text) {
        ConditionalClassification classification = clss.classify(text);
        return classification.bestCategory();
        // /return java.util.Arrays.asList(categories).indexOf(classification.bestCategory());
    }
}
