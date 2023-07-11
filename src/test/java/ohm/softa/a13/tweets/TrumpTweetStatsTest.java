package ohm.softa.a13.tweets;

import ohm.softa.a13.model.Tweet;
import ohm.softa.a13.utils.PrintUtils;
import ohm.softa.a13.utils.ResourceUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Test cases for TrumpTweetStats
 * @author Peter Kurfer
 */
class TrumpTweetStatsTest {

    private static final Logger logger = Logger.getLogger(TrumpTweetStatsTest.class.getName());
	private static String path = "/trump_tweets.json";
    private final TweetStreamGenerator tsg;
    private final List<String> stopWords;

    TrumpTweetStatsTest() {
        /* get singleton instance of generator factory */
        this.tsg = TweetStreamGenerator.fromJson(path);
        /* load stop words */
        this.stopWords = ResourceUtils.loadStopWords();
    }

    /**
     * Test of `calculateTweetsBySourceApp`
     */
    @Test
    void calculateTweetsBySourceApp() {
        Stream<Tweet> tweetStream = tsg.getTweetStream();
        Map<String, Set<Tweet>> tweetsBySourceApp = TrumpTweetStats.calculateTweetsBySourceApp(tweetStream);

        assertNotEquals(0, tweetsBySourceApp.keySet().size());
        logger.info(PrintUtils.mapToString(tweetsBySourceApp, Function.identity(), set -> PrintUtils.iterableToString(set, Tweet::getText)));
    }

    /**
     * Test of `calculateWordCount`
     */
    @Test
    void getWordCount() {
		Stream<Tweet> tweetStream = tsg.getTweetStream();
        Map<String, Long> wordCount = TrumpTweetStats.calculateWordCount(tweetStream, stopWords);

		assertNotEquals(0, wordCount.keySet().size());
        logger.info(PrintUtils.mapToString(wordCount));
    }

    /**
     * Test of `calculateSourceAppStats`
     */
    @Test
    void calculateSourceAppStats() {
		Stream<Tweet> tweetStream = tsg.getTweetStream();
        Map<String, Long> sourceAppStats = TrumpTweetStats.calculateSourceAppStats(tweetStream);
        assertNotEquals(0, sourceAppStats.keySet().size());
        logger.info(PrintUtils.mapToString(sourceAppStats));
    }
}
