package collectionsAndGenerics;

import java.util.*;

public class MapToMaps {
    public static void main(String[] args) {
        mapsToMaps();

    }

    private static void mapsToMaps() {
        Map<String, Integer> channelToSubscribers = new TreeMap<>(); // channel, newSubscribers
        Map<String, String> channelToPublisher = new TreeMap<>(); // channel, publisher
        Map<String, Integer> publisherToSubscribers = new TreeMap<>(); // publisher, numSubscribers

        // channel -> number of subscribers
        // K -> V1
        channelToSubscribers.put("JustForLaughs", 120_000);
        channelToSubscribers.put("JustForGags", 10_000);
        channelToSubscribers.put("ContemplationTechniques", 10_000);
        channelToSubscribers.put("A New Earth", 20_000);

        // channel -> publisher
        // K -> V2
        channelToPublisher.put("JustForLaughs", "Charlie Chaplin");
        channelToPublisher.put("JustForGags", "Charlie Chaplin");
        channelToPublisher.put("ContemplationTechniques", "Echhart Tolle");
        channelToPublisher.put("A New Earth", "Echhart Tolle");

        // 1. Setup "publisherToSubscribers"
        // publisher -> number of subscribers (total)
        // V2 -> V1
        channelToPublisher.forEach((channel, publisher) -> {
            publisherToSubscribers.computeIfPresent(publisher, (key, oldValue) -> oldValue + channelToSubscribers.get(channel));
            publisherToSubscribers.computeIfAbsent(publisher, (key) -> channelToSubscribers.get(channel));

//            if (publisherToSubscribers.containsKey(publisher)) {
//                publisherToSubscribers.compute(publisher, (key, oldValue) -> oldValue + channelToSubscribers.get(channel));
//            } else {
//                publisherToSubscribers.put(publisher, channelToSubscribers.get(channel));
//            }
        });

        // 2. Output "publisherToSubscribers"
        publisherToSubscribers.forEach((publisher, numOfSubs) ->
                System.out.println("publisher: " + publisher + "; numSubscribers:" + numOfSubs));

        // 3. Who has the most/least subscribers?
        String minKey = Collections.min(publisherToSubscribers.entrySet(), Map.Entry.comparingByValue()).getKey();
        String maxKey = Collections.max(publisherToSubscribers.entrySet(), Map.Entry.comparingByValue()).getKey();

        System.out.println("Publisher with the most subscribers: " + maxKey + " " + publisherToSubscribers.get(maxKey));
        System.out.println("Publisher with fewest subscribers: " + minKey + " " + publisherToSubscribers.get(minKey));

    }
}
