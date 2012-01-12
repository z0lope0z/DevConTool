package org.devcon.tool.controller.twitter;

import javax.security.auth.callback.CallbackHandler;

import org.devcon.tool.model.Tweet;
import org.devcon.tool.service.TwitterService;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PrePut;

public class AddTweetController extends Controller {

    private TwitterService twitterService = new TwitterService();
    
    private void fillForm(String stringKey){
        Tweet tweet = new Tweet();
        if (stringKey != null){
            if (!stringKey.equals("")){
                try {
                    tweet = twitterService.get(KeyFactory.stringToKey(stringKey));
                } catch (Exception e) {
                    tweet = new Tweet();
                }
            }
        }
        requestScope("tweet", tweet);
    }
    
    @PrePut
    @Override
    public Navigation run() throws Exception {
        String stringKey = (String) request.getAttribute("key");
        String submitForm = (String) request.getAttribute("saveOrUpdate");
        fillForm(stringKey);
        if (submitForm != null){
            Tweet tweet = twitterService.tweet(new RequestMap(request));
            tweet.notify();
            if (tweet.getKey() != null){
                return redirect("/searchTweet");
            } else { 
                return redirect("/searchTweet");
            }
        }
        return forward("addTweet.jsp");
    }

}