package assignment2;
/* EE422C Assignment #2
 * Fall 2017
*/

public class GameConfiguration{

	public static String gameinit(){			//generate random sequence
		System.out.println("Generating secret code.......");
		String secretCode = SecretCodeGenerator.getInstance().getNewSecretCode();
		secretCode = "BGOP";
		return secretCode;
	}

	public static final int guessNumber = 12;
	public static final String[] colors = {"B","G","O","P","R","Y"};
	public static final int pegNumber = 4;
}
