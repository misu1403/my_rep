package problema4;

import java.time.LocalDate;

public class Persoana {
    private String nume;
    private String cnp;

    public Persoana(String nume, String cnp) {
        this.nume = nume;
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public int getVarsta()
    {
        String an_nastere=cnp.substring(1,3);
        LocalDate data_curenta=LocalDate.now();
        int an_curent = data_curenta.getYear();
        int anul_nasterii = Integer.parseInt(an_nastere);
        if(anul_nasterii>= 0 && anul_nasterii<=23)
        {
            anul_nasterii+=2000;
        }
        else
        {
            anul_nasterii+=1900;
        }
        return an_curent-anul_nasterii;
    }
}
