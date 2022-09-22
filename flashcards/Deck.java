package flashcards;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.*;

public class Deck {
    //private ArrayList<Cards> cards = new ArrayList<>();
    private Map<String, String> cards = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    public void correctOrNot () {
        System.out.println("How many times to ask?");
        int repeat = sc.nextInt();
        sc.nextLine(); // for enter

        for (int i = 0; i < repeat; i++) {
            for (var entry : cards.entrySet()) {
                System.out.println("Print the definition of \"" + entry.getKey() + "\":");
                String guess = sc.nextLine();
                if (entry.getValue().equals(guess))
                    System.out.println("Correct!");
                else if (findDuplyDesc(guess) != null) {
                    System.out.println("Wrong. The right answer is \"" + entry.getValue() + "\", " +
                            "but your definition is correct for \"" + findDuplyDesc(guess) + "\".");
                } else {
                    System.out.println("Wrong. The right answer is \"" + entry.getValue() + "\"");
                }
            }
        }
    }

    public String findDuplyDesc (String test) {
        String back = null;
        for (var e : cards.entrySet()) {
            if (e.getValue().equals(test)) {
                back = e.getKey();
                break;
            }
        }
        return back;
    }

    public Boolean stopDuplicateKey (String test) {
        boolean back = false;
        for (String e : cards.keySet()) {
            if (e.equals(test)) {
                back = true;
                break;
            }
        }
        return back;
    }

    public Boolean stopDuplicateValue (String test) {
        boolean back = false;
        for (var e: cards.values()) {
            if (test.equals(e)) {
                back = true;
                break;
            }
        }
        return back;
    }

    public void createCard () {
/*
        System.out.println("Input the number of cards:");
        int repeat = sc.nextInt();
        sc.nextLine(); // for enter
*/
        //for (int i = 0; i < repeat; i++) {
        do {
            System.out.println("The card:");
            String cardGet = sc.nextLine();
            if (stopDuplicateKey(cardGet)) {
                System.out.println("The card \"" + cardGet + "\" already exists.");
                break;
            }
            /*while (stopDuplicateKey(cardGet)) {
                System.out.println("The term \"" + cardGet + "\" already exists. Try again:");
                cardGet = sc.nextLine();
            }*/
            System.out.println("The definition of the card:");
            String cardDis = sc.nextLine();
            if (stopDuplicateValue(cardDis)) {
                System.out.println("The definition \"" + cardDis + "\" already exists.");
                break;
            }
            /*while (stopDuplicateValue(cardDis)) {
                System.out.println("The definition \"" + cardDis + "\" already exists. Try again:");
                cardDis = sc.nextLine();
            }*/
            cards.put(cardGet, cardDis);
            System.out.printf("The pair (\"%s\":\"%s\") has been added.\n", cardGet, cardDis);
            break;
            // }
        } while (true);
    }

    public void removeCard () {
        System.out.println("Which card?");
        String card = sc.nextLine();

        if (!cards.containsKey(card)) {
            System.out.printf("Can't remove \"%s\": there is no such card.\n", card);
        }
        else {
            cards.remove(card);
            System.out.println("The card has been removed.");
        }
    }

    public void importInFile () {
        System.out.println("File name:");
        String fileName = sc.nextLine();
        File forImport = new File(".\\", fileName);
        String text = null;
        int count = 0;

        if (!forImport.isFile()) {
            System.out.println("File not found.");
        }
        else {
            System.out.println("test1");
            try (Scanner scanner = new Scanner(forImport)) {
                while (scanner.hasNext()) {
                    text = readFileAsString(fileName);
                    System.out.println(text);
                    System.out.println("test2");
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("No file found: " + fileName);
            }
            catch (IOException e) {
                System.out.println("Cannot read file: " + e.getMessage());
            }

            String[] input = text.split(" ");
            for (int i = 0; i < input.length; i += 2) {
                if (cards.containsKey(input[i])) {
                    cards.replace(input[i], input[i], input [i+1]);
                }
                else {
                    cards.put(input[i], input[i+1]);
                    count++;
                }
            }
            System.out.printf("%d cards have been saved.\n", count);
        }
    }

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public void exportFromFile () {
        System.out.println("File name:");
        String fileName = sc.nextLine();
        File file = new File(".\\", fileName);
        int count = 0;

        try (FileWriter writer = new FileWriter(file)) {
            for (var e : cards.entrySet()) {
                writer.write(e.getKey() + " " + e.getValue() + " ");
                count++;
            }
            System.out.printf("%d cards have been saved.", count);
        } catch (IOException e) {
            System.out.printf("An exception occurred %s", e.getMessage());
        }
    }
    //File fileOnWin = new File(".\\images\\picture.jpg");
}
