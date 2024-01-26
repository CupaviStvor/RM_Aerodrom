package Server.Control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientControler implements Runnable{
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    

    public ClientControler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Command command = new Command();
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String poruka;
            while(!command.isFlag()){
                out.println(">> 1.LogIn");
                out.println(">> 2.Register");
                String input = in.readLine();
                if(Integer.parseInt(input) == 1){
                    out.println("/**** Unesi username. ****/");
                    String username = in.readLine();
                    out.println("/**** Unesi password. ****/");
                    String password = in.readLine();
                    command.logIn(username, password);
                    if(command.isFlag())out.println("Uspjesno ulogovan");
                }else if(Integer.parseInt(input) == 2){
                    out.println("/**** Unesi username. ****/");
                    String username = in.readLine();
                    out.println("/**** Unesi password. ****/");
                    String password = in.readLine();
                    out.println("/*** Potvrdi password. ***/");
                    if(password.equals(in.readLine()) && password.length() > 8){
                        command.addUser(username, password);
                    }
                    if(command.isFlag())out.println("Uspjesno registrovan");
                }
            }
            while(!socket.isClosed()){
                poruka = in.readLine();
                if(poruka.equals("#quit")){
                    shutDown(socket, in, out);
                }else if(poruka.equals("#list_flights")){
                    command.list_flights(out);
                }else if(poruka.equals("#reserve_flights")){
                    out.println("/* Unesite id zeljenog leta */");
                    String id = in.readLine();
                    command.reserve_flight(id);
                }else if(poruka.equals("#check_reservations")){
                    command.check_reservations(out);
                }else if(poruka.equals("#help")){
                    out.println("/*** #quit                ***/");
                    out.println("/*** #list_flights        ***/");
                    out.println("/*** #reserve_flight      ***/");
                    out.println("/*** #check_reservatrions ***/");
                    out.println("/*** #help                ***/");
                }else{
                    out.println("Pogresna komanda "+poruka);
                    System.out.println("/**** "+poruka+" ****/");
                }
            }

        } catch (IOException e) {
            shutDown(socket, in, out);
        }
    }

    private void shutDown(Socket socket, BufferedReader in, PrintWriter out){
        try{
            if(socket != null){
                socket.close();
            }
            if(in != null){
                in.close();
            }
            if(out != null){
                out.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
