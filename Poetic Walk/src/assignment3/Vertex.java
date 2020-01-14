/* EE422C Assignment #3 submission by
 * Austin Blanchard
 * aab3958
 */
package assignment3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Vertex<T> {
    private String name;
    private Map<String,Integer> edges; // L is a vertex, Integer is the weight

    public Vertex(String name){         //this is constructor, sets value of name, and creates new map
        this.name = name;
        edges = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void printvals(Vertex v){
        System.out.println(v.edges.values());
    }

    public int getWeight(String key){
        if(edges.size() != 0 && edges.containsKey(key)) {
            return edges.get(key);
        }else{
            return 1;
        }

    }

    public void addWeight(String key){
        if(!edges.containsKey(key)){
            this.setWeight(key);
        }else{
            int weight = this.edges.get(key);
            weight++;
            this.edges.replace(key, weight);
        }
    }

    public void setWeight(String key){           //set weight to be 1 for new vertex and the word after it
        this.edges.put(key, 1);
    }

}
