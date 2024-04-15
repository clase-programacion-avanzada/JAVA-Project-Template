package com.javeriana.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The Customer class represents a customer in a music application.
 * It is a simple Java class that implements the Serializable interface, allowing instances of this class to be converted into a byte stream and vice versa.
 * This is useful for saving objects to disk or sending them over a network.
 *
 * This class provides methods for:
 * - Creating a Customer with specific details, artists, and playlists
 * - Creating a Customer with specific details and initializing the followedArtists and playLists as empty
 * - Retrieving the id, username, password, and name of the Customer
 * - Adding a playlist to the customer's list of playlists
 * - Allowing a customer to follow an artist
 * - Returning the playlists, artist IDs, playlist IDs, playlist IDs, string representations of followed artists, and followed artists of the customer
 * - Returning a CSV representation of the Customer object
 * - Returning a string representation of the Customer object
 *
 * The class has eight attributes: id, username, password, name, lastName, age, followedArtists, and playLists.
 * The id is a UUID (Universally Unique Identifier), which is used to uniquely identify each Customer instance.
 * The username, password, name, and lastName are String values that represent the username, password, name, and last name of the customer respectively.
 * The age is an int that represents the age of the customer.
 * The followedArtists is a Set of Artist objects, representing the artists that the customer is following.
 * The playLists is a List of PlayList objects, representing the playlists that the customer has created.
 */
public class PlayList implements Serializable {

    // region Attributes
    /**
     * The id attribute is an instance of the UUID class.
     * It is used to uniquely identify each PlayList instance.
     */
    private UUID id;

    /**
     * The name attribute is a String that represents the name of the playlist.
     */
    private String name;

    /**
     * The songs attribute is a List of Song objects.
     * It represents the songs that are included in the playlist.
     */
    private List<Song> songs;

    // endregion

    /**
     * Constructs a PlayList object with the provided id, name, and list of songs.
     *
     * @param id The unique identifier for the playlist.
     * @param name The name of the playlist.
     * @param songs The list of songs in the playlist.
     */
    public PlayList(UUID id, String name, List<Song> songs) {
        this.id = id;
        this.name = name;
        this.songs = songs;
    }

    /**
     * Constructs a PlayList object with the provided name.
     * The id is generated randomly and an empty list of songs is initialized.
     *
     * @param name The name of the playlist.
     */
    public PlayList(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.songs = new ArrayList<>();
    }

    // region Getters

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    // endregion

    // region Methods

    /**
     * Adds a song to the playlist.
     *
     * @param song The song to be added to the playlist.
     */
    public void addSong(Song song) {

        this.songs.add(song);
    }

    /**
     * Returns a list of song IDs in the playlist.
     *
     * @return A list of song IDs.
     */
    public List<String> getSongIds() {
        List<String> songIds = new ArrayList<>();
        for (Song song : songs) {
            songIds.add(song.getId().toString());
        }
        return songIds;
    }

    /**
     * Removes a song from the playlist by its id.
     *
     * @param songId The id of the song to be removed.
     * @return A boolean indicating whether the song was successfully removed.
     */
    public boolean removeSong(String songId) {

        for (Song song : songs) {
            if (song.getId().toString().equals(songId)) {
                return songs.remove(song);
            }
        }

        return false;

    }

    /**
     * Returns a list of string representations of the songs in the playlist.
     *
     * @return A list of string representations of the songs.
     */
    public List<String> getSongsToString() {
        List<String> songNames = new ArrayList<>();
        for (Song song : songs) {
            songNames.add(song.toString());
        }
        return songNames;
    }

    /**
     * Returns a CSV representation of the PlayList object.
     * The representation format is: id;name;{songId1,songId2,songId3}
     *
     * @param separator The separator to be used in the CSV representation.
     * @return A CSV representation of the PlayList.
     */
    public String toCSV(String separator) {
        String songIds = "{" + String.join(",", getSongIds()) + "}";

        return id + separator + name + separator + songIds;
    }

    /**
     * Returns a string representation of the PlayList object.
     * The representation format is: "nombre de la playlist: " + name + " con id: " + id + " y número de canciones: " + songs.size()
     *
     * @return A string representation of the PlayList.
     */
    @Override
    public String toString() {
        return "nombre de la playlist: " + name + " con id: " + id + " y número de canciones: " + songs.size();
    }



    /**
     * Returns a PlayList object with unknown data. This method is used when the playlist is not found in the file.
     *
     * @param playListId The id of the unknown playlist.
     * @return A PlayList object with the provided id and name set as "Unknown PlayList".
     */
    public static PlayList getUnknownPlayList(String playListId) {
        return new PlayList(UUID.fromString(playListId), "Unknown PlayList", new ArrayList<>());
    }

    // endregion
}
