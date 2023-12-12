import java.io.Serializable;

public class copiator extends echipament implements Serializable
{
    private int p_ton;
    private formate format; // A3 sau A4

    public copiator(String denumire, int numar_inventar, float pret, String zona_magazin, situatie_produs situatie, int p_ton, formate format)
    {
        super(denumire, numar_inventar, pret, zona_magazin, situatie);
        this.p_ton = p_ton;
        this.format = format;
    }

    @Override
    public String toString() {
        String m = mesaj();
        return m+"\nPagini per toner: "+ this.p_ton+"\nFormat: "+this.format;
    }

    public void setFormat(formate f)
    {
        this.format = f;
    }
}
