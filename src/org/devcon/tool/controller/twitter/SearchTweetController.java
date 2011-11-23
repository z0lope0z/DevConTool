package org.devcon.tool.controller.twitter;

import java.util.ArrayList;
import java.util.List;

import org.devcon.tool.model.Tweet;
import org.devcon.tool.service.TwitterService;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class SearchTweetController extends Controller {
    
    private TwitterService twitterService = new TwitterService();
    
    private List<Tweet> searchFilter(String contentQuery, String versionQuery){
        if ((contentQuery != null) && (versionQuery != "")){
            return twitterService.getTweetListByContentAndVersion(contentQuery, versionQuery);
        } else if (contentQuery != null) {
            return twitterService.getTweetListByContent(contentQuery);
        } else if (versionQuery != null){
            return twitterService.getTweetListByVersion(versionQuery);
        } else {
            return twitterService.getTweetList();
        }
    }
    
    public void deleteTweets(String[] deleteKeys){
        for(String stringKey : deleteKeys){
            Key key = KeyFactory.stringToKey(stringKey);
            twitterService.remove(key);
        }
    }
    
    @Override
    public Navigation run() throws Exception {
        String contentQuery = (String) request.getAttribute("content");
        String versionQuery = (String) request.getAttribute("version");
        String modify = (String) request.getAttribute("modify");
        if (modify != null){
            if (modify.equals("delete")) {
                String[] deleteKeys = (String[]) request.getParameterValues("delete[]");
                if (deleteKeys.length > 0){
                    deleteTweets(deleteKeys);
                }
            } else if (modify.equals("edit")){
                return redirect("addTweet?key=" + request.getParameter("edit[]"));
            }
        }
        requestScope("tweetList", searchFilter(contentQuery, versionQuery));
        return forward("index.jsp");
    }
}
