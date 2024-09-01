package com.davithayrapetyan.scratchgame;

import com.davithayrapetyan.scratchgame.data.GameConfig;
import com.davithayrapetyan.scratchgame.data.GameResult;
import com.davithayrapetyan.scratchgame.logic.ConfigLoader;
import com.davithayrapetyan.scratchgame.logic.GameRunner;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Number of args:" + args.length);
            Arrays.stream(args).forEach(arg -> System.out.println("Arg: " + arg));
            System.out.println("Usage: java -jar <your-jar-file>.jar --config <config-file> --betting-amount <amount>");
            return;
        }

        String configPath = args[1];
        int betAmount = Integer.parseInt(args[3]);

        try {
            GameConfig config = ConfigLoader.loadConfig(configPath);
            GameResult result = GameRunner.runGame(config, betAmount);

            // Print the result in JSON format (you can use Jackson or manually format it)
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}