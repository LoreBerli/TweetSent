/**
 * Created by cioni on 08/02/17.
 */
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FilterMapper extends Mapper<LongWritable,Text,Text,IntWritable>{
    private ArrayList<String> topics;
    public FilterMapper(){
        String[] arr = {"tom","cruise"};
        this.topics=new ArrayList<String>(Arrays.asList(arr));
    }

    public boolean checkForTopics(String twt){
        ArrayList<String> twtLst= new ArrayList<String>(Arrays.asList(twt.split(" ")));
        for(String t:topics){
            if(twtLst.contains(t)){
            return true;}
        }
        return false;

    }

    @Override
    public void map(LongWritable key,Text val,Context context) throws IOException,InterruptedException{

        String words= val.toString().toLowerCase();
        Scanner scan = new Scanner(words);
        while (scan.hasNextLine()){
            String ln=scan.nextLine();
            if(checkForTopics(ln)){
            context.write(new Text(ln),new IntWritable(1));}
        }
    }

}
