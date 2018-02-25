/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package package1;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import package1.TwitterConnector;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

/**
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @since Twitter4J 2.1.7
 */
public class SearchTweetsCLI {
    /**
     * Usage: java twitter4j.examples.search.SearchTweets [query]
     *
     * @param args search query
     */
    public static void main(String[] args) {
        /*if (args.length < 1) {
        	// TODO : arg1 : search term; arg2 : output file path
            System.out.println("java twitter4j.examples.search.SearchTweets [query]");
            System.exit(-1);
        }*/
        
        //SearchTweets s = new SearchTweets();
       
        /*
        Twitter twitter = new TwitterFactory().getInstance();
        try {
            Query query = new Query(args[0]);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
        */
        // Step 1 : Create connector object
        // Step 2 : Give call to searchTweets method
        // Step 3 : Open output stream on given file path.
        // Step 4 : Iterate through list of tweets (for loop); Write to output stream; close stream
        
        
        TwitterConnector tc = new TwitterConnector();
        KafkaP kp = new KafkaP();
        KafkaC kc = new KafkaC();
        List<Status> tweets = tc.searchTweets(args[0]);
        
        String msg;
        
        
        try{
        	
		    // Create file 
		    FileWriter fstream = new FileWriter(System.currentTimeMillis() + args[1]);
	    	BufferedWriter out = new BufferedWriter(fstream);
	    	Status tweet1 = null;
		    for (Status tweet : tweets) {
		    	//BufferedWriter out = new BufferedWriter(fstream);
			    out.write(tweet.getText());
			    msg = tweet.toString();
			    kp.Topic(msg);
			    tweet1 = tweet;
            }
		    
		    kc.consumeMsg();
		   
		    //Close the output stream
		    out.close();
		    
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
        
    }
}