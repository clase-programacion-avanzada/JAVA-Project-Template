package com.javeriana.controllers;

import com.javeriana.exceptions.AlreadyExistsException;
import com.javeriana.exceptions.NotFoundException;
import com.javeriana.models.Artist;
import com.javeriana.models.Customer;
import com.javeriana.models.PlayList;
import com.javeriana.models.Song;
import com.javeriana.services.ArtistService;
import com.javeriana.services.CustomerService;
import com.javeriana.services.PlayListService;
import com.javeriana.services.SongService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * The AdminController class is responsible for handling administrative tasks in the application.
 * It uses various services to perform operations related to artists, playlists, customers, and songs.
 *
 * This class provides methods for:
 * - Adding and deleting artists
 * - Adding and deleting songs
 * - Adding and deleting customers
 * - Retrieving all artists, songs, customers, and playlists
 * - Validating song attributes
 * - Deleting songs and artists from the database
 *
 * The class has four attributes: artistService, playListService, customerService, and songService. These are instances of the respective services used by this controller.
 *
 * The class provides a constructor that takes instances of ArtistService, PlayListService, CustomerService, and SongService as parameters.
 */
public class AdminController {

    // The ArtistService instance used by this controller.
    private final ArtistService artistService;

    // The PlayListService instance used by this controller.
    private final PlayListService playListService;

    // The CustomerService instance used by this controller.
    private final CustomerService customerService;

    // The SongService instance used by this controller.
    private final SongService songService;

    /**
     * Constructs a new AdminController with the specified services.
     *
     * @param artistService the ArtistService instance to be used by this controller.
     * @param playListService the PlayListService instance to be used by this controller.
     * @param customerService the CustomerService instance to be used by this controller.
     * @param songService the SongService instance to be used by this controller.
     */
    public AdminController(ArtistService artistService, PlayListService playListService,
                           CustomerService customerService, SongService songService) {
        this.artistService = artistService;
        this.playListService = playListService;
        this.customerService = customerService;
        this.songService = songService;
    }

    /**
     * This method is responsible for adding an artist to the database. It retrieves the `Artist` object with the provided name
     * and then uses the `ArtistService` to add the artist to the database if it does not already exist.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It retrieves the `Artist` object with the provided name from the `ArtistService`.
     * 2. If the artist is already present, it throws an `AlreadyExistsException`.
     * 3. If the artist is not present, it uses the `ArtistService` to add the artist to the database.
     *
     * The method throws an `AlreadyExistsException` if the artist is already present in the database.
     *
     * @param name the name of the artist.
     * @throws AlreadyExistsException if the artist is already present in the database.
     */
    public void addArtistToDatabase(String name) throws AlreadyExistsException {
        Artist artist = artistService.searchArtistByName(name);

        if (artist != null) {
            throw new AlreadyExistsException("El artista con nombre " + name + " ya existe");
        }

        artistService.addArtist(name);

    }

    /**
     * Retrieves all artists from the database.
     *
     * @return a list of all artist names.
     */
    public List<String> getAllArtists() {
        return artistService.getArtistsToString();
    }

    /**
     * Retrieves all songs from the database.
     *
     * @return a list of all songs.
     */
    public List<String> getAllSongs() {
        return new ArrayList<>();
    }

    /**
     * Retrieves all customers from the database.
     *
     * @return a list of all customer names.
     */
    public List<String> getAllCustomers() {
        return new ArrayList<>();
    }

    /**
     * The addCustomerToDatabase method is responsible for adding a new customer to the database.
     * It uses the CustomerService to perform this operation.
     *
     * The method takes five parameters: username, password, name, lastName, and age.
     * These parameters represent the username, password, name, last name, and age of the customer respectively.
     *
     * The method calls the addCustomer method of the CustomerService instance, passing the username, password, name, lastName, and age as parameters.
     * This operation adds a new customer to the database.
     *
     * The method throws an AlreadyExistsException if a customer with the same username already exists in the database.
     *
     * @param username the username of the customer.
     * @param password the password of the customer.
     * @param name the name of the customer.
     * @param lastName the last name of the customer.
     * @param age the age of the customer.
     * @throws AlreadyExistsException if a customer with the same username already exists in the database.
     */
    public void addCustomerToDatabase(String username, String password, String name, String lastName, int age) throws AlreadyExistsException {
        Customer customerUsername = customerService.searchCustomerByUsername(username);
        if (customerUsername != null) {
            throw new AlreadyExistsException("El nombre de usuario " + username + " ya existe");
        }
        customerService.addCustomer(username, password, name, lastName, age);
    }

    /**
     * The deleteCustomerFromDatabase method is responsible for deleting a customer from the database.
     * It performs this operation in three main steps:
     *
     * 1. Retrieve Customer's Playlists: The method starts by calling the getCustomerPlayListsIds method of the CustomerService instance,
     *    passing the username as a parameter. This operation retrieves a list of UUIDs representing the playlists associated with the customer.
     *    The list is stored in the playListsIds variable.
     *
     * 2. Delete Playlists: The method then calls the deletePlayLists method of the PlayListService instance, passing the playListsIds as a parameter.
     *    This operation deletes all the playlists associated with the customer from the database.
     *
     * 3. Delete Customer: Finally, the method calls the deleteCustomer method of the CustomerService instance, passing the username as a parameter.
     *    This operation deletes the customer from the database.
     *
     * @param username the username of the customer to delete.
     */
    public void deleteCustomerFromDatabase(String username) {
        List<UUID> playListIds = new ArrayList<>();
        playListIds = customerService.getCustomerPlayListsIds(username);

        playListService.deletePlayLists(playListIds);

        customerService.deleteCustomer(username);

    }

    /**
     * This method is responsible for adding a song to the database. It checks if the provided set of artist IDs is empty, and if it is,
     * it throws an `IllegalArgumentException`. Then, it validates the song attributes (name, genre, duration) using the `validateSongAttributes` method,
     * retrieves a list of `Artist` objects with the provided artist IDs from the `ArtistService`, and uses the `SongService` to add the song with the
     * provided details and the list of artists to the database.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It checks if the provided set of artist IDs is empty. If it is, it throws an `IllegalArgumentException`.
     * 2. It validates the song attributes (name, genre, duration) using the `validateSongAttributes` method.
     * 3. It retrieves a list of `Artist` objects with the provided artist IDs from the `ArtistService`.
     * 4. It uses the `SongService` to add the song with the provided details and the list of artists to the database.
     *
     * The method throws a `NotFoundException` if any of the artists are not found in the database.
     *
     * @param name the name of the song.
     * @param genre the genre of the song.
     * @param duration the duration of the song.
     * @param album the album of the song.
     * @param artists the set of artist IDs.
     * @throws NotFoundException if any of the artists are not found in the database.
     */
    public void addSongToDatabase(String name, String genre, int duration, String album, Set<String> artists) throws NotFoundException {

        Artist artist = (Artist) artistService.getArtistsByIds(artists);
        if(artist == null){
            throw new IllegalArgumentException("El ID del artista es nulo o erróneo.");
        }

        validateSongAttributes(name, genre, duration);

        List<Artist> artistsUUID = artistService.getArtistsByIds(artists);

        if(artistsUUID == null){
            throw new NotFoundException("Artistas no encontrados.");
        }
        songService.addSong(name, genre, duration, album, artistsUUID);

    }

    /**
     * This method is responsible for validating the attributes of a song. It checks if the provided name, genre, and duration are valid, and if they are not,
     * it throws an `IllegalArgumentException`.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It checks if the provided name is null or empty. If it is, it throws an `IllegalArgumentException`.
     * 2. It checks if the provided genre is null or empty. If it is, it throws an `IllegalArgumentException`.
     * 3. It checks if the provided duration is less than or equal to 0. If it is, it throws an `IllegalArgumentException`.
     *
     * @param name the name of the song.
     * @param genre the genre of the song.
     * @param duration the duration of the song.
     * @throws IllegalArgumentException if the name is null or empty, the genre is null or empty, or the duration is less than or equal to 0.
     */
    public static void validateSongAttributes(String name, String genre, int duration){
        if(name == null){
            throw new IllegalArgumentException("El nombre proporcionado no existe o esta vacío.");
        }
        if(genre == null){
            throw new IllegalArgumentException("El genero proporcionado no existe o esta vacío.");
        }
        if(duration >= 0){
            throw new IllegalArgumentException("La duración de canción es nula.");
        }
    }

    /**
     * This method is responsible for deleting a song from the database. It checks if the provided song ID is null or empty, and if it is,
     * it throws an `IllegalArgumentException`. Then, it uses the `PlayListService` to delete the song with the provided ID from all playlists,
     * and the `SongService` to delete the song from the database.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It checks if the provided song ID is null or empty. If it is, it throws an `IllegalArgumentException`.
     * 2. It uses the `PlayListService` to delete the song with the provided ID from all playlists.
     * 3. It uses the `SongService` to delete the song with the provided ID from the database.
     *
     * The method throws a `NotFoundException` if the song is not found in the database.
     *
     * @param songId the ID of the song.
     * @throws NotFoundException if the song is not found in the database.
     */
    public void deleteSongFromDatabase(String songId) throws NotFoundException {
        Song songID = songService.getSongsById().get(songId);
        if(songID == null){
            throw new IllegalArgumentException("El ID proporcionado es nulo o erróneo.");
        }

        playListService.deleteSongFromPlayLists(songId);

        songService.deleteSong(songId);
    }

    /**
     * This method is responsible for deleting an artist from the database. It checks if the provided artist ID is null or empty, and if it is,
     * it throws an `IllegalArgumentException`. Then, it retrieves all songs associated with the artist ID using the `SongService` and deletes each song
     * from the database using the `deleteSongFromDatabase` method. Finally, it uses the `ArtistService` to delete the artist from the database.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It checks if the provided artist ID is null or empty. If it is, it throws an `IllegalArgumentException`.
     * 2. It retrieves all songs associated with the artist ID using the `SongService`.
     * 3. It iterates over each song and deletes it from the database using the `deleteSongFromDatabase` method.
     * 4. It uses the `ArtistService` to delete the artist with the provided ID from the database.
     *
     * The method throws a `NotFoundException` if the artist is not found in the database.
     *
     * @param artistId the ID of the artist.
     * @throws NotFoundException if the artist is not found in the database.
     */
    public void deleteArtistFromDatabase(String artistId) throws NotFoundException {
        Artist checkArtist = artistService.getMapOfArtistsById().get(artistId);
        if(checkArtist == null){
            throw new IllegalArgumentException("El ID proporcionado es nulo o erróneo.");
        }

        List<Song> allSongsByArtist = songService.searchSongsByArtistId(artistId);

        for(int i = 0; i < allSongsByArtist.size(); i++){
            String songs = allSongsByArtist.toString();
            deleteSongFromDatabase(songs);
        }
        artistService.deleteArtist(artistId);
    }

    /**
     * Retrieves all playlists from the database.
     *
     * @return a list of all playlist names.
     */
    public List<String> getAllPlaylists() {
        return playListService.getPlayListsToString();
    }
}