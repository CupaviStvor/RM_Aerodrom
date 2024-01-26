package Server.DataBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataBase {
    private ArrayList<Letovi> letovi = new ArrayList<>();
    private ArrayList<Users> users = new ArrayList<>();
    
    
    public DataBase() {
        createLetovi();
        createUsers();
    }

    private void createLetovi(){
        try {
            Scanner scan = new Scanner(new File("Server\\DataBase\\Data\\Letovi.txt"));
            
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String[] dio = line.split(",");
                Letovi let = new Letovi(Integer.parseInt(dio[0]), dio[1], dio[2], Integer.parseInt(dio[3]));
                letovi.add(let);
                
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void createUsers(){
        BufferedReader br; 
        FileReader file;
        try {
            file = new FileReader("C:\\Users\\mdrag\\OneDrive\\Радна површина\\VSCode\\Faks\\Server\\DataBase\\Data\\Users.txt");
            br = new BufferedReader(file);
            String line = br.readLine();
            while(line != null){
                String[] dio = line.split(":");
                Users user = new Users(dio[0], dio[1]);
                users.add(user);
                line = br.readLine();
            }
            br.close();
            file.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

     public void updateDataBase(){
        File file = new File("C:\\Users\\mdrag\\OneDrive\\Радна површина\\VSCode\\Faks\\Server\\DataBase\\Data\\Users.txt");
        try {
            FileWriter fr = new FileWriter(file);
            BufferedWriter bf = new BufferedWriter(fr);
            for(Users x: users){
                bf.write(x.getUsername()+":"+x.getPassword());
            }
            fr.close();
            bf.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        file = new File("C:\\Users\\mdrag\\OneDrive\\Радна површина\\VSCode\\Faks\\Server\\DataBase\\Data\\Letovi.txt");
        try {
            FileWriter fr = new FileWriter(file);
            BufferedWriter bf = new BufferedWriter(fr);
            for(Letovi x: letovi){
                bf.write(x.getId()+","+x.getPut()+","+x.getVrijeme()+","+x.getBrSlob());
            }
            fr.close();
            bf.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
     }

    public ArrayList<Letovi> getLetovi() {
        return letovi;
    }

    public void setLetovi(ArrayList<Letovi> letovi) {
        this.letovi = letovi;
    }

    public ArrayList<Users> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Users> users) {
        this.users = users;
    }


}
