package search;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SearchEngine {

    private String inputNumberPeople;
    private String inputSearchElement;
    private String inputNumberQueries;
    private String choices;

    public SearchEngine(String[] args) {
        this.args = args;
    }

    private final String[] args;


    private final List<String> listOfPeople = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public void runMenu() {

        // collectData();

        readFile(args);

        while (true) {

            System.out.println("=== Menu ===\n" +
                    "1. Find a person\n" +
                    "2. Print all people\n" +
                    "0. Exit");

            choices = sc.nextLine();

            switch (choices) {

                case "1":
                    find();
                    break;
                case "2":
                    printAll();
                    break;
                case "0":
                    System.out.println();
                    System.exit(0);

            }

        }


    }

    public void find() {

        System.out.println("Enter a name or email to search all suitable people.");
        inputSearchElement = sc.nextLine();
        outputResults(searchStringInList(listOfPeople, inputSearchElement));

    }

    public List<String> searchStringInList(List<String> list, String searchElement) {

        return list.stream().filter(el -> el.toLowerCase().contains(searchElement.toLowerCase())).collect(Collectors.toList());

    }

    public void outputResults(List<String> results) {

        if (results.size() == 0) {
            System.out.println("No matching people found.");
        } else {
            System.out.println("Found people:");
            results.forEach(result -> System.out.println(result));
        }

    }

    public void printAll() {

        System.out.println("=== List of people ===");
        listOfPeople.forEach(el -> System.out.println(el));

    }

    public void collectData() {

        System.out.println("Enter the number of people:");
        inputNumberPeople = sc.nextLine();

        System.out.println("Enter all people:");

        for (int i = 0; i < Integer.parseInt(inputNumberPeople); i++) {

            listOfPeople.add(sc.nextLine());
        }
    }

    public void readFile(String[] parameters) {

        String fileName="";

        if (parameters[0].equals("--data")){
            fileName = parameters[1];
        }

        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                listOfPeople.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}

