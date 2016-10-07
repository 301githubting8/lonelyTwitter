package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Collections;



/**
 * Created by ting8 on 9/29/16.
 */
public class TweetList {
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public TweetList(){

    }

    public boolean hasTweet(Tweet tweet){
        return tweets.contains(tweet);
    }

    public void add(Tweet tweet) {
        tweets.add(tweet);
    }

    public void delete(Tweet tweet) {
        tweets.remove(tweet);
    }

    public Tweet getTweet(int index) {
        return tweets.get(index);
    }

    public int getCount() {
        return tweets.size();
    }

    public ArrayList<Tweet> getTweets() {

        //Collections.sort(tweets);
        return tweets;

    }

    public void addTweet(Tweet tweet){

        for (Tweet i : tweets) {
            if (i.getMessage().equals(tweet.getMessage())){
                throw new IllegalArgumentException();
            }
        }
        tweets.add(tweet);
    }
}
