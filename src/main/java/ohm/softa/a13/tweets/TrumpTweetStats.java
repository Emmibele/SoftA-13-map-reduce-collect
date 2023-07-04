package ohm.softa.a13.tweets;

import ohm.softa.a13.model.Tweet;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class TrumpTweetStats {

    public static Map<String, Long> calculateSourceAppStats(Stream<Tweet> tweetStream) {
		Map<String, Long> tweetAppCount = tweetStream
			.collect(groupingBy(Tweet::getSourceApp,
					Collectors.counting()));

		return tweetAppCount;
    }

    public static Map<String, Set<Tweet>> calculateTweetsBySourceApp(Stream<Tweet> tweetStream) {
        Map<String, Set<Tweet>> tweetsByApp = tweetStream
			.collect(groupingBy(Tweet::getSourceApp, toSet()));
		return tweetsByApp;
    }

    public static Map<String, Integer> calculateWordCount(Stream<Tweet> tweetStream, List<String> stopWords) {
        /* Remark: implement this method at last */
        /* TODO split the tweets, lower them, trim them, remove all words that are in the `stopWords`,
         * reduce the result to a Map<String, Integer> to count how often each word were in the tweets
         * optionally you could filter for all words that were used more than 10 times */

        throw new NotImplementedException("TrumpTweetStats.tweetStream(...) not implemented yet.");
    }
}
