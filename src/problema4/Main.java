package problema4;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int n,i;
        System.out.println("Dati numarul de persoane: ");
        n= scanner.nextInt();
        Persoana[] persoane= new Persoana[n];
        String nume, cnp;
        for(i=0;i<n;i++)
        {
            System.out.println("Dati numele persoanei: ");
            nume = scanner.next();
            do {
                System.out.println("Dati cnp-ul: ");
                cnp = scanner.next();
            }while (!validare_cnp(cnp));
            Persoana persoana = new Persoana(nume, cnp);
            persoane[i] = persoana;
        }
        for(i=0;i<n;i++)
        {
            System.out.println("Numele: "+persoane[i].getNume()+"\nCNP-ul: "+persoane[i].getCnp()+"\nVarsta: "+persoane[i].getVarsta());
            System.out.println("\n");
        }
    }

    public static boolean validare_cnp(String cnp) {
        //1
        if (cnp.length() != 13) {
            System.out.println("Numarul de cifre nu corespunde!");
            return false;
        }
        //2
        int i;
        for (i = 0; i < cnp.length(); i++)
            if (!(cnp.charAt(i) >= '0' && cnp.charAt(i) <= '9')) {
                System.out.println("CNP-ul introdus nu contine doare litere!");
                return false;
            }
        //3
        if (!(cnp.charAt(0) == '1' || cnp.charAt(0) == '2' || cnp.charAt(0) == '5' || cnp.charAt(0) == '6')) {
            System.out.println("Prima litera nu corespunde! ( nu este 1, 2, 5 sau 6)");
            return false;
        }

        //4
        String cnp_control = "279146358279";
        int suma = 0, cifra1, cifra2;
        for (i = 0; i < cnp.length() - 1; i++) {
            cifra1 = (int) cnp.charAt(i) - '0';
            cifra2 = (int) cnp_control.charAt(i) - '0';
            suma += cifra1 * cifra2;
        }
        suma %= 11;
        if (suma == 10)
            suma = 1;
        if (suma != (int) cnp.charAt(12) - '0')
        {
            System.out.println("Cifra de control este gresita!");
            return false;
        }
        return true;
    }
}
