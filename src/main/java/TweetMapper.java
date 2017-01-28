/**
 * Created by cioni on 21/01/17.
 */
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Scanner;

public class TweetMapper extends Mapper<LongWritable,Text,Text,IntWritable>{
    SentimentClassifier sentClass;

    public TweetMapper(){
        sentClass=new SentimentClassifier();
    }
    @Override
    public void map(LongWritable key,Text val,Context context) throws IOException,InterruptedException{

        String words= val.toString().toLowerCase();
        Scanner scan = new Scanner(words);
        while (scan.hasNextLine()){
            context.write(new Text(sentClass.classify(scan.nextLine())),new IntWritable(1));
        }
    }
}
