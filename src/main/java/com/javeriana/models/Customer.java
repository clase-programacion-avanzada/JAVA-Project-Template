package com.javeriana.models;

import com.javeriana.exceptions.UnsupportedTypeException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public abstract class Customer implements Serializable {

    // region Attributes
    /**
     * The id attribute is an instance of the UUID class.
     * It is used to uniquely identify each Customer instance.
     */
    private UUID id;

    /**
     * The username attribute is a String that represents the username of the customer.
     */
    private String username;

    /**
     * The password attribute is a String that represents the password of the customer.
     */
    private String password;

    /**
     * The name attribute is a String that represents the name of the customer.
     */
    private String name;

    /**
     * The lastName attribute is a String that represents the last name of the customer.
     */
    private String lastName;

    /**
     * The age attribute is an int that represents the age of the customer.
     */
    private int age;

    /**
     * The followedArtists attribute is a Set of Artist objects.
     * It represents the artists that the customer is following.
     */
    private final Set<Artist> followedArtists;

    /**
     * The playLists attribute is a List of PlayList objects.
     * It represents the playlists that the customer has created.
     */
    private final List<PlayList> playLists;

    // endregion

    // region Constructors
    /**
     * Constructs a new Customer with the specified details, artists and playlists.
     *
     * @param id The unique identifier for the customer.
     * @param username The username of the customer.
     * @param password The password of the customer.
     * @param name The name of the customer.
     * @param lastName The last name of the customer.
     * @param age The age of the customer.
     * @param followedArtists The set of artists followed by the customer.
     * @param playLists The list of playlists owned by the customer.
     */
    public Customer(UUID id, String username, String password, String name, String lastName, int age,
                    Set<Artist> followedArtists, List<PlayList> playLists) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.followedArtists = followedArtists;
        this.playLists = playLists;
    }

    /**
     * Constructs a new Customer with the specified details. Initializes the set of artists and list of playlists as empty.
     *
     * @param username The username of the customer.
     * @param password The password of the customer.
     * @param name The name of the customer.
     * @param lastName The last name of the customer.
     * @param age The age of the customer.
     */
    public Customer(String username, String password, String name, String lastName, int age) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.followedArtists = new HashSet<>();
        this.playLists = new ArrayList<>();
    }

    public Customer(UUID id, String username, String password, String name, String lastName, int age, Set<Artist> followedArtists) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.followedArtists = followedArtists;
        this.playLists = new ArrayList<>();
    }

    public Customer() {
        this.followedArtists = new HashSet<>();
        this.playLists = new ArrayList<>();
    }


    // endregion

    // region getters
    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    // endregion

    //region methods

    /**
     * Adds a playlist to the customer's list of playlists.
     *
     * @param playList The playlist to be added.
     */
    public abstract void addPlayList(PlayList playList);

    /**
     * Follows an artist by adding the artist to the customer's set of followed artists.
     *
     * @param artist The artist to be followed.
     * @return True if the artist was successfully followed, false otherwise.
     */
    public boolean followArtist(Artist artist) {

        return this.followedArtists.add(artist);

    }


    /*
    *  Method to get the playlists of the customer
    *
    * @return List of PlayList
    * */
    public abstract List<PlayList> getPlayLists();


    /*
    *  Method to get the ids of the artists followed by the customer
    *
    *  @return List of artist ids as String
    *
    * */
    private List<String> getArtistIds() {
        List<String> artistIds = new ArrayList<>();
        for (Artist artist : followedArtists) {
            artistIds.add(artist.getId().toString());
        }
        return artistIds;
    }

    /*
    * Method to get the ids of the playlists of the customer
    *
    * @return List of playlists ids as String
    *
    * */
    protected abstract List<String> getPlayListIds();


    /**
     * Returns a list of the IDs of the playlists that the customer has.
     *
     * @return A list of playlist IDs.
     */
    public abstract List<UUID> getPlayListsIds();

    /**
     * Returns a list of string representations of the artists that the customer is following.
     *
     * @return A list of string representations of followed artists.
     */
    public List<String> getFollowedArtistToString() {
        List<String> artistNames = new ArrayList<>();

        for (Artist artist : followedArtists) {
            artistNames.add(artist.toString());
        }

        return artistNames;
    }

    /**
     * Returns a list of the artists that the customer is following.
     *
     * @return A list of followed artists.
     */
    public List<Artist> getFollowedArtists() {
        return new ArrayList<>(followedArtists);
    }

    /**
     * Method to get the string representation of the artist
     * formatted as CSV
     *  i.e if the separator is ";"
     *  format is: id;username;password;name;lastName;age;{artistId1,artistId2,artistId3};{playListId1,playListId2,playListId3}
     *
     * @param separator The separator to use in the CSV string.
     * @return A CSV string representation of the customer.
     */
    public String toCSV(String separator) {

        String artistIds = "{" + String.join(",", getArtistIds()) + "}";
        String playListIds = "{" + String.join(",", getPlayListIds()) + "}";
        return id + separator
            + username + separator
            + password + separator
            + name + separator
            + lastName + separator
            + age + separator
            + artistIds + separator
            + playListIds;
    }

    /**
     * Method to get the string representation of the followed artists
     * @return String with the attributes of the customer as a string
     */
    @Override
    public String toString() {
        return "Nombre completo : " + name + " " + lastName
            + " - username: " + username
            + " - Edad: " + age
            + " - Artistas seguidos: " + followedArtists.size()
            + " - Playlists: " + playLists.size();

    }

    public static Customer createCustomer(String type, UUID id, String username, String password, String name, String lastName, int age, Set<Artist> followedArtists) throws UnsupportedTypeException {
        if (type.equalsIgnoreCase("Regular")) {
            return new RegularCustomer(id, username, password, name, lastName, age, followedArtists);
        } else if (type.equalsIgnoreCase("Premium")) {
            return new PremiumCustomer(id, username, password, name, lastName, age, followedArtists);
        } else {
            throw new UnsupportedTypeException("Tipo de cliente no soportado: " + type);
        }
    }

    public static Customer createCustomer(String type, String username, String password, String name, String lastName, int age) throws UnsupportedTypeException {
        if (type.equalsIgnoreCase("Regular")) {
            return new RegularCustomer(username, password, name, lastName, age);
        } else if (type.equalsIgnoreCase("Premium")) {
            return new PremiumCustomer(username, password, name, lastName, age);
        } else {
            throw new UnsupportedTypeException("Tipo de cliente no soportado: " + type);
        }
    }

    //endregion
}
