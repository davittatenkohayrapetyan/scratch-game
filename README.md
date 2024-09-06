# Scratch Game

This is a Java-based scratch game where the player places a bet, and a 2D matrix of symbols is generated based on probabilities. The game determines if the player wins or loses based on specific symbol combinations. Bonus symbols are applied if there are any winning combinations in the matrix.

## Features

- **Standard Symbols**: Winning or losing is determined based on combinations of standard symbols.
- **Bonus Symbols**: Applied when there is a winning combination in the matrix, modifying the final reward.
- **Configurable**: Game configurations are loaded from a `config.json` file, allowing you to define custom symbol probabilities, winning combinations, and bonus symbols.

## Project Structure

- **src/main/java**:
    - ```com.davithayrapetyan.scratchgame.data```: Contains data classes like ```GameConfig```, ```Symbol```, ```GameResult```, and ```WinCombination```.
    - ```com.davithayrapetyan.scratchgame.logic```: Contains the core logic for the game such as ```MatrixGenerator```, ```BonusSymbolApplier```, ```GameRunner```, and ```ConfigLoader```.
    - ```App```: The main entry point for running the application.
- **resources**:
    - ```config.json```: The configuration file that defines the symbols, probabilities, and winning combinations.

## Prerequisites

- Java 8 or higher
- Maven 3.x or higher

## Building the Project

This project uses Maven for building and dependency management. Follow the steps below to build and package the project:

1. Clone the repository:

   ```git clone https://github.com/davittatenkohayrapetyan/scratch-game```

2. Navigate to the project directory:

   ```cd scratch-game```

3. Build the project:

   ```mvn clean install```
   This command will compile the code, run the tests, and create a package in the target folder.

4. Package the project:

   ```mvn package```
   This will generate the executable JAR file in the target folder. The JAR file will contain all the necessary dependencies.

## Running the Game

After building the project, you can run the game from the command line. You need to specify the configuration file and the betting amount as arguments.

Example command to run the game:

```java -jar ./target/scratch-game-1.0-SNAPSHOT-jar-with-dependencies.jar --config src/main/resources/config.json --betting-amount 100```

### Command Line Arguments

- ```--config```: The path to the ```config.json``` file that defines the game setup.
- ```--betting-amount```: The amount of money the player is betting on the game.

## Running Tests

To run the test cases, use the following Maven command:

```mvn test```

This will execute the unit tests and validate the functionality of the game logic.

## Additional Information

The game configuration is defined in a ```config.json``` file located in the resources folder. You can modify this file to adjust the game’s behavior, symbol probabilities, and winning combinations.
