package problema2;

public class SetTobe extends InstrumentMuzical
{
    private TipTobe tipTobe;
    private int nr_tobe;
    private int nr_cinele;

    public SetTobe()
    {
        super("",0);
    }
    public SetTobe(String producator, float pret, TipTobe tipTobe, int nr_tobe, int nr_cinele) {
        super(producator, pret);
        this.tipTobe = tipTobe;
        this.nr_tobe = nr_tobe;
        this.nr_cinele = nr_cinele;
    }

    public TipTobe getTipTobe() {
        return tipTobe;
    }

    public void setTipTobe(TipTobe tipTobe) {
        this.tipTobe = tipTobe;
    }

    public int getNr_tobe() {
        return nr_tobe;
    }

    public void setNr_tobe(int nr_tobe) {
        this.nr_tobe = nr_tobe;
    }

    public int getNr_cinele() {
        return nr_cinele;
    }

    public void setNr_cinele(int nr_cinele) {
        this.nr_cinele = nr_cinele;
    }

    @Override
    public String toString() {
        return super.toString()+"\nToba: "+"\nTip tobe: "+ tipTobe+"\nNumar de tobe: "+nr_tobe+"\nNumar de cinele: "+ nr_cinele;
    }
}