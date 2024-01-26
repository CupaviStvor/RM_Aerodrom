package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Server.Control.ClientControler;


public class Server implements Runnable{

    ExecutorService exe;
    ServerSocket Aserver;
    

    @Override
    public void run() {
        try{
            Aserver = new ServerSocket(2020);
            exe = Executors.newCachedThreadPool();
            while(!Aserver.isClosed()){
                Socket client = Aserver.accept();
                ClientControler user = new ClientControler(client);
                System.out.println("/*****User has connected*****/");
                exe.execute(user);
            }
        }catch(IOException e){
            try {
                Aserver.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        Server server = new Server();
        server.run();
        System.out.println("Server napravljen");
    }

}
