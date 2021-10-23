package search;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SearchEngine {

    private String inputNumberPeople;
    private String inputSearchElement;
    private String inputNumberQueries;

    private List<String> listOfPeople = new ArrayList<String>();

    Scanner sc = new Scanner(System.in);

    public void run(){

        System.out.println("Enter the number of people:");
        inputNumberPeople = sc.nextLine();

        System.out.println("Enter all people:");

        for(int i=0; i < Integer.parseInt(inputNumberPeople); i++){

            listOfPeople.add(sc.nextLine());
        }

        System.out.println("Enter the number of search queries");
        inputNumberQueries = sc.nextLine();

        for(int i=0; i < Integer.parseInt(inputNumberQueries); i++){

            System.out.println("Enter data to search people:");
            inputSearchElement = sc.nextLine();
            outputResults(searchStringInList(listOfPeople, inputSearchElement));
        }


    }

    public List<String> searchStringInList(List<String> list, String searchElement) {
    
    return list.stream().filter(el -> el.toLowerCase().contains(searchElement.toLowerCase())).collect(Collectors.toList());
        
    }
    
    public void outputResults(List<String> results){

        if (results.size() == 0){
            System.out.println("No matching people found.");
        } else {
            System.out.println("Found people:");
            results.forEach(result -> System.out.println(result));
        }

    }

}

