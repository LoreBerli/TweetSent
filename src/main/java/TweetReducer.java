/**
 * Created by cioni on 21/01/17.
 */
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashMap;


public class TweetReducer extends Reducer<Text,IntWritable,Text,Text>{
    private HashMap<String,ArrayList<Integer>> out;
    public TweetReducer(){
        out=new HashMap<String, ArrayList<Integer>>();
    }

    @Override
    public void reduce(Text key,Iterable<IntWritable> vals,Context context) throws IOException,InterruptedException{
        //String splt=key;
        for(IntWritable i:vals){
            vote(key.toString(), i.get());
        }
        context.write(key,new Text(getCount(key.toString())));
    }

    private String getCount(String topic){
        return out.get(topic).toString();
    }

    private void vote(String topic,int polarity){
        if(!out.containsKey(topic)){
            addTopic(topic);
        }
        out.get(topic).set(polarity,out.get(topic).get(polarity)+1);
    }


    private void addTopic(String topic){
        ArrayList<Integer> counts=new ArrayList<Integer>();
        counts.add(0);
        counts.add(0);
        out.put(topic,counts);


    }

}
