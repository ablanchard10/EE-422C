/* EE422C Assignment #3 submission by
 * Austin Blanchard
 * aab3958
 */

package assignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.*;

import static java.util.Collections.frequency;

public class GraphPoet {
    private HashMap<String, Vertex> affGraph;         //Map will contain the word being examined and a list of the words that follow it
    private ArrayList<String> corpusWords;
    private ArrayList<String> inputWords;
    //private ArrayList<String> edge;

    /**
     *
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */

    public GraphPoet(File corpus) throws IOException {

        String st, inPoem = "";
        BufferedReader br = new BufferedReader(new FileReader(corpus));
        while ((st = br.readLine()) != null){           //st will have line of poem, inPoem has whole poem when loop is done
            inPoem += st.toLowerCase();
            inPoem += " ";
        }
        String[] full = inPoem.split(" ");         //full is array with full text input from file, every word separated by a space

        corpusWords = new ArrayList<>();
        for(int i = 0; i < full.length; i++){              //put the words of corpus into arraylist for use in poem() routine
            corpusWords.add(i, full[i]);
        }

        affGraph = new HashMap<String, Vertex>();
        for(int i = 0; i < full.length-1; i++){             //for loop to create affGraph
                Vertex vert = new Vertex(full[i]);              //make new vertex with current word
                vert.setWeight(full[i + 1]);

                if (!affGraph.containsKey(full[i])) {
                    affGraph.put(full[i], vert);
                } else {
                    Vertex temp = affGraph.get(full[i]);
                    temp.addWeight(full[i + 1]);
                    affGraph.replace(full[i], temp);
                }

        }

    }

    /**
     * Generate a poem.
     *
     * @param input File from which to create the poem
     * @return poem (as described above)
     */
    public String poem(File input) throws IOException {

        String st, inPoem = "";
        BufferedReader br = new BufferedReader(new FileReader(input));
        while ((st = br.readLine()) != null){           //st will have line of poem, inPoem has whole poem when loop is done
            inPoem += st.toLowerCase();
            inPoem += " ";
        }
        String[] full = inPoem.split(" ");         //full is array with full text input from file, every word separated by a space
        inputWords = new ArrayList<>();
        for(int i = 0; i < full.length; i++){              //put the words of input into arraylist for use as dictionary
            inputWords.add(i, full[i]);
        }

        for(int i = 0; i < full.length-2; i++){                                                                         //go through input and check for bridge words
            if(corpusWords.contains(full[i])){                                                                          //if word in input i is in corpus file, check if there is bridge
                int freq = Collections.frequency(corpusWords, full[i]);
                /*//get amount of times is in corpus to see how many bridge words to check for
                if(freq > 1){                                                                                           //if >1 bridge opportunity, check all from corpus
                    Vertex vert = affGraph.get(full[i]);
                    String inputfull = String.join(" ",full);
                    String arr[] = inputfull.split(full[i]);
                    int bigW = 0;
                    String key = "";

                    if(corpusWords.get(corpusWords.indexOf(full[i]) + 2).equals(full[i + 1])){                          //copy words before bridge word
                        for(int j = 0; j <= i; j++){
                            inputWords.remove(j);
                            inputWords.add(j,full[j]);
                        }
                        inputWords.add(i+1,corpusWords.get(corpusWords.indexOf(full[i]) + 1));                    //put bridge word in inputWords arraylist
                        for(int j = corpusWords.indexOf(full[i+1])+1; j < full.length; j++){                            //copy words after bridge word
                            inputWords.remove(j);
                            inputWords.add(j,full[j-1]);
                        }
                    }
                    for(int j = 0; j < freq; j++){                                                                      //loop to find key of highest weighted edge to make bridge word
                        int tempW = vert.getWeight(arr[0]);
                        if(tempW > bigW){
                            key = full[i+1];
                            bigW = tempW;
                        }

                    }

                }else{
                //loop for adding in bridge word
                */

                    if(corpusWords.get(corpusWords.indexOf(full[i]) + 2).equals(full[i + 1])){                          //copy words before bridge word
                        for(int j = 0; j <= i; j++){
                            inputWords.remove(j);
                            inputWords.add(j,full[j]);
                        }
                        inputWords.add(i+1,corpusWords.get(corpusWords.indexOf(full[i]) + 1));                    //put bridge word in inputWords arraylist
                        for(int j = corpusWords.indexOf(full[i+1])+1; j < full.length; j++){                            //copy words after bridge word
                            inputWords.remove(j);
                            inputWords.add(j,full[j-1]);
                        }
                    }
                }
            }


        String[] fin = new String[inputWords.size()];
        for(int l = 0; l < inputWords.size(); l++) {
            fin[l] = inputWords.get(l);
        }

        String str = String.join(" ", fin);
        System.out.println(str);
        return str;
    }

}
