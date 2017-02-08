/**
 * Created by cioni on 21/01/17.
 */
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//TODO CHAINING;Argomenti;Stemming
public class SentimentTweet {
    public static void main(String[] args) throws Exception{
        Properties prop = new Properties();
        try {

            prop.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Job job = new Job();
        job.setJarByClass(SentimentTweet.class);
        job.setJobName("Tweet");

        if(args.length == 0)
        {
            FileInputFormat.addInputPath(job,new Path(prop.getProperty("inputPath").toString()));
            FileOutputFormat.setOutputPath(job,new Path(prop.getProperty("outputPath").toString()));
        }
        else{
            FileInputFormat.addInputPath(job,new Path(args[0]));
            FileOutputFormat.setOutputPath(job,new Path(args[1]));
        }

        job.setMapperClass(FilterMapper.class);
        job.setReducerClass(TweetReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        System.exit(job.waitForCompletion(false) ? 0:1);
    }
}
