package com.javeriana.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The Song class represents a song in a music application.
 * It is a simple Java class that implements the Serializable interface, allowing instances of this class to be converted into a byte stream and vice versa.
 * This is useful for saving objects to disk or sending them over a network.
 *
 * This class provides methods for:
 * - Creating a Song with a specific id, name, artists, genre, durationInSeconds, and album
 * - Creating a Song with specific details and initializing the artists as an empty list
 * - Retrieving the id, name, and artists of the Song
 * - Adding a list of artists to the song
 * - Returning a list of artist IDs in the song
 * - Returning a CSV representation of the Song object
 * - Returning a string representation of the Song object
 * - Returning a Song object with unknown data when the song is not found in the file
 *
 * The class has six attributes: id, name, artists, genre, durationInSeconds, and album.
 * The id is a UUID (Universally Unique Identifier), which is used to uniquely identify each Song instance.
 * The name is a String that represents the name of the song.
 * The artists is a List of Artist objects, representing the artists that are associated with the song.
 * The genre is a String that represents the genre of the song.
 * The durationInSeconds is an int that represents the duration of the song in seconds.
 * The album is a String that represents the album of the song.
 */

public class Song implements Serializable, Playable {

    // region Attributes
    /**
     * The id attribute is an instance of the UUID class.
     * It is used to uniquely identify each Song instance.
     */
    private UUID id;

    /**
     * The name attribute is a String that represents the name of the song.
     */
    private String name;

    /**
     * The artists attribute is a List of Artist objects.
     * It represents the artists that are associated with the song.
     */
    private List<Artist> artists;

    /**
     * The genre attribute is a String that represents the genre of the song.
     */
    private String genre;

    /**
     * The durationInSeconds attribute is an int that represents the duration of the song in seconds.
     */
    private int durationInSeconds;

    /**
     * The album attribute is a String that represents the album of the song.
     */
    private String album;
    // endregion

    // region Constructors
    /**
     * Constructs a Song object with the provided id, name, list of artists, genre, duration in seconds, and album.
     *
     * @param id The unique identifier for the song.
     * @param name The name of the song.
     * @param artists The list of artists of the song.
     * @param genre The genre of the song.
     * @param durationInSeconds The duration of the song in seconds.
     * @param album The album of the song.
     */
    public Song(UUID id, String name, List<Artist> artists, String genre, int durationInSeconds, String album) {
        this.id = id;
        this.name = name;
        this.artists = artists;
        this.genre = genre;
        this.durationInSeconds = durationInSeconds;
        this.album = album;
    }

    /**
     * Constructs a Song object with the provided name, genre, duration in seconds, and album.
     * The id is generated randomly and an empty list of artists is initialized.
     *
     * @param name The name of the song.
     * @param genre The genre of the song.
     * @param durationInSeconds The duration of the song in seconds.
     * @param album The album of the song.
     */
    public Song(String name, String genre, int durationInSeconds, String album) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.genre = genre;
        this.durationInSeconds = durationInSeconds;
        this.album = album;
        this.artists = new ArrayList<>();
    }

    // endregion



    // region Getters

    public List<Artist> getArtists() {
        return new ArrayList<>(artists);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // endregion

    // region Methods

    /**
     * Adds a list of artists to the song.
     *
     * @param artists The list of artists to be added to the song.
     */
    public void addArtists(List<Artist> artists) {
        this.artists.addAll(artists);
    }

    /**
     * Returns a list of artist IDs in the song.
     *
     * @return A list of artist IDs.
     */
    private List<String> getArtistIds() {
        List<String> artistIds = new ArrayList<>();
        for (Artist artist : artists) {
            artistIds.add(artist.getId().toString());
        }
        return artistIds;
    }

    /**
     * Returns a CSV representation of the Song object.
     * The representation format is: id;name;{artistId1,artistId2,artistId3};genre;durationInSeconds;album
     *
     * @param separator The separator to be used in the CSV representation.
     * @return A CSV representation of the Song.
     */
    public String toCSV(String separator) {

        String artistIds = "{" + String.join(",", getArtistIds()) + "}";

        return id + separator
            + name + separator
            + artistIds + separator
            + genre + separator
            + durationInSeconds + separator
            + album;
    }

    /**
     * Returns a string representation of the Song object.
     * The representation format is: "Nombre: " + name + " - Artistas: " + artistNames + " - Genero: " + genre + " - Duración en segundos: " + durationInSeconds + " - Album: " + album + " - ID: " + id
     *
     * @return A string representation of the Song.
     */
    @Override
    public String toString() {
        String artistNames =
            String.join(",", getArtists().stream().map(Artist::getName).toList());

        return "Nombre: " + name
            + " - Artistas: " + artistNames
            + " - Genero: " + genre
            + " - Duración en segundos: " + durationInSeconds
            + " - Album: " + album
            + " - ID: " + id;
    }

    /**
     * Returns a Song object with unknown data. This method is used when the song is not found in the file.
     *
     * @param songId The id of the unknown song.
     * @return A Song object with the provided id and name set as "Unknown Song", genre as "Unknown Genre", duration as 0, and album as "Unknown Album".
     */
    public static Song getUnknownSong(String songId) {
        return new Song(UUID.fromString(songId),
            "Unknown Song",
            List.of(Artist.GetUnknownArtist(songId)),
            "Unknown Genre",
            0,
            "Unknown Album");
    }

    @Override
    public String play() {
        return "Reproduciendo canción: " + getName();
    }

    // endregion
}
