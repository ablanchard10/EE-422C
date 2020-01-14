package assignment4;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Instant;
import java.util.Date;

import java.net.MulticastSocket;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.lang.Exception;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import java.util.function.Predicate;

/**
 * TweetReader contains method used to return tweets from method
 * Do not change the method header
 */

public class TweetReader {

    /**
     * Find tweets written by a particular user.
     *
     * @param url
     *            url used to query a GET Request from the server
     * @return return list of tweets from the server
     *
     */

    public static List<Tweets> readTweetsFromWeb(String url) throws Exception
    {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        //System.out.println(response.body());

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        List<Tweets> tweetList = mapper.readValue(response.body().string(), new TypeReference<List<Tweets>>() {
        });                            //create list of all tweets


        //find invalid input tweets and remove them from list
        tweetList.removeIf(n -> (!(n.getId() >= 1)));
        tweetList.removeIf(n -> (n.getName() == null));

        //TO DO: finish line below and see if dates are valid
        tweetList.removeIf(tweets -> (tweets.getText() == null || tweets.getText().length() < 1 || tweets.getText().length() > 140));

        tweetList.removeIf(tweets -> tweets.getDate() == null);

        for(int i = 0; i < tweetList.size(); i++){
            try{
                Instant datechk = Instant.parse(tweetList.get(i).getDate());
            }catch(DateTimeException e){
                tweetList.remove(i);
                i--;
            }
        }
        //System.out.println(tweetList.size());

        return tweetList;
    }
}
