/**
 * Created by cioni on 21/01/17.
 */
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TweetReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
    @Override
    public void reduce(Text key,Iterable<IntWritable> vals,Context context) throws IOException,InterruptedException{
        int sum=0;
        for(IntWritable i:vals){
            sum=sum+i.get();
        }
        context.write(key,new IntWritable(sum));

    }

}
