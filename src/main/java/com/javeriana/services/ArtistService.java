package com.javeriana.services;

import com.javeriana.exceptions.NotFoundException;
import com.javeriana.models.Artist;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * The ArtistService class is part of a music application and is responsible for managing artists.
 * It provides a variety of methods for handling artist-related operations.
 *
 * This class provides methods for:
 * - Adding new artists
 * - Deleting artists
 * - Finding an artist by their ID or name
 * - Retrieving a list of all artists, a list of the names of all artists, or a list of artists by their IDs
 * - Replacing the current list of artists with a given list
 * - Creating a map where the keys are artist IDs and the values are Artist objects
 *
 * This class uses a list to store artists. It also uses several methods for validating artist names and IDs.
 */
public class ArtistService {

    // region Attributes
    private List<Artist> artists ;

    // endregion

    // region Constructors
    /**
     * Constructs an ArtistService object.
     * Initializes the list of artists as an empty ArrayList.
     */
    public ArtistService() {
        this.artists = new ArrayList<>();
    }

    // endregion

    // region getters
    /**
     * Returns a list of all artists.
     *
     * @return A list of all Artist objects.
     */
    public List<Artist> getArtists() {
        return new ArrayList<>(artists);
    }
    // endregion

    // region Methods
    /**
     * Loads a list of artists into the service, replacing any existing artists.
     *
     * @param artists The list of artists to be loaded into the service.
     */
    public void loadArtists(List<Artist> artists) {
        this.artists.clear();
        this.artists.addAll(artists);
    }

    /**
     * This method is responsible for creating a map of artists by their IDs. It creates a new `HashMap` where the keys are artist IDs (as strings)
     * and the values are `Artist` objects. It iterates over the list of artists, and for each artist, it adds an entry to the map where the key is
     * the artist's ID (converted to a string) and the value is the artist object. Finally, it returns the created map.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It creates a new `HashMap` where the keys are artist IDs (as strings) and the values are `Artist` objects.
     * 2. It iterates over the list of artists.
     * 3. For each artist, it adds an entry to the map where the key is the artist's ID (converted to a string) and the value is the artist object.
     *
     * @return a map where the key is the artist's ID and the value is the Artist object.
     */
    public Map<String, Artist> getMapOfArtistsById() {
        Map <String, Artist> artistsById = new HashMap<>();

        for (Artist artist : artists) {
            artistsById.put(artist.getId().toString(), artist);
        }
        return artistsById;
    }

    /**
     * This method is responsible for getting a list of artist names. It creates a new `ArrayList` of strings to store the artist names,
     * iterates over the list of artists, and for each artist, it adds the artist's name to the list. The `toString` method of the `Artist` class
     * is used to get the artist's name. Finally, it returns the created list of artist names.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It creates a new `ArrayList` of strings to store the artist names.
     * 2. It iterates over the list of artists.
     * 3. For each artist, it adds the artist's name to the list.
     *
     * @return a list of artist names.
     */
    public List<String> getArtistsToString() {
        List<String> artistsNames = new ArrayList<>();

        for (Artist artist : artists) {
            artistsNames.add(artist.toString());
        }
        return artistsNames;
    }

    /**
     * This method is responsible for searching an artist by their ID. It takes in a parameter: the ID of the artist, which it converts from a string to a `UUID`.
     * It then iterates over the list of artists, and for each artist, it checks if the artist's ID matches the provided ID. If it does, it returns the artist.
     * If no artist is found with the provided ID, it returns null.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It takes in a parameter: the ID of the artist.
     * 2. It converts the artist ID from a string to a `UUID`.
     * 3. It iterates over the list of artists.
     * 4. For each artist, it checks if the artist's ID matches the provided ID. If it does, it returns the artist.
     * 5. If no artist is found with the provided ID, it returns null.
     *
     * @param artistId The ID of the artist to search for.
     * @return The Artist object if found, null otherwise.
     */
    public Artist searchArtistById(String artistId) {
        UUID uuid = UUID.fromString(artistId);
        for (Artist artist : artists){
            if (artist.getId().equals(uuid)){
                return artist;
            }
        }

        return Artist.GetUnknownArtist(UUID.randomUUID().toString());
    }

    /**
     * This method is responsible for adding a new artist to the list of artists. It takes in a parameter: the name of the artist,
     * checks if the provided name is null or empty, and if it is, it throws an `IllegalArgumentException`. Then, it creates a new `Artist` object
     * with the provided name and adds the new artist to the list of artists.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It takes in a parameter: the name of the artist.
     * 2. It checks if the provided name is null or empty. If it is, it throws an `IllegalArgumentException`.
     * 3. It creates a new `Artist` object with the provided name.
     * 4. It adds the new artist to the list of artists.
     *
     * @param name The name of the artist to add.
     * @throws IllegalArgumentException If the name is null or empty.
     */
    public void addArtist(String name) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("El nombre del artista no puede ser nulo o vacío");
        }

        Artist artist = new Artist(name);
        artists.add(artist);
    }

    /**
     * This method is responsible for searching an artist by their name. It takes in a parameter: the name of the artist,
     * then iterates over the list of artists, and for each artist, it checks if the artist's name matches the provided name.
     * If it does, it returns the artist. If no artist is found with the provided name, it returns null.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It takes in a parameter: the name of the artist.
     * 2. It iterates over the list of artists.
     * 3. For each artist, it checks if the artist's name matches the provided name. If it does, it returns the artist.
     * 4. If no artist is found with the provided name, it returns null.
     *
     * @param name The name of the artist to search for.
     * @return The Artist object if found, null otherwise.
     */
    public Artist searchArtistByName(String name) {
        for (Artist artist : artists) {
            if (artist.getName().equals(name)) {
                return artist;
            }
        }

        return null;
    }

    /**
     * This method is responsible for retrieving a list of artists by their IDs. It takes in a parameter: a set of artist IDs,
     * creates a new `ArrayList` of `Artist` objects to store the found artists, and then iterates over the set of artist IDs.
     * For each ID, it calls the `searchArtistById` method to find the artist with that ID. If the artist is not found, it throws a `NotFoundException`.
     * If the artist is found, it adds the artist to the list. Finally, it returns the created list of artists.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It takes in a parameter: a set of artist IDs.
     * 2. It creates a new `ArrayList` of `Artist` objects to store the found artists.
     * 3. It iterates over the set of artist IDs.
     * 4. For each ID, it calls the `searchArtistById` method to find the artist with that ID. If the artist is not found, it throws a `NotFoundException`.
     * 5. If the artist is found, it adds the artist to the list.
     *
     * @param artists A set of artist IDs to search for.
     * @return A list of Artist objects.
     * @throws NotFoundException If an artist with the given ID does not exist.
     */
    public List<Artist> getArtistsByIds(Set<String> artists) throws NotFoundException {
        List<Artist> foundArtists = new ArrayList<>();
        for (String artistId : artists){
            Artist artist = searchArtistById(artistId);
            if (artist == null){
                throw new NotFoundException("El artista con el id " + artistId + " no existe");
            }
            foundArtists.add(artist);
        }
        return new ArrayList<>();
    }

    /**
     * This method is responsible for deleting an artist by their ID. It takes in a parameter: the ID of the artist,
     * then calls the `searchArtistById` method to find the artist with the provided ID. If the artist is not found, it throws a `NotFoundException`.
     * If the artist is found, it removes the artist from the list of artists.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It takes in a parameter: the ID of the artist.
     * 2. It calls the `searchArtistById` method to find the artist with the provided ID.
     * 3. If the artist is not found, it throws a `NotFoundException`.
     * 4. If the artist is found, it removes the artist from the list of artists.
     *
     * @param artistId The ID of the artist to delete.
     * @throws NotFoundException If an artist with the given ID does not exist.
     */
    public void deleteArtist(String artistId) throws NotFoundException {
        Artist artist = searchArtistById(artistId);
        if (artist == null){
            throw new NotFoundException("El artista con el id " + artistId + " no existe");
        }
        artists.remove(artist);

    }

    // endregion
}
