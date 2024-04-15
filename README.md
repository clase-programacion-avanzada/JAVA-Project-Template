
# Indice

1. Proyecto Aplicación de Música
   2Paquetes
   - Paquete Models
      - Clase `Artist`
      - Clase `PlayListService`
      - Clase `ReportService`
      - Clase `SongService`
   - Paquete `controllers`
      - Clase `AdminController`
      - Clase `CustomerController`
      - Clase `FileManagementController`
      - Clase `ReportController`
   - Paquete `views`
      - Clase `AdminView`
      - Clase `CustomerView`
      - Clase `FileManagementView`
      - Clase `ReportView`
   - Paquete `exceptions`
      - Clase `AlreadyExistsException`
      - Clase `NotFoundException`
      - Clase `WrongLogInException`
3. Clases sin paquete
   - Clase `Main`
4. Diagrama de clases
5. Entrega del proyecto
   - Funcionalidades
      - Funcionalidades del módulo de administrador
      - Funcionalidades del módulo de cliente
      - Funcionalidades del módulo de gestión de archivos
      - Funcionalidades del módulo de informes
   - Reglas de negocio

# Proyecto Aplicación de Música

A continuación se presenta la documentación del proyecto de una aplicación de música. 
Va a encontrar una explicación detallada de las clases que deben existir en el proyecto.
    Este proyecto no se encuentra terminado, por lo que puede que algunos de los métodos o atributos presentados no estén implementados aún en el código. 
    Su tarea es completar la implementación de las clases y métodos faltantes.



# Paquetes

## Paquete Models

El paquete `Models` contiene las clases que representan las entidades principales de la aplicación de música.
  Una entidad, en el contexto de la programación y el desarrollo de software, se refiere a un objeto o concepto que es identificable. 
  En términos simples, una entidad es una instancia única de un objeto.
    En este programa, las entidades son: Artist, Customer, PlayList y Song.

### Clase `Artist`:

La clase `Artist` representa un artista en una aplicación de música. Es una clase simple de Java que implementa la interfaz `Serializable`, lo que permite que las instancias de esta clase se conviertan en un flujo de bytes y viceversa. Esto es útil para guardar objetos en disco o enviarlos a través de una red.

#### Atributos

La clase `Artist` tiene dos atributos:

1. `id`: Este atributo es una instancia de la clase `UUID`. Se utiliza para identificar de manera única cada instancia de `Artist`.

2. `name`: Este atributo es una cadena que representa el nombre del artista.

#### Constructores

La clase `Artist` tiene dos constructores:

1. `public Artist(UUID id, String name)`: Este constructor crea un objeto `Artist` con el `id` y `name` proporcionados.

2. `public Artist(String name)`: Este constructor crea un objeto `Artist` con el `name` proporcionado. El `id` se genera automáticamente.

#### Métodos

La clase `Artist` tiene varios métodos:

1. `public UUID getId()`: Este método devuelve el `id` del artista.

2. `public String getName()`: Este método devuelve el `name` del artista.

3. `public String toCSV(String separator)`: Este método devuelve una representación CSV del objeto `Artist`. El formato de representación es: `id;name`.

4. `public static Artist fromCSV(String csvLine, String separator)`: Este método crea un objeto `Artist` a partir de una línea CSV. El formato esperado es: `id;name`.

5. `public String toString()`: Este método devuelve una representación de cadena del objeto `Artist`. El formato de representación es: `"Nombre: " + name + " - id: " + id`.

6. `public static Artist GetUnknownArtist(String id)`: Este método devuelve un objeto `Artist` con datos desconocidos. Este método se utiliza cuando el artista no se encuentra en el archivo.

### Clase `Customer`

La clase `Customer` representa un cliente en una aplicación de música. Es una clase simple de Java que implementa la interfaz `Serializable`, lo que permite que las instancias de esta clase se conviertan en un flujo de bytes y viceversa. Esto es útil para guardar objetos en disco o enviarlos a través de una red.

#### Atributos

La clase `Customer` tiene ocho atributos:

1. `id`: Este atributo es una instancia de la clase `UUID`. Se utiliza para identificar de manera única cada instancia de `Customer`.

2. `username`: Este atributo es una cadena que representa el nombre de usuario del cliente.

3. `password`: Este atributo es una cadena que representa la contraseña del cliente.

4. `name`: Este atributo es una cadena que representa el nombre del cliente.

5. `lastName`: Este atributo es una cadena que representa el apellido del cliente.

6. `age`: Este atributo es un entero que representa la edad del cliente.

7. `followedArtists`: Este atributo es un conjunto de objetos `Artist`, que representan los artistas que el cliente está siguiendo.

8. `playLists`: Este atributo es una lista de objetos `PlayList`, que representan las listas de reproducción que el cliente ha creado.

#### Constructores

La clase `Customer` tiene dos constructores:

1. `public Customer(UUID id, String username, String password, String name, String lastName, int age, Set<Artist> followedArtists, List<PlayList> playLists)`: Este constructor crea un objeto `Customer` con el `id`, `username`, `password`, `name`, `lastName`, `age`, `followedArtists` y `playLists` proporcionados.

2. `public Customer(String username, String password, String name, String lastName, int age)`: Este constructor crea un objeto `Customer` con el `username`, `password`, `name`, `lastName` y `age` proporcionados. El `id` se genera automáticamente y los conjuntos `followedArtists` y `playLists` se inicializan como vacíos.

#### Métodos

La clase `Customer` tiene varios métodos:

1. `public UUID getId()`: Este método devuelve el `id` del cliente.

2. `public String getUsername()`: Este método devuelve el `username` del cliente.

3. `public String getPassword()`: Este método devuelve la `password` del cliente.

4. `public String getName()`: Este método devuelve el `name` del cliente.

5. `public void addPlayList(PlayList playList)`: Este método agrega una lista de reproducción a la lista de listas de reproducción del cliente.

6. `public boolean followArtist(Artist artist)`: Este método permite al cliente seguir a un artista.

7. `public List<PlayList> getPlayLists()`: Este método devuelve las listas de reproducción del cliente.

8. `public List<UUID> getPlayListsIds()`: Este método devuelve una lista de los IDs de las listas de reproducción que tiene el cliente.

9. `public List<String> getFollowedArtistToString()`: Este método devuelve una lista de representaciones de cadena de los artistas que el cliente está siguiendo.

10. `public List<Artist> getFollowedArtists()`: Este método devuelve una lista de los artistas que el cliente está siguiendo.

11. `public String toCSV(String separator)`: Este método devuelve una representación CSV del objeto `Customer`. El formato de representación es: `id;username;password;name;lastName;age;{artistId1,artistId2,artistId3};{playListId1,playListId2,playListId3}`.

12. `public String toString()`: Este método devuelve una representación de cadena del objeto `Customer`. El formato de representación es: `"Nombre completo : " + name + " " + lastName + " - username: " + username + " - Edad: " + age + " - Artistas seguidos: " + followedArtists.size() + " - Playlists: " + playLists.size()`.

### Clase PlayList

La clase `PlayList` representa una lista de reproducción en una aplicación de música. Es una clase simple de Java que implementa la interfaz `Serializable`, lo que permite que las instancias de esta clase se conviertan en un flujo de bytes y viceversa. Esto es útil para guardar objetos en disco o enviarlos a través de una red.

#### Atributos

La clase `PlayList` tiene tres atributos:

1. `id`: Este atributo es una instancia de la clase `UUID`. Se utiliza para identificar de manera única cada instancia de `PlayList`.

2. `name`: Este atributo es una cadena que representa el nombre de la lista de reproducción.

3. `songs`: Este atributo es una lista de objetos `Song`, que representan las canciones que están incluidas en la lista de reproducción.

#### Constructores

La clase `PlayList` tiene dos constructores:

1. `public PlayList(UUID id, String name, List<Song> songs)`: Este constructor crea un objeto `PlayList` con el `id`, `name` y `songs` proporcionados.

2. `public PlayList(String name)`: Este constructor crea un objeto `PlayList` con el `name` proporcionado. El `id` se genera automáticamente y se inicializa una lista vacía de canciones.

#### Métodos

La clase `PlayList` tiene varios métodos:

1. `public UUID getId()`: Este método devuelve el `id` de la lista de reproducción.

2. `public String getName()`: Este método devuelve el `name` de la lista de reproducción.

3. `public List<Song> getSongs()`: Este método devuelve una nueva lista de las canciones en la lista de reproducción.

4. `public void addSong(Song song)`: Este método agrega una canción a la lista de reproducción.

5. `public List<String> getSongIds()`: Este método devuelve una lista de los IDs de las canciones en la lista de reproducción.

6. `public boolean removeSong(String songId)`: Este método elimina una canción de la lista de reproducción por su id.

7. `public List<String> getSongsToString()`: Este método devuelve una lista de representaciones de cadena de las canciones en la lista de reproducción.

8. `public String toCSV(String separator)`: Este método devuelve una representación CSV del objeto `PlayList`. El formato de representación es: `id;name;{songId1,songId2,songId3}`.

9. `public String toString()`: Este método devuelve una representación de cadena del objeto `PlayList`. El formato de representación es: `"nombre de la playlist: " + name + " con id: " + id + " y número de canciones: " + songs.size()`.

10. `public static PlayList getUnknownPlayList(String playListId)`: Este método devuelve un objeto `PlayList` con datos desconocidos. Este método se utiliza cuando la lista de reproducción no se encuentra en el archivo.

### Clase Song

La clase `Song` representa una canción en una aplicación de música. Es una clase simple de Java que implementa la interfaz `Serializable`, lo que permite que las instancias de esta clase se conviertan en un flujo de bytes y viceversa. Esto es útil para guardar objetos en disco o enviarlos a través de una red.

#### Atributos

La clase `Song` tiene seis atributos:

1. `id`: Este atributo es una instancia de la clase `UUID`. Se utiliza para identificar de manera única cada instancia de `Song`.

2. `name`: Este atributo es una cadena que representa el nombre de la canción.

3. `artists`: Este atributo es una lista de objetos `Artist`, que representan los artistas que están asociados con la canción.

4. `genre`: Este atributo es una cadena que representa el género de la canción.

5. `durationInSeconds`: Este atributo es un entero que representa la duración de la canción en segundos.

6. `album`: Este atributo es una cadena que representa el álbum de la canción.

#### Constructores

La clase `Song` tiene dos constructores:

1. `public Song(UUID id, String name, List<Artist> artists, String genre, int durationInSeconds, String album)`: Este constructor crea un objeto `Song` con el `id`, `name`, `artists`, `genre`, `durationInSeconds` y `album` proporcionados.

2. `public Song(String name, String genre, int durationInSeconds, String album)`: Este constructor crea un objeto `Song` con el `name`, `genre`, `durationInSeconds` y `album` proporcionados. El `id` se genera automáticamente y se inicializa una lista vacía de artistas.

#### Métodos

La clase `Song` tiene varios métodos:

1. `public UUID getId()`: Este método devuelve el `id` de la canción.

2. `public String getName()`: Este método devuelve el `name` de la canción.

3. `public List<Artist> getArtists()`: Este método devuelve una nueva lista de los artistas en la canción.

4. `public void addArtists(List<Artist> artists)`: Este método agrega una lista de artistas a la canción.

5. `private List<String> getArtistIds()`: Este método devuelve una lista de los IDs de los artistas en la canción.

6. `public String toCSV(String separator)`: Este método devuelve una representación CSV del objeto `Song`. El formato de representación es: `id;name;{artistId1,artistId2,artistId3};genre;durationInSeconds;album`.

7. `public String toString()`: Este método devuelve una representación de cadena del objeto `Song`. El formato de representación es: `"Nombre: " + name + " - Artistas: " + artistNames + " - Genero: " + genre + " - Duración en segundos: " + durationInSeconds + " - Album: " + album + " - ID: " + id`.

8. `public static Song getUnknownSong(String songId)`: Este método devuelve un objeto `Song` con datos desconocidos. Este método se utiliza cuando la canción no se encuentra en el archivo.

## Paquete `services`

El paquete Services contiene las clases que implementan la lógica de negocio de la aplicación de música.

La lógica de negocio, también conocida como lógica de dominio, es la parte del programa que determina las reglas de negocio, 
que son las directrices, cálculos y operaciones que transforman los datos en información útil para una empresa o usuario.

En el contexto de una aplicación de música, la lógica de negocio podría incluir operaciones como agregar una nueva canción, eliminar una canción, crear una nueva lista de reproducción, seguir a un artista, entre otros. 
    Estas operaciones son implementadas en las clases de servicio, como `ArtistService`, `CustomerService`, `PlayListService`, `SongService`, `FileManagementService` y `ReportService`.

Por ejemplo, la clase `ArtistService` implementa la lógica de negocio relacionada con los artistas, proporcionando métodos para agregar, obtener y eliminar artistas, 
    así como para obtener información relacionada con los artistas. De manera similar, la clase `CustomerService` implementa la lógica de negocio relacionada con los clientes, proporcionando métodos para agregar, obtener y eliminar clientes, así como para manejar la autenticación y la información relacionada con los clientes.

### Clase ArtistService

La clase `ArtistService` es parte de una aplicación de música y es responsable de administrar los artistas. Proporciona una variedad de métodos para manejar operaciones relacionadas con los artistas.

#### Atributos

La clase `ArtistService` tiene un atributo:

1. `artists`: Este atributo es una lista de objetos `Artist`, que representan los artistos que están siendo administrados por el servicio.

#### Constructores

La clase `ArtistService` tiene un constructor:

1. `public ArtistService()`: Este constructor crea un objeto `ArtistService` e inicializa la lista de artistos como un ArrayList vacío.

#### Métodos

La clase `ArtistService` tiene varios métodos:

1. `public List<Artist> getArtists()`: Este método devuelve una nueva lista de los artistos en el servicio.

2. `public void loadArtists(List<Artist> artists)`: Este método reemplaza la lista actual de artistos con la lista proporcionada.

3. `public Map<String, Artist> getMapOfArtistsById()`: Este método devuelve un mapa de artistos por sus IDs.

4. `public List<String> getArtistsToString()`: Este método devuelve una lista de los nombres de todos los artistos.

5. `public Artist searchArtistById(String artistId)`: Este método busca un artista por su ID.

6. `public void addArtist(String name)`: Este método agrega un nuevo artista a la lista de artistos.

7. `public Artist searchArtistByName(String name)`: Este método busca un artista por su nombre.

8. `public List<Artist> getArtistsByIds(Set<String> artists)`: Este método recupera una lista de artistos por sus IDs.

9. `public void deleteArtist(String artistId)`: Este método elimina un artista por su ID.

### Clase CustomerService

La clase `CustomerService` es parte de una aplicación de música y es responsable de administrar los clientes. Proporciona una variedad de métodos para manejar operaciones relacionadas con los clientes.

#### Atributos

La clase `CustomerService` tiene dos atributos:

1. `customers`: Este atributo es una lista de objetos `Customer`, que representan los clientes que están siendo administrados por el servicio.

2. `loggedCustomer`: Este atributo es un objeto `Customer` que representa al cliente que ha iniciado sesión actualmente.

#### Constructores

La clase `CustomerService` tiene un constructor:

1. `public CustomerService()`: Este constructor crea un objeto `CustomerService` e inicializa la lista de clientes como un ArrayList vacío.

#### Métodos

La clase `CustomerService` tiene varios métodos:

1. `public List<Customer> getCustomers()`: Este método devuelve una nueva lista de los clientes en el servicio.

2. `public void loadCustomers(List<Customer> customers)`: Este método reemplaza la lista actual de clientes con la lista proporcionada.

3. `public boolean logIn(String username, String password)`: Este método inicia sesión de un cliente con un nombre de usuario y contraseña dados.

4. `public void addCustomer(String username, String password, String name, String lastName, int age)`: Este método agrega un nuevo cliente a la lista de clientes.

5. `public Customer searchCustomerByUsername(String username)`: Este método busca un cliente por su nombre de usuario.

6. `public void addPlayListToLoggedCustomer(PlayList newPlayList)`: Este método agrega una nueva lista de reproducción al cliente que ha iniciado sesión actualmente.

7. `public List<String> getLoggedCustomerPlayLists()`: Este método recupera los nombres de todas las listas de reproducción del cliente que ha iniciado sesión actualmente.

8. `public void followArtist(Artist artist)`: Este método hace que el cliente que ha iniciado sesión actualmente siga al artista dado.

9. `public List<String> getCustomersToString()`: Este método devuelve una lista de los nombres de todos los clientes.

10. `public List<UUID> getCustomerPlayListsIds(String username)`: Este método devuelve una lista de los IDs de todas las listas de reproducción del cliente con el nombre de usuario dado.

11. `public void deleteCustomer(String username)`: Este método elimina al cliente con el nombre de usuario dado.

12. `public List<String> getFollowedArtistsByLoggedUser()`: Este método devuelve una lista de la representación de cadena de todos los artistas seguidos por el cliente que ha iniciado sesión actualmente.

13. `public List<Artist> getAllFollowedArtists()`: Este método devuelve una lista de todos los artistas seguidos por todos los clientes.

14. `public void logOut()`: Este método cierra la sesión del cliente que ha iniciado sesión actualmente.

### Clase FileManagementService

La clase `FileManagementService` es parte de una aplicación de música y es responsable de la gestión de archivos. Proporciona una variedad de métodos para manejar operaciones relacionadas con la importación y exportación de datos.

#### Métodos

La clase `FileManagementService` tiene varios métodos:

1. `public List<Artist> importArtistsFromCSV(String path, String separator,String artistsFileName)`: Este método importa una lista de artistas desde un archivo CSV.

2. `public List<Song> importSongsFromCSV(String path, String separator, String songsFileName, Map<String,Artist> artistsById)`: Este método importa una lista de canciones desde un archivo CSV.

3. `public List<PlayList> importPlayListsFromCSV(String path, String separator, String playListsFileName, Map<String, Song> songsById)`: Este método importa una lista de listas de reproducción desde un archivo CSV.

4. `public List<Customer> importCustomersFromCSV(String path, String separator, String customersCSVFileName, Map<String, Artist> artistsById, Map<String, PlayList> playListById)`: Este método importa una lista de clientes desde un archivo CSV.

5. `private List<String> extractIds(String ids)`: Este método extrae IDs de una cadena y los devuelve como una lista.

6. `public void exportArtistsToCSV(String defaultPath, String separator, String defaultArtistsFileName, List<Artist> artists)`: Este método exporta una lista de artistas a un archivo CSV.

7. `public void exportSongsToCSV(String defaultPath, String separator, String defaultSongsFileName, List<Song> songs)`: Este método exporta una lista de canciones a un archivo CSV.

8. `public void exportPlayListsToCSV(String path, String separator, String playListsCSVFileName, List<PlayList> playLists)`: Este método exporta una lista de listas de reproducción a un archivo CSV.

9. `public void exportCustomersToCSV(String path, String separator, String customersCSVFileName, List<Customer> customers)`: Este método exporta una lista de clientes a un archivo CSV.

10. `private void writeTextFile(String path, List<String> linesToWrite)`: Este método escribe una lista de cadenas en un archivo de texto.

11. `public List<Artist> importArtistsFromBinary(String path, String artistsFileName)`: Este método importa una lista de artistas desde un archivo binario.

12. `public List<Song> importSongsFromBinary(String path, String songsFileName)`: Este método importa una lista de canciones desde un archivo binario.

13. `public List<PlayList> importPlayListsFromBinary(String path, String playListsFileName)`: Este método importa una lista de listas de reproducción desde un archivo binario.

14. `public List<Customer> importCustomersFromBinary(String path, String customersFileName)`: Este método importa una lista de clientes desde un archivo binario.

15. `public <T> List<T> importObjectsFromBinary(String path, String fileName)`: Este método importa una lista de objetos desde un archivo binario.

16. `public void exportArtistsToBinary(String defaultPath, String defaultArtistsFileName, List<Artist> artists)`: Este método exporta una lista de artistas a un archivo binario.

17. `public void exportSongsToBinary(String path, String songsFileName, List<Song> songs)`: Este método exporta una lista de canciones a un archivo binario.

18. `public void exportPlayListsToBinary(String path, String playListsFileName, List<PlayList> playLists)`: Este método exporta una lista de listas de reproducción a un archivo binario.

19. `public void exportCustomersToBinary(String path, String customersFileName, List<Customer> customers)`: Este método exporta una lista de clientes a un archivo binario.

### Clase PlayListService

La clase `PlayListService` es parte de una aplicación de música y es responsable de administrar las listas de reproducción. Proporciona una variedad de métodos para manejar operaciones relacionadas con las listas de reproducción.

#### Atributos

La clase `PlayListService` tiene un atributo:

1. `playLists`: Este atributo es una lista de objetos `PlayList`, que representan las listas de reproducción que están siendo administradas por el servicio.

#### Constructores

La clase `PlayListService` tiene un constructor:

1. `public PlayListService()`: Este constructor crea un objeto `PlayListService` e inicializa la lista de listas de reproducción como un ArrayList vacío.

#### Métodos

La clase `PlayListService` tiene varios métodos:

1. `public List<PlayList> getPlayLists()`: Este método devuelve una nueva lista de las listas de reproducción en el servicio.

2. `public PlayList addPlayList(String name)`: Este método agrega una nueva lista de reproducción al servicio.

3. `public void loadPlayLists(List<PlayList> playLists)`: Este método reemplaza la lista actual de listas de reproducción con la lista proporcionada.

4. `public PlayList getPlayListById(String id)`: Este método busca una lista de reproducción por su ID.

5. `public Map<String, PlayList> getPlayListsById()`: Este método devuelve un mapa de listas de reproducción por sus IDs.

6. `public boolean deleteSongFromPlayList(String playListId, String songId)`: Este método elimina una canción de una lista de reproducción por su ID.

7. `public void deletePlayLists(List<UUID> playListsIds)`: Este método elimina listas de reproducción con los IDs dados.

8. `public void deleteSongFromPlayLists(String songId)`: Este método elimina una canción de todas las listas de reproducción.

9. `public List<String> getPlayListsToString()`: Este método devuelve una lista de las representaciones de cadena de todas las listas de reproducción.

10. `public List<Song> getAllSongsInPlayLists()`: Este método devuelve una lista de todas las canciones en todas las listas de reproducción.

### Clase ReportService

La clase `ReportService` es parte de una aplicación de música y es responsable de generar informes. Proporciona una variedad de métodos para manejar operaciones relacionadas con la generación de informes.

#### Métodos

La clase `ReportService` tiene varios métodos:

1. `public Map<String, Integer> getMostFollowedArtists(List<Artist> followedArtists)`: Este método devuelve un mapa de nombres de artistas a la cantidad de veces que son seguidos.

2. `public UUID maxSong(Map<UUID, Integer> mostAddedSongs)`: Este método devuelve el ID de la canción que tiene el recuento máximo en el mapa dado.

3. `public Map<UUID, Integer> getCountOfSongsByArtist(List<Song> songsByArtist)`: Este método devuelve un mapa de IDs de canciones a la cantidad de veces que aparecen en la lista dada.

### Clase SongService

La clase `SongService` es parte de una aplicación de música y es responsable de administrar las canciones. Proporciona una variedad de métodos para manejar operaciones relacionadas con las canciones.

#### Atributos

La clase `SongService` tiene un atributo:

1. `songs`: Este atributo es una lista de objetos `Song`, que representan las canciones que están siendo administradas por el servicio.

#### Constructores

La clase `SongService` tiene un constructor:

1. `public SongService()`: Este constructor crea un objeto `SongService` e inicializa la lista de canciones como un ArrayList vacío.

#### Métodos

La clase `SongService` tiene varios métodos:

1. `public List<Song> getSongs()`: Este método devuelve una nueva lista de las canciones en el servicio.

2. `public void addSong(String name, String genre, int durationInSeconds, String album, List<Artist> artistsList)`: Este método agrega una nueva canción al servicio.

3. `public Song searchSongById(String id)`: Este método busca una canción por su ID.

4. `public void loadSongs(List<Song> songs)`: Este método reemplaza la lista actual de canciones con la lista proporcionada.

5. `public Map<String, Song> getSongsById()`: Este método devuelve un mapa de canciones por sus IDs.

6. `public List<String> getSongsToString()`: Este método devuelve una lista de las representaciones de cadena de todas las canciones.

7. `public void deleteSong(String songId)`: Este método elimina una canción por su ID.

8. `public List<Song> searchSongsByArtistId(String artistId)`: Este método devuelve una lista de canciones por el ID del artista.

## Paquete `controllers`

El paquete Controllers contiene las clases que coordinan las acciones entre el modelo y la vista en la aplicación de música. Estas clases son `AdminController`, `CustomerController`, `FileManagementController`, y `ReportController`.

### Clase AdminController

La clase `AdminController` es parte de una aplicación de música y es responsable de manejar tareas administrativas en la aplicación. Proporciona una variedad de métodos para manejar operaciones relacionadas con artistas, listas de reproducción, clientes y canciones.

#### Atributos

La clase `AdminController` tiene cuatro atributos:

1. `artistService`: Esta es una instancia de `ArtistService` utilizada por este controlador.

2. `playListService`: Esta es una instancia de `PlayListService` utilizada por este controlador.

3. `customerService`: Esta es una instancia de `CustomerService` utilizada por este controlador.

4. `songService`: Esta es una instancia de `SongService` utilizada por este controlador.

#### Constructores

La clase `AdminController` tiene un constructor:

1. `public AdminController(ArtistService artistService, PlayListService playListService, CustomerService customerService, SongService songService)`: Este constructor crea un objeto `AdminController` e inicializa las instancias de los servicios con los proporcionados.

#### Métodos

La clase `AdminController` tiene varios métodos:

1. `public void addArtistToDatabase(String name)`: Este método es responsable de agregar un artista a la base de datos.

2. `public List<String> getAllArtists()`: Este método recupera todos los artistas de la base de datos.

3. `public List<String> getAllSongs()`: Este método recupera todas las canciones de la base de datos.

4. `public List<String> getAllCustomers()`: Este método recupera todos los clientes de la base de datos.

5. `public void addCustomerToDatabase(String username, String password, String name, String lastName, int age)`: Este método es responsable de agregar un nuevo cliente a la base de datos.

6. `public void deleteCustomerFromDatabase(String username)`: Este método es responsable de eliminar un cliente de la base de datos.

7. `public void addSongToDatabase(String name, String genre, int duration, String album, Set<String> artists)`: Este método es responsable de agregar una canción a la base de datos.

8. `public static void validateSongAttributes(String name, String genre, int duration)`: Este método es responsable de validar los atributos de una canción.

9. `public void deleteSongFromDatabase(String songId)`: Este método es responsable de eliminar una canción de la base de datos.

10. `public void deleteArtistFromDatabase(String artistId)`: Este método es responsable de eliminar un artista de la base de datos.

11. `public List<String> getAllPlaylists()`: Este método recupera todas las listas de reproducción de la base de datos.

### Clase CustomerController

La clase `CustomerController` es parte de una aplicación de música y es responsable de manejar tareas relacionadas con los clientes en la aplicación. Proporciona una variedad de métodos para manejar operaciones relacionadas con artistas, listas de reproducción y canciones.

#### Atributos

La clase `CustomerController` tiene cuatro atributos:

1. `customerService`: Esta es una instancia de `CustomerService` utilizada por este controlador.

2. `artistService`: Esta es una instancia de `ArtistService` utilizada por este controlador.

3. `playListService`: Esta es una instancia de `PlayListService` utilizada por este controlador.

4. `songService`: Esta es una instancia de `SongService` utilizada por este controlador.

#### Constructores

La clase `CustomerController` tiene un constructor:

1. `public CustomerController(CustomerService customerService, ArtistService artistService, PlayListService playListService, SongService songService)`: Este constructor crea un objeto `CustomerController` e inicializa las instancias de los servicios con los proporcionados.

#### Métodos

La clase `CustomerController` tiene varios métodos:

1. `public boolean logIn(String username, String password)`: Este método es responsable de iniciar sesión de un cliente.

2. `public void addNewPlayList(String playListName)`: Este método es responsable de agregar una nueva lista de reproducción para el cliente actualmente conectado.

3. `public List<String> getLoggedCustomerPlaylists()`: Este método recupera las listas de reproducción del cliente actualmente conectado.

4. `public List<String> getAllSongs()`: Este método recupera todas las canciones.

5. `public void addSongToPlayList(String playListId, String songId)`: Este método es responsable de agregar una canción a una lista de reproducción.

6. `public List<String> getAllSongsFromPlayList(String playListId)`: Este método es responsable de recuperar todas las canciones de una lista de reproducción específica.

7. `public boolean deleteSongFromPlayList(String playListId, String songId)`: Este método es responsable de eliminar una canción de una lista de reproducción.

8. `public List<String> getAllArtists()`: Este método recupera todos los artistas.

9. `public void followArtist(String artistId)`: Este método es responsable de permitir a un cliente seguir a un artista.

10. `public List<String> getFollowedArtists()`: Este método es responsable de recuperar los artistas seguidos por el cliente actualmente conectado.

11. `public void logOut()`: Este método es responsable de cerrar la sesión del cliente actualmente conectado.

### Clase FileManagementController

La clase `FileManagementController` es parte de una aplicación de música y es responsable de manejar tareas relacionadas con la gestión de archivos en la aplicación. Proporciona una variedad de métodos para manejar operaciones relacionadas con artistas, canciones, listas de reproducción y clientes.

#### Atributos

La clase `FileManagementController` tiene cinco atributos:

1. `fileManagementService`: Esta es una instancia de `FileManagementService` utilizada por este controlador.

2. `artistService`: Esta es una instancia de `ArtistService` utilizada por este controlador.

3. `songService`: Esta es una instancia de `SongService` utilizada por este controlador.

4. `customerService`: Esta es una instancia de `CustomerService` utilizada por este controlador.

5. `playListService`: Esta es una instancia de `PlayListService` utilizada por este controlador.

#### Constructores

La clase `FileManagementController` tiene un constructor:

1. `public FileManagementController(FileManagementService fileManagementService, ArtistService artistService, SongService songService, CustomerService customerService, PlayListService playListService)`: Este constructor crea un objeto `FileManagementController` e inicializa las instancias de los servicios con los proporcionados.

#### Métodos

La clase `FileManagementController` tiene varios métodos:

1. `public void importCSVFiles(String path, String separator, String extension, String artistsFileName, String songsFileName, String playListsFileName, String customersFileName)`: Este método es responsable de importar datos desde archivos CSV.

2. `public void exportCSVFiles(String path, String separator, String extension, String artistsFileName, String customersFileName, String playListsFileName, String songsFileName)`: Este método es responsable de exportar datos a archivos CSV.

3. `public void saveSpotifyFiles(String path, String extension, String artistsFileName, String songsFileName, String playListsFileName, String customersFileName)`: Este método es responsable de guardar datos en archivos binarios.

4. `public void loadSpotifyFiles(String path, String extension, String artistsFileName, String songsFileName, String playListsFileName, String customersFileName)`: Este método es responsable de cargar datos desde archivos binarios.

### Clase ReportController

La clase `ReportController` es parte de una aplicación de música y es responsable de manejar tareas relacionadas con los informes en la aplicación. Proporciona una variedad de métodos para manejar operaciones relacionadas con artistas, canciones, listas de reproducción y clientes.

#### Atributos

La clase `ReportController` tiene cinco atributos:

1. `reportService`: Esta es una instancia de `ReportService` utilizada por este controlador.

2. `artistService`: Esta es una instancia de `ArtistService` utilizada por este controlador.

3. `songService`: Esta es una instancia de `SongService` utilizada por este controlador.

4. `customerService`: Esta es una instancia de `CustomerService` utilizada por este controlador.

5. `playListService`: Esta es una instancia de `PlayListService` utilizada por este controlador.

#### Constructores

La clase `ReportController` tiene un constructor:

1. `public ReportController(ReportService reportService, ArtistService artistService, SongService songService, CustomerService customerService, PlayListService playListService)`: Este constructor crea un objeto `ReportController` e inicializa las instancias de los servicios con los proporcionados.

#### Métodos

La clase `ReportController` tiene varios métodos:

1. `public Map<String, Integer> showMostFollowedArtists()`: Este método es responsable de mostrar los artistas más seguidos.

2. `public String showMostAddedSongInPlayList()`: Este método es responsable de mostrar la canción más agregada en las listas de reproducción.

3. `public List<String> getAllArtists()`: Este método recupera todos los artistas.

4. `public String showMostAddedSongOfArtist(String artistId)`: Este método es responsable de mostrar la canción más agregada de un artista específico en las listas de reproducción.

## Paquete `views`

El paquete Views contiene las clases que se encargan de la interacción con el usuario en la aplicación de música. Estas clases son `AdminView`, `CustomerView`, `FileManagementView`, y `ReportView`.

### Clase AdminView

La clase `AdminView` es parte de una aplicación de música y es responsable de proporcionar una interfaz de usuario para interactuar con la aplicación desde una perspectiva administrativa. Proporciona una variedad de métodos para manejar operaciones relacionadas con artistas, canciones, clientes y listas de reproducción.

#### Atributos

La clase `AdminView` tiene dos atributos:

1. `adminController`: Esta es una instancia de `AdminController` utilizada por esta vista.

2. `scanner`: Esta es una instancia de `Scanner` utilizada para leer la entrada del usuario desde la consola.

#### Constructores

La clase `AdminView` tiene un constructor:

1. `public AdminView(AdminController adminController, Scanner scanner)`: Este constructor crea un objeto `AdminView` e inicializa las instancias de `AdminController` y `Scanner` con los proporcionados.

#### Métodos

La clase `AdminView` tiene varios métodos:

1. `public void showView()`: Este método es responsable de mostrar el menú al usuario y manejar la selección del usuario.

2. `private void showAllPlaylists()`: Este método es responsable de mostrar todas las listas de reproducción al usuario.

3. `private void showAllArtists()`: Este método es responsable de mostrar todos los artistas al usuario.

4. `private void showAllSongs()`: Este método es responsable de mostrar todas las canciones al usuario.

5. `private void showAllCustomers()`: Este método es responsable de mostrar todos los clientes al usuario.

6. `private void deleteCustomerFromDatabase()`: Este método es responsable de eliminar un cliente y todas sus listas de reproducción de la base de datos.

7. `private void addCustomerToDatabase()`: Este método es responsable de crear un nuevo cliente en la base de datos.

8. `private void deleteSongFromDatabase()`: Este método es responsable de eliminar una canción de la base de datos y cualquier lista de reproducción en la que exista.

9. `private void addSongToDatabase()`: Este método es responsable de crear una nueva canción en la base de datos.

10. `private void deleteArtistFromDatabase()`: Este método es responsable de eliminar un artista de la base de datos, cualquier canción con la que estén asociados, y eliminar esas canciones de cualquier lista de reproducción en la que existan.

11. `private void addArtistToDatabase()`: Este método es responsable de crear un nuevo artista en la base de datos.

12. `private void printMenu()`: Este método es responsable de mostrar el menú al usuario.

### Clase CustomerView

La clase `CustomerView` es parte de una aplicación de música y es responsable de proporcionar una interfaz de usuario para interactuar con la aplicación desde la perspectiva de un cliente. Proporciona una variedad de métodos para manejar operaciones relacionadas con listas de reproducción, canciones y artistas.

#### Atributos

La clase `CustomerView` tiene dos atributos:

1. `customerController`: Esta es una instancia de `CustomerController` utilizada por esta vista.

2. `scanner`: Esta es una instancia de `Scanner` utilizada para leer la entrada del usuario desde la consola.

#### Constructores

La clase `CustomerView` tiene un constructor:

1. `public CustomerView(CustomerController customerController, Scanner scanner)`: Este constructor crea un objeto `CustomerView` e inicializa las instancias de `CustomerController` y `Scanner` con los proporcionados.

#### Métodos

La clase `CustomerView` tiene varios métodos:

1. `public void showView()`: Este método es responsable de mostrar el menú al usuario y manejar la selección del usuario.

2. `private void seeFollowedArtists()`: Este método es responsable de mostrar los artistas seguidos por el usuario.

3. `private void followArtist()`: Este método es responsable de permitir al usuario seguir a un artista.

4. `private void seeAllSongsFromPlayList()`: Este método es responsable de mostrar todas las canciones de una lista de reproducción específica.

5. `private String getPlayListId(String header)`: Este método es responsable de obtener el ID de una lista de reproducción.

6. `private void deleteSongFromPlayList()`: Este método es responsable de eliminar una canción de una lista de reproducción.

7. `private void showSongsInPlayList(String playListId)`: Este método es responsable de mostrar todas las canciones en una lista de reproducción específica.

8. `private void addNewSongToPlayList()`: Este método es responsable de agregar una nueva canción a una lista de reproducción.

9. `private String getSongId(String header)`: Este método es responsable de obtener el ID de una canción.

10. `private void showAllArtists()`: Este método es responsable de mostrar todos los artistas.

11. `private void showAllSongs()`: Este método es responsable de mostrar todas las canciones.

12. `private void showMyPlayLists()`: Este método es responsable de mostrar todas las listas de reproducción del usuario.

13. `private void addNewPlayList()`: Este método es responsable de crear una nueva lista de reproducción.

14. `public void printMenu()`: Este método es responsable de mostrar el menú al usuario.

15. `public boolean logIn()`: Este método es responsable de iniciar sesión del usuario.

### Clase FileManagementView

La clase `FileManagementView` es parte de una aplicación de música y es responsable de proporcionar una interfaz de usuario para interactuar con la aplicación desde la perspectiva de la gestión de archivos. Proporciona una variedad de métodos para manejar operaciones relacionadas con la importación y exportación de archivos.

#### Atributos

La clase `FileManagementView` tiene varios atributos:

1. `fileManagementController`: Esta es una instancia de `FileManagementController` utilizada por esta vista.

2. `scanner`: Esta es una instancia de `Scanner` utilizada para leer la entrada del usuario desde la consola.

3. `defaultPath`: Esta es una cadena que representa la ruta predeterminada para los archivos.

4. `defaultArtistsFileName`: Esta es una cadena que representa el nombre del archivo de artistas por defecto.

5. `defaultCustomersFileName`: Esta es una cadena que representa el nombre del archivo de clientes por defecto.

6. `defaultPlayListsFileName`: Esta es una cadena que representa el nombre del archivo de listas de reproducción por defecto.

7. `defaultSongsFileName`: Esta es una cadena que representa el nombre del archivo de canciones por defecto.

8. `defaultCSVSeparator`: Esta es una cadena que representa el separador CSV por defecto.

#### Constructores

La clase `FileManagementView` tiene un constructor:

1. `public FileManagementView(FileManagementController fileManagementController, Scanner scanner, String defaultPath, String defaultArtistsFileName, String defaultCustomersFileName, String defaultPlayListsFileName, String defaultSongsFileName)`: Este constructor crea un objeto `FileManagementView` e inicializa las instancias de `FileManagementController`, `Scanner` y las cadenas con los proporcionados.

#### Métodos

La clase `FileManagementView` tiene varios métodos:

1. `public void showView()`: Este método es responsable de mostrar el menú al usuario y manejar la selección del usuario.

2. `private void saveSpotifyFiles()`: Este método es responsable de guardar datos en archivos binarios.

3. `private void loadSpotifyFiles()`: Este método es responsable de cargar datos desde archivos binarios.

4. `private void exportCSVFiles()`: Este método es responsable de exportar datos a archivos CSV.

5. `private void importCSVFiles()`: Este método es responsable de importar datos desde archivos CSV.

6. `public void printMenu()`: Este método es responsable de mostrar el menú al usuario.

### Clase ReportView

La clase `ReportView` es parte de una aplicación de música y es responsable de proporcionar una interfaz de usuario para interactuar con la aplicación desde la perspectiva de los informes. Proporciona una variedad de métodos para manejar operaciones relacionadas con la visualización de informes de artistas, canciones y listas de reproducción.

#### Atributos

La clase `ReportView` tiene dos atributos:

1. `reportController`: Esta es una instancia de `ReportController` utilizada por esta vista.

2. `scanner`: Esta es una instancia de `Scanner` utilizada para leer la entrada del usuario desde la consola.

#### Constructores

La clase `ReportView` tiene un constructor:

1. `public ReportView(ReportController reportController, Scanner scanner)`: Este constructor crea un objeto `ReportView` e inicializa las instancias de `ReportController` y `Scanner` con los proporcionados.

#### Métodos

La clase `ReportView` tiene varios métodos:

1. `public void showView()`: Este método es responsable de mostrar el menú al usuario y manejar la selección del usuario.

2. `private void showMostAddedSongByArtist()`: Este método es responsable de mostrar la canción más agregada por un artista específico.

3. `private void showAllArtists()`: Este método es responsable de mostrar todos los artistas.

4. `private void showMostAddedSong()`: Este método es responsable de mostrar la canción más agregada.

5. `private void showMostFollowedArtists()`: Este método es responsable de mostrar los artistas más seguidos.

6. `private void printMenu()`: Este método es responsable de mostrar el menú al usuario.


## Paquete `exceptions`

El paquete Exceptions contiene las clases que representan excepciones personalizadas que pueden ser lanzadas por la aplicación de música. Estas clases son `AlreadyExistsException`, `NotFoundException`, y `WrongLogInException`.

### Clase AlreadyExistsException

La clase `AlreadyExistsException` es una excepción personalizada que se lanza cuando se intenta crear una entidad que ya existe. Esto podría usarse, por ejemplo, al intentar crear un nuevo usuario con un nombre de usuario que ya está en uso.

#### Constructores

La clase `AlreadyExistsException` tiene un constructor:

1. `public AlreadyExistsException(String message)`: Este constructor crea un objeto `AlreadyExistsException` e inicializa el mensaje de detalle con el proporcionado.

### Clase NotFoundException

La clase `NotFoundException` es una excepción personalizada que se lanza cuando una entidad no se encuentra. Esto podría usarse, por ejemplo, al intentar recuperar un usuario, una canción, una lista de reproducción o un artista por su ID y no se encuentra ninguna coincidencia.

#### Constructores

La clase `NotFoundException` tiene un constructor:

1. `public NotFoundException(String message)`: Este constructor crea un objeto `NotFoundException` e inicializa el mensaje de detalle con el proporcionado.

### Clase WrongLogInException

La clase `WrongLogInException` es una excepción personalizada que se lanza cuando un intento de inicio de sesión falla. Esto podría usarse, por ejemplo, cuando un usuario intenta iniciar sesión con credenciales incorrectas.

#### Constructores

La clase `WrongLogInException` tiene un constructor:

1. `public WrongLogInException(String message)`: Este constructor crea un objeto `WrongLogInException` e inicializa el mensaje de detalle con el proporcionado.

Por favor, hágamelo saber si necesita más detalles sobre alguna parte específica de la clase `WrongLogInException`.

## Clases sin paquete

### Clase Main

La clase `Main` es la clase principal de una aplicación de música y es responsable de iniciar la aplicación y manejar la interacción del usuario con los diferentes módulos de la aplicación.

#### Métodos

La clase `Main` tiene dos métodos:

1. `public static void main(String[] args)`: Este es el método principal que se ejecuta al iniciar la aplicación. Crea instancias de los servicios, controladores y vistas necesarios, y luego entra en un bucle que muestra un menú al usuario y maneja la selección del usuario.

2. `private static void printMenu()`: Este método es responsable de mostrar el menú principal al usuario.

El método `main` realiza las siguientes operaciones:

1. Crea instancias de las clases de servicio que contienen la lógica de negocio de la aplicación.

2. Crea instancias de los controladores, proporcionándoles las instancias de los servicios que necesitan para realizar sus tareas.

3. Crea instancias de las vistas, proporcionándoles las instancias de los controladores que necesitan para enviar la entrada del usuario.

4. Entra en un bucle que muestra un menú al usuario y maneja la selección del usuario. El bucle continúa hasta que el usuario selecciona la opción para salir.

5. Maneja las excepciones que pueden ocurrir durante la ejecución de la aplicación.

# Diagrama de clases
A continuación encontrará el diagrama de clases del proyecto, si requiere verlo en mayor resolución, puede hacer click en la imagen
ir al link [Diagrama de clases](https://lucid.app/lucidchart/298a94ce-6504-41f0-a367-01e606486ff0/edit?view_items=JMVp7G3cWYrz%2C9i~BzpAiBi-4%2CGXal3yoJy9jv%2COValCZBWjaIm%2CK.1sMn_5G0x-%2CM.1sjBUAHh4E%2Cy1BvXFmBOj7-%2CnKUy7q_hVzLD%2Cuj~B1h1L6k-z%2CR7BvY6Hr2SI0%2C4_BvAkupP3Eg%2Cw.BvnFzXGqbg%2C2R.BReD4CuyR%2CiW1sBcwpB1o1%2C651s.DJYikHn%2Cl61sVv~dLGma%2CZ.1syjS7mY9H%2CQ.1sOlCkrcE7%2CV_BvIDvgz3Rf%2CD.Bvxy.5C7TI%2CuNVpsrw3NNPZ%2Csj~Bvte8vT2Q%2CDOgpFEMn0EXV%2COOgp2d40bZCt%2CoPVp--1QFZtn%2CQF1sEcFOoe0e%2CYi2sGEgl_4.D%2CMj2s8Ct3foVu%2Cdj2sQ74laLB4%2Chj2sE~ZD6p6T%2CHMVpvzkUTjjU%2CJWaljbRARq8D%2Cd~BvF9LYD1dQ%2CEWalfLD9InSw%2CV.BvlSalsy.D%2Cvg2s_-oGNXrh%2CQWalbjrGeCir%2CE81sctryFE0B%2Cxf2s9fOjhIrX%2Cza2s_13XE.e9%2CXc2sdnruMV4e&invitationId=inv_b404a1ff-43d1-445e-8c14-c9fc136cb86d)
![Diagrama de clases](assets/Class-diagram.png)

# Entrega del proyecto

Para la entrega debe completar las siguientes funcionalidades:

## Funcionalidades

### Funcionalidades del módulo de administrador 
1. Debe permitir que en el programa se pueda agregar un nuevo artista, una nueva canción y un nuevo cliente.
2. Debe permitir que en el programa se pueda eliminar un artista, una canción, un cliente y una lista de reproducción:
    - Al eliminar un artista, se deben eliminar todas las canciones asociadas a ese artista.
    - Al eliminar un cliente, se deben eliminar todas las listas de reproducción asociadas a ese cliente.
    - Al eliminar una canción, se debe eliminar la canción de todas las listas de reproducción en las que esté presente.
3. Debe permitir ver todas las canciones, artistas, clientes y listas de reproducción en el programa.

### Funcionalidades del módulo de cliente

1. Debe permitir que un cliente pueda iniciar sesión en el programa.
2. Debe permitir al cliente agregar una nueva lista de reproducción.
3. Debe permitir al cliente agregar una canción a una lista de reproducción.
4. Debe permitir eliminar una canción de una lista de reproducción.
5. Debe permitir ver todas las listas de reproducción del cliente.
6. Debe permitir ver todas las canciones de una lista de reproducción.
7. Debe permitir al cliente seguir a un artista.
8. Debe permitir al cliente ver los artistas que sigue.

### Funcionalidades del módulo de gestión de archivos

1. Debe permitir al usuario importar datos desde archivos CSV. Los archivos a importar son:
    - [Artistas](src/main/resources/artists.csv)
    - [Canciones](src/main/resources/songs.csv)
    - [Listas de reproducción](src/main/resources/playlists.csv)
    - [Clientes](src/main/resources/customers.csv)
2. Debe permitir al usuario exportar datos a archivos CSV.
3. Debe permitir al usuario guardar datos en archivos binarios.
4. Debe permitir al usuario cargar datos desde archivos binarios.

### Funcionalidades del módulo de informes

1. Debe permitir al usuario ver los artistas más seguidos.
2. Debe permitir al usuario ver la canción más agregada en las listas de reproducción.
3. Debe permitir al usuario ver la canción más agregada de un artista específico en las listas de reproducción.

### Reglas de negocio

- Al crear un nuevo artista se debe validar que el nombre del artista no esté vacío.
- Al crear una nueva canción se debe validar que el nombre de la cancióny el género no estén vacíos y que la duración sea mayor a 0.
- Al crear un nuevo cliente se debe validar que el nombre de usuario y la contraseña no estén vacíos y que la edad sea mayor 14.
- Al crear un nuevo cliente se debe validar que el nombre de usuario no exista en la base de datos.
- Al crear un nuevo cliente, el username debe tener al menos 8 caracteres, el primer caracter debe ser una letra, puede incluir números, letras o el caracter '_' .
  - puede que le sea útil la siguiente expresión regular: `^[a-zA-Z][a-zA-Z0-9_]{7,30}$$` 
- Al crear un nuevo cliente, la contraseña debe tener al menos 8 caracteres, una letra mayúscula, una minúscula y cualquiera de los caracteres especiales (#?!@$ %^&*-).
  - puede que le sea útil la siguiente expresión regular: `^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$`
- Un Cliente no peude seguir dos veces al mismo artista. Esto quiere decir que si un cliente ya sigue a un artista, no puede volver a seguirlo.
- Si el usuario ingresa una letra en lugar de un número en la opción del menú, el programa debe mostrar un mensaje de error y solicitar al usuario que ingrese un número válido.
- Al eliminar un artista, se deben eliminar todas las canciones asociadas a ese artista.
- Al eliminar un cliente, se deben eliminar todas las listas de reproducción asociadas a ese cliente.
- Al eliminar una canción, se debe eliminar la canción de todas las listas de reproducción en las que esté presente.
- Se deben manejar todas las excepciones que puedan ocurrir durante la ejecución del programa.
- Al agregar una canción a una lista de reproducción, se debe validar que la canción y la lista de reproducción existan en la base de datos.
- Para asegurarse que el programa pueda crecer en el futuro se debe seguir la arquitectura propuesta en el documento.