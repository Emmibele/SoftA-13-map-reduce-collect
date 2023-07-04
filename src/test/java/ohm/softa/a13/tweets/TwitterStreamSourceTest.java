package ohm.softa.a13.tweets;

import ohm.softa.a13.model.Tweet;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of the Tweet stream generators
 * @author Peter Kurfer
 */
class TwitterStreamSourceTest {

    private static final Logger logger = Logger.getLogger(TwitterStreamSourceTest.class.getName());

	private static final String path = "/trump_tweets.json";

	@Test
    void testTweetStreamGenerator() {
        TweetStreamGenerator tsg = TweetStreamGenerator.fromJson(path);
		assertNotNull(tsg);

		List<String> tweets = tsg.getTweetStream()
			.map(Tweet::getText)
			.collect(Collectors.toList());

		assertNotNull(tweets);
        assertEquals(3225, tweets.size());
        assertTrue(tweets.stream().noneMatch(Objects::isNull));
    }

	@Test
	void outputTweets(){
		TweetStreamGenerator tsg = TweetStreamGenerator.fromJson(path);
		tsg.getTweetStream().limit(10).map(x -> x.getSourceApp()).forEach(System.out::println);
	}
}
