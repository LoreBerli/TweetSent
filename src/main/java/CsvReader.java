import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by cioni on 14/02/17.
 */
public class CsvReader {
    BufferedReader br;
    public CsvReader(String path) throws FileNotFoundException,IOException{
        br = new BufferedReader(new FileReader(path));
    }
    public String nextTweet()throws IOException{
        String ln;
        if((ln=br.readLine())!=null){
            return clean(ln);
        };
        return null;
    }

    private String clean(String in){
        //Specifico per il dataset usato nel training
        int commas=0;
        int pos=0;
        while(commas<5 && pos<in.length()-1){
            if(in.charAt(pos)==','){
                commas++;
            }
            pos++;
        }
        return in.substring(pos);
    }
}
