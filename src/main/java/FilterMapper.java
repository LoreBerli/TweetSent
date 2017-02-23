/**
 * Created by cioni on 08/02/17.
 */
import org.apache.commons.lang.ObjectUtils;
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
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

public class FilterMapper extends Mapper<LongWritable,Text,Text,Text>{
    private ArrayList<String> topics;

    public FilterMapper() throws FileNotFoundException{
        Properties prop = new Properties();
        PropertiesLoader prs=new PropertiesLoader();
        try {
            prop.load(prs.getPropAsStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] arr = prop.getProperty("topics").split(" ");
        this.topics=new ArrayList<String>(Arrays.asList(arr));
    }

    public String checkForTopics(String twt){
        ArrayList<String> twtLst= new ArrayList<String>(Arrays.asList(twt.split(" ")));
        for(String t:topics){
            if(twtLst.contains(t.replace(" ",""))){
            return t;}
        }
        return null;

    }

    @Override
    public void map(LongWritable key,Text val,Context context) throws IOException,InterruptedException{

        String words= val.toString().toLowerCase();
        Scanner scan = new Scanner(words);
        while (scan.hasNextLine()){
            String ln=scan.nextLine();
            String top = checkForTopics(ln.toLowerCase());
            if(top!=null){

            context.write(new Text(ln),new Text(top));}
        }
    }

}
