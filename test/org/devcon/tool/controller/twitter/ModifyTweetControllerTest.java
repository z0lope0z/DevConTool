package org.devcon.tool.controller.twitter;

import org.slim3.datastore.Datastore;
import org.slim3.tester.ControllerTestCase;
import org.devcon.tool.model.Tweet;
import org.devcon.tool.service.TwitterService;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ModifyTweetControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        Tweet tweet = new Tweet();
        TwitterService twitterService = new TwitterService();
//        twitterService.tweet("asdfasdfasdf");
        tweet.getKey();
        
        tester.param("content", "Hello");
        tester.start("/twitter/modifyTweet");
        
        ModifyTweetController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(true));
        assertThat(tester.getDestinationPath(), is("/twitter/"));
        Tweet stored = Datastore.query(Tweet.class).asSingle();
        
        assertThat(stored, is(notNullValue()));
        assertThat(stored.getContent(), is("Hello"));
        assertThat(tester.requestScope("tweetList"), is(notNullValue()));
    }
}
