package flashcards;

import java.util.Scanner;

public class DeckLibApp {
    Scanner sc = new Scanner(System.in);
    private Deck deck = new Deck();

    public void flashMenu () {
        boolean exit = true;

        while(exit) {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            String input = sc.nextLine();

            switch (input) {
                case "add":
                    deck.createCard();
                    break;
                case "remove":
                    deck.removeCard();
                    break;
                case "import":
                    deck.importInFile();
                    break;
                case "export":
                    deck.exportFromFile();
                    break;
                case "ask":
                    deck.correctOrNot();
                    break;
                case "exit":
                    System.out.println("Bye bye!");
                    exit = false;
                    break;
                default:
                    System.out.println("Input is not valid, try again:");
                    break;
            }
            System.out.println("");
        }
    }
}
