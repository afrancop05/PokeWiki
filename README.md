# PokeWiki

Proyecto de aplicacion en Android sobre una **Pokedex** con [**API**](https://pokeapi.co/api/v2/pokemon/)

## Información de Pokémon

### Obtener información de un Pokémon

Para obtener información detallada de un Pokémon, puedes utilizar la siguiente [URL](https://pokeapi.co/api/v2/pokemon/charizard)  

Si quieres ver otro pokemon cambia el `Pokemon` de la `URL`

### Estructura de Datos

- **name:** Nombre del Pokémon

- **types:**
  - **idType(0,1,2):**
    - **type:**
      - **name:** Tipo del Pokémon

- **sprites:**
  - **front_default:** Imagen frontal del Pokémon
  
- **abilities:**
  - **idAbility(0,1,2):**
    - **ability:**
      - **name:** Nombre de la habilidad
    - **is_hidden:** Indica si la habilidad es oculta o no

- **height:** Altura del Pokémon

- **weight:** Peso del Pokémon

## Explicación del Proyecto

### Base de Datos

La clase `AppDatabase.kt` extiende de **RoomDatabase** para definir la base de datos.  
Contiene un método abstracto **pokeDao** que proporciona acceso al DAO  
y un método **getDatabase** utilizando el patron **singleton** para instanciar la base de datos.

`LocalPokeDao.kt` es una interfaz anotada con @Dao que declara métodos para la entidad **Poke**  
obteniendo todos los pokemons, insertando un pokemon o pudiendo eliminar un pokemon.

`Poke.kt` Es la entidad que representa un pokemon en la base de datos.

`PokeRepository.kt` es un repositorio que utiliza la base de datos y el DAO para  
realizar operaciones en la base de datos.

### Acceso a la API



`PokeDTO.kt` contiene las clases de datos que representan la respuesta de la API.  
Tiene clases anidadas como Abilities, Types...

`ApiService.kt` es una interfaz para las llamadas a la API a través del método **getPokemon**  
que coge un numero (ID del pokemon) como parámetro y devuelve un objeto de tipo PokeDTO

La clase `RemotePokeDataSource.kt` coge el método de la interfaz ApiService para  
devolver la respuesta de la API. Es el intermediario entre el cliente y la API.

`RetrofitBuilder.kt` configura una instancia de Retrofit para realizar llamadas a la API  
a través de un cliente mediante peticiones HTTP.

Para acceder a internet para coger los datos de la API se ha utilizado el siguiente permiso en el Manifest

    <uses-permission android:name="android.permission.INTERNET" />

### Almacenamiento Registros con Room

`MainViewModel.kt` utiliza corrutinas para cargar, insertar y eliminar datos de pokemons  
en un hilo secundario.

`MainActivity.kt` permite ingresar IDs de pokemons en un TextField para insertarlos,  
mostrarlos y borrarlos de la base de datos local **Room**.