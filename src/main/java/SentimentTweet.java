/**
 * Created by cioni on 21/01/17.
 */
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;

import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//TODO Stemming
public class SentimentTweet {
    public static void main(String[] args) throws Exception{
        Properties prop = new Properties();
        PropertiesLoader prs=new PropertiesLoader();
        try {

            //prop.load(new FileInputStream("src/main/resources/config.properties"));
            prop.load(prs.getPropAsStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JobConf conf=new JobConf(SentimentTweet.class);
        Configuration mapAConf = new Configuration(false);
        Configuration mapBConf = new Configuration(false);
        conf.setJobName("Tweet");
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);
        Job job=Job.getInstance(conf);


        ChainMapper.addMapper(job,FilterMapper.class,LongWritable.class,Text.class,Text.class,Text.class,mapAConf);
        ChainMapper.addMapper(job,TweetMapper.class, Text.class, Text.class, Text.class, IntWritable.class,mapBConf);

        if(args.length == 0)
        {
            FileInputFormat.addInputPath(job,new Path(prop.getProperty("inputPath").toString()));
            FileOutputFormat.setOutputPath(job,new Path(prop.getProperty("outputPath").toString()));
        }
        else{
            FileInputFormat.addInputPath(job,new Path(args[0]));
            FileOutputFormat.setOutputPath(job,new Path(args[1]));
        }




        job.setReducerClass(TweetReducer.class);
        long start=System.currentTimeMillis();

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        if(job.waitForCompletion(false)) {
            System.out.println(System.currentTimeMillis() - start);
            System.exit(1);
        }
    }
}
