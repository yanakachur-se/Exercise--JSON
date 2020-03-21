package edu.sda.commands;

import com.sun.javafx.iio.ios.IosDescriptor;
import edu.sda.service.PokeService;

import java.io.IOException;
import java.util.Scanner;

public class CommandProcess {

    private static PokeService pokeService = new PokeService();

    public static void main(String[] args) {
        boolean isContinue = true;
        while (isContinue) {
            try {
                printMenu();

                Scanner scanner = new Scanner(System.in);
                int input = scanner.nextInt();

                switch (input) {
                    case 1:
                        System.out.println("Please choose one of the pokemon names:");
                        System.out.println(callPokemonNames());
                        System.out.println("> ");
                        scanner = new Scanner(System.in);
                        String inputName = scanner.next();
                        System.out.println(callPokemonFromInput(inputName));
                        break;

                    case 2:
                        System.out.println("Please choose one of the location names:");
                        System.out.println(callLocationNames());
                        System.out.println("> ");
                        scanner = new Scanner(System.in);
                        String inputLocation = scanner.next();
                        System.out.println(callLocationFromInput(inputLocation));
                        break;
                    case 3:
                        pokeService.closeConnection();
                        isContinue = false;
                        break;
                }

            } catch (IOException e) {
                System.out.println("Error occurred");
            }

        }
    }

    private static void printMenu() {
        System.out.println("\n----------------------------");
        System.out.println("Welcome to Poke encyclopedia!");
        System.out.println("To get info about pokemon, enter 1");
        System.out.println("To get info about location, enter 2");
        System.out.println("To exit, enter 3");
    }

    private static String callPokemonNames() throws IOException {
        return pokeService.retrieveAllPokemons();
    }

    private static String callLocationNames() throws IOException {
        return pokeService.retrieveAllLocations();
    }

    private static String callPokemonFromInput(String inputPokemon) throws IOException {
        return pokeService.retrievePokemon(inputPokemon);
    }

    private static String callLocationFromInput(String inputLocation) throws IOException {
        return pokeService.retrieveLocation(inputLocation);
    }
}
