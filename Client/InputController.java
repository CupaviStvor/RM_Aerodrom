package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class InputController implements Runnable{

    Socket socket;
    BufferedReader br;
    PrintWriter pw;

    public InputController(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        br = new BufferedReader(new InputStreamReader(System.in));
        try {
            pw = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            shutDown(socket, br);
        }
        String poruka;
        while(!socket.isClosed()){
            try {
                poruka = br.readLine();
                if(poruka.equals("#quit")){
                    pw.println(poruka);
                    shutDown(socket, br);
                }else{
                    pw.println(poruka);
                }
            } catch (IOException e) {
               shutDown(socket, br);
            }
           
        }
    }

    private void shutDown(Socket socket, BufferedReader br){
        try{
            if(br != null){
                br.close();
            }
            if(socket != null){
                socket.close();
            }
        }catch(IOException e){
            //damn
        }
    }
}
