package problema2;

import java.util.Random;
public class Vers {
    private String vers;

    public Vers(String vers) {
        this.vers = vers;
    }

    public int nrCuvinte()
    {
        String []cuvinte= vers.split(" ");
        return cuvinte.length;
    }

    public String getVers()
    {
        return vers;
    }

    public int nrVocale()
    {
        int i, nrv=0;
        for(i=0;i<vers.length();i++)
            if(isVocala(vers.charAt(i)))
                nrv++;
        return nrv;
    }

    private boolean isVocala(char litera)
    {
        if(litera == 'a' || litera == 'e'||litera == 'i' || litera == 'o' || litera == 'u'
                ||litera == 'A' || litera == 'E'||litera == 'I' || litera == 'O' || litera == 'U')
            return true;
        return false;
    }

    public double generareRandom()
    {
        Random random = new Random();
        double valoare;
        valoare=random.nextDouble();
        return valoare;
    }

    public String sufixe(String sufix)
    {
        if(vers.endsWith(sufix))
            return "*";
        return "";
    }
}