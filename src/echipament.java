import java.io.Serializable;

public class echipament implements Serializable
{
    protected String denumire;
    protected int numar_inventar;
    protected float pret;
    protected String zona_magazin;
    protected situatie_produs situatie;
    public echipament(String denumire, int numar_inventar, float pret, String zona_magazin, situatie_produs situatie)
    {
        this.denumire = denumire;
        this.numar_inventar = numar_inventar;
        this.pret = pret;
        this.zona_magazin = zona_magazin;
        this.situatie = situatie;
    }

    public String getDenumire()
    {
        return this.denumire;
    }

    public int getNumar_inventar()
    {
        return this.numar_inventar;
    }

    public float getPret()
    {
        return this.pret;
    }
    public situatie_produs getSituatie()
    {
        return this.situatie;
    }

    public void setSituatie(situatie_produs s)
    {
        this.situatie= s;
    }


    public String mesaj()
    {
        return "\nDenumire: "+this.denumire+"\nNumar inventar: "+this.numar_inventar+"\nPret:"+this.pret+"\nZona magazin: "+this.zona_magazin+"\nSituatie produs: "+this.situatie;
    }


}