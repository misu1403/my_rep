package problema2;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<Produs> listaProduse = citesteProduseDinCSV("src/problema2/produse.csv");
        String linie;
        double banet, incasari=0;
        int optiune;
        do{
            System.out.println("Meniu:");
            System.out.println("1. Afișarea tuturor produselor");
            System.out.println("2. Afișare produse expirate");
            System.out.println("3.Vanzarea unui produs");
            System.out.println("4.Afisarea produselor cu pretul minim");
            System.out.println("5.Afisarea produselor cu o cantitate mai mica ca cea data de utilizator");
            System.out.println("0. Ieșire");
            System.out.print("Alege o opțiune: ");
            optiune = scanner.nextInt();
            switch ( optiune ) {
                case 0:
                    System.out.println("La revedere!");
                    break;
                case 1:
                    afiseazaToateProdusele(listaProduse);
                    break;
                case 2:
                    afiseazaProduseleExpirate(listaProduse);
                    break;
                case 3:
                    banet=vanzare(listaProduse);
                    incasari+=banet;
                    System.out.println("Incasari curente: "+incasari);
                    break;
                case 4:
                    produse_pret_minim(listaProduse);
                    break;
                case 5:
                    fisier_iesire(listaProduse);
                    break;
                default:
                    System.out.println("Opțiune invalida!");
                    break;
            }

        }while( optiune != 0);
    }

    private static List<Produs> citesteProduseDinCSV(String numeFisier) {
        List<Produs> listaProduse = new ArrayList<Produs>();
        String linie;
        try (BufferedReader br = new BufferedReader(new FileReader(numeFisier))) {
            while ((linie = br.readLine()) != null) {

                String[] elemente = linie.split(",");
                // "zahar" "1.1" "50" "2023-11-12"
                if (elemente.length == 4)
                {
                    String denumire = elemente[0].trim();
                    double pret = Double.parseDouble(elemente[1].trim());
                    int cantitate = Integer.parseInt(elemente[2].trim());
                    LocalDate dataExpirare = LocalDate.parse(elemente[3].trim());
                    Produs produs = new Produs(denumire, pret, cantitate, dataExpirare);
                    listaProduse.add(produs);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaProduse;
    }

    private static void afiseazaToateProdusele(List<Produs> listaProduse) {
        System.out.println("Toate produsele:");
        for (Produs produs : listaProduse) {
            System.out.println(produs);
        }
    }

    private static void afiseazaProduseleExpirate(List<Produs> listaProduse) {
        LocalDate dataCurenta = LocalDate.now();
        System.out.println("Produse expirate:");
        for (Produs produs : listaProduse) {
            if (produs.getexpire_date().isBefore(dataCurenta)) {
                System.out.println(produs);
            }
        }
    }

    private static double vanzare(List<Produs> listaProduse)
    {
        String produs_dorit;
        int cantitate_dorita;
        double incasari=0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ce produs doriti sa cumparati?");
        produs_dorit=scanner.next();
        for (Produs p : listaProduse)
        {
            if (produs_dorit.equals(p.getDenumire())) {
                System.out.println("Am gasit produsul: " + p.getDenumire());
                System.out.println("Ce cantitate doriti?");
                cantitate_dorita = scanner.nextInt();
                if (cantitate_dorita <= p.getCantitate()) {
                    System.out.println("Aveti de plata: " + p.vanzari(cantitate_dorita));
                    incasari = p.getIncasari(cantitate_dorita);
                    p.setCantitate(p.getCantitate() - cantitate_dorita);
                    if (p.getCantitate() == 0) {
                        {
                            listaProduse.remove(p);
                            break;
                        }
                    }
                } else
                    System.out.println("Nu avem aceasta cantitate, avem doar: " + p.getCantitate());
            }
        }
        return incasari;
    }

    public static void produse_pret_minim(List<Produs> listaProduse)
    {
        Produs p_min=listaProduse.get(0);
        for(Produs p : listaProduse)
        {
            if(p_min.getPret()>p.getPret())
                p_min=p;
        }

        for(Produs p : listaProduse)
            if(p.getPret()== p_min.getPret())
                System.out.println(p);
    }

    public static void fisier_iesire(List<Produs> listaProduse) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int cantitate;
        System.out.println("Dati cantitatea: ");
        cantitate= scanner.nextInt();
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/problema2/out.txt"));
        for(Produs p:listaProduse)
        {
            if(p.getCantitate()<cantitate)
                writer.write(p.getDenumire()+" "+p.getPret()+" "+p.getCantitate()+" "+p.getexpire_date()+"\n");
        }
        System.out.println("Datele au fost salvate!");
        writer.close();
    }
}
