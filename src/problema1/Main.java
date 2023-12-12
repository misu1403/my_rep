package problema1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException {
        String linie, judetCautat;
        String []judete= new String[40];
        int i=0;
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new FileReader("src/problema1/judete.txt"));
        while((linie= reader.readLine())!=null)
        {
            judete[i]=linie;
            i++;
        }
        Arrays.sort(judete);
        System.out.println("Judetele ordonate alfabetic: "+Arrays.toString(judete));
        System.out.println("Dati judetul pe care vreti sa l cautati: ");
        judetCautat=scanner.next();
        int pozitie = Arrays.binarySearch(judete,judetCautat);
        if(pozitie>=0)
        {
            System.out.println("Judetul: "+judetCautat+" a fost gasit pe pozitia: "+(pozitie+1));
        }
        else
            System.out.println("JUFETUL NU A FOST GASIT!");
    }
}
