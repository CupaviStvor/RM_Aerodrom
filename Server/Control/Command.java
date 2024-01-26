package Server.Control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Server.DataBase.DataBase;
import Server.DataBase.Letovi;
import Server.DataBase.Users;

public class Command {

    private boolean flag = false;
    private DataBase data = new DataBase();
    private Users user;
    

    public void logIn(String username, String password){
        for(Users x : data.getUsers()){
            if(password.equals(x.getPassword()) && username.equals(x.getUsername())){
                user = x;
                flag = true;
            }
        }
    }

    public void addUser(String username, String password){
        data.getUsers().add(new Users(username, password));
        data.updateDataBase();
        flag = true;
    }

    public void list_flights(PrintWriter pw){
        for(Letovi x: data.getLetovi()){
            pw.println(x.getId()+":"+x.getPut()+":"+x.getVrijeme()+":"+x.getBrSlob());
        }
    }

    public void reserve_flight(String id_let){
        File file = new File("C:\\Users\\mdrag\\OneDrive\\Радна површина\\VSCode\\Faks\\Server\\DataBase\\Data\\Rezervisano.txt");
        FileWriter fr;
        BufferedWriter bf;
        try {
            fr = new FileWriter(file,true);
            bf = new BufferedWriter(fr);
            
            for(Letovi x: data.getLetovi()){
                if(x.getId() == Integer.parseInt(id_let)){
                    bf.write("\n"+user.getUsername()+","+x.getId()+","+x.getPut()+","+x.getVrijeme());
                }
            }
            bf.close();
            fr.close();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            
         
            
    }

    public void check_reservations(PrintWriter pr){
        FileReader file;
        try {
            file = new FileReader("C:\\Users\\mdrag\\OneDrive\\Радна површина\\VSCode\\Faks\\Server\\DataBase\\Data\\Rezervisano.txt");
            BufferedReader br = new BufferedReader(file);
            String line = br.readLine();
            while(line != null){
                String[] dio = line.split(",");
                if(dio[0].equals(user.getUsername())){
                    pr.println(dio[0]+","+dio[1]+","+dio[2]+","+dio[3]);
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }

    public boolean isFlag() {
        return flag;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
}
