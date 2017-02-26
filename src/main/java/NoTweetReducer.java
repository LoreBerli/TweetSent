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
import java.util.Map;


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
        context.write(key,new Text(ratio(getCount(key.toString()))));
    }

    private ArrayList<Integer> getCount(String topic){
        return out.get(topic);
    }

    private void vote(String topic,int polarity){
        if(!out.containsKey(topic)){
            addTopic(topic);
        }
        out.get(topic).set(polarity,out.get(topic).get(polarity)+1);
    }

    private String ratio(ArrayList<Integer> votes){
        if(votes.get(1)==0){
            return "0";
        }
        else {
            return String.valueOf((float)votes.get(1)/(float)votes.get(0));
        }
    }


    private HashMap<String,Float> getRatios(){
        HashMap<String,Float> ratios= new HashMap<String, Float>();

        for(Map.Entry<String,ArrayList<Integer>> entry:out.entrySet()){
            if(entry.getValue().get(1)==0){
                ratios.put(entry.getKey(),Float.valueOf((float) 0.0));
            }
            else {
                Float ratio=Float.valueOf((float)entry.getValue().get(0)/(float)entry.getValue().get(1));
                ratios.put(entry.getKey(),ratio);
            }
        }
        return ratios;
    }


    private void addTopic(String topic){
        ArrayList<Integer> counts=new ArrayList<Integer>();
        counts.add(0);
        counts.add(0);
        out.put(topic,counts);


    }

}
