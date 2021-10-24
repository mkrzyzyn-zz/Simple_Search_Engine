package search;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {

    private String inputSearchElement;

    private String choices;

    public SearchEngine(String[] args) {
        this.args = args;
    }

    private String[] args;

    private List<String> listOfPeople = new ArrayList<>();
    private List<String[]> listOfPeopleSplit = new ArrayList<>();
    private List<List<String>> listOfPeopleSplit2 = new ArrayList<>();
    private Map<String, List<Integer>> mapOfPeople = new HashMap<>();
    private List<String> results = new ArrayList<>();


    Scanner sc = new Scanner(System.in);

    public void runMenu() {

        readFile(args);

        prepareIndex();

        while (true) {

            System.out.println("=== Menu ===\n" +
                    "1. Find a person\n" +
                    "2. Print all people\n" +
                    "0. Exit");

            choices = sc.nextLine();

            switch (choices) {

                case "1":
                    outputResults(findInvertedIndex());
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

  /*  public void find() {

        System.out.println("Enter a name or email to search all suitable people.");
        inputSearchElement = sc.nextLine();
        outputResults(searchStringInList(listOfPeople, inputSearchElement));

    }*/

    public List<String> findInvertedIndex() {

        System.out.println("Enter a name or email to search all suitable people.");
        inputSearchElement = sc.nextLine();

        if(mapOfPeople.get(inputSearchElement)!= null) {
            results = mapOfPeople.get(inputSearchElement).stream()
                    .map(el -> listOfPeople.get(el))
                    .collect(Collectors.toList());
        }
        return results;

    }

    public void prepareIndex() {

        listOfPeopleSplit = listOfPeople.stream().map(el -> el.split(" ")).collect(Collectors.toList());

        listOfPeopleSplit2 = listOfPeopleSplit.stream().map(el -> Arrays.asList(el)).collect(Collectors.toList());

        for (int i = 0; i < listOfPeopleSplit2.size(); i++) {

            for (int j = 0; j < listOfPeopleSplit2.get(i).size(); j++) {

                List<Integer> tempList = new ArrayList<>();

                if ( mapOfPeople.get(listOfPeopleSplit2.get(i).get(j)) != null) {
                    tempList = mapOfPeople.get(listOfPeopleSplit2.get(i).get(j));
                }
                tempList.add(i);
                mapOfPeople.put(listOfPeopleSplit2.get(i).get(j), tempList);

            }

        }

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

    public void readFile(String[] parameters) {

        String fileName = "";

        if (parameters[0].equals("--data")) {
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

