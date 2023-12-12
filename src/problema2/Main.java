package problema2;

import java.io.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        String linie, sufix;
        Vers vers;
        BufferedReader reader= new BufferedReader(new FileReader("src/problema2/cantec_in.txt"));
        BufferedWriter writer= new BufferedWriter(new FileWriter("src/problema2/cantec_out.txt"));
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dati gruparea de litere: ");    // exemplu: touch
        sufix=scanner.next();
        while((linie= reader.readLine())!=null)
        {
            vers = new Vers(linie);
            if(vers.generareRandom()>0.1)
                writer.write(vers.getVers()+" "+vers.nrCuvinte()+ " "+vers.nrVocale()+" "+vers.sufixe(sufix));
            else
                writer.write(vers.getVers().toUpperCase()+" "+vers.nrCuvinte()+ " "+vers.nrVocale()+" "+vers.sufixe(sufix));
            writer.write("\n");
        }
        writer.close();
    }
}
