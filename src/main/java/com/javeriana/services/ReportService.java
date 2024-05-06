package com.javeriana.services;

import com.javeriana.models.Artist;
import com.javeriana.models.Song;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The ReportService class in the com.javeriana.services package is a service class that provides methods for generating reports in a music application.
 *
 * The class does the following:
 * 1. Provides a method getMostFollowedArtists for returning a map of artist names to the number of times they are followed. It initializes an empty map, iterates over the followed artists list, and adds each artist's name to the map with a count. If the artist's name is already in the map, it increments the count.
 * 2. Provides a method getCountOfSongsByArtist for returning a map of song IDs to the number of times they appear in the given list. It initializes an empty map, iterates over the songs by artist list, and adds each song's ID to the map with a count. If the song's ID is already in the map, it increments the count.
 * 3. Provides a method maxSong for returning the ID of the song that has the maximum count in the given map. It initializes maxSong as null and max as 0, iterates over the entries in the most added songs map, and checks if the value (the count of the song) is greater than max. If it is, it updates max to the current count and maxSong to the current song ID.
 */
public class ReportService {

    /**
     * Returns a map of artist names to the number of times they are followed.
     *
     * The method does the following:
     * 1. Initializes an empty map of artist names to counts.
     * 2. Iterates over the followedArtists list and adds each artist's name to the map with a count. If the artist's name is already in the map, it increments the count. This is done using the merge method from Java 8, which is a more efficient way to handle this operation.
     * 3. Returns the map of artist names to counts.
     *
     * @param followedArtists A list of artists that are followed.
     * @return A map of artist names to the number of times they are followed.
     */
    public Map<String, Integer> getMostFollowedArtists(List<Artist> followedArtists) {
        Map<String, Integer> artistCounts = new HashMap<>();
        for (Artist artist : followedArtists)  {
            artistCounts.merge(artist.getName(), 1, Integer::sum);
        }

        return artistCounts;
    }

    /**
     * Returns the ID of the song that has the maximum count in the given map.
     *
     * The method does the following:
     * 1. Initializes maxSong as null and max as 0. maxSong will hold the ID of the song with the maximum count, and max will hold the maximum count found so far.
     * 2. Iterates over the entries in the mostAddedSongs map. For each entry, it checks if the value (the count of the song) is greater than max. If it is, it updates max to the current count and maxSong to the current song ID.
     * 3. Returns maxSong, the ID of the song with the maximum count.
     *
     * @param mostAddedSongs A map of song IDs to their counts.
     * @return The ID of the song with the maximum count.
     */
    public UUID maxSong(Map<UUID, Integer> mostAddedSongs) {
        UUID maxSong = null;
        for (Map.Entry<UUID, Integer> entry : mostAddedSongs.entrySet()){
            Integer max = 0;
            if (entry.getValue() > max){
                max = entry.getValue();
                maxSong = entry.getKey();
            }
        }
        return maxSong;
    }

    /**
     * Returns a map of song IDs to the number of times they appear in the given list.
     *
     * The method does the following:
     * 1. Initializes an empty map of song IDs to counts.
     * 2. Iterates over the songsByArtist list. For each song, it checks if the song's ID is already in the map. If it is, it increments the count. Otherwise, it adds the song's ID to the map with a count of 1.
     * 3. Returns the map of song IDs to counts.
     *
     * @param songsByArtist A list of songs by an artist.
     * @return A map of song IDs to the number of times they appear in the list.
     */
    public Map<UUID, Integer> getCountOfSongsByArtist(List<Song> songsByArtist) {
        Map<UUID, Integer> songCounts = new HashMap<>();
        for (Song song : songsByArtist)  {
            songCounts.merge(song.getId(), 1, Integer::sum);
        }

        return songCounts;
    }
}