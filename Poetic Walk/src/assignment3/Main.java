/* EE422C Assignment #3 submission by
 * Austin Blanchard
 * aab3958
 */


package assignment3;

import java.io.File;
import java.io.IOException;

public class Main {
    /**
     * Example program using GraphPoet.
     */

    /**
     * Generate example poetry.
     *
     * @param args unused
     * @throws IOException if a poet corpus file cannot be found or read
     */
    public static void main(String[] args) throws IOException {
        final GraphPoet nimoy = new GraphPoet(new File("assignment3/"+args[0]));

        System.out.println(nimoy.poem(new File("assignment3/"+args[1])));
    }
}

