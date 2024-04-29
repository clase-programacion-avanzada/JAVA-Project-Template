package com.javeriana.views;

import com.javeriana.controllers.AdminController;
import com.javeriana.exceptions.AlreadyExistsException;
import com.javeriana.exceptions.NotFoundException;
import com.javeriana.models.PlayList;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * The AdminView class in the com.javeriana.views package is a user interface class that provides methods for interacting with the music application from an administrative perspective.
 *
 * The class does the following:
 * 1. Initialization: The AdminView class has a constructor that initializes an AdminController object and a Scanner object.
 * 2. Menu Display: It provides a method (showView) for displaying a menu of options to the user and handling the user's selection.
 * 3. Artist Management: It provides methods for adding an artist to the database (addArtistToDatabase) and deleting an artist from the database (deleteArtistFromDatabase).
 * 4. Song Management: It provides methods for adding a song to the database (addSongToDatabase) and deleting a song from the database (deleteSongFromDatabase).
 * 5. Customer Management: It provides methods for adding a customer to the database (addCustomerToDatabase) and deleting a customer from the database (deleteCustomerFromDatabase).
 * 6. Data Display: It provides methods for displaying all customers (showAllCustomers), all songs (showAllSongs), all artists (showAllArtists), and all playlists (showAllPlaylists).
 */
public class AdminView {

    /**
     * An instance of the AdminController class.
     * It is used to handle the business logic related to administrative tasks in the music application,
     * such as managing artists, songs, and customers.
     */
    private final AdminController adminController;

    /**
     * An instance of the Scanner class.
     * It is used to read the user's input from the console.
     */
    private final Scanner scanner;

    /**
     * The constructor of the AdminView class.
     * It takes an AdminController object and a Scanner object as parameters.
     *
     * @param adminController An instance of the AdminController class.
     *                        This parameter is used to handle the business logic related to administrative tasks in the music application,
     *                        such as managing artists, songs, and customers.
     * @param scanner An instance of the Scanner class.
     *                This parameter is used to read the user's input from the console.
     *
     * Inside the constructor, the parameters are assigned to the corresponding private final variables.
     */
    public AdminView(AdminController adminController, Scanner scanner) {
        this.adminController = adminController;
        this.scanner = scanner;
    }

    /**
     * The showView method is responsible for displaying the menu to the user and handling the user's selection.
     *
     * The method does the following:
     * 1. Initialization: The method initializes an integer variable option to 0. This variable is used to store the user's menu selection.
     * 2. Menu Loop: The method enters a do-while loop that continues until the user enters 0, which is the option to exit the menu.
     * 3. Menu Display and Input Reading: Inside the loop, the method first calls the printMenu method to display the menu options to the user. Then it reads the user's input from the console and tries to parse it as an integer to store in the option variable.
     * 4. Option Handling: Depending on the value of option, the method calls different methods to perform various tasks, such as adding or deleting artists, songs, and customers, or displaying lists of customers, songs, artists, and playlists.
     * 5. Error Handling: The method includes a try-catch block to handle any exceptions that might occur when parsing the user's input or performing the selected task. If an exception occurs, an error message is displayed to the user.
     */
    public void showView() {
        int option = 0;

        do {

            try {
                printMenu();

                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        System.out.println("Crear artista y agregarlo a la base de datos.");
                        addArtistToDatabase();
                        break;
                    case 2:
                        System.out.println("Eliminar artista de la base de datos, las canciones en las que exista y eliminar canciones de las playlists en las que existan esas canciones.");
                        deleteArtistFromDatabase();
                        break;
                    case 3:
                        System.out.println("Crear canción y agregarla a la base de datos.");
                        addSongToDatabase();
                        break;
                    case 4:
                        System.out.println("Eliminar canción de la base de datos y las playlists en las que exista.");
                        deleteSongFromDatabase();
                        break;
                    case 5:
                        System.out.println("Crear Cliente.");
                        addCustomerToDatabase();
                        break;
                    case 6:
                        System.out.println("Eliminar Cliente. Recuerde que esta opción eliminará al cliente y todas sus playlists.");
                        deleteCustomerFromDatabase();
                        break;
                    case 7:
                        System.out.println("Ver la lista de clientes.");
                        showAllCustomers();
                        break;
                    case 8:
                        System.out.println("Ver la lista de canciones.");
                        showAllSongs();
                        break;
                    case 9:
                        System.out.println("Ver la lista de artistas.");
                        showAllArtists();
                        break;
                    case 10:
                        System.out.println("Ver la lista de playlists.");
                        showAllPlaylists();
                        break;
                    case 0:
                        System.out.println("Volviendo al menú principal.");
                        break;
                    default:
                        System.out.println("Opción inválida");
                        break;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Ingrese un número válido");
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }

        } while (option != 0);
    }

    /**
     * The showAllPlaylists method is responsible for displaying all playlists to the user.
     *
     * The method does the following:
     * 1. Data Retrieval: The method calls the getAllPlaylists method of the adminController object to retrieve a list of all playlists. This list is stored in the playlists variable.
     * 2. Data Display: The method then enters a for loop to iterate over the playlists list. For each playlist, it prints the index of the playlist in the list and the string representation of the playlist.
     * 3. End Message: After the loop, the method prints a message to indicate the end of the playlist list.
     */
    private void showAllPlaylists() {
        List<String> playlists = adminController.getAllPlaylists();

        for (int i = 0; i < playlists.size(); i++) {
            System.out.println(i + ") " + playlists.get(i));
        }

        System.out.println("Fin de la lista de PlayLists.");
    }

    /**
     * The showAllArtists method is responsible for displaying all artists to the user.
     *
     * The method does the following:
     * 1. Data Retrieval: The method calls the getAllArtists method of the adminController object to retrieve a list of all artists. This list is stored in the artists variable.
     * 2. Data Display: The method then enters a for loop to iterate over the artists list. For each artist, it prints the index of the artist in the list and the string representation of the artist.
     * 3. End Message: After the loop, the method prints a message to indicate the end of the artist list.
     */
    private void showAllArtists() {

        List<String> artists = adminController.getAllArtists();

        for (int i = 0; i < artists.size(); i++) {
            System.out.println(i + ") " + artists.get(i));
        }

        System.out.println("Fin de la lista de artistas.");
    }

    /**
     * The showAllSongs method is responsible for displaying all songs to the user.
     *
     * The method does the following:
     * 1. Data Retrieval: The method calls the getAllSongs method of the adminController object to retrieve a list of all songs. This list is stored in the songs variable.
     * 2. Data Display: The method then enters a for loop to iterate over the songs list. For each song, it prints the index of the song in the list and the string representation of the song.
     * 3. End Message: After the loop, the method prints a message to indicate the end of the song list.
     */
    private void showAllSongs() {
        List<String> songs = adminController.getAllSongs();

        for (int i = 0; i < songs.size(); i++) {
            System.out.println(i + ") " + songs.get(i));
        }

        System.out.println("Fin de la lista de canciones.");
    }

    /**
     * The showAllCustomers method is responsible for displaying all customers to the user.
     *
     * The method does the following:
     * 1. Data Retrieval: The method calls the getAllCustomers method of the adminController object to retrieve a list of all customers. This list is stored in the customers variable.
     * 2. Data Display: The method then enters a for loop to iterate over the customers list. For each customer, it prints the index of the customer in the list and the string representation of the customer.
     * 3. End Message: After the loop, the method prints a message to indicate the end of the customer list.
     */
    private void showAllCustomers() {
        List<String> customers = adminController.getAllCustomers();

        for (int i = 0; i < customers.size(); i++) {
            System.out.println(i + ") " + customers.get(i));
        }

        System.out.println("Fin de la lista de usuarios.");

    }

    /**
     * The deleteCustomerFromDatabase method is responsible for deleting a customer and all their playlists from the database.
     *
     * The method does the following:
     * 1. Initial Message: The method starts by printing a message to the console, informing the user that this operation will delete a customer and all their playlists.
     * 2. Data Display: The method calls the showAllCustomers method to display a list of all customers to the user.
     * 3. Input Reading: The method prompts the user to enter the username of the customer they want to delete. It reads the user's input from the console and stores it in the username variable.
     * 4. Data Deletion: The method calls the deleteCustomerFromDatabase method of the adminController object, passing the username as a parameter. This operation deletes the customer from the database.
     * 5. Confirmation Message: Finally, the method prints a message to the console to confirm that the customer has been deleted.
     */
    private void deleteCustomerFromDatabase() {

        System.out.println("Eliminar cliente. Recuerde que esta opción eliminará al cliente y todas sus playlists.");
        System.out.println("Estos son los clientes disponibles:");
        showAllCustomers();

        System.out.println("Ingrese el username del cliente que desea eliminar:");
        String username = scanner.nextLine();

        adminController.deleteCustomerFromDatabase(username);

        System.out.println("Cliente eliminado.");
    }

    /**
     * The addCustomerToDatabase method is responsible for creating a new customer in the database.
     *
     * The method does the following:
     * 1. Initial Message: The method starts by printing a message to the console, informing the user that this operation will create a new customer.
     * 2. Input Reading: The method prompts the user to enter the details of the customer they want to create. It reads the user's input from the console and stores it in the respective variables (name, lastName, username, password, age).
     * 3. Data Creation: The method calls the addCustomerToDatabase method of the adminController object, passing the customer details as parameters. This operation creates a new customer in the database.
     * 4. Confirmation Message: Finally, the method prints a message to the console to confirm that the customer has been created.
     */
    private void addCustomerToDatabase() throws AlreadyExistsException{

        System.out.println("Crear cliente.");

        System.out.println("Ingrese el nombre del cliente:");
        String name = scanner.nextLine();

        System.out.println("Ingrese el apellido del cliente:");
        String lastName = scanner.nextLine();

        System.out.println("Ingrese el nombre de usuario (username) del cliente:");
        String username = scanner.nextLine();

        System.out.println("Ingrese la contraseña del cliente:");
        String password = scanner.nextLine();

        System.out.println("Ingrese la edad del cliente:");
        int age = Integer.parseInt(scanner.nextLine());

        adminController.addCustomerToDatabase(username, password, name, lastName, age);

        System.out.println("Cliente creado.");

    }

    /**
     * The deleteSongFromDatabase method is responsible for deleting a song from the database and any playlists it exists in.
     *
     * The method does the following:
     * 1. Initial Message: The method starts by printing a message to the console, informing the user that this operation will delete a song from the database and any playlists it exists in.
     * 2. Data Display: The method calls the showAllSongs method to display a list of all songs to the user.
     * 3. Input Reading: The method prompts the user to enter the ID of the song they want to delete. It reads the user's input from the console and stores it in the songId variable.
     * 4. Data Deletion: The method calls the deleteSongFromDatabase method of the adminController object, passing the songId as a parameter. This operation deletes the song from the database.
     * 5. Confirmation Message: Finally, the method prints a message to the console to confirm that the song has been deleted.
     */
    private void deleteSongFromDatabase() throws NotFoundException {

        System.out.println("Eliminar canción de la base de datos y las playlists en las que exista.");
        System.out.println("Estas son las canciones disponibles:");
        showAllSongs();

        System.out.println("Ingrese el id de la canción que desea eliminar:");
        String songId = scanner.nextLine();

        adminController.deleteSongFromDatabase(songId);

        System.out.println("Canción eliminada.");

    }

    /**
     * The addSongToDatabase method is responsible for creating a new song in the database.
     *
     * The method does the following:
     * 1. Initial Message: The method starts by printing a message to the console, informing the user that this operation will create a new song.
     * 2. Input Reading: The method prompts the user to enter the details of the song they want to create (name, genre, duration, album). It reads the user's input from the console and stores it in the respective variables.
     * 3. Artist Addition: The method enters a loop where it prompts the user to add artists to the song. It displays all available artists, reads the user's input for the artist ID, and adds it to the artists set. This continues until the user decides not to add more artists.
     * 4. Data Creation: The method calls the addSongToDatabase method of the adminController object, passing the song details and the set of artists as parameters. This operation creates a new song in the database.
     * 5. Confirmation Message: Finally, the method prints a message to the console to confirm that the song has been created.
     */
    private void addSongToDatabase() throws NotFoundException {

        System.out.println("Crear canción.");

        System.out.println("Ingrese el nombre de la canción:");
        String name = scanner.nextLine();

        System.out.println("Ingrese el género de la canción:");
        String genre = scanner.nextLine();

        System.out.println("Ingrese la duración de la canción en segundos:");
        int duration = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese el álbum de la canción:");
        String album = scanner.nextLine();

        System.out.println("Agregar artistas a la canción.");
        boolean addAnotherArtist = true;

        showAllArtists();
        Set<String> artists = new HashSet<>();
        do {

            System.out.println("Ingrese el id del artista que desea agregar a la canción:");
            String artistId = scanner.nextLine();

            artists.add(artistId);

            System.out.println("Artista agregado.");
            System.out.println("Desea agregar otro artista a la canción? (S/N)");
            String answer = scanner.nextLine();
            addAnotherArtist = answer.equalsIgnoreCase("s");

        } while (addAnotherArtist);

        adminController.addSongToDatabase(name, genre, duration, album, artists);

        System.out.println("Canción creada.");

    }

    /**
     * The deleteArtistFromDatabase method is responsible for deleting an artist from the database, any songs they're associated with, and remove those songs from any playlists they exist in.
     *
     * The method does the following:
     * 1. Initial Message: The method starts by printing a message to the console, informing the user that this operation will delete an artist from the database, any songs they're associated with, and remove those songs from any playlists they exist in.
     * 2. Data Display: The method calls the showAllArtists method to display a list of all artists to the user.
     * 3. Input Reading: The method prompts the user to enter the ID of the artist they want to delete. It reads the user's input from the console and stores it in the artistId variable.
     * 4. Data Deletion: The method calls the deleteArtistFromDatabase method of the adminController object, passing the artistId as a parameter. This operation deletes the artist from the database.
     * 5. Confirmation Message: Finally, the method prints a message to the console to confirm that the artist has been deleted.
     */
    private void deleteArtistFromDatabase() throws NotFoundException {

        System.out.println("Eliminar artista de la base de datos, las canciones en las que exista y eliminar canciones de las playlists en las que existan esas canciones.");
        System.out.println("Estos son los artistas disponibles:");
        showAllArtists();

        System.out.println("Ingrese el id del artista que desea eliminar:");
        String artistId = scanner.nextLine();

        adminController.deleteArtistFromDatabase(artistId);

        System.out.println("Artista eliminado.");
    }

    /**
     * The addArtistToDatabase method is responsible for creating a new artist in the database.
     *
     * The method does the following:
     * 1. Initial Message: The method starts by printing a message to the console, informing the user that this operation will create a new artist.
     * 2. Input Reading: The method prompts the user to enter the name of the artist they want to create. It reads the user's input from the console and stores it in the name variable.
     * 3. Data Creation: The method calls the addArtistToDatabase method of the adminController object, passing the name as a parameter. This operation creates a new artist in the database.
     */
    private void addArtistToDatabase() throws AlreadyExistsException {
        System.out.println("Crear artista.");
        System.out.println("Ingrese el nombre del artista:");
        String name = scanner.nextLine();

        adminController.addArtistToDatabase(name);

    }

    /**
     * The printMenu method is responsible for displaying the menu to the user.
     *
     * The method does the following:
     * 1. Menu Creation: The method starts by creating a multiline string that represents the menu. This string includes the options that the user can choose from, such as creating or deleting artists, songs, and customers, viewing lists of customers, songs, artists, and playlists, and returning to the main menu.
     * 2. Menu Display: The method then prints this menu to the console, allowing the user to see the available options.
     */
    private void printMenu() {
        String menu = """
            1. Crear artista y agregarlo a la base de datos.
            2. Eliminar artista de la base de datos, y las canciones en las que exista.
            3. Crear canción y agregarla a la base de datos.
            4. Eliminar canción de la base de datos y las playlists en las que exista.
            5. Crear Cliente.
            6. Eliminar Cliente.
            7. Ver la lista de clientes.
            8. Ver la lista de canciones.
            9. Ver la lista de artistas.
            10. Ver la lista de playlists.
            
            0. Volver al menú principal.
        """;
        System.out.println(menu);
    }


}
