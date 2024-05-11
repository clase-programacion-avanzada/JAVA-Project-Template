package com.javeriana.services;

import com.javeriana.models.Artist;
import com.javeriana.models.Customer;
import com.javeriana.models.PlayList;
import com.javeriana.models.Song;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * The CustomerService class is part of a music application and is responsible for managing customers.
 * It provides a variety of methods for handling customer-related operations.
 *
 * This class provides methods for:
 * - Adding new customers
 * - Deleting customers
 * - Finding a customer by their username
 * - Managing customer login and logout
 * - Managing the playlists of the currently logged in customer
 * - Managing the artists followed by the currently logged in customer
 * - Retrieving a list of all customers, a list of the names of all customers, a list of playlist IDs of a customer with a given username, and a list of all artists followed by all customers
 * - Replacing the current list of customers with a given list
 * - Checking if a customer is currently logged in
 *
 * This class uses a list to store customers and a Customer object to keep track of the currently logged in customer.
 * It also uses several constants for validating usernames, passwords, and the minimum age for registration.
 */
public class FileManagementService {

    //region import from csv

    /**
     * Imports a list of artists from a CSV file.
     *
     * The method does the following:
     * 1. Creates a File object using the provided path and filename.
     * 2. Reads all lines from the file into a list of strings.
     * 3. Initializes an empty list to store the Artist objects.
     * 4. Loops through each line in the file. For each line, it:
     *    - Creates an Artist object from the line using the Artist.fromCSV method, which takes the line and the provided separator as parameters.
     *    - Adds the created Artist object to the list of artists.
     * 5. Returns the list of Artist objects.
     *
     * @param path The path to the CSV file.
     * @param separator The separator used in the CSV file.
     * @param artistsFileName The name of the CSV file.
     * @return A list of Artist objects.
     * @throws IOException If an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read.
     */
    public List<Artist> importArtistsFromCSV(String path, String separator,String artistsFileName) throws IOException {

        // Create a File object with the given path and filename
        File file = new File(path + artistsFileName);

        // Read all lines from the file
        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        // Create a list to store the artists
        List<Artist> artists = new ArrayList<>();

        // Loop through each line in the file
        for (String line : lines) {
            // Split the line into an array using the separator
            Artist artist = Artist.fromCSV(line, separator);

            // Add the artist to the list
            artists.add(artist);
        }

        // Return the list of artists
        return artists;
    }

    /**
     * Imports a list of songs from a CSV file.
     *
     * The method does the following:
     * 1. Creates a File object using the provided path and filename.
     * 2. Reads all lines from the file into a list of strings.
     * 3. Initializes an empty list to store the Song objects.
     * 4. Loops through each line in the file. For each line, it:
     *    - Splits the line into an array using the provided separator. Each element of the array corresponds to a piece of song data (ID, name, artist IDs, genre, duration in seconds, album).
     *    - Extracts the artist IDs from the data, loops through each ID, and adds the corresponding Artist object to a list. If an artist ID does not exist in the provided map of artists, it adds an unknown artist with the ID.
     *    - Creates a Song object using the extracted data and the list of artists.
     *    - Adds the created Song object to the list of songs.
     * 5. Returns the list of Song objects.
     *
     * @param path The path to the CSV file.
     * @param separator The separator used in the CSV file.
     * @param songsFileName The name of the CSV file.
     * @param artistsById A map of artists by their IDs.
     * @return A list of Song objects.
     * @throws IOException If an I/O error occurs reading from the file.
     */
    public List<Song> importSongsFromCSV(String path,
                                         String separator,
                                         String songsFileName,
                                         Map<String,Artist> artistsById) throws IOException {

        // Create a File object with the given path and filename
        File file = new File(path + songsFileName);

        // Read all lines from the file
        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        // Create a list to store the songs
        List<Song> songs = new ArrayList<>();

        // Loop through each line in the file
        for (String line : lines) {
            // Split the line into an array using the separator
            String[] data = line.split(separator);

            // Create a Song object with the data from the line
            UUID id = UUID.fromString(data[0]);
            String name = data[1];
            List<String> artistIds = extractIds(data[2]);
            String genre = data[3];
            int durationInSeconds = Integer.parseInt(data[4]);
            String album = data[5];
            List<Artist> artists = new ArrayList<>();

            // Loop through each artist ID
            for (String artistId : artistIds) {
                // Add the artist to the list of artists for the song
                Artist artist = artistsById.getOrDefault(
                    artistId,
                    Artist.GetUnknownArtist(artistId)
                );

                artists.add(artist);
            }

            // Create a Song object with the data from the line
            Song song = new Song(id, name, artists, genre, durationInSeconds, album);
            // Add the song to the list
            songs.add(song);
        }

        // Return the list of songs
        return songs;
    }



    /**
     * Imports a list of playlists from a CSV file.
     *
     * The method does the following:
     * 1. Creates a File object using the provided path and filename.
     * 2. Reads all lines from the file into a list of strings.
     * 3. Initializes an empty list to store the PlayList objects.
     * 4. Loops through each line in the file. For each line, it:
     *    - Splits the line into an array using the provided separator. Each element of the array corresponds to a piece of playlist data (ID, name, song IDs).
     *    - Extracts the song IDs from the data, loops through each ID, and adds the corresponding Song object to a list. If a song ID does not exist in the provided map of songs, it adds an unknown song with the ID.
     *    - Creates a PlayList object using the extracted data and the list of songs.
     *    - Adds the created PlayList object to the list of playlists.
     * 5. Returns the list of PlayList objects.
     *
     * @param path The path to the CSV file.
     * @param separator The separator used in the CSV file.
     * @param playListsFileName The name of the CSV file.
     * @param songsById A map of songs by their IDs.
     * @return A list of PlayList objects.
     * @throws IOException If an I/O error occurs reading from the file.
     */
    public List<PlayList> importPlayListsFromCSV(String path,
                                                 String separator,
                                                 String playListsFileName,
                                                 Map<String, Song> songsById)
        throws IOException {

        //PlayList File has the following format:
        //PlayListId;PlayListName;{SongId1,SongId2,SongId3,...}

        // Create a File object with the given path and filename
        File file = new File(path + playListsFileName);

        // Create a list to store the playLists
        List<PlayList> playLists = new ArrayList<>();

        // Read all lines from the file
        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);


        // Loop through each line in the file
        for(String line : lines) {
            String[] tokens = line.split(separator);

            // Data format: [PlayListId,
            // PlayListName,
            // "{SongId1,SongId2,SongId3,...}"]
            UUID id = UUID.fromString(tokens[0]);
            String name = tokens[1];
            // Extract the song IDs from the data
            //This is a list of song ids
            //["SongId1",SongId2,SongId3,...]
            List<String> songIds = extractIds(tokens[2]);
            List<Song> songs = new ArrayList<>();
            for (String songId : songIds) {
                // Add the song to the list of songs for the playlist
                Song song = songsById.getOrDefault(
                    songId,
                    Song.getUnknownSong(songId)
                );
                songs.add(song);
            }

            PlayList playList = new PlayList(id, name, songs);
            playLists.add(playList);
        }

        // Return the list of playLists
        return playLists;
    }

    /**
     * Imports a list of customers from a CSV file.
     *
     * The method does the following:
     * 1. Creates a File object using the provided path and filename.
     * 2. Reads all lines from the file into a list of strings.
     * 3. Initializes an empty list to store the Customer objects.
     * 4. Loops through each line in the file. For each line, it:
     *    - Splits the line into an array using the provided separator. Each element of the array corresponds to a piece of customer data (ID, username, password, name, last name, age, artist IDs, playlist IDs).
     *    - Extracts the artist IDs from the data, loops through each ID, and adds the corresponding Artist object to a set. If an artist ID does not exist in the provided map of artists, it adds an unknown artist with the ID.
     *    - Extracts the playlist IDs from the data, loops through each ID, and adds the corresponding PlayList object to a list. If a playlist ID does not exist in the provided map of playlists, it adds an unknown playlist with the ID.
     *    - Creates a Customer object using the extracted data, the set of artists, and the list of playlists.
     *    - Adds the created Customer object to the list of customers.
     * 5. Returns the list of Customer objects.
     *
     * @param path The path to the CSV file.
     * @param separator The separator used in the CSV file.
     * @param customersCSVFileName The name of the CSV file.
     * @param artistsById A map of artists by their IDs.
     * @param playListById A map of playlists by their IDs.
     * @return A list of Customer objects.
     * @throws IOException If an I/O error occurs reading from the file.
     */
    public List<Customer> importCustomersFromCSV(String path,
                                                 String separator,
                                                 String customersCSVFileName,
                                                 Map<String, Artist> artistsById,
                                                 Map<String, PlayList> playListById) throws IOException {

        //File has the following format:
        //id; username; password; name; lastName; age; {ArtistId1,ArtistId2,ArtistId3,...};{PlayListId1,PlayListId2,PlayListId3,...}

        // Create a File object with the given path and filename
        File file = new File(path + customersCSVFileName);

        // Create a list to store the customers
        List<Customer> customers = new ArrayList<>();

        // Read all lines from the file
        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        // Loop through each line in the file


        // Return the list of customers
        return customers;

    }

    /**
     * Extracts IDs from a string and returns them as a list.
     *
     * The method does the following:
     * 1. Initializes an empty list to store the IDs.
     * 2. Splits the input string into an array using the comma as a separator.
     * 3. Loops through each ID in the array. For each ID, it:
     *    - Removes the curly braces from the ID.
     *    - Adds the cleaned ID to the list of IDs.
     * 4. Returns the list of IDs.
     *
     * @param ids The string containing the IDs.
     * @return A list of IDs.
     */
    private List<String> extractIds(String ids) {

        // Create a list to store the IDs
        List<String> idList = new ArrayList<>();

        // Split the string into an array using the comma as a separator
        String[] idArray = ids.split(",");

        // Loop through each ID in the array
        for (String id : idArray) {
            // Remove the curly braces and add the ID to the list
            idList.add(id.replace("{", "").replace("}", ""));
        }

        // Return the list of IDs
        return idList;
    }

    /**
     * Exports a list of artists to a CSV file.
     *
     * The method does the following:
     * 1. Initializes an empty list to store the lines to write to the file.
     * 2. Loops through each artist in the provided list. For each artist, it:
     *    - Converts the artist to its CSV representation using the toCSV method of the Artist class, which takes the provided separator as a parameter.
     *    - Adds the CSV representation of the artist to the list of lines to write.
     * 3. Writes the lines to the file using the writeTextFile method, which takes the path to the file (constructed by concatenating the provided path and filename) and the list of lines as parameters.
     *
     * @param defaultPath The default path to the CSV file.
     * @param separator The separator used in the CSV file.
     * @param defaultArtistsFileName The name of the CSV file.
     * @param artists The list of Artist objects to export.
     * @throws IOException If an I/O error occurs writing to the file.
     */
    public void exportArtistsToCSV(String defaultPath, String separator, String defaultArtistsFileName, List<Artist> artists)
        throws IOException {

        // Create a list to store the lines to write to the file
        List<String> linesToWrite = new ArrayList<>();

        // Loop through each artist
        for (Artist artist : artists) {
            // Add the CSV representation of the artist to the list
            linesToWrite.add(artist.toCSV(separator));
        }

        // Write the lines to the file
        writeTextFile(defaultPath + defaultArtistsFileName, linesToWrite);
    }

    /**
     * Exports a list of songs to a CSV file.
     *
     * The method does the following:
     * 1. Initializes an empty list to store the lines to write to the file.
     * 2. Loops through each song in the provided list. For each song, it:
     *    - Converts the song to its CSV representation using the toCSV method of the Song class, which takes the provided separator as a parameter.
     *    - Adds the CSV representation of the song to the list of lines to write.
     * 3. Writes the lines to the file using the writeTextFile method, which takes the path to the file (constructed by concatenating the provided path and filename) and the list of lines as parameters.
     *
     * @param defaultPath The default path to the CSV file.
     * @param separator The separator used in the CSV file.
     * @param defaultSongsFileName The name of the CSV file.
     * @param songs The list of Song objects to export.
     * @throws IOException If an I/O error occurs writing to the file.
     */
    public void exportSongsToCSV(String defaultPath, String separator, String defaultSongsFileName, List<Song> songs)
        throws IOException {

        // Create a list to store the lines to write to the file
        List<String> linesToWrite = new ArrayList<>();

        // Loop through each song
        for (Song song : songs) {
            // Add the CSV representation of the song to the list
            linesToWrite.add(song.toCSV(separator));
        }

        // Write the lines to the file
        writeTextFile(defaultPath + defaultSongsFileName, linesToWrite);
    }

    /**
     * Exports a list of playlists to a CSV file.
     *
     * The method does the following:
     * 1. Initializes an empty list to store the lines to write to the file.
     * 2. Loops through each playlist in the provided list. For each playlist, it:
     *    - Converts the playlist to its CSV representation using the toCSV method of the PlayList class, which takes the provided separator as a parameter.
     *    - Adds the CSV representation of the playlist to the list of lines to write.
     * 3. Writes the lines to the file using the writeTextFile method, which takes the path to the file (constructed by concatenating the provided path and filename) and the list of lines as parameters.
     *
     * @param path The path to the CSV file.
     * @param separator The separator used in the CSV file.
     * @param playListsCSVFileName The name of the CSV file.
     * @param playLists The list of PlayList objects to export.
     * @throws IOException If an I/O error occurs writing to the file.
     */
    public void exportPlayListsToCSV(String path, String separator, String playListsCSVFileName, List<PlayList> playLists)
        throws IOException {

        // Create a list to store the lines to write to the file
        List<String> linesToWrite = new ArrayList<>();

        // Loop through each playList
        for (PlayList playList : playLists) {
            // Add the CSV representation of the playList to the list
            linesToWrite.add(playList.toCSV(separator));
        }

        // Write the lines to the file
        writeTextFile(path + playListsCSVFileName, linesToWrite);

    }

    /**
     * Exports a list of customers to a CSV file.
     *
     * The method does the following:
     * 1. Initializes an empty list to store the lines to write to the file.
     * 2. Loops through each customer in the provided list. For each customer, it:
     *    - Converts the customer to its CSV representation using the toCSV method of the Customer class, which takes the provided separator as a parameter.
     *    - Adds the CSV representation of the customer to the list of lines to write.
     * 3. Writes the lines to the file using the writeTextFile method, which takes the path to the file (constructed by concatenating the provided path and filename) and the list of lines as parameters.
     *
     * @param path The path to the CSV file.
     * @param separator The separator used in the CSV file.
     * @param customersCSVFileName The name of the CSV file.
     * @param customers The list of Customer objects to export.
     * @throws IOException If an I/O error occurs writing to the file.
     */
    public void exportCustomersToCSV(String path, String separator, String customersCSVFileName, List<Customer> customers)
        throws IOException {

        // Create a list to store the lines to write to the file
        List<String> linesToWrite = new ArrayList<>();

        // Loop through each customer


        // Write the lines to the file
        writeTextFile(path + customersCSVFileName, linesToWrite);

    }

    /**
     * Writes a list of strings to a text file.
     *
     * The method does the following:
     * 1. Creates a File object using the provided path.
     * 2. Deletes the file if it already exists.
     * 3. Creates a new file at the specified path.
     * 4. Loops through each line in the provided list. For each line, it:
     *    - Adds a newline character before the line if it's not the first line.
     *    - Writes the line to the file.
     *
     * @param path The path to the text file.
     * @param linesToWrite The list of strings to write to the file.
     * @throws IOException If an I/O error occurs writing to the file.
     */
    private void writeTextFile(String path,
                               List<String> linesToWrite)
        throws IOException {

        // Create a File object with the given path
        File file = new File(path);

        // Delete the file if it exists
        Files.deleteIfExists(file.toPath());
        // Create the file
        Files.createFile(file.toPath());

        // Loop through each line in the list
        for (int i = 0; i < linesToWrite.size(); i++) {

            // If it's not the first line, add a newline character
            if (i!=0) {
                Files.writeString(file.toPath(), "\n", StandardOpenOption.APPEND);
            }
            // Write the line to the file
            Files.writeString(file.toPath(), linesToWrite.get(i), StandardOpenOption.APPEND);
        }
    }

    //endregion

   //region import from binary

    /**
     * Imports a list of artists from a binary file.
     *
     * The method does the following:
     * 1. Initializes a FileInputStream with the provided path and filename.
     * 2. Initializes an ObjectInputStream with the FileInputStream.
     * 3. Reads an object from the ObjectInputStream, casts it to a list of Artist objects, and returns it.
     *
     * Regarding the risks of using ObjectInputStream and the Serializable interface:
     * Security Risk: Deserializing objects can lead to security vulnerabilities, as maliciously crafted input can lead to unexpected code execution. This is because the process of deserialization involves executing code defined in the serialized object, which can be manipulated by an attacker.
     * Versioning: If you change the structure of a class (add/remove fields), you might not be able to deserialize objects serialized with an older version of the class. This can be mitigated by using the serialVersionUID field to manage versions, but it's still a risk.
     * Performance: Serialization can be slower and produce larger outputs compared to other methods of object persistence, such as converting objects to JSON or XML.
     * Inheritance: If a superclass implements Serializable, all subclasses are automatically serializable, which might not always be desired. Also, if a superclass does not implement Serializable, the responsibility of saving the state of the superclass falls on the subclass, which can lead to issues.
     * Encapsulation: Serialization exposes private fields, breaking the principles of encapsulation in object-oriented programming.
     *
     * @param path The path to the binary file.
     * @param artistsFileName The name of the binary file.
     * @return A list of Artist objects.
     * @throws IOException If an I/O error occurs reading from the file.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
    public List<Artist> importArtistsFromBinary(String path, String artistsFileName) throws IOException, ClassNotFoundException {
        // We use try-with-resources to automatically close the FileInputStream and ObjectInputStream
        try (FileInputStream fileInputStream = new FileInputStream(path + artistsFileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (List<Artist>) objectInputStream.readObject();
        }
    }

    /**
     * Imports a list of songs from a binary file.
     *
     * The method does the following:
     * 1. Initializes an empty list to store the Song objects.
     * 2. Initializes a FileInputStream with the provided path and filename, and an ObjectInputStream with the FileInputStream.
     * 3. Loops while there are more bytes available in the ObjectInputStream. For each iteration, it reads an object from the ObjectInputStream, casts it to a Song object, and adds it to the list.
     * 4. Returns the list of Song objects.
     *
     * Regarding the risks of using ObjectInputStream and the Serializable interface:
     * Security Risk: Deserializing objects can lead to security vulnerabilities, as maliciously crafted input can lead to unexpected code execution. This is because the process of deserialization involves executing code defined in the serialized object, which can be manipulated by an attacker.
     * Versioning: If you change the structure of a class (add/remove fields), you might not be able to deserialize objects serialized with an older version of the class. This can be mitigated by using the serialVersionUID field to manage versions, but it's still a risk.
     * Performance: Serialization can be slower and produce larger outputs compared to other methods of object persistence, such as converting objects to JSON or XML.
     * Inheritance: If a superclass implements Serializable, all subclasses are automatically serializable, which might not always be desired. Also, if a superclass does not implement Serializable, the responsibility of saving the state of the superclass falls on the subclass, which can lead to issues.
     * Encapsulation: Serialization exposes private fields, breaking the principles of encapsulation in object-oriented programming.
     *
     * @param path The path to the binary file.
     * @param songsFileName The name of the binary file.
     * @return A list of Song objects.
     * @throws IOException If an I/O error occurs reading from the file.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
    public List<Song> importSongsFromBinary(String path, String songsFileName) throws IOException, ClassNotFoundException {
        // Read the list of songs from the file
        List<Song> songs = new ArrayList<>();
        // We use try-with-resources to automatically close the FileInputStream and ObjectInputStream
        try(FileInputStream fileInputStream = new FileInputStream(path + songsFileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            while (objectInputStream.available() > 0) {
                Song songFromFile = (Song) objectInputStream.readObject();
                songs.add(songFromFile);
            }
        }
        return songs;
    }

    /**
     * Imports a list of playlists from a binary file.
     *
     * The method does the following:
     * 1. Initializes a FileInputStream with the provided path and filename.
     * 2. Initializes an ObjectInputStream with the FileInputStream.
     * 3. Reads an object from the ObjectInputStream, casts it to a list of PlayList objects, and returns it.
     *
     * Regarding the risks of using ObjectInputStream and the Serializable interface:
     * Security Risk: Deserializing objects can lead to security vulnerabilities, as maliciously crafted input can lead to unexpected code execution. This is because the process of deserialization involves executing code defined in the serialized object, which can be manipulated by an attacker.
     * Versioning: If you change the structure of a class (add/remove fields), you might not be able to deserialize objects serialized with an older version of the class. This can be mitigated by using the serialVersionUID field to manage versions, but it's still a risk.
     * Performance: Serialization can be slower and produce larger outputs compared to other methods of object persistence, such as converting objects to JSON or XML.
     * Inheritance: If a superclass implements Serializable, all subclasses are automatically serializable, which might not always be desired. Also, if a superclass does not implement Serializable, the responsibility of saving the state of the superclass falls on the subclass, which can lead to issues.
     * Encapsulation: Serialization exposes private fields, breaking the principles of encapsulation in object-oriented programming.
     *
     * @param path The path to the binary file.
     * @param playListsFileName The name of the binary file.
     * @return A list of PlayList objects.
     * @throws IOException If an I/O error occurs reading from the file.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
    public List<PlayList> importPlayListsFromBinary(String path, String playListsFileName) throws IOException, ClassNotFoundException {
        // We use try-with-resources to automatically close the FileInputStream and ObjectInputStream
        return new ArrayList<>();
    }

    /**
     * Imports a list of customers from a binary file.
     *
     * The method does the following:
     * 1. Initializes a FileInputStream with the provided path and filename.
     * 2. Initializes an ObjectInputStream with the FileInputStream.
     * 3. Reads an object from the ObjectInputStream, casts it to a list of Customer objects, and returns it.
     *
     * Regarding the risks of using ObjectInputStream and the Serializable interface:
     * Security Risk: Deserializing objects can lead to security vulnerabilities, as maliciously crafted input can lead to unexpected code execution. This is because the process of deserialization involves executing code defined in the serialized object, which can be manipulated by an attacker.
     * Versioning: If you change the structure of a class (add/remove fields), you might not be able to deserialize objects serialized with an older version of the class. This can be mitigated by using the serialVersionUID field to manage versions, but it's still a risk.
     * Performance: Serialization can be slower and produce larger outputs compared to other methods of object persistence, such as converting objects to JSON or XML.
     * Inheritance: If a superclass implements Serializable, all subclasses are automatically serializable, which might not always be desired. Also, if a superclass does not implement Serializable, the responsibility of saving the state of the superclass falls on the subclass, which can lead to issues.
     * Encapsulation: Serialization exposes private fields, breaking the principles of encapsulation in object-oriented programming.
     *
     * @param path The path to the binary file.
     * @param customersFileName The name of the binary file.
     * @return A list of Customer objects.
     * @throws IOException If an I/O error occurs reading from the file.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
    public List<Customer> importCustomersFromBinary(String path, String customersFileName) throws IOException, ClassNotFoundException {
        // We use try-with-resources to automatically close the FileInputStream and ObjectInputStream
       return new ArrayList<>();
    }

    /**
     * Imports a list of objects from a binary file.
     *
     * The method does the following:
     * 1. Initializes a FileInputStream with the provided path and filename.
     * 2. Initializes an ObjectInputStream with the FileInputStream.
     * 3. Reads an object from the ObjectInputStream, casts it to a list of objects of type T, and returns it.
     *
     * Regarding the risks of using ObjectInputStream and the Serializable interface:
     * Security Risk: Deserializing objects can lead to security vulnerabilities, as maliciously crafted input can lead to unexpected code execution.
     *      This is because the process of deserialization involves executing code defined in the serialized object, which can be manipulated by an attacker.
     * Versioning: If you change the structure of a class (add/remove fields), you might not be able to deserialize objects serialized with an older version of the class.
     *      This can be mitigated by using the serialVersionUID field to manage versions, but it's still a risk.
     * Performance: Serialization can be slower and produce larger outputs compared to other methods of object persistence, such as converting objects to JSON or XML.
     * Inheritance: If a superclass implements Serializable, all subclasses are automatically serializable, which might not always be desired.
     *      Also, if a superclass does not implement Serializable, the responsibility of saving the state of the superclass falls on the subclass, which can lead to issues.
     * Encapsulation: Serialization exposes private fields, breaking the principles of encapsulation in object-oriented programming.
     *
     * Generics in Java is a mechanism that allows for type (classes and interfaces) to be parameters when defining classes, interfaces, and methods.
     * Much like the more familiar formal parameters used in method declarations, type parameters provide a way for you to re-use the same code with different inputs.
     * The difference is that the inputs to formal parameters are values, while the inputs to type parameters are types.
     * In the context of this method, <T> is a type parameter that represents the type of objects in the list that this method will return.
     * This allows the method to be used with any type of object, increasing its reusability.
     * The actual type to replace T will be determined at runtime.
     *
     * @param path The path to the binary file.
     * @param fileName The name of the binary file.
     * @return A list of objects of type T.
     * @throws IOException If an I/O error occurs reading from the file.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
    public <T> List<T> importObjectsFromBinary(String path, String fileName) throws IOException, ClassNotFoundException {
        // We use try-with-resources to automatically close the FileInputStream and ObjectInputStream
        return new ArrayList<>();
    }

    //endregion

    //region export to binary

    /**
     * Exports a list of artists to a binary file.
     *
     * The method does the following:
     * 1. Initializes a FileOutputStream with the provided path and filename.
     * 2. Initializes an ObjectOutputStream with the FileOutputStream.
     * 3. Writes the list of Artist objects to the ObjectOutputStream.
     *
     * @param defaultPath The default path to the binary file.
     * @param defaultArtistsFileName The name of the binary file.
     * @param artists The list of Artist objects to export.
     * @throws IOException If an I/O error occurs writing to the file.
     */
    public void exportArtistsToBinary(String defaultPath, String defaultArtistsFileName, List<Artist> artists) throws IOException {
        // We use try-with-resources to automatically close the FileOutputStream and ObjectOutputStream
        try (FileOutputStream fileOutputStream = new FileOutputStream(defaultPath + defaultArtistsFileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(artists);
        }
    }

    /**
     * Exports a list of songs to a binary file.
     *
     * The method does the following:
     * 1. Initializes a FileOutputStream with the provided path and filename.
     * 2. Initializes an ObjectOutputStream with the FileOutputStream.
     * 3. Writes the list of Song objects to the ObjectOutputStream.
     *
     * @param path The default path to the binary file.
     * @param songsFileName The name of the binary file.
     * @param songs The list of Song objects to export.
     * @throws IOException If an I/O error occurs writing to the file.
     */
    public void exportSongsToBinary(String path, String songsFileName, List<Song> songs) throws IOException {
        // We use try-with-resources to automatically close the FileOutputStream and ObjectOutputStream
        try (FileOutputStream fileOutputStream = new FileOutputStream(path + songsFileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            for (Song song : songs) {
                objectOutputStream.writeObject(song);
            }
        }
    }

    /**
     * Exports a list of playlists to a binary file.
     *
     * The method does the following:
     * 1. Initializes a FileOutputStream with the provided path and filename.
     * 2. Initializes an ObjectOutputStream with the FileOutputStream.
     * 3. Writes the list of PlayList objects to the ObjectOutputStream.
     *
     * @param path The path to the binary file.
     * @param playListsFileName The name of the binary file.
     * @param playLists The list of PlayList objects to export.
     * @throws IOException If an I/O error occurs writing to the file.
     */
    public void exportPlayListsToBinary(String path, String playListsFileName, List<PlayList> playLists) throws IOException {
        // We use try-with-resources to automatically close the FileOutputStream and ObjectOutputStream

    }

    /**
     * Exports a list of customers to a binary file.
     *
     * The method does the following:
     * 1. Initializes a FileOutputStream with the provided path and filename.
     * 2. Initializes an ObjectOutputStream with the FileOutputStream.
     * 3. Writes the list of Customer objects to the ObjectOutputStream.
     *
     * @param path The path to the binary file.
     * @param customersFileName The name of the binary file.
     * @param customers The list of Customer objects to export.
     * @throws IOException If an I/O error occurs writing to the file.
     */
    public void exportCustomersToBinary(String path, String customersFileName, List<Customer> customers) throws IOException {
        // We use try-with-resources to automatically close the FileOutputStream and ObjectOutputStream

    }
    //endregion

}