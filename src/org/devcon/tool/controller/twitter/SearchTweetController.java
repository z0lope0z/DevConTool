package org.devcon.tool.controller.twitter;

import java.util.ArrayList;
import java.util.List;

import org.devcon.tool.model.Tweet;
import org.devcon.tool.service.TwitterService;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class SearchTweetController extends Controller {
    
    private TwitterService twitterService = new TwitterService();

    @Override
    public Navigation run() throws Exception {
        List<Tweet> tweetList = new ArrayList<Tweet>();
        String contentQuery = (String) request.getAttribute("content");
        String versionQuery = (String) request.getAttribute("version");
        
        if ((contentQuery != null) && (versionQuery != "")){
            tweetList = twitterService.getTweetListByContentAndVersion(contentQuery, versionQuery);
        } else if (contentQuery != null) {
            tweetList = twitterService.getTweetListByContent(contentQuery);
        } else {
            tweetList = twitterService.getTweetListByVersion(versionQuery);
        }
        
        requestScope("tweetList", tweetList);
        return forward("index.jsp");
    }
}
