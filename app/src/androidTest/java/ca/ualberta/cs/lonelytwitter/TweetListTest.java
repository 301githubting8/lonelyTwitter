package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ting8 on 9/29/16.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {

    public TweetListTest(){
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testAdd(){
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("adding tweet");
        tweets.add(tweet);
        assertTrue(tweets.hasTweet(tweet));
    }

    public void testHasTweet(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        assertTrue(list.hasTweet(tweet));
    }

    public void testDelete(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        list.delete(tweet);
        assertFalse(list.hasTweet(tweet));
    }

    public void testGetTweet(){
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("test");
        tweets.add(tweet);
        Tweet returnedTweet = tweets.getTweet(0);
        assertEquals(returnedTweet.getMessage(),tweet.getMessage());
    }

    public void testGetCount(){
        TweetList tweets = new TweetList();
        Tweet tweet1 = new NormalTweet("test1");
        tweets.add(tweet1);
        Tweet tweet2 = new NormalTweet("test2");
        tweets.add(tweet2);
        int count = tweets.getCount();
        assertEquals(count, 2);
    }

    public void testGetTweets(){
        TweetList tweets = new TweetList();
        Tweet tweet1 = new NormalTweet("test1");
        tweets.add(tweet1);
        Tweet tweet2 = new NormalTweet("test2");
        tweets.add(tweet2);
        ArrayList<Tweet> result = new ArrayList<Tweet>();
        result.add(tweet1);
        result.add(tweet2);
        assertEquals(result,tweets.getTweets());
    }

    public void testAddTweet(){
        TweetList tweets = new TweetList();
        Tweet tweet1 = new NormalTweet("test1");
        tweets.add(tweet1);
        Tweet tweet2 = new NormalTweet("test2");
        tweets.add(tweet2);
        Tweet tweet3 = new NormalTweet("test2");
        try{
            tweets.addTweet(tweet3);
            assertTrue(Boolean.FALSE);
        }catch (IllegalArgumentException e){
            assertTrue(Boolean.TRUE);
        }




    }
}
