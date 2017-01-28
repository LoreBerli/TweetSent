import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by cioni on 22/01/17.
 */

public class SentimentTester {
    public static void main(String[] args) throws FileNotFoundException{
        SentimentClassifier sntCls = new SentimentClassifier();

        Scanner scan = new Scanner(new File("trainset.txt"));
        float total=0;
        float correct=0;
        while(scan.hasNextLine()){
            total=total+1;
            String line = scan.nextLine();
            int pol = line.charAt(0)=='0' ?1:0;
            String tweet = line.substring(2).toLowerCase();
            int val =(sntCls.classify(tweet)=="Positive")?1:0;
            if(val==pol){
                //System.out.println(val+" . "+pol);
                correct=correct+1;
            }
            String polarity=(val==0)?"Positive":"Negative";
            System.out.println(tweet+"--"+polarity);
            //System.out.println(tweet+" --- " +(val==pol));
        }
        System.out.println("correct: "+correct);
        System.out.println("total: "+total);
        System.out.println(correct/total);
    }
}
