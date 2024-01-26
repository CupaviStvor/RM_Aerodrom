package Server.DataBase;

public class Letovi {

    private int id;
    private String put;
    private String vrijeme;
    private int brSlob;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPut() {
        return put;
    }

    public void setPut(String put) {
        this.put = put;
    }

    public String getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(String vrijeme) {
        this.vrijeme = vrijeme;
    }

    public int getBrSlob() {
        return brSlob;
    }

    public void setBrSlob(int brSlob) {
        this.brSlob = brSlob;
    }

    public Letovi(int id, String put, String vrijeme, int brSlob) {
        this.id = id;
        this.put = put;
        this.vrijeme = vrijeme;
        this.brSlob = brSlob;
    }

   
}
