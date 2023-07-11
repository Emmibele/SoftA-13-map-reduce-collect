package ohm.softa.a13.tweets;

import ohm.softa.a13.model.Tweet;
import org.apache.commons.lang3.NotImplementedException;

import java.util.*;
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

    public static Map<String, Long> calculateWordCount(Stream<Tweet> tweetStream, List<String> stopWords) {
		Map<String, Long> möp = tweetStream
			.map(Tweet::getText)
			.map(t -> t.split(" "))
			.flatMap(Arrays::stream)
			.map(String::toLowerCase)
			.map(String::trim)
			.filter(t -> !stopWords.contains(t))
			.collect(Collectors.groupingBy(s -> s, counting()));

		return möp;
    }
}
