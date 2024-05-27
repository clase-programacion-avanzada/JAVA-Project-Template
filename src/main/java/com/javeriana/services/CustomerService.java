package com.javeriana.services;

import com.javeriana.controllers.AdminController;
import com.javeriana.controllers.CustomerController;
import com.javeriana.controllers.ReportController;
import com.javeriana.exceptions.AlreadyExistsException;
import com.javeriana.exceptions.WrongLogInException;
import com.javeriana.models.Artist;
import com.javeriana.models.Customer;
import com.javeriana.models.PlayList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
public class CustomerService {

    /**
     * A regular expression pattern for validating passwords.
     * The password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long.
     */
    private static final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$";

    /**
     * A regular expression pattern for validating usernames.
     * The username must start with a letter and can contain letters, digits, and underscores. It must be between 8 and 30 characters long.
     */
    private static final String USERNAME_PATTERN = "^[a-zA-Z][a-zA-Z0-9_]{7,30}$";

    /**
     * The minimum age required for a customer to register.
     */
    private static final int MINIMUM_AGE = 14;

    /**
     * A list of customers.
     */
    private List<Customer> customers;

    /**
     * The currently logged in customer.
     */
    private Customer loggedCustomer;

    /**
     * Constructor for the CustomerService class.
     * Initializes the customers list and sets the loggedCustomer to null.
     */
    public CustomerService() {
        this.customers = new ArrayList<>();
        this.loggedCustomer = null;
    }

    /**
     * Returns a new list containing all customers.
     *
     * @return A new list containing all customers.
     */
    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    /**
     * This method is responsible for adding a new customer to the list of customers. It takes in several parameters: the username, password, name, last name, and age of the customer.
     * It checks if the provided name or last name is empty, and if it is, it throws an `IllegalArgumentException`. It also checks if the provided username matches the `USERNAME_PATTERN`,
     * and if it doesn't, it throws an `IllegalArgumentException`. Similarly, it checks if the provided password matches the `PASSWORD_PATTERN`, and if it doesn't, it throws an `IllegalArgumentException`.
     * It also checks if the provided age is less than `MINIMUM_AGE`, and if it is, it throws an `IllegalArgumentException`. It checks if the provided username is already taken, and if it is, it throws an `AlreadyExistsException`.
     * Then, it creates a new `Customer` object with the provided details and adds the new customer to the list of customers.
     * <p>
     * Here's a breakdown of what each part of the method does:
     * 1. It takes in several parameters: the username, password, name, last name, and age of the customer.
     * 2. It checks if the provided name or last name is empty. If it is, it throws an `IllegalArgumentException`.
     * 3. It checks if the provided username matches the `USERNAME_PATTERN`. If it doesn't, it throws an `IllegalArgumentException`.
     * 4. It checks if the provided password matches the `PASSWORD_PATTERN`. If it doesn't, it throws an `IllegalArgumentException`.
     * 5. It checks if the provided age is less than `MINIMUM_AGE`. If it is, it throws an `IllegalArgumentException`.
     * 6. It checks if the provided username is already taken. If it is, it throws an `AlreadyExistsException`.
     * 7. It creates a new `Customer` object with the provided details.
     * 8. It adds the new customer to the list of customers.
     *
     * @param username The username of the new customer.
     * @param password The password of the new customer.
     * @param name     The name of the new customer.
     * @param lastName The last name of the new customer.
     * @param age      The age of the new customer.
     * @throws IllegalArgumentException If the name or last name is empty, the username does not match the USERNAME_PATTERN, the password does not match the PASSWORD_PATTERN, or the age is less than MINIMUM_AGE.
     * @throws AlreadyExistsException   If the username is already taken.
     */
    public void addCustomer(String username, String password, String name, String lastName, int age) throws AlreadyExistsException {
        if (name.isEmpty() || lastName.isEmpty()) {
            throw new IllegalArgumentException("El nombre y el apellido no pueden estar vacios.");
        }
        if (!username.matches(USERNAME_PATTERN)) {
            throw new IllegalArgumentException("Username invalido. Los usernames tienen que iniciar con una letra y deben contener letras y digitos. Debe tener entre 8 y 30 caracteres.");
        }
        if (!password.matches(PASSWORD_PATTERN)) {
            throw new IllegalArgumentException("Contraseña inválida. Las contraseñas deben contener al menos una letra mayúscula, una letra minúscula, un dígito, un carácter especial y tener al menos 8 caracteres de longitud.");
        }
        if (age < MINIMUM_AGE) {
            throw new IllegalArgumentException("Edad invalida. Debe ser minimo 14.");
        }
        if (customers.stream().anyMatch(customer -> customer.getUsername().equals(username))) {
            throw new AlreadyExistsException("Username invalido.");
        }
        Customer customer = new Customer(username, password, name, lastName, age) {
            @Override
            public void addPlayList(PlayList playList) {
            }

            @Override
            public List<PlayList> getPlayLists() {
                return List.of();
            }

            @Override
            protected List<String> getPlayListIds() {
                return List.of();
            }

            @Override
            public List<UUID> getPlayListsIds() {
                return List.of();
            }
        };
        customers.add(customer);

    }



    /**
     * This method is responsible for searching a customer by their username. It takes in a parameter: the username of the customer,
     * then iterates over the list of customers, and for each customer, it checks if the customer's username matches the provided username.
     * If it does, it returns the customer. If no customer is found with the provided username, it returns null.
     * <p>
     * Here's a breakdown of what each part of the method does:
     * 1. It takes in a parameter: the username of the customer.
     * 2. It iterates over the list of customers.
     * 3. For each customer, it checks if the customer's username matches the provided username. If it does, it returns the customer.
     * 4. If no customer is found with the provided username, it returns null.
     *
     * @param username The username of the customer to search for.
     * @return The Customer object if found, null otherwise.
     */
    public Customer searchCustomerByUsername(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * This method is responsible for logging in a customer with a given username and password. It takes in two parameters: the username and password of the customer,
     * then calls the `searchCustomerByUsername` method to find the customer with the provided username. If the customer is not found, it returns false.
     * If the customer is found, it checks if the provided password matches the customer's password by calling the `checkPassword` method.
     * If the password is correct, it sets the `loggedCustomer` to the found customer. It returns true if the password is correct, false otherwise.
     * <p>
     * Here's a breakdown of what each part of the method does:
     * 1. It takes in two parameters: the username and password of the customer.
     * 2. It calls the `searchCustomerByUsername` method to find the customer with the provided username.
     * 3. If the customer is not found, it returns false.
     * 4. If the customer is found, it checks if the provided password matches the customer's password by calling the `checkPassword` method.
     * 5. If the password is correct, it sets the `loggedCustomer` to the found customer.
     * 6. It returns true if the password is correct, false otherwise.
     *
     * @param username The username of the customer to log in.
     * @param password The password of the customer to log in.
     * @return True if the login was successful, false otherwise.
     */
    public boolean logIn(String username, String password) {

        Customer customer = searchCustomerByUsername(username);

        if (customer == null) {
            return false;
        }

        boolean isPasswordCorrect = checkPassword(customer, password);

        if (isPasswordCorrect) {
            this.loggedCustomer = customer;
        }

        return isPasswordCorrect;

    }

    /**
     * Checks if the given password matches the password of the given customer.
     *
     * @param customer The customer to check the password for.
     * @param password The password to check.
     * @return True if the passwords match, false otherwise.
     */
    private boolean checkPassword(Customer customer, String password) {
        return customer.getPassword().equals(password);
    }

    /**
     * Replaces the current list of customers with the given list.
     *
     * @param customers The new list of customers.
     */
    public void loadCustomers(List<Customer> customers) {
        this.customers = customers;
    }


    /**
     * Checks if a customer is currently logged in.
     * It checks if the loggedCustomer is not null.
     *
     * @return True if a customer is logged in, false otherwise.
     */
    public boolean isCustomerLogged() {
        return this.loggedCustomer != null;
    }

    /**
     * This method is responsible for adding a new playlist to the currently logged in customer. It takes in a parameter: the new playlist to be added.
     * It checks if there is a currently logged in customer. If there isn't, it throws a `WrongLogInException`.
     * If there is a logged in customer, it adds the new playlist to the customer's list of playlists.
     * <p>
     * Here's a breakdown of what each part of the method does:
     * 1. It takes in a parameter: the new playlist to be added.
     * 2. It checks if there is a currently logged in customer. If there isn't, it throws a `WrongLogInException`.
     * 3. If there is a logged in customer, it adds the new playlist to the customer's list of playlists.
     *
     * @param newPlayList The new playlist to add.
     * @throws WrongLogInException If no customer is currently logged in.
     */
    public void addPlayListToLoggedCustomer(PlayList newPlayList) throws WrongLogInException {
        if (this.loggedCustomer == null) {
            throw new WrongLogInException("No hay ningún cliente logeado.");
        }
        this.loggedCustomer.addPlayList(newPlayList);
    }

    /**
     * Retrieves the names of all playlists of the currently logged in customer.
     * <p>
     * It first checks if a customer is currently logged in. If no customer is logged in, it throws a WrongLogInException.
     * If a customer is logged in, it retrieves the list of playlists of the logged in customer.
     * It then initializes an empty list of strings to store the names of the playlists.
     * It iterates over each playlist in the retrieved list of playlists, converts each playlist to a string representation (presumably the name of the playlist), and adds it to the list.
     * Finally, it returns the list, which contains the names of all playlists of the currently logged in customer.
     *
     * @return A list of the names of all playlists of the currently logged in customer.
     * @throws WrongLogInException If no customer is currently logged in.
     */
    public List<String> getLoggedCustomerPlayLists() throws WrongLogInException {
        if (this.loggedCustomer == null) {
            throw new WrongLogInException("No hay ningún cliente conectado.");
        }
        List<PlayList> playLists = this.loggedCustomer.getPlayLists();
        List<String> playListNames = new ArrayList<>();
        for (PlayList playList : playLists) {
            playListNames.add(playList.getName());
        }
        return playListNames;

    }

    /**
     * Makes the currently logged in customer follow the given artist.
     * <p>
     * It first checks if the currently logged in customer is already following the given artist by calling the `followArtist()` method on the `loggedCustomer` object.
     * The `followArtist()` method returns `true` if the artist is already being followed, and `false` otherwise. The result is negated (`!`) to get `true` if the artist is not being followed, and `false` if the artist is being followed. This result is stored in the `artistAlreadyExists` variable.
     * If `artistAlreadyExists` is `true`, which means the artist is already being followed by the customer, it throws an `AlreadyExistsException` with a message indicating that the artist is already being followed by the customer.
     *
     * @param artist The artist to follow.
     * @throws AlreadyExistsException If the artist is already being followed by the customer.
     */
    public void followArtist(Artist artist) throws AlreadyExistsException {
        if (!this.loggedCustomer.followArtist(artist)) {
            throw new AlreadyExistsException("El artista ya es seguido por el cliente");
        }
    }


    /**
     * Returns a list of the names of all customers.
     * It initializes an empty list of strings to store the names of the customers.
     * It iterates over each customer in the customers list, converts each customer to a string representation (presumably the name of the customer), and adds it to the list.
     * Finally, it returns the list, which contains the names of all customers.
     *
     * @return A list of the names of all customers.
     */
    public List<String> getCustomersToString() {
        List<String> customersNames = new ArrayList<>();

        for (Customer customer : customers) {
            customersNames.add(customer.toString());
        }

        return customersNames;
    }


    /**
     * Returns a list of the IDs of all playlists of the customer with the given username.
     * It first calls the `searchCustomerByUsername` method to find the customer with the provided username. If the customer is not found, it returns an empty list.
     * If the customer is found, it retrieves the list of playlist IDs of the found customer and returns it.
     *
     * @param username The username of the customer to get the playlist IDs for.
     * @return A list of the IDs of all playlists of the customer with the given username, or an empty list if no such customer exists.
     */
    public List<UUID> getCustomerPlayListsIds(String username) {
        Customer customer = searchCustomerByUsername(username);
        if (customer == null) {
            return new ArrayList<>();
        }
        return Collections.singletonList(UUID.fromString(username));
    }

    /**
     * Deletes the customer with the given username.
     * It first calls the `searchCustomerByUsername` method to find the customer with the provided username. If the customer is not found, it throws an `IllegalArgumentException`.
     * If the customer is found, it removes the customer from the customers list.
     *
     * @param username The username of the customer to delete.
     * @throws IllegalArgumentException If no customer with the given username exists.
     */
    public void deleteCustomer(String username) {

        Customer customer = searchCustomerByUsername(username);

        if (customer == null) {
            throw new IllegalArgumentException("El cliente no existe");
        }

        this.customers.remove(customer);
    }

    /**
     * Returns a list of the string representation of all artists followed by the currently logged in customer.
     * It first checks if a customer is currently logged in. If no customer is logged in, it throws a `WrongLogInException`.
     * If a customer is logged in, it retrieves the list of followed artists of the logged in customer.
     * It then initializes an empty list of strings to store the names of the artists.
     * It iterates over each artist in the retrieved list of artists, converts each artist to a string representation (presumably the name of the artist), and adds it to the list.
     * Finally, it returns the list, which contains the names of all artists followed by the currently logged in customer.
     *
     * @return A list of the string representation of all artists followed by the currently logged in customer.
     * @throws WrongLogInException If no customer is currently logged in.
     */
    public List<String> getFollowedArtistsByLoggedUser() throws WrongLogInException {
        if (this.loggedCustomer == null) {
            throw new WrongLogInException("No hay ningún cliente conectado.");
        }
        List<Artist> followedArtists = this.loggedCustomer.getFollowedArtists();
        List<String> followedArtistsNames = new ArrayList<>();
        for (Artist artist : followedArtists) {
            followedArtistsNames.add(artist.toString());
        }
        return followedArtistsNames;
    }

    /**
     * Returns a list of all artists followed by all customers.
     * It initializes an empty list of artists to store the followed artists.
     * It iterates over each customer in the customers list, retrieves the list of followed artists of each customer, and adds all artists in the retrieved list to the followed artists list.
     * Finally, it returns the followed artists list, which contains all artists followed by all customers.
     *
     * @return A list of all artists followed by all customers.
     */
    public List<Artist> getAllFollowedArtists() {
        List<Artist> followedArtists = new ArrayList<>();
        for (Customer customer : customers) {
            List<Artist> customerFollowedArtists = customer.getFollowedArtists();
            followedArtists.addAll(customerFollowedArtists);
        }
        return followedArtists;
    }

    /**
     * Logs out the currently logged in customer.
     * It sets the `loggedCustomer` to null.
     */
    public void logOut() {
        this.loggedCustomer = null;
    }

    public List<Artist> getFollowedArtists() {
        return null;
    }

    public boolean isPlayListOwnedByLoggedCustomer(String playListId){
        List<UUID> uuidCustomer = getCustomerPlayListsIds(playListId);
        return uuidCustomer.contains(loggedCustomer.getId());
    }

    public void addCustomer(Customer customer) {
    }

    //endregion
}