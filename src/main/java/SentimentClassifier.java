import com.aliasi.classify.ConditionalClassification;
import com.aliasi.classify.LMClassifier;
import com.aliasi.util.AbstractExternalizable;

import java.io.File;
import java.io.IOException;

/**
 * Created by cioni on 22/01/17.
 */

public class SentimentClassifier {
    LMClassifier clss;
    public SentimentClassifier() {
        try {
            clss= (LMClassifier)AbstractExternalizable.readObject(new File("classifier1.txt"));
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
