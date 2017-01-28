/**
 * Created by cioni on 21/01/17.
 */
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class SentimentTweet {
    public static void main(String[] args) throws Exception{
        Job job = new Job();
        job.setJarByClass(SentimentTweet.class);
        job.setJobName("Tweet");
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        job.setMapperClass(TweetMapper.class);
        job.setReducerClass(TweetReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        System.exit(job.waitForCompletion(false) ? 0:1);
    }
}
