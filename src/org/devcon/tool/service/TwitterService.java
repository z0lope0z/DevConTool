package org.devcon.tool.service;

import java.util.List;
import java.util.Map;

import org.devcon.tool.meta.TweetMeta;
import org.devcon.tool.model.Tweet;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Filter;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.Query.FilterOperator;


public class TwitterService {

    private TweetMeta t = new TweetMeta();
    
    public Tweet tweet(Map<String, Object> input) {
        Tweet tweet = new Tweet();
        BeanUtil.copy(input, tweet);
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(tweet);
        tx.commit();
        return tweet;
    }
    
    public List<Tweet> getTweetList(){
        return Datastore.query(t).sort(t.createdDate.desc).asList();
    }
    
    public List<Tweet> getTweetListByContent(String contentQuery){
        TweetMeta tweetMeta = TweetMeta.get();
        List<Tweet> list = Datastore.query(tweetMeta)
            .filter(tweetMeta.content.startsWith(contentQuery))
            .sort(tweetMeta.content.asc)
            .asList();
        return Datastore.query(t).sort(t.createdDate.desc).asList();
    }
    
    public List<Tweet> getTweetListByVersion(String version){
        TweetMeta tweetMeta = TweetMeta.get();
        List<Tweet> list = Datastore.query(tweetMeta)
            .filter(new Filter("version", FilterOperator.EQUAL, version))
            .sort(tweetMeta.content.asc)
            .asList();
        return Datastore.query(t).sort(t.createdDate.desc).asList();
    }
    
    public List<Tweet> getTweetListByContentAndVersion(String contentQuery, String version){
        TweetMeta tweetMeta = TweetMeta.get();
        List<Tweet> list = Datastore.query(tweetMeta)
            .filter(new Filter("content", FilterOperator.IN, contentQuery), new Filter("version", FilterOperator.EQUAL, version))
            .sort(tweetMeta.content.asc)
            .asList();
        return Datastore.query(t).sort(t.createdDate.desc).asList();
    }
}
