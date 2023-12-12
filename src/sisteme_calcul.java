import java.io.Serializable;

public class sisteme_calcul extends echipament implements Serializable
{
    private String tip_mon;
    private double vit_proc;
    private int c_hdd;
    private sistem_op sistemOperare;

    public sisteme_calcul(String denumire, int numar_inventar, float pret, String zona_magazin, situatie_produs situatie, String tip_mon, double vit_proc, int c_hdd, sistem_op sistemOperare)
    {
        super(denumire, numar_inventar, pret, zona_magazin, situatie);
        this.tip_mon = tip_mon;
        this.vit_proc = vit_proc;
        this.c_hdd = c_hdd;
        this.sistemOperare = sistemOperare;
    }

    @Override
    public String toString()
    {
        String m = mesaj();
        return m+"\nMonitor: "+ tip_mon+"\nViteza procesare: "+this.vit_proc +"\nCapacitate HDD: "+c_hdd+"\nSistem de operare: "+this.sistemOperare;
    }

    public void setSistemOperare(sistem_op SO)
    {
        this.sistemOperare = SO;
    }
}