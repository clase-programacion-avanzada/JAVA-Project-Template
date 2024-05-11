package com.javeriana.services;

import com.javeriana.controllers.AdminController;
import com.javeriana.exceptions.NotFoundException;
import com.javeriana.models.Artist;
import com.javeriana.models.Song;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The SongService class in the com.javeriana.services package is a service class that provides methods for managing songs in a music application.
 *
 * The class does the following:
 * 1. Initialization: The SongService class has a constructor that initializes an empty list of songs.
 * 2. Song Management: It provides methods for adding a new song (addSong), deleting a song by its ID (deleteSong), and searching for a song by its ID (searchSongById).
 * 3. Song List Management: It provides methods for replacing the current list of songs with a new list (loadSongs), getting a new list containing all songs (getSongs), and getting a list of the names of all songs (getSongsToString).
 * 4. Song Mapping: It provides a method for getting a map of song IDs to their corresponding Song objects (getSongsById).
 * 5. Artist-Song Relationship: It provides a method for getting a list of songs by the artist with a given ID (searchSongsByArtistId).
 */
public class SongService {

    /**
     * A list of songs.
     */
    private final List<Song> songs;

    /**
     * Constructor for the SongService class.
     * Initializes the songs list.
     */
    public SongService() {
        this.songs = new ArrayList<>();
    }

    /**
     * Returns a new list containing all songs.
     *
     * @return A new list containing all songs.
     */
    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    /**
     * Adds a new song to the list of songs.
     *
     * The method does the following:
     * 1. Validates the song attributes (name, genre, durationInSeconds) using the validateSongAttributes method from the AdminController class.
     * 2. Checks if the album of the song is null or empty. If it is, it throws an IllegalArgumentException.
     * 3. Creates a new Song object with the given attributes (name, genre, durationInSeconds, album).
     * 4. Adds the list of artists to the song using the addArtists method of the Song class.
     * 5. Adds the song to the list of songs.
     *
     * @param name The name of the new song.
     * @param genre The genre of the new song.
     * @param durationInSeconds The duration of the new song in seconds.
     * @param album The album of the new song.
     * @param artistsList The list of artists of the new song.
     */
    public void addSong(String name, String genre, int durationInSeconds, String album, List<Artist> artistsList) {




    }

    /**
     * Searches for a song by its ID.
     *
     * The method does the following:
     * 1. Converts the input string ID to a UUID using the UUID.fromString method.
     * 2. Iterates over the songs list. For each song, it checks if the song's ID is equal to the input ID. If it is, it returns the song.
     * 3. If no song with the input ID is found, it returns null.
     *
     * @param id The ID of the song to search for.
     * @return The song with the given ID, or null if no such song exists.
     */
    public Song searchSongById(String id) {

        UUID songId = UUID.fromString(id);

        for (Song song : songs) {
            if (song.getId().equals(songId)) {
                return song;
            }
        }

        return null;
    }

    /**
     * Replaces the current list of songs with the given list.
     *
     * The method does the following:
     * 1. Clears the current list of songs using the clear method.
     * 2. Adds all songs from the given list to the current list using the addAll method.
     *
     * @param songs The new list of songs.
     */
    public void loadSongs(List<Song> songs) {
        this.songs.clear();
        this.songs.addAll(songs);
    }

    /**
     * Returns a map of song IDs to their corresponding Song objects.
     *
     * The method does the following:
     * 1. Initializes an empty map of song IDs to Song objects.
     * 2. Iterates over the songs list. For each song, it adds the song's ID and the song itself to the map.
     * 3. Returns the map of song IDs to Song objects.
     *
     * @return A map of song IDs to their corresponding Song objects.
     */
    public Map<String, Song> getSongsById() {

        Map<String, Song> songsById = new HashMap<>();

        for (Song song : songs) {
            songsById.put(
                song.getId().toString(),
                song);
        }

        return songsById;
    }

    /**
     * Returns a list of the names of all songs.
     *
     * The method does the following:
     * 1. Initializes an empty list of song names.
     * 2. Iterates over the songs list. For each song, it adds the song's name to the list.
     * 3. Returns the list of song names.
     *
     * @return A list of the names of all songs.
     */
    public List<String> getSongsToString() {
        List<String> songNames = new ArrayList<>();

        for (Song song : songs) {
            songNames.add(song.toString());
        }

        return songNames;
    }

    /**
     * Deletes the song with the given ID.
     *
     * The method does the following:
     * 1. Searches for the song with the given ID using the searchSongById method. If the song is found, it is returned; otherwise, null is returned.
     * 2. Checks if the song is null (i.e., it does not exist). If it is, it throws a NotFoundException.
     * 3. Removes the song from the list of songs.
     *
     * @param songId The ID of the song to delete.
     * @throws NotFoundException If no song with the given ID exists.
     */
    public void deleteSong(String songId) throws NotFoundException {

    }

    /**
     * Returns a list of songs by the artist with the given ID.
     *
     * The method does the following:
     * 1. Initializes an empty list of songs by the artist.
     * 2. Iterates over the songs list. For each song, it iterates over the song's artists. If the artist's ID is equal to the input artist ID, it adds the song to the list of songs by the artist.
     * 3. Returns the list of songs by the artist.
     *
     * @param artistId The ID of the artist to get the songs for.
     * @return A list of songs by the artist with the given ID.
     */
    public List<Song> searchSongsByArtistId(String artistId) {
        return new ArrayList<>();
    }
}
