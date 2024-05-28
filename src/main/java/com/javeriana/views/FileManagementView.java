package com.javeriana.views;

import com.javeriana.controllers.FileManagementController;
import com.javeriana.exceptions.UnsupportedTypeException;

import java.io.IOException;
import java.util.Scanner;

public class FileManagementView {

    public static final String AFFIRMATIVE_RESPONSE = "S";
    private final FileManagementController fileManagementController;
    private final Scanner scanner;
    private final String defaultPath;
    private final String defaultArtistsFileName;
    private final String defaultCustomersFileName;
    private final String defaultPlayListsFileName;
    private final String defaultSongsFileName;

    private final String defaultCSVSeparator;


    private static final String CSV_EXTENSION = ".csv";

    private static final String SPOTIFY_EXTENSION = ".spot";

    public FileManagementView(FileManagementController fileManagementController,
                              Scanner scanner,
                              String defaultPath,
                              String defaultArtistsFileName,
                              String defaultCustomersFileName,
                              String defaultPlayListsFileName,
                              String defaultSongsFileName
                              ) {

        this.fileManagementController = fileManagementController;
        this.scanner = scanner;
        this.defaultPath = defaultPath;
        this.defaultArtistsFileName = defaultArtistsFileName;
        this.defaultCustomersFileName = defaultCustomersFileName;
        this.defaultPlayListsFileName = defaultPlayListsFileName;
        this.defaultSongsFileName = defaultSongsFileName;
        this.defaultCSVSeparator = ";";

    }


    public void showView() {

        int option = 0;

        do {
            try{
                printMenu();

                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        importCSVFiles();
                        break;
                    case 2:
                        exportCSVFiles();
                        break;
                    case 3:
                        loadSpotifyFiles();
                        break;
                    case 4:
                        saveSpotifyFiles();
                        break;
                    case 0:
                        System.out.println("Volviendo al menú principal");
                        break;
                    default:
                        System.out.println("Opción inválida");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error al leer la opción");
            } catch (UnsupportedOperationException e) {
                System.out.println("Operación no soportada");
                System.out.println(e.getMessage());
            }
        } while (option != 0);


}

    private void saveSpotifyFiles() {

        System.out.println("Desea guardar los archivos en la ruta por defecto? (S/N)");

        String response = scanner.nextLine();

        if (!response.equalsIgnoreCase(AFFIRMATIVE_RESPONSE)) {
            System.out.println("Ingrese la ruta donde desea guardar los archivos");
        }

        String path = response.equalsIgnoreCase(AFFIRMATIVE_RESPONSE) ? defaultPath : scanner.nextLine();

        try {
            fileManagementController.saveSpotifyFiles(path,
                SPOTIFY_EXTENSION,
                defaultArtistsFileName,
                defaultSongsFileName,
                defaultPlayListsFileName,
                defaultCustomersFileName
                );
        } catch (IOException e) {
            System.out.println("Error al guardar los archivos");
            System.out.println(e.getMessage());
        }
    }

    private void loadSpotifyFiles() {

        System.out.println("Desea cargar los archivos en la ruta por defecto? (S/N)");

        String response = scanner.nextLine();

        if (!response.equalsIgnoreCase(AFFIRMATIVE_RESPONSE)) {
            System.out.println("Ingrese la ruta donde se encuentran los archivos");
        }

        String path = response.equalsIgnoreCase(AFFIRMATIVE_RESPONSE) ? defaultPath : scanner.nextLine();

        try {
            fileManagementController.loadSpotifyFiles(path,
                SPOTIFY_EXTENSION,
                defaultArtistsFileName,
                defaultSongsFileName,
                defaultPlayListsFileName,
                defaultCustomersFileName

                );
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los archivos");
            System.out.println(e.getMessage());
        }

    }

    private void exportCSVFiles() {

        System.out.println("¿Desea exportar los archivos por defecto? (S/N)");
        String defaultExportResponse = scanner.nextLine();

        System.out.println("¿Desea usar el separador por defecto? [El separador es \";\"] (S/N)");
        String defaultSeparatorResponse = scanner.nextLine();

        if(!defaultSeparatorResponse.equalsIgnoreCase(AFFIRMATIVE_RESPONSE)){
            System.out.println("Ingrese el separador que desea usar: ");
        }

        String separator = defaultSeparatorResponse.equalsIgnoreCase(AFFIRMATIVE_RESPONSE)
            ? defaultCSVSeparator
            : scanner.nextLine();

        String path = defaultPath;
        String artistsFileName = defaultArtistsFileName;
        String customersFileName = defaultCustomersFileName;
        String playListsFileName = defaultPlayListsFileName;
        String songsFileName = defaultSongsFileName;

        if (!defaultExportResponse.equalsIgnoreCase(AFFIRMATIVE_RESPONSE)) {
            System.out.println("Ingrese la ruta de los archivos CSV");
            path = scanner.nextLine();

            System.out.println("Ingrese el nombre del archivo de artistas, sin extensión, recuerde que la extensión será .csv");
            artistsFileName = scanner.nextLine();

            System.out.println("Ingrese el nombre del archivo de clientes, sin extensión, recuerde que la extensión será .csv");
            customersFileName = scanner.nextLine();

            System.out.println("Ingrese el nombre del archivo de playlists, sin extensión, recuerde que la extensión será .csv");
            playListsFileName = scanner.nextLine();

            System.out.println("Ingrese el nombre del archivo de canciones, sin extensión, recuerde que la extensión será .csv");
            songsFileName = scanner.nextLine();
        }

        try {
            fileManagementController.exportCSVFiles(path, separator, CSV_EXTENSION,
                artistsFileName,
                songsFileName,
                playListsFileName,
                customersFileName
                    );
        } catch (IOException e) {
            System.out.println("Error al exportar los archivos");
            System.out.println(e.getMessage());
        }
    }

    private void importCSVFiles() {

        System.out.println("Desea importar los archivos por defecto? (S/N)");
        String response = scanner.nextLine();

        System.out.println("¿Desea usar el separador por defecto? [El separador es \";\"] (S/N)");
        String defaultSeparatorResponse = scanner.nextLine();

        if(!defaultSeparatorResponse.equalsIgnoreCase(AFFIRMATIVE_RESPONSE)){
            System.out.println("Ingrese el separador que desea usar: ");
        }


        String separator = defaultSeparatorResponse.equalsIgnoreCase(AFFIRMATIVE_RESPONSE)
            ? defaultCSVSeparator
            : scanner.nextLine();

        String path = defaultPath;
        String artistsFileName = defaultArtistsFileName;
        String customersFileName = defaultCustomersFileName;
        String playListsFileName = defaultPlayListsFileName;
        String songsFileName = defaultSongsFileName;

        if (!response.equalsIgnoreCase(AFFIRMATIVE_RESPONSE)) {
            System.out.println("Ingrese la ruta de los archivos CSV");
            path = scanner.nextLine();

            System.out.println("Ingrese el nombre del archivo de artistas, sin extensión, recuerde que la extensión será .csv");
            artistsFileName = scanner.nextLine();

            System.out.println("Ingrese el nombre del archivo de clientes, sin extensión, recuerde que la extensión será .csv");
            customersFileName = scanner.nextLine();

            System.out.println("Ingrese el nombre del archivo de playlists, sin extensión, recuerde que la extensión será .csv");
            playListsFileName = scanner.nextLine();

            System.out.println("Ingrese el nombre del archivo de canciones, sin extensión, recuerde que la extensión será .csv");
            songsFileName = scanner.nextLine();
        }

        try {
            fileManagementController.importCSVFiles(path, separator,
                CSV_EXTENSION,
                artistsFileName,
                songsFileName,
                playListsFileName,
                customersFileName
            );

        } catch (IOException | UnsupportedTypeException e) {
            System.out.println("Error al importar los archivos");
            System.out.println(e.getMessage());
        }
    }

    public void printMenu() {

        String menu = """
            1. Importar archivos CSV.
            2. Exportar archivos CSV.
            3. Cargar archivos Spotify.
            4. Guardar archivos Spotify.
            
            0. Volver al menu principal.
            """;

        System.out.println(menu);

    }
}


