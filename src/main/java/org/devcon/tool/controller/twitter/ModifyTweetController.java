package org.devcon.tool.controller.twitter;

import java.util.ArrayList;
import java.util.List;

import org.devcon.tool.model.Tweet;
import org.devcon.tool.service.TwitterService;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class ModifyTweetController extends Controller {
    
    private TwitterService twitterService = new TwitterService();
    
    public void deleteTweets(String[] deleteKeys){
        for(String stringKey : deleteKeys){
            Key key = KeyFactory.stringToKey(stringKey);
            twitterService.remove(key);
        }
    }
    
    @Override
    public Navigation run() throws Exception {
        List<Tweet> tweetList = new ArrayList<Tweet>();
        tweetList = twitterService.getTweetList();
        String[] deleteKeys = (String[]) request.getParameterValues("delete[]");
        if (deleteKeys.length > 0){
            deleteTweets(deleteKeys);
        } else {
            
        }
        requestScope("tweetList", tweetList);
        return forward("index.jsp");
    }
    
}