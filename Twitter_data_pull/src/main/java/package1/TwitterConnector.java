package package1;

import java.util.ArrayList;

import java.util.List;
//import package1.SearchTweetsCLI;
//import com.twitter.SearchTweets;

import twitter4j.*;

public class TwitterConnector {

	public TwitterConnector() {
		// TODO Create Twitter class object
		
		//Twitter twitter = new TwitterFactory().getInstance();
	}

	public List<Status> searchTweets(String searchTerm) {
		// Take search term as argument.
		// Give call to twitter.search
		// Iterate through results populate List<>
		// return list<>
		
		Twitter twitter = new TwitterFactory().getInstance();
		List<Status> tweets = null;
        List<Status> resultList = new ArrayList<Status>();
		
		try {
            Query query = new Query(searchTerm);
            query.setLang("en");
            QueryResult result = null;
            //result = twitter.search(query.since("2017-01-01").until("2017-01-04"));

            //query = result.since("2017-01-01").until("2017-01-04");
            
            
            do{
                result = twitter.search(query);
                tweets = result.getTweets();
                for (Status tweet : tweets) {
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText() + tweet.getCreatedAt());
                    query = result.nextQuery();
                    resultList.add(tweet);
                }
                

                //query = result.nextQuery();
            } while ((query=result.nextQuery() ) != null);
            
            /*for(int i=0;i<5;i++){
            	
            	result = twitter.search(query.since("2017-06-01").until("2017-06-02"));
                tweets = result.getTweets();
                for (Status tweet : tweets) {
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                    query = result.nextQuery();
                    
                    resultList.add(tweet);
                }           
            }*/
            
            
            //System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
		
		
		return resultList;
	}
}
