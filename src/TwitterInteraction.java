import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.util.*;

//This class uses the twitter4j library to update a twitter status via code and perform limited searches.
//http://twitter4j.org/en/
public class TwitterInteraction {

	Twitter twitter; //holds the twitter API

    //logs into twitter using OAuth
	TwitterInteraction() {

		try {
			//find the keys here: https://developer.twitter.com/en/apps/
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey("3NAyjDdgfvGWkEa3LCSKRv0yn") //API Key here
					.setOAuthConsumerSecret("4Lbwn9FZzha0fp7KHX7voerxjKOCIZuwVLuYT23w2P2qJCbhh6") //Secret key here
					.setOAuthAccessToken("1060597881923612672-Gywtv25hvTEf3Czk7sLochivcj5K2e") //access token here
					.setOAuthAccessTokenSecret("ds47XftfWrjfq4Pmlqdj2XuUEVogxhsWZVNDzG1wlVfYs"); //secret access token here
			TwitterFactory tf = new TwitterFactory(cb.build());
			twitter = tf.getInstance();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to get timeline: " + e.getMessage());
		}

	}

	//updates twitter status with the update_str
	public void updateTwitter(String update_str) {
		try {

			Status status = twitter.updateStatus(update_str);
			System.out.println("Successfully updated the status to [" + status.getText() + "].");
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to read the system input.");
		}
	}

	//returns a list of tweets with the given search term
	public ArrayList<String> searchForTweets(String searchTerm) {
		ArrayList<String> res = new ArrayList(); 
		try {
			Query query = new Query(searchTerm);
			query.count(100);
			
			QueryResult result = twitter.search(query);
			for (Status status : result.getTweets()) {
//				System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
				res.add(status.getText()); 
			}
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to read the system input.");
		}
		return res; 
	}

}
