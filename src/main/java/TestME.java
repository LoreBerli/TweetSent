import java.util.Properties;

/**
 * Created by cioni on 22/02/17.
 */
public class TestME {
    public static void main(String[] args)throws Exception{
        for(int i=0;i<10;i++){
            String[] agv = {"hadTest","cities"+i};
            SentimentTweet.main(agv);
        }
    }
}
