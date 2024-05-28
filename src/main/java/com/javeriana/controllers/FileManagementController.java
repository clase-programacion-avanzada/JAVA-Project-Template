package com.javeriana.controllers;

import com.javeriana.exceptions.UnsupportedTypeException;
import com.javeriana.models.Artist;
import com.javeriana.models.Customer;
import com.javeriana.models.PlayList;
import com.javeriana.models.Song;
import com.javeriana.services.ArtistService;
import com.javeriana.services.CustomerService;
import com.javeriana.services.FileManagementService;
import com.javeriana.services.PlayListService;
import com.javeriana.services.SongService;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The FileManagementController class is responsible for handling file-related tasks in the application.
 * It uses various services to perform operations related to artists, songs, playlists, and customers.
 *
 * This class provides methods for:
 * - Importing data from CSV files
 * - Exporting data to CSV files
 * - Saving data to binary files
 * - Loading data from binary files
 *
 * The class has five attributes: fileManagementService, artistService, songService, customerService, and playListService.
 * These are instances of the respective services used by this controller.
 *
 * The class provides a constructor that takes instances of FileManagementService, ArtistService, SongService, CustomerService,
 * and PlayListService as parameters.
 */
public class FileManagementController {

    // The FileManagementService instance used by this controller.
    private final FileManagementService fileManagementService;

    // The ArtistService instance used by this controller.
    private final ArtistService artistService;

    // The SongService instance used by this controller.
    private final SongService songService;

    // The CustomerService instance used by this controller.
    private final CustomerService customerService;

    // The PlayListService instance used by this controller.
    private final PlayListService playListService;

    /**
     * Constructs a new FileManagementController with the specified services.
     *
     * @param fileManagementService the FileManagementService instance to be used by this controller.
     * @param artistService the ArtistService instance to be used by this controller.
     * @param songService the SongService instance to be used by this controller.
     * @param customerService the CustomerService instance to be used by this controller.
     * @param playListService the PlayListService instance to be used by this controller.
     */
    public FileManagementController(FileManagementService fileManagementService,
                                    ArtistService artistService,
                                    SongService songService,
                                    CustomerService customerService,
                                    PlayListService playListService) {
        this.fileManagementService = fileManagementService;
        this.artistService = artistService;
        this.songService = songService;
        this.customerService = customerService;
        this.playListService = playListService;
    }

    /**
     * This method is responsible for importing data from CSV files. It uses the FileManagementService
     * to read artists and songs from CSV files, and then loads this data into the ArtistService, SongService,
     * playListService, and CustomerService.
     *
     *
     * @param path the path where the CSV files are located.
     * @param separator the separator used in the CSV files.
     * @param extension the extension of the CSV files.
     * @param artistsFileName the name of the artists CSV file.
     * @param songsFileName the name of the songs CSV file.
     * @param playListsFileName the name of the playlists CSV file.
     * @param customersFileName the name of the customers CSV file.
     * @throws IOException if an I/O error occurs.
     */
    public void importCSVFiles(String path,
                               String separator,
                               String extension,
                               String artistsFileName,
                               String songsFileName,
                               String playListsFileName,
                               String customersFileName)
            throws IOException, UnsupportedTypeException {


            String artistsCSVFileName = artistsFileName + extension;
            List<Artist> artists = fileManagementService.importArtistsFromCSV(path, separator, artistsCSVFileName);
            artistService.loadArtists(artists);
            Map<String, Artist> artistsById = artistService.getMapOfArtistsById();

            String songsCSVFileName = songsFileName + extension;
            List<Song> songs = fileManagementService.importSongsFromCSV(path, separator, songsCSVFileName, artistsById);
            songService.loadSongs(songs);
            Map<String, Song> songsById = songService.getSongsById();

            String playListsCSVFileName = playListsFileName + extension;
            List<PlayList> playLists = fileManagementService.importPlayListsFromCSV(path, separator, playListsCSVFileName, songsById);
            playListService.loadPlayLists(playLists);
            Map<String, PlayList> playListById = playListService.getPlayListsById();

            String customersCSVFileName = customersFileName + extension;
            List<Customer> customers = fileManagementService.importCustomersFromCSV(path, separator, customersCSVFileName, artistsById, playListById);
            customerService.loadCustomers(customers);

    }

    /**
     * This method is responsible for exporting data to CSV files. It retrieves artists, songs, playlists, and customers
     * from their respective services, and then uses the `FileManagementService` to write this data to CSV files.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It first retrieves a list of `Artist` objects from the `ArtistService`.
     * 2. It then constructs the filename for the artists CSV file by appending the provided extension to the provided filename.
     * 3. It uses the `FileManagementService` to export the list of artists to a CSV file at the specified path, using the specified separator.
     * This process is then repeated for songs, playlists, and customers, each time retrieving the relevant data from the appropriate service,
     * constructing the filename, and using the `FileManagementService` to export the data to a CSV file.
     *
     * The method throws an `IOException` if an I/O error occurs during the process.
     *
     * @param path the path where the CSV files will be saved.
     * @param separator the separator used in the CSV files.
     * @param extension the extension of the CSV files.
     * @param artistsFileName the filename for the artists CSV file.
     * @param customersFileName the filename for the customers CSV file.
     * @param playListsFileName the filename for the playlists CSV file.
     * @param songsFileName the filename for the songs CSV file.
     * @throws IOException if an I/O error occurs.
     */
    public void exportCSVFiles(String path,
                               String separator,
                               String extension,
                               String artistsFileName,
                               String customersFileName,
                               String playListsFileName,
                               String songsFileName)
            throws IOException {
        List<Artist> artists = artistService.getArtists();
        String artistsCSVFileName = artistsFileName + extension;
        fileManagementService.exportArtistsToCSV(path, separator, artistsCSVFileName, artists);

        List<Song> songs = songService.getSongs();
        String songsCSVFileName = songsFileName + extension;
        fileManagementService.exportSongsToCSV(path, separator, songsCSVFileName, songs);

        List<PlayList> playLists = playListService.getPlayLists();
        String playListsCSVFileName = playListsFileName + extension;
        fileManagementService.exportPlayListsToCSV(path, separator, playListsCSVFileName, playLists);

        List<Customer> customers = customerService.getCustomers();
        String customersCSVFileName = customersFileName + extension;
        fileManagementService.exportCustomersToCSV(path, separator, customersCSVFileName, customers);
    }

    /**
     * This method is responsible for saving data to binary files. It retrieves artists, songs, playlists, and customers
     * from their respective services, and then uses the `FileManagementService` to write this data to binary files.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It first retrieves a list of `Artist` objects from the `ArtistService`.
     * 2. It then constructs the filename for the artists binary file by appending the provided extension to the provided filename.
     * 3. It uses the `FileManagementService` to export the list of artists to a binary file at the specified path.
     * This process is then repeated for songs, playlists, and customers, each time retrieving the relevant data from the appropriate service,
     * constructing the filename, and using the `FileManagementService` to export the data to a binary file.
     *
     * The method throws an `IOException` if an I/O error occurs during the process.
     *
     * @param path the path where the binary files will be saved.
     * @param extension the extension of the binary files.
     * @param artistsFileName the filename for the artists binary file.
     * @param customersFileName the filename for the customers binary file.
     * @param playListsFileName the filename for the playlists binary file.
     * @param songsFileName the filename for the songs binary file.
     * @throws IOException if an I/O error occurs.
     */
    public void saveSpotifyFiles(String path,
                                 String extension,
                                 String artistsFileName,
                                 String songsFileName,
                                 String playListsFileName,
                                 String customersFileName  ) throws IOException {

        List<Artist> artists = artistService.getArtists();
        String artistsSpotifyFileName = artistsFileName + extension;
        fileManagementService.exportArtistsToBinary(path, artistsSpotifyFileName, artists);

        List<Song> songs = songService.getSongs();
        String songsSpotifyFileName = songsFileName + extension;
        fileManagementService.exportSongsToBinary(path, songsSpotifyFileName, songs);

        List<PlayList> playLists = playListService.getPlayLists();
        String playListsSpotifyFileName = playListsFileName + extension;
        fileManagementService.exportPlayListsToBinary(path, playListsSpotifyFileName, playLists);

        List<Customer> customers = customerService.getCustomers();
        String customersSpotifyFileName = customersFileName + extension;
        fileManagementService.exportCustomersToBinary(path, customersSpotifyFileName, customers);
    }

    /**
     * This method is responsible for loading data from binary files. It retrieves artists, songs, playlists, and customers
     * from their respective binary files, and then loads this data into the respective services.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It uses the `FileManagementService` to import a list of `Artist` objects from a binary file at the specified path.
     * 2. It then loads this list of artists into the `ArtistService`.
     * This process is then repeated for songs, playlists, and customers, each time importing the relevant data from a binary file
     * and loading it into the appropriate service.
     *
     * The method throws an `IOException` and `ClassNotFoundException` if an I/O error occurs during the process or the class of a serialized object cannot be found.
     *
     * @param path the path where the binary files are located.
     * @param extension the extension of the binary files.
     * @param artistsFileName the filename for the artists binary file.
     * @param songsFileName the filename for the songs binary file.
     * @param playListsFileName the filename for the playlists binary file.
     * @param customersFileName the filename for the customers binary file.
     * @throws IOException if an I/O error occurs.
     * @throws ClassNotFoundException if the class of a serialized object cannot be found.
     */
    public void loadSpotifyFiles(String path,
                                 String extension,
                                 String artistsFileName,
                                 String songsFileName,
                                 String playListsFileName,
                                 String customersFileName

    ) throws IOException, ClassNotFoundException {

        List<Artist> artists = fileManagementService.importArtistsFromBinary(path, artistsFileName + extension);
        artistService.loadArtists(artists);

        List<Song> songs = fileManagementService.importSongsFromBinary(path, songsFileName + extension);
        songService.loadSongs(songs);

        List<PlayList> playLists = fileManagementService.importPlayListsFromBinary(path, playListsFileName + extension);
        playListService.loadPlayLists(playLists);

        List<Customer> customers = fileManagementService.importCustomersFromBinary(path, customersFileName + extension);
        customerService.loadCustomers(customers);

    }
}