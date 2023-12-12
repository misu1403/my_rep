package problema2;

import java.time.LocalDate;
public class Produs {
    private String denumire;
    private double pret;
    private int cantitate;
    private LocalDate expire_date;

    private double incasari=0;

    // private static double incasari = 0.0;

    public Produs(String denumire, double pret, int cantitate, LocalDate expire_date) {
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
        this.expire_date = expire_date;
    }

    public String getDenumire() {
        return denumire;
    }

    public double getPret() {
        return pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int nouaCantitate)
    {
        cantitate = nouaCantitate;
    }

    public LocalDate getexpire_date() {
        return expire_date;
    }

    @Override
    public String toString() {
        return "Denumire: " + denumire +  ", Pret:" + pret+ ", Cantitate=" + cantitate + ", DataExpirare=" + expire_date;
    }

    public double vanzari(int cantitatea_mea)
    {
        return pret*cantitatea_mea;
    }

    public double getIncasari(int cantitatea_mea)
    {
        double total;
        total=incasari+vanzari(cantitatea_mea);
        return total;
    }
}