package problema1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String fisier = "src/problema1/in.txt", linie;
        BufferedReader reader = new BufferedReader(new FileReader(fisier));
        List<Parabola> parabole = new ArrayList<>();
        while((linie= reader.readLine())!=null)
        {
            String[] elemente = linie.split(" ");
            int a = Integer.parseInt(elemente[0].trim());
            int b = Integer.parseInt(elemente[1].trim());
            int c = Integer.parseInt(elemente[2].trim());
            Parabola p = new Parabola(a,b,c);
            parabole.add(p);
        }

        for (Parabola p: parabole)
            System.out.println(p + "    Varful: Xv="+p.Xv()+"    Yv="+p.Yv());

        //Calculam coordonatele mijlocului pentru parabolele din lista cu indexul 0 si 1
        Parabola.mijloc(parabole.get(0), parabole.get(1));
        //Calculam distanta dintre varfurile parabolelor din lista cu indexul 0 si 1
        System.out.println("Distanta: "+Parabola.lungime2(parabole.get(0), parabole.get(1)));
    }
}
