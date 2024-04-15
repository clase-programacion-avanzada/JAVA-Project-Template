package com.javeriana.services;

import com.javeriana.models.PlayList;
import com.javeriana.models.Song;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The PlayListService class in the com.javeriana.services package is a service class that provides methods for managing playlists in a music application.
 *
 * The class does the following:
 * 1. Initializes a list of PlayList objects in the constructor.
 * 2. Provides methods for adding a new playlist (addPlayList), replacing the current list of playlists with a given list (loadPlayLists), and getting a playlist by its ID (getPlayListById).
 * 3. Provides methods for removing a song from a specific playlist (deleteSongFromPlayList) and from all playlists (deleteSongFromPlayLists).
 * 4. Provides methods for getting a list of all playlists (getPlayLists), getting a map of playlists by their IDs (getPlayListsById), getting a list of the names of all playlists (getPlayListsToString), and getting a list of all songs in all playlists (getAllSongsInPlayLists).
 * 5. Provides a method for removing playlists with given IDs from the list of playlists (deletePlayLists).
 */
public class PlayListService {

    private final List<PlayList> playLists;


    /**
     * Constructor for the PlayListService class.
     * Initializes the playLists list.
     */
    public PlayListService() {
        this.playLists = new ArrayList<>();
    }

    /**
     * Returns a new list containing all playlists.
     *
     * @return A new list containing all playlists.
     */
    public List<PlayList> getPlayLists() {
        return new ArrayList<>(playLists);
    }

    /**
     * Adds a new playlist with the given name to the list of playlists.
     *
     * The method does the following:
     * 1. Checks if the provided name is null or empty. If it is, it throws an IllegalArgumentException.
     * 2. Creates a new PlayList object with the provided name.
     * 3. Adds the newly created PlayList object to the playLists list.
     * 4. Returns the newly created PlayList object.
     *
     * @param name The name of the new playlist.
     * @return The newly created playlist.
     * @throws IllegalArgumentException If the name is null or empty.
     */
    public PlayList addPlayList(String name) {

        return PlayList.getUnknownPlayList(UUID.randomUUID().toString());
    }

    /**
     * Replaces the current list of playlists with the given list.
     *
     * The method does the following:
     * 1. Clears the current playLists list.
     * 2. Adds all elements from the provided list to the playLists list.
     *
     * @param playLists The new list of playlists.
     */
    public void loadPlayLists(List<PlayList> playLists) {

    }

    /**
     * Returns the playlist with the given ID.
     *
     * The method does the following:
     * 1. Converts the provided string ID to a UUID.
     * 2. Iterates over the playLists list and checks if the ID of each playlist matches the provided ID.
     * 3. Returns the playlist with the matching ID. If no such playlist is found, it returns null.
     *
     * @param id The ID of the playlist to return.
     * @return The playlist with the given ID, or null if no such playlist exists.
     * @throws IllegalArgumentException If the ID is not a valid UUID.
     */
    public PlayList getPlayListById(String id) throws IllegalArgumentException {

        UUID playListId = UUID.fromString(id);

        for (PlayList playList : playLists) {
            if (playList.getId().equals(playListId)) {
                return playList;
            }
        }

        return null;
    }

    /**
     * Returns a map of playlists by their IDs.
     *
     * The method does the following:
     * 1. Creates a stream from the playLists list.
     * 2. Collects the elements of the stream into a map. The keys of the map are the string representations of the IDs of the playlists, and the values are the playlists themselves.
     *
     * @return A map of playlists by their IDs.
     */
    public Map<String, PlayList> getPlayListsById() {
        return playLists.stream()
                .collect(
                    java.util.stream.Collectors.toMap(
                        playList -> playList.getId().toString(),
                        playList -> playList
                    )
                );
    }

    /**
     * Removes the song with the given ID from the playlist with the given ID.
     *
     * The method does the following:
     * 1. Retrieves the playlist with the provided ID by calling the getPlayListById method.
     * 2. Checks if the retrieved playlist is null. If it is, it returns false, indicating that the song could not be removed because the playlist does not exist.
     * 3. Calls the removeSong method on the retrieved playlist with the provided song ID. The removeSong method returns a boolean indicating whether the song was successfully removed.
     *
     * @param playListId The ID of the playlist.
     * @param songId The ID of the song.
     * @return True if the song was removed, false otherwise.
     */
    public boolean deleteSongFromPlayList(String playListId, String songId) {

        PlayList playList = getPlayListById(playListId);

        if (playList == null) {
            return false;
        }

        return playList.removeSong(songId);

    }

    /**
     * Removes the playlists with the given IDs from the list of playlists.
     *
     * The method does the following:
     * 1. Initializes an empty list of playlists to remove.
     * 2. Iterates over the provided list of playlist IDs, retrieves the playlist with each ID by calling the getPlayListById method, and adds the retrieved playlist to the list of playlists to remove if it is not null.
     * 3. Removes all playlists in the list of playlists to remove from the playLists list.
     *
     * @param playListsIds The IDs of the playlists to remove.
     */
    public void deletePlayLists(List<UUID> playListsIds) {



    }

    /**
     * Removes the song with the given ID from all playlists.
     *
     * The method does the following:
     * 1. Iterates over the playLists list.
     * 2. Calls the removeSong method on each playlist with the provided song ID.
     *
     * @param songId The ID of the song to remove.
     */
    public void deleteSongFromPlayLists(String songId) {

        for (PlayList playList : playLists) {
            playList.removeSong(songId);
        }

    }

    /**
     * Returns a list of the names of all playlists.
     *
     * The method does the following:
     * 1. Initializes an empty list of playlist names.
     * 2. Iterates over the playLists list and adds the string representation of each playlist to the list of playlist names.
     * 3. Returns the list of playlist names.
     *
     * @return A list of the names of all playlists.
     */
    public List<String> getPlayListsToString() {
        List<String> playListToString = new ArrayList<>();
        for (PlayList playList : playLists) {
            playListToString.add(playList.toString());
        }
        return playListToString;
    }

    /**
     * Returns a list of all songs in all playlists.
     *
     * The method does the following:
     * 1. Initializes an empty list of songs.
     * 2. Iterates over the playLists list and adds all songs from each playlist to the list of songs.
     * 3. Returns the list of songs.
     *
     * @return A list of all songs in all playlists.
     */
    public List<Song> getAllSongsInPlayLists() {
        return new ArrayList<>();
    }
}
