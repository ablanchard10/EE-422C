import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyClient {
    public static String username;


    public static void main(String[] args) throws IOException {
        Scanner scn = new Scanner(System.in);

        Socket s=new Socket("localhost",6666);

        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        DataInputStream inputStream = new DataInputStream(s.getInputStream());
        dout.flush();

        Thread sendText = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    String msg = scn.nextLine();
                    try {
                        dout.writeUTF(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread readText = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        String text = inputStream.readUTF();
                        System.out.println(text);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        sendText.start();
        readText.start();

        //dout.close();
        //s.close();
    }
}
