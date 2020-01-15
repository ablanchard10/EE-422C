//class to handle all the clients that are created

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ClientHandler implements Runnable{
    private String username;
    private Socket socket;
    public static Scanner myObj = new Scanner(System.in);   //create scanner object to wait for username and messages
    private DataOutputStream dout;
    private DataInputStream inputStream;


    public ClientHandler(Socket clientSocket, String name, DataInputStream dis, DataOutputStream dout) throws IOException {
        this.socket = clientSocket;
        this.username = name;
        this.dout = dout;
        this.inputStream = dis;
    }


    @Override
    public void run() {

        try{
            this.dout.writeUTF("Welcome to chat "+this.username+"!\nUse '@' sign to send direct message.\nUse 'stop' to exit");
        }catch(IOException e){
            e.printStackTrace();
        }
/*
        try {
            this.dout.writeUTF("Whats your name?");
            this.username = myObj.nextLine();
            this.dout.writeUTF(this.username);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
            String received = null;
            boolean dm = false;
            String MsgToSend = "";
            String recipient = "";
            while(true){
                //read line and print to clients
                try{
                    received = inputStream.readUTF();
                    System.out.println(this.username+": "+received);
                    if(received.equalsIgnoreCase("stop")){
                        this.socket.close();
                        break;
                    }


                    if(received.contains("@")){
                        StringTokenizer st = new StringTokenizer(received, "@");
                        MsgToSend = st.nextToken();
                        recipient = st.nextToken();
                        dm = true;
                    }


                    for(ClientHandler mc : MyServer.clients){
                        if (!dm) {
                            mc.dout.writeUTF(this.username+" : "+received);
                        }
                        else if(mc.username.equals(recipient)){
                            mc.dout.writeUTF(this.username+" : "+MsgToSend);
                        }
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        try {
            this.dout.close();
            this.inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
