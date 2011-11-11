package org.devcon.tool.controller.twitter;

import java.util.List;

import org.devcon.tool.model.Tweet;
import org.devcon.tool.service.TwitterService;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class TweetController extends Controller {
    
    private TwitterService twitterservice = new TwitterService();
    
    @Override
    public Navigation run() throws Exception {
        if ((request.getAttribute("content") != null) && (request.getAttribute("content") != "")){
            twitterservice.tweet(new RequestMap(request));
        }
        List<Tweet> tweetList = twitterservice.getTweetList();
        requestScope("tweetList", tweetList);
        return forward("index.jsp");
    }
    
}
