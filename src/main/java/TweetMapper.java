/**
 * Created by cioni on 21/01/17.
 */
import com.aliasi.tokenizer.IndoEuropeanTokenCategorizer;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.PorterStemmerTokenizerFactory;
import com.aliasi.tokenizer.TokenizerFactory;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Scanner;



public class TweetMapper extends Mapper<Text,Text,Text,IntWritable>{
    SentimentClassifier sentClass;
    PorterStemmerTokenizerFactory prt;
    public TweetMapper() throws IOException{
        sentClass=new SentimentClassifier();
        prt = new PorterStemmerTokenizerFactory(new IndoEuropeanTokenizerFactory());
    }

    private String stemProcess(String s){
        String[] tmp=s.split(" ");
        String out="";
        for(String t:tmp){
            out=out+" "+prt.stem(t);
        }
        return out;
    }

    private int polToInt(String pol){

        if(pol.equals("pos")){

            return 0;
        }
        else{
            return 1;
        }

    }

    @Override
    public void map(Text key,Text val,Context context) throws IOException,InterruptedException{

        String words= stemProcess(key.toString().toLowerCase());

        String topic=val.toString();
        Scanner scan = new Scanner(words);
        while (scan.hasNextLine()){
            String tmp=sentClass.classify(scan.nextLine());            context.write(new Text(topic),new IntWritable(polToInt(tmp)));

        }
    }
}
