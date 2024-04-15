package com.javeriana.models;

import java.io.Serializable;
import java.util.UUID;

/**
 * The Artist class represents an artist in a music application.
 * It is a simple Java class that implements the Serializable interface, allowing instances of this class to be converted into a byte stream and vice versa.
 * This is useful for saving objects to disk or sending them over a network.
 *
 * This class provides methods for:
 * - Creating an Artist with a specific id and name
 * - Creating an Artist with a name and a randomly generated id
 * - Retrieving the id and name of the Artist
 * - Converting the Artist object to a CSV string
 * - Creating an Artist object from a CSV string
 * - Returning a string representation of the Artist object
 * - Returning an Artist object with unknown data when the artist is not found in the file
 *
 * The class has two attributes: id and name. The id is a UUID (Universally Unique Identifier), which is used to uniquely identify each Artist instance.
 * The name is a String that represents the name of the artist.
 */
public class Artist implements Serializable {

    // region Attributes
    /**
     * The id attribute is an instance of the UUID class.
     * It is used to uniquely identify each Artist instance.
     */
    private UUID id;

    /**
     * The name attribute is a String that represents the name of the artist.
     */
    private String name;

    // endregion

    // region Constructors

   /**
    * Constructs an Artist object with the provided id and name.
    *
    * @param id The unique identifier for the artist.
    * @param name The name of the artist.
    */
    public Artist(UUID id, String name) {
       this.id = id;
       this.name = name;
    }

    /**
     * Constructs an Artist object with the provided name.
     * The id is generated automatically.
     * @param name The name of the artist.
     */
    public Artist(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    // endregion

    // region Getters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    // endregion

    /**
     * Returns a CSV representation of the Artist object.
     * The separator is passed as an argument.
     * The representation format is: id;name
     *
     * @param separator The separator to use in the CSV string.
     * @return A CSV string representation of the Artist.
     */
    public String toCSV(String separator) {
        return id + separator + name;
    }

    /**
     * Creates an Artist object from a CSV line.
     * The separator is passed as an argument.
     * The expected format is: id;name
     *
     * @param csvLine The CSV line that represents an Artist.
     * @param separator The separator used in the CSV line.
     * @return An Artist object created from the CSV line.
     * @throws IllegalArgumentException If the format of the CSV line is incorrect.
     */
    public static Artist fromCSV(String csvLine, String separator) {
        String[] data = csvLine.split(separator);

        if (data.length != 2) {
            throw new IllegalArgumentException("El formato de la línea no es correcto, se esperan 2 campos"
             + " y se envío " + csvLine + " con el separador " + separator);
        }

        // Create an Artist object with the data from the line
        UUID id = UUID.fromString(data[0]);
        String name = data[1];

        return new Artist(id,name);
    }

    /**
     * Returns a string representation of the Artist object.
     * The representation format is: "Nombre: " + name + " - id: " + id
     *
     * @return A string representation of the Artist.
     */
    @Override
    public String toString() {
        return "Nombre: " + name + " - id: " + id;
    }

    /**
     * Returns an Artist object with unknown data. This method is used when the artist is not found in the file.
     *
     * @param id The id of the unknown artist.
     * @return An Artist object with the provided id and name set as "Unknown Artist".
     */
    public static Artist GetUnknownArtist(String id) {
        return new Artist(UUID.fromString(id), "Unknown Artist");
    }

}
