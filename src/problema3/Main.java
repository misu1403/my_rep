package problema3;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String sir, insert_sir;
        int pozitie;
        System.out.println("Dati un sir de caractere: ");
        sir= scanner.nextLine();
        System.out.println("Dati sirul de inserat: ");
        insert_sir = scanner.nextLine();
        System.out.println("Dati pozitia din sirul initial unde doriti sa inserati: ");
        pozitie = scanner.nextInt();
        StringBuilder builder = new StringBuilder(sir);
        builder.insert(pozitie,insert_sir);
        System.out.println("Sirul rezultat: "+builder.toString());
        int pozitie_stergere, nr_caractere;
        System.out.println("Dati pozitia de inceput a stergerii: ");
        pozitie_stergere= scanner.nextInt();
        System.out.println("Dati numarul de caractere de sters: ");
        nr_caractere = scanner.nextInt();
        builder.delete(pozitie_stergere,pozitie_stergere + nr_caractere);
        System.out.println("Sirul dupa stergere: "+builder.toString());
    }
}
