package problema3;


import java.util.ArrayList;
import java.util.List;
public class Mobilier {
    String nume;
    List<Placa> placi = new ArrayList<>();

    public Mobilier()
    {

    }

    public Mobilier(String nume) {
        this.nume = nume;
        this.placi = new ArrayList<>();
    }
    void adaugarePlaca(Placa p)
    {
        placi.add(p);
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List<Placa> getPlaci() {
        return placi;
    }

    public void setPlaci(List<Placa> placi) {
        this.placi = placi;
    }

    @Override
    public String toString() {
        return "\nNume: "+nume+"\nPlaci:\n"+placi+"\n";
    }
}