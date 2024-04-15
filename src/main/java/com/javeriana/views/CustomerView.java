package com.javeriana.views;

import com.javeriana.controllers.CustomerController;
import com.javeriana.exceptions.AlreadyExistsException;
import com.javeriana.exceptions.NotFoundException;
import com.javeriana.exceptions.WrongLogInException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CustomerView {

    /**
     * The customerController attribute is an instance of the CustomerController class.
     * It is used to handle the business logic related to customers, such as logging in,
     * creating playlists, adding songs to playlists, and following artists.
     */
    private final CustomerController customerController;

    /**
     * The customerController attribute is an instance of the CustomerController class.
     * It is used to handle the business logic related to customers, such as logging in,
     * creating playlists, adding songs to playlists, and following artists.
     */
    private final Scanner scanner;

    /**
     * The constructor for the CustomerView class.
     *
     * The constructor does the following:
     * 1. Parameter Acceptance: The constructor accepts two parameters: a CustomerController object and a Scanner object.
     * 2. Attribute Assignment: The constructor assigns the passed CustomerController object to the customerController attribute of the CustomerView instance. Similarly, it assigns the passed Scanner object to the scanner attribute of the CustomerView instance.
     *
     * @param customerController The CustomerController object to be assigned to the customerController attribute.
     * @param scanner The Scanner object to be assigned to the scanner attribute.
     */
    public CustomerView(CustomerController customerController, Scanner scanner) {
        this.customerController = customerController;
        this.scanner = scanner;
    }

    public void showView() throws WrongLogInException {

        boolean customerLogged = logIn();

        if (!customerLogged) {
            throw new WrongLogInException("Usuario o contraseña incorrectos.");
        }

        int option = 0;

        do {

            printMenu();

            try{
                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1 -> addNewPlayList();

                    case 2 -> showMyPlayLists();

                    case 3 -> addNewSongToPlayList();

                    case 4 -> deleteSongFromPlayList();

                    case 5 -> seeAllSongsFromPlayList();

                    case 6 -> followArtist();

                    case 7 -> seeFollowedArtists();

                    case 0 -> System.out.println("Volviendo al menú principal.");

                    default -> System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Ingrese un número válido");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (option != 0);

        customerController.logOut();

    }

    private void seeFollowedArtists() throws WrongLogInException {
        System.out.println("Artistas seguidos:");
        List<String> followedArtists = customerController.getFollowedArtists();

        for (int i = 0; i < followedArtists.size(); i++) {
            System.out.println(i + ") " + followedArtists.get(i));
        }

    }

    private void followArtist() throws NotFoundException, AlreadyExistsException {

        System.out.println("Seguir artista.");
        System.out.println("Estos son los artistas disponibles:");
        showAllArtists();

        System.out.println("Ingrese el id del artista que desea seguir:");
        String artistId = scanner.nextLine();

        customerController.followArtist(artistId);

    }

    private void seeAllSongsFromPlayList() throws NotFoundException, WrongLogInException {

        String playListId = getPlayListId("Ver todas las canciones de una playlist.");

        List<String> songs = customerController.getAllSongsFromPlayList(playListId);

        for (int i = 0; i < songs.size(); i++) {
            System.out.println(i + ") " + songs.get(i));
        }

        System.out.println("Fin de la lista de canciones.");

    }

    private String getPlayListId(String header) throws WrongLogInException {
        System.out.println(header);
        showMyPlayLists();

        System.out.println("Ingrese el id de la playlist:");

        return scanner.nextLine();
    }

    private void deleteSongFromPlayList() throws WrongLogInException, NotFoundException {

        String playListId = getPlayListId("Eliminar canción de una playlist.");
        System.out.println("Ingrese el id de la canción a eliminar:");
        showSongsInPlayList(playListId);
        String songId = scanner.nextLine();

        boolean songWasDeleted = customerController.deleteSongFromPlayList(playListId, songId);

        if (songWasDeleted) {
            System.out.println("Canción eliminada de la playlist.");
        } else {
            System.out.println("No se pudo eliminar la canción de la playlist.");
        }

    }

    private void showSongsInPlayList(String playListId) throws NotFoundException {

            List<String> songsFromPlayList = customerController.getAllSongsFromPlayList(playListId);

            for (int i = 0; i < songsFromPlayList.size(); i++) {
                System.out.println(i + ") " + songsFromPlayList.get(i));
            }
    }

    private void addNewSongToPlayList() throws WrongLogInException, NotFoundException {

        System.out.println("Agregar canción a una playlist.");

        String playListId = getPlayListId("Elija una playlist.");
        String songId = getSongId("Elige una canción para agregar a la playlist.");

        customerController.addSongToPlayList(playListId, songId);


    }

    private String getSongId(String header) {
        System.out.println(header);
        showAllSongs();
        System.out.println("Ingrese el id de la canción:");
        return scanner.nextLine();
    }

    private void showAllArtists() {

        System.out.println("Artistas disponibles:");
        List<String> artists = customerController.getAllArtists();

        for (int i = 0; i < artists.size(); i++) {
            System.out.println(i + ") " + artists.get(i));
        }

    }
    private void showAllSongs() {

        System.out.println("Canciones disponibles:");
        List<String> songs = customerController.getAllSongs();

        for (int i = 0; i < songs.size(); i++) {
            System.out.println(i + ") " + songs.get(i));
        }


    }

    private void showMyPlayLists() {

        List<String> playLists = customerController.getLoggedCustomerPlaylists();
        System.out.println("Mis playlist:");

        //Prints playlist with index
        for (int i = 0; i < playLists.size(); i++) {
            System.out.println(i + ") " + playLists.get(i));
        }

    }

    private void addNewPlayList()  {
        System.out.println("Creando una nueva playlist.");
        System.out.println("Ingrese el nombre de la playlist:");
        String playListName = scanner.nextLine();

        customerController.addNewPlayList(playListName);
    }



    public void printMenu() {

        String menu = """
            1. Crear una nueva playlist.
            2. Ver mis playlist.
            3. Agregar canción a una playlist.
            4. Eliminar canción de una playlist.
            5. Ver todas las canciones de una playlist.
            6. Seguir artista.
            
            0. Volver al menú principal.
            """;

        System.out.println(menu);

    }

    public boolean logIn() {
        System.out.println("Iniciar sesión");
        System.out.println("Ingrese su nombre de usuario:");
        String username = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String password = scanner.nextLine();

        return customerController.logIn(username, password);
    }


}
