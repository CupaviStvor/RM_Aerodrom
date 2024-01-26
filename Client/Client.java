package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class Client implements Runnable{

    private Socket socket;
    private BufferedReader in;

    @Override
    public void run() {
        try {
            socket = new Socket("127.0.0.1", 2020);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            InputController client = new InputController(socket);
            Thread thread = new Thread(client);
            thread.start();

            String message;
            while((message = in.readLine()) != null ){
                System.out.println(message);
                
            }
            
        }catch (IOException e) {
            shutDown(socket, in);
        }
    }

    private void shutDown(Socket socket, BufferedReader in){
        try{
            if(socket != null){
                socket.close();
            }
            if(in != null){
                in.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
    
}
