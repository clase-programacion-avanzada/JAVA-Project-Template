package com.javeriana.controllers;

import com.javeriana.models.Artist;
import com.javeriana.models.Customer;
import com.javeriana.models.Song;
import com.javeriana.services.ArtistService;
import com.javeriana.services.CustomerService;
import com.javeriana.services.PlayListService;
import com.javeriana.services.ReportService;
import com.javeriana.services.SongService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The ReportController class is responsible for handling report-related tasks in the application.
 * It uses various services to perform operations related to artists, songs, playlists, and customers.
 *
 * This class provides methods for:
 * - Showing the most followed artists
 * - Showing the most added song in playlists
 * - Getting all artists
 * - Showing the most added song of a specific artist in playlists
 *
 * The class has five attributes: reportService, artistService, songService, customerService, and playListService.
 * These are instances of the respective services used by this controller.
 * <p>
 * The class provides a constructor that takes instances of ReportService, ArtistService, SongService, CustomerService,
 * and PlayListService as parameters.
 */
public class ReportController {

    private final ReportService reportService;
    private final ArtistService artistService;
    private final SongService songService;
    private final CustomerService customerService;

    public ReportController(ReportService reportService, ArtistService artistService, SongService songService, CustomerService customerService, PlayListService playListService) {
        this.reportService = reportService;
        this.artistService = artistService;
        this.songService = songService;
        this.customerService = customerService;
    }

    /**
     * This method is responsible for showing the most followed artists. It retrieves a list of artists that are followed by customers
     * and then uses the `ReportService` to get a map of the most followed artists and their respective follower counts.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It retrieves a list of `Artist` objects that are followed by customers from the `CustomerService`.
     * 2. It uses the `ReportService` to get a map where the keys are the names of the artists and the values are the number of followers for each artist.
     *
     * The method returns this map.
     *
     * @return a map where the keys are the names of the artists and the values are the number of followers for each artist.
     */
    public Map<String, Integer> showMostFollowedArtists() {
        List<Artist> followedArtists = customerService.getAllFollowedArtists();
        return reportService.getMostFollowedArtists(followedArtists);
    }

    /**
     * This method is responsible for showing the most added song in playlists. It retrieves a list of songs that are in playlists
     * and then uses the `ReportService` to get a map of the songs and their respective counts of being added to playlists.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It retrieves a list of `Song` objects that are in playlists from the `PlayListService`.
     * 2. It uses the `ReportService` to get a map where the keys are the IDs of the songs and the values are the number of times each song has been added to playlists.
     * 3. It identifies the song that has been added the most to playlists.
     * 4. It retrieves the details of this song from the `SongService` and returns these details as a string.
     *
     * The method returns the details of the most added song in playlists.
     *
     * @return a string representing the details of the most added song in playlists.
     */
    public String showMostAddedSongInPlayList() {
        List<Song> songs = PlayListService.getAllSongsInPlayLists();
        Map<UUID, Integer> maps = ReportService.getCountOfSongsByArtist(songs);

        UUID mostAddedSongId = null;
        int maxCount = 0;
        for (Map.Entry<UUID, Integer> entry : maps.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostAddedSongId = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        if (mostAddedSongId == null) {
            return "No songs found in playlists.";
        }
        String mostAddedSong = String.valueOf(songService.searchSongById(mostAddedSongId.toString()));

        return mostAddedSong;
    }


    public List<String> getAllArtists() {
        return artistService.getArtistsToString();
    }

    /**
     * This method is responsible for showing the most added song of a specific artist in playlists. It retrieves a list of songs by the artist
     * and then uses the `ReportService` to get a map of the songs and their respective counts of being added to playlists.
     *
     * Here's a breakdown of what each part of the method does:
     * 1. It retrieves a list of `Song` objects by the artist from the `SongService`.
     * 2. It uses the `ReportService` to get a map where the keys are the IDs of the songs and the values are the number of times each song has been added to playlists.
     * 3. It identifies the song that has been added the most to playlists.
     * 4. It retrieves the details of this song from the `SongService` and returns these details as a string.
     *
     * The method returns the details of the most added song of a specific artist in playlists.
     *
     * @param artistId the ID of the artist.
     * @return a string representing the details of the most added song of a specific artist in playlists.
     */
    public String showMostAddedSongOfArtist(String artistId) {
        List<Song> songbyArtist = songService.searchSongsByArtistId(artistId);
        Map<UUID, Integer> songCounts = reportService.getCountOfSongsByArtist(songbyArtist);

        Map.Entry<UUID, Integer> mostAddedSongEntry = null;
        for (Map.Entry<UUID, Integer> entry : songCounts.entrySet()) {
            if (mostAddedSongEntry == null || entry.getValue().compareTo(mostAddedSongEntry.getValue()) > 0) {
                mostAddedSongEntry = entry;
            }
        }

        if (mostAddedSongEntry != null) {
            UUID mostAddedSongId = mostAddedSongEntry.getKey();
            return songService.searchSongById(mostAddedSongId.toString()).toString();
        } else {
            return "No se encontro el artista con el ID: " + artistId;
        }
    }
}