import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer {
    private static ServerSocket ss;
    private static Socket client;
    //private static int amount;
    private static ExecutorService pool = Executors.newFixedThreadPool(4);
    private static int i = 1;
    public static ArrayList<ClientHandler> clients = new ArrayList<>();


    public static void main(String[] args){
        try{
        ss=new ServerSocket(6666);
            //System.out.println("Waiting for clients...");


        //while loop to keep server open until client types 'quit'
        while(true){
            client =ss.accept();//establishes connection
            System.out.println("Client Connected!:"+client);

            DataInputStream inputStream  = new DataInputStream(client.getInputStream());
            DataOutputStream dout = new DataOutputStream(client.getOutputStream());

            System.out.println("Creating client handler...");
            ClientHandler handler = new ClientHandler(client, "user"+i, inputStream, dout);
            i++;

            //System.out.println("innnn");
            //Thread t = new Thread(handler);

            clients.add(handler);

            //t.start();

            pool.execute(handler);

            System.out.println(clients.size());

        }


        //ss.close();

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
