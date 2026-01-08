# Mic Mac Moe
An Android App to play Tic Tac Toe

<table>
  <tr>
    <td><img src="https://github.com/slagyr/micmacmoe/blob/master/assets/home.png?raw=true" alt="Home Screen" width="300"/></td>
    <td><img src="https://github.com/slagyr/micmacmoe/blob/master/assets/game.png?raw=true" alt="Game Screen" width="300"/></td>
    <td><img src="https://github.com/slagyr/micmacmoe/blob/master/assets/tui.png?raw=true" alt="Text UI" width="300"/></td>
  </tr>
</table>

## Highlights
 * Build using Kotlin and Jetpack Compose
 * Comprehensive Unit Tests (TDD baby!)
 * UI Unit Tests (androidTest?)
 * Human v Human or Human v Computer
 * Easy, Medium, and UNBEATABLE computer AIs.
 * Text UI playable from the terminal

## Development
```sh
# run all tests
./gradlew test connectedAndroidTest 

# run console tic tac toe
./gradlew tui -q --console=plain
```
### Run in the Emulator
 * Open the Project in Android Studio and Run the app.

