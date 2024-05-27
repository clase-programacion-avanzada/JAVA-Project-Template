package com.javeriana.controllers;

import com.javeriana.exceptions.AlreadyExistsException;
import com.javeriana.exceptions.NotFoundException;
import com.javeriana.exceptions.WrongLogInException;
import com.javeriana.models.Artist;
import com.javeriana.models.Customer;
import com.javeriana.models.PlayList;
import com.javeriana.models.Song;
import com.javeriana.services.ArtistService;
import com.javeriana.services.CustomerService;
import com.javeriana.services.PlayListService;
import com.javeriana.services.SongService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The CustomerController class is responsible for handling customer-related tasks in the application.
 * It uses various services to perform operations related to artists, playlists, and songs.
 *
 * This class provides methods for:
 * - Logging in a customer
 * - Adding a new playlist for the logged-in customer
 * - Retrieving the playlists of the logged-in customer
 * - Retrieving all songs
 * - Adding a song to a playlist
 * - Retrieving all songs from a playlist
 * - Deleting a song from a playlist
 * - Retrieving all artists
 * - Following an artist
 * - Retrieving the artists followed by the currently logged in customer
 * - Logging out the currently logged in customer
 *
 * The class has four attributes: customerService, artistService, playListService, and songService. These are instances of the respective services used by this controller.
 *
 * The class provides a constructor that takes instances of CustomerService, ArtistService, PlayListService, and SongService as parameters.
 */
public class CustomerController {

    // The CustomerService instance used by this controller.
    private final CustomerService customerService;

    // The ArtistService instance used by this controller.
    private final ArtistService artistService;

    // The PlayListService instance used by this controller.
    private final PlayListService playListService;

    // The SongService instance used by this controller.
    private final SongService songService;

    /**
     * Constructs a new CustomerController with the specified services.
     *
     * @param customerService the CustomerService instance to be used by this controller.
     * @param artistService the ArtistService instance to be used by this controller.
     * @param playListService the PlayListService instance to be used by this controller.
     * @param songService the SongService instance to be used by this controller.
     */
    public CustomerController(CustomerService customerService, ArtistService artistService, PlayListService playListService, SongService songService) {
        this.customerService = customerService;
        this.artistService = artistService;
        this.playListService = playListService;
        this.songService = songService;
    }

    /**
     * Logs in a customer using their username and password.
     *
     * @param username the username of the customer.
     * @param password the password of the customer.
     * @return true if the login was successful, false otherwise.
     */
    public boolean logIn(String username, String password) {
        return customerService.logIn(username, password);
    }

    /**
     * The addNewPlayList method is responsible for adding a new playlist for the currently logged in customer.
     * It performs this operation in three main steps:
     *
     * 1. Check if Customer is Logged In: The method starts by calling the isCustomerLogged method of the CustomerService instance.
     *    If no customer is currently logged in, it throws a WrongLogInException.
     *
     * 2. Create New Playlist: The method then calls the addPlayList method of the PlayListService instance, passing the playListName as a parameter.
     *    This operation creates a new playlist and returns it. The new playlist is stored in the newPlayList variable.
     *
     * 3. Add Playlist to Logged Customer: Finally, the method calls the addPlayListToLoggedCustomer method of the CustomerService instance,
     *    passing the newPlayList as a parameter. This operation adds the new playlist to the currently logged in customer.
     *
     * @param playListName the name of the new playlist.
     * @throws WrongLogInException if no customer is currently logged in.
     */
    public void addNewPlayList(String playListName) throws WrongLogInException {
        if(!customerService.isCustomerLogged()){
            throw new WrongLogInException("El inicio de sesión ha fallado.");
        }
        PlayList newPlayList = playListService.addPlayList(playListName);

        customerService.addPlayListToLoggedCustomer(newPlayList);

    }

    /**
     * Retrieves the playlists of the currently logged in customer.
     *
     * @return a list of the logged in customer's playlists.
     * @throws WrongLogInException if no customer is currently logged in.
     */
    public List<String> getLoggedCustomerPlaylists() throws WrongLogInException {
        return customerService.getLoggedCustomerPlayLists();
    }

    /**
     * Retrieves all songs.
     *
     * @return a list of all songs.
     */
    public List<String> getAllSongs() {

        return songService.getSongsToString();

    }

    /**
     * This method is responsible for adding a song to a playlist. It retrieves the `PlayList` and `Song` objects with the provided IDs
     * and then adds the song to the playlist.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It retrieves the `PlayList` object with the provided ID from the `PlayListService`.
     * 2. If the playlist is not found, it throws a `NotFoundException`.
     * 3. It retrieves the `Song` object with the provided ID from the `SongService`.
     * 4. If the song is not found, it throws a `NotFoundException`.
     * 5. It adds the song to the playlist.
     *
     * @param playListId the ID of the playlist.
     * @param songId the ID of the song.
     * @throws NotFoundException if the playlist or the song is not found.
     */
    public void addSongToPlayList(String playListId, String songId) throws NotFoundException {
    PlayList playList = playListService.getPlayListById(playListId);
    if(playList == null){
        throw new NotFoundException("PlayList no encontrada.");
    }
    Song song = songService.getSongsById().get(songId);
        if(song == null){
            throw new NotFoundException("Canción no encontrada.");
        }
        playList.addSong(songService.getSongsById().get(songId));
    }

    /**
     * This method is responsible for retrieving all songs from a specific playlist. It retrieves the `PlayList` object with the provided ID
     * and then retrieves all songs from the playlist.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It retrieves the `PlayList` object with the provided ID from the `PlayListService`.
     * 2. If the playlist is not found, it throws a `NotFoundException`.
     * 3. It retrieves all songs from the playlist and returns them as a list of strings.
     *
     * The method returns a list of all songs in the playlist.
     *
     * @param playListId the ID of the playlist.
     * @return a list of all songs in the playlist.
     * @throws NotFoundException if the playlist is not found.
     */
    public List<String> getAllSongsFromPlayList(String playListId) throws NotFoundException {
        PlayList playList = playListService.getPlayListById(playListId);
        if(playList == null){
            throw new NotFoundException("PlayList no encontrada.");
        }
        List<Song> allSongs = playListService.getAllSongsInPlayLists();

       return Collections.singletonList(String.valueOf(allSongs));
    }

    /**
     * This method is responsible for deleting a song from a playlist. It uses the `PlayListService` to delete the song with the provided ID
     * from the playlist with the provided ID.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It takes in two parameters: the ID of the playlist and the ID of the song.
     * 2. It uses the `PlayListService` to delete the song with the provided ID from the playlist with the provided ID.
     *
     * The method returns a boolean indicating whether the song was successfully deleted from the playlist.
     *
     * @param playListId the ID of the playlist.
     * @param songId the ID of the song.
     * @return a boolean indicating whether the song was successfully deleted from the playlist.
     */
    public boolean deleteSongFromPlayList(String playListId, String songId) {
        playListService.deleteSongFromPlayList(playListId, songId);

        if(!Objects.equals(songId, playListId)){
            return true;
        }else{
        return false;
}
    }

    /**
     * Retrieves all artists.
     *
     * @return a list of all artists.
     */
    public List<String> getAllArtists() {

        return artistService.getArtistsToString();
    }

    /**
     * This method is responsible for allowing a customer to follow an artist. It retrieves the `Artist` object with the provided ID
     * and then uses the `CustomerService` to allow the logged-in customer to follow the artist.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It retrieves the `Artist` object with the provided ID from the `ArtistService`.
     * 2. If the artist is not found, it throws a `NotFoundException`.
     * 3. It uses the `CustomerService` to allow the logged-in customer to follow the artist.
     *
     * The method throws a `NotFoundException` if the artist is not found and an `AlreadyExistsException` if the artist is already followed.
     *
     * @param artistId the ID of the artist.
     * @throws NotFoundException if the artist is not found.
     * @throws AlreadyExistsException if the artist is already followed.
     */
    public void followArtist(String artistId) throws NotFoundException, WrongLogInException, AlreadyExistsException {
    Artist artist = (Artist) artistService.getArtistsByIds(Collections.singleton(artistId));
    if(artist == null){
        throw new NotFoundException("Artista no encontrado.");
    }
    List<String> followed = getFollowedArtists();
    if(Objects.equals(followed, Collections.singletonList(artistId)))
        throw new AlreadyExistsException("El artista ya esta en seguidos.");
    else{
        followArtist(artistId);
    }
    }

    /**
     * The getFollowedArtists method is responsible for retrieving the artists followed by the currently logged in customer.
     * It performs this operation in two main steps:
     *
     * 1. Check if Customer is Logged In: The method starts by calling the getFollowedArtistsByLoggedUser method of the CustomerService instance.
     *    If no customer is currently logged in, it throws a WrongLogInException.
     *
     * 2. Return Followed Artists: The method then returns the list of artists followed by the currently logged in customer.
     *
     * @return a list of the logged in customer's followed artists.
     * @throws WrongLogInException if no customer is currently logged in.
     */
    public List<String> getFollowedArtists() throws WrongLogInException {
        boolean customerLogged = customerService.isCustomerLogged();
        if(!customerLogged){
            throw new WrongLogInException("El usuario no ha iniciado sesión.");
        }
        return customerService.getFollowedArtistsByLoggedUser();
    }

    /**
     * Logs out the currently logged in customer.
     */
    public void logOut() {
        customerService.logOut();
    }

    public List<String> playPlayList(String playListId) throws WrongLogInException, NotFoundException {
        if (!customerService.isCustomerLogged()) {
            throw new WrongLogInException("No hay un cliente logueado.");
        }

        PlayList playList = playListService.getPlayListById(playListId);
        if (playList == null) {
            throw new NotFoundException("La lista de reproducción no existe.");
        }

        if (!customerService.isPlayListOwnedByLoggedCustomer(playListId)) {
            throw new NotFoundException("La lista de reproducción no pertenece al cliente logueado.");
        }

        List<String> result = new ArrayList<>();
        for (Song song : playList.getSongs()) {
            result.add(song.play());
        }
        return result;
}

}
