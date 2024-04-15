package com.javeriana.views;

import com.javeriana.controllers.ReportController;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReportView {

    private final ReportController reportController;
    private final Scanner scanner;

    public ReportView(ReportController reportController, Scanner scanner) {
        this.reportController = reportController;
        this.scanner = scanner;
    }

    public void showView() {

        int option = 0;

        do {
            try{
                printMenu();

                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        showMostFollowedArtists();
                        break;
                    case 2:
                        showMostAddedSong();
                        break;
                    case 3:
                        showMostAddedSongByArtist();
                        break;
                    case 0:
                        System.out.println("Volviendo al menú principal");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }

            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Ingrese un número válido");

            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }

        } while (option != 0);


    }

    private void showMostAddedSongByArtist() {
        System.out.println("Reporte de canciones más escuchadas por artista");

        showAllArtists();

        System.out.println("Ingrese el id del artista");

        String artistId = scanner.nextLine();
        String mostAddedSongByArtist = reportController.showMostAddedSongOfArtist(artistId);


        System.out.println("La canción más escuchada por el artista  es: " + mostAddedSongByArtist);
    }

    private void showAllArtists() {
        System.out.println("Lista de artistas");
        List<String> artists = reportController.getAllArtists();

        for (String artist : artists) {
            System.out.println(artist);
        }
    }

    private void showMostAddedSong() {
        System.out.println("Reporte de canciones más escuchadas");
        String mostAddedSong = reportController.showMostAddedSongInPlayList();

        System.out.println("La canción más escuchada es: " + mostAddedSong);
    }

    private void showMostFollowedArtists() {
        System.out.println("Reporte de artistas más seguidos");
        Map<String, Integer> reportOfFollowedArtists = reportController.showMostFollowedArtists();

        for (Map.Entry<String, Integer> entry : reportOfFollowedArtists.entrySet()) {
            System.out.println("Artista: " + entry.getKey() + " - Seguidores: " + entry.getValue());
        }


    }

    private void printMenu() {

        String menu = """
            1. Generar reporte de artistas seguidos.
            2. Mostrar la canción más agregada.
            3. Generar reporte de canciones más escuchadas por artista.
            
            0. Volver al menú principal.
            """;

        System.out.println(menu);
    }
}
