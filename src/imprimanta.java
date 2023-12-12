
import java.io.Serializable;

public class imprimanta extends echipament implements Serializable
{
    private int ppm;
    private int dpi;
    private int p_car;
    private boolean color;

    public imprimanta(String denumire, int numar_inventar, float pret, String zona_magazin, situatie_produs situatie, int ppm,int dpi,int p_car,boolean color)
    {
        super(denumire, numar_inventar, pret, zona_magazin, situatie);
        this.ppm = ppm;
        this.dpi = dpi;
        this.p_car = p_car;
        this.color = color;
    }

    public int getPPM() {
        return ppm;
    }

    public int getDPI() {
        return dpi;
    }

    public int getPCar() {
        return p_car;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean C)
    {
        this.color = C;
    }

    @Override
    public String toString() {
        String m = mesaj();
        return m+"\nPagini/ minut: "+ this.ppm + "\nDPI: " + this.dpi+"\nPagini/ cartus: "+ this.p_car+"\nColor: "+this.color;
    }

}