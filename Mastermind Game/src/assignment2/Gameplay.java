/* EE422C Assignment #2 submission by
 * Replace <...> with your actual data. 
 * Austin Blanchard
 * aab3958
 */
package assignment2;

public class Gameplay{
    public static String history = "";
    private static String guess = "";

    public static boolean game(String secretCode, int guesses){
        for(; guesses > 0; guesses--){
            System.out.println("You have "+guesses+" guesses left.\n" +
                    "What is your next guess?\n" +
                    "Type in "+GameConfiguration.pegNumber+" characters (B,G,O,P,R,Y) for your guess and press enter.\n" +
                    "Enter guess: ");

            boolean valid = false;
            while(!valid) {                             //check if inputted guess is valid
                guess = Driver.myObj.nextLine();
                String histcheck = "HISTORY";
                if (guess.toUpperCase().equals(histcheck)) {
                    System.out.println(history);
                    System.out.println("What is your next guess?\n" +
                            "Type in "+GameConfiguration.pegNumber+" characters (B,G,O,P,R,Y) for your guess and press enter.\n" +
                            "Enter guess: ");
                }else if (guess.length() == GameConfiguration.pegNumber) {
                    if (guess.equals(guess.toUpperCase())) {
                        valid = true;
                    }
                    if (!valid) {
                        System.out.println(" -> INVALID GUESS\n" +
                                "What is your next guess?\n" +
                                "Type in the characters for your guess and press enter.\n" +
                                "Enter guess:");
                    }
                }else{
                    System.out.println(" -> INVALID GUESS\n" +
                            "What is your next guess?\n" +
                            "Type in the characters for your guess and press enter.\n" +
                            "Enter guess:");
                }
            }
            //check guess with secret code
            //first check for black pegs
            String blacks = blackPegs(guess, secretCode);           //returns string in format '#blacks/newguess/newsecret'

            //split up returned string in order to check for white pegs
            String[] parts = blacks.split("/");
            int bPegs = Integer.parseInt(parts[0]);
            String newGuess = parts[1];
            String newSecret = parts[2];

            int wPegs = whitePegs(newGuess, newSecret);
            String hist = guess+" "+bPegs+"B_"+wPegs+"W";
            history += hist+"\n";


            System.out.print(guess+" -> Result: ");
            if(bPegs < GameConfiguration.pegNumber){    //if guess not right
                if(bPegs != 0){
                    System.out.print(bPegs+" black pegs ");
                }
                if(wPegs != 0){
                    System.out.print(wPegs+" white pegs ");
                }
                if(bPegs == 0 && wPegs == 0){
                    System.out.print("No pegs");
                }
                System.out.println();
            }else{                                      //if guess is right
                System.out.println(bPegs+" black pegs - YOU WIN!!");
                return true;
            }
        }
        System.out.println("Sorry, you are out of guesses. You lose, boo-hoo.");
        return false;
    }


    private static String blackPegs(String guess, String secret){
        int count = 0;
        char dash = '-';
        for(int i = 0; i < secret.length(); i++){
            if(guess.charAt(i) == secret.charAt(i)){
                count++;
                guess = guess.substring(0, i) + dash + guess.substring(i+1);
                secret = secret.substring(0, i) + dash + secret.substring(i+1);
            }
        }
        String data = count+"/"+guess+"/"+secret;
        return data;
    }

    private static int whitePegs(String guess, String secret){
        int count = 0;
        char dash = '-';

        for(int i = 0; i < guess.length(); i++){
            if(guess.charAt(i) != '-'){
                for(int j = 0; j < secret.length(); j++){
                    if(guess.charAt(i) == secret.charAt(j) && secret.charAt(j) != dash){
                        count++;
                        guess = guess.substring(0, i) + dash + guess.substring(i+1);
                        secret = secret.substring(0, j) + dash + secret.substring(j+1);
                    }
                }
            }
        }
        return count;
    }
}