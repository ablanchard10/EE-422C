package assignment2;

import java.util.Scanner;


public class Driver{

    public static Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    //System.out.println("Enter username");

    //String userName = myObj.nextLine();  // Read user input
    //System.out.println("Username is: " + userName);  // Output user input

    public static void main(String args[]){

        boolean testing = true;
/*linux
        if(Integer.parseInt(args[0]) == 1){
            testing = true;
        }else{
            testing = false;
        }
*/
        while(testing){
            System.out.println("Welcome to Mastermind. Here are the rules.\n" +
                    "This is a text version of the classic board game Mastermind.\n" +
                    "The computer will think of a secret code. The code consists of 4\n" +
                    "colored pegs. The pegs MUST be one of six colors: blue, green,\n" +
                    "orange, purple, red, or yellow. A color may appear more than once in\n" +
                    "the code. You try to guess what colored pegs are in the code and\n" +
                    "what order they are in. After you make a valid guess the result\n" +
                    "(feedback) will be displayed.\n" +
                    "The result consists of a black peg for each peg you have guessed\n" +
                    "exactly correct (color and position) in your guess. For each peg in\n" +
                    "the guess that is the correct color, but is out of position, you get\n" +
                    "a white peg. For each peg, which is fully incorrect, you get no\n" +
                    "feedback.\n" +
                    "Only the first letter of the color is displayed. B for Blue, R for\n" +
                    "Red, and so forth. When entering guesses you only need to enter the\n" +
                    "first character of each color as a capital letter.\n" +
                    "You have 12 guesses to figure out the secret code or you lose the\n" +
                    "game. Are you ready to play? (Y/N):");

            String upY = "Y", upN = "N";
            String ready  = myObj.nextLine();

            if(ready.equalsIgnoreCase(upY)){
                //play game
                boolean go = true;
                while(go) {
                    String code = GameConfiguration.gameinit();
                    boolean win = Gameplay.game(code, GameConfiguration.guessNumber);

                    System.out.println("Are you ready for another game (Y/N): ");
                    Gameplay.history = "";

                    String again = "";
                    boolean valid = false;
                    while (!valid) {
                        again = myObj.nextLine();
                        if (again.equalsIgnoreCase(upN) || again.equalsIgnoreCase(upY)) {
                            valid = true;
                        } else {
                            System.out.println("Invalid Input\nNew Game? (Y/N)");
                        }
                    }
                    if (again.equalsIgnoreCase(upN)) {
                        testing = false;
                        go = false;
                    }
                }

            }else if(!(ready.equalsIgnoreCase(upN))){
                System.out.println("Invalid Input\n");
            }else if(ready.equalsIgnoreCase(upN)){
                testing = false;
            }


        }
    }
}