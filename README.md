º# PokeWiki

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
