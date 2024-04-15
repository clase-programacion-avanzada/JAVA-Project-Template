package com.javeriana;

import com.javeriana.controllers.AdminController;
import com.javeriana.controllers.CustomerController;
import com.javeriana.controllers.FileManagementController;
import com.javeriana.controllers.ReportController;
import com.javeriana.exceptions.WrongLogInException;
import com.javeriana.services.ArtistService;
import com.javeriana.services.CustomerService;
import com.javeriana.services.FileManagementService;
import com.javeriana.services.PlayListService;
import com.javeriana.services.ReportService;
import com.javeriana.services.SongService;
import com.javeriana.views.AdminView;
import com.javeriana.views.CustomerView;
import com.javeriana.views.FileManagementView;
import com.javeriana.views.ReportView;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Create Services
        // These are the service classes that contain the business logic of the application.
        // Each service is responsible for a specific part of the application's functionality.
        ArtistService artistService = new ArtistService();
        PlayListService playListService = new PlayListService();
        CustomerService customerService = new CustomerService();
        SongService songService = new SongService();
        FileManagementService fileManagementService = new FileManagementService();
        ReportService reportService = new ReportService();

        // Create Controllers
        // Controllers are responsible for handling user input and calling the appropriate services.
        // They act as an intermediary between the services and the views.
        // Each controller is initialized with the services it needs to perform its tasks.
        // This is an example of Inversion of Control (IoC), where the controllers do not create their own dependencies (the services), but are provided with them when they are created.
        AdminController adminController = new AdminController(
            artistService,
            playListService,
            customerService,
            songService);
        FileManagementController fileService = new FileManagementController(
            fileManagementService,
            artistService,
            songService,
            customerService,
            playListService
            );
        CustomerController customerController = new CustomerController(customerService,
            artistService,
            playListService,
            songService);

        ReportController reportController = new ReportController(
            reportService,
            artistService,
            songService,
            customerService,
            playListService
        );

        // Create Views
        // The views are responsible for displaying information to the user and getting user input.
        // Each view is initialized with the controller it needs to send user input to.
        // This is another example of IoC, where the views do not create their own dependencies (the controllers), but are provided with them when they are created.
        Scanner scanner = new Scanner(System.in);
        AdminView adminView = new AdminView(adminController, scanner);
        FileManagementView fileManagementView = new FileManagementView(fileService, scanner, "src/main/resources/", "artists", "customers", "playlists", "songs");
        CustomerView customerView = new CustomerView(customerController, scanner);
        ReportView reportView = new ReportView(reportController, scanner);

        int option = 0;
        do {
            try{
                printMenu();

                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        adminView.showView();
                        break;
                    case 2:
                        fileManagementView.showView();
                        break;
                    case 3:
                        customerView.showView();
                        break;
                    case 4:
                        reportView.showView();
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema");
                        break;
                    default:
                        System.out.println("Opción inválida");
                        break;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Ingrese un número válido");

            } catch (WrongLogInException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }

        } while (option != 0);


        scanner.close();
    }

    private static void printMenu() {
        String menu = """
            Elija el módulo al que desea ingresar:
            1. Módulo de administración
            2. Módulo de gestión de archivos
            3. Módulo de clientes
            4. Módulo de reportes
            
            0. Salir
            """;
        System.out.println(menu);

    }
}