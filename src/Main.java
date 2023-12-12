import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int optiune;
        List<echipament> echipamente = new ArrayList<echipament>();
        citire_fisier(echipamente);
        do
        {
            meniu();
            optiune = scanner.nextInt();
            switch ( optiune ) {
                case 0:
                    System.out.println("La revedere!");
                    break;
                case 1:
                    afisare(echipamente);
                    break;
                case 2:
                    afisare_Imprimanta(echipamente);
                    break;
                case 3:
                    afisare_Copiatoar(echipamente);
                    break;
                case 4:
                    afisare_Sistem_calcul(echipamente);
                    break;
                case 5:
                    modificare_situatie(echipamente);
                    break;
                case 6:
                    modificare_mod_scriere(echipamente);
                    break;
                case 7:
                    modificare_formatCopiere(echipamente);
                    break;
                case 8:
                    modificare_sistem_operare(echipamente);
                    break;
                case 9:
                    afisare_echipamente_vandute(echipamente);
                    break;
                case 10:
                    serializare_deserializare( echipamente);
                    break;
                default:
                    System.out.println("Opțiune invalida!");
                    break;
            }
        }while( optiune != 0);

    }

    public static void meniu()
    {
        System.out.println("MENIU");
        System.out.println("0.Iesire");
        System.out.println("1.Afisarea tuturor echipamentelor");
        System.out.println("2.Afişarea imprimantelor");
        System.out.println("3.Afişarea copiatoarelor");
        System.out.println("4.Afisarea sistemelor de calcul");
        System.out.println("5.Modificarea situatiei unui echipament");
        System.out.println("6.Modificarea modului de scriere al unei imprimante");
        System.out.println("7.Modificarea unui format de copiere pentru un copiator");
        System.out.println("8.Modificarea unui sistem de operare pentru un anumit sistem de calcul");
        System.out.println("9.Afisarea echipamentelor vandute");
        System.out.println("10.Serializare/ deserializare");
        System.out.println("Dati optiunea: ");
    }


    public static void citire_fisier(List<echipament> echipamente)
    {
        String denumire;
        int numar_inventar;
        float pret;
        String zona_magazin;
        situatie_produs situatie;
        int situatieInt;
        int tip;
        try
        {
            Scanner scanner = new Scanner(new File("src/date.in"));
            while(scanner.hasNext())
            {
                tip = scanner.nextInt();
                denumire = scanner.next();
                numar_inventar = scanner.nextInt();
                pret = scanner.nextFloat();
                zona_magazin = scanner.next();
                situatieInt = scanner.nextInt();
                situatie = situatie_produs.values()[situatieInt];
                if(tip == 0)
                {
                    int ppm;
                    int dpi;
                    int p_car;
                    boolean color;
                    ppm = scanner.nextInt();
                    dpi = scanner.nextInt();
                    p_car = scanner.nextInt();
                    color = scanner.nextBoolean();
                    echipament e = new imprimanta(denumire,numar_inventar,pret,zona_magazin,situatie,ppm,dpi,p_car,color);
                    echipamente.add(e);
                }
                else if(tip == 1)
                {
                    int p_ton;
                    int formatInt;
                    formate format;
                    p_ton = scanner.nextInt();
                    formatInt = scanner.nextInt();
                    format = formate.values()[formatInt];
                    echipament e = new copiator(denumire,numar_inventar,pret,zona_magazin,situatie,p_ton,format);
                    echipamente.add(e);
                }
                else if(tip == 2)
                {
                    String tip_mon;
                    double vit_proc;
                    int c_hdd;
                    int sistemOperareInt;
                    sistem_op sistem_operare;
                    tip_mon = scanner.next();
                    vit_proc = scanner.nextDouble();
                    c_hdd = scanner.nextInt();
                    sistemOperareInt = scanner.nextInt();
                    sistem_operare = sistem_op.values()[sistemOperareInt];
                    echipament e = new sisteme_calcul(denumire,numar_inventar,pret,zona_magazin,situatie,tip_mon,vit_proc,c_hdd,sistem_operare);
                    echipamente.add(e);
                }

            }

        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

    }

    public static void afisare(List<echipament> echipamente)
    {
        System.out.println("Echipamentele: ");
        for(echipament ech: echipamente)
        {
            System.out.println(ech);
        }
    }

    public static void afisare_Imprimanta(List<echipament> echipamente)
    {
        System.out.println("Echipamentele: ");
        for(echipament ech: echipamente)
        {
            if(ech instanceof imprimanta)
                System.out.println(ech);
        }
    }

    public static void afisare_Copiatoar(List<echipament> echipamente)
    {
        System.out.println("Echipamentele: ");
        for(echipament ech: echipamente)
        {
            if(ech instanceof copiator)
                System.out.println(ech);
        }
    }

    public static void afisare_Sistem_calcul(List<echipament> echipamente)
    {
        System.out.println("Echipamentele: ");
        for(echipament ech: echipamente)
        {
            if(ech instanceof sisteme_calcul)
                System.out.println(ech);
        }
    }

    public static void modificare_situatie(List<echipament> echipamente)
    {
        Scanner scanner = new Scanner(System.in);
        String den;
        System.out.println("Dati denumirea echipamentului la care doriti sa modificati situatia: ");
        den = scanner.next();
        int noua_SituatieInt;
        situatie_produs noua_situatie;
        for(echipament ech: echipamente)
        {
            if(den.equals(ech.getDenumire()))
            {
                System.out.println("Dati noua situatie (0->achizitionat/ 1->expus/ 2->vandut): ");
                noua_SituatieInt = scanner.nextInt();
                noua_situatie = situatie_produs.values()[noua_SituatieInt];
                ech.setSituatie(noua_situatie);
            }
        }
    }

    public static void modificare_mod_scriere(List<echipament> echipamente)
    {
        Scanner scanner = new Scanner(System.in);
        String den;
        System.out.println("Dati denumirea imprimantei la care doriti sa modificati formatul: ");
        den = scanner.next();
        boolean noul_modColor_Necolor;
        for(echipament ech: echipamente)
        {
            if((ech instanceof imprimanta) && den.equals(ech.getDenumire()))
            {
                System.out.println("Schimbati modul de tiparire: true(color)/ false(alb-negru): ");
                noul_modColor_Necolor = scanner.nextBoolean();
                imprimanta imp = (imprimanta) ech;
                imp.setColor(noul_modColor_Necolor);
                break;
            }
        }
    }

    public static void modificare_formatCopiere(List<echipament> echipamente)
    {
        Scanner scanner = new Scanner(System.in);
        String den;
        System.out.println("Dati denumirea imprimantei la care doriti sa modificati formatul: ");
        den = scanner.next();
        int newFormatInt;
        formate newFormat;
        for(echipament ech: echipamente)
        {
            if((ech instanceof copiator) && den.equals(ech.getDenumire()))
            {
                copiator cop = (copiator) ech;
                System.out.println("Dati noul format de copiere (0->A3/ 1->A4): ");
                newFormatInt = scanner.nextInt();
                newFormat = formate.values()[newFormatInt];
                cop.setFormat(newFormat);
            }
        }
    }
    public static void modificare_sistem_operare(List<echipament> echipamente)
    {
        Scanner scanner = new Scanner(System.in);
        String den;
        System.out.println("Dati denumirea sistemului de calcul la care doriti sa modificati sistemul de operare: ");
        den = scanner.next();
        int new_sist_op;
        sistem_op new_sistem;
        for(echipament ech: echipamente)
        {
            if((ech instanceof sisteme_calcul) && den.equals(ech.getDenumire()))
            {
                sisteme_calcul sist = (sisteme_calcul) ech;
                System.out.println("Dati noul sistem de operare (0->Windows/ 1->Linux): ");
                new_sist_op = scanner.nextInt();
                new_sistem = sistem_op.values()[new_sist_op];
                sist.setSistemOperare(new_sistem);
            }
        }
    }

    public static void afisare_echipamente_vandute(List<echipament> echipamente)
    {
        for(echipament ech: echipamente)
            if(ech.getSituatie() == situatie_produs.values()[2])
            {
                System.out.println(ech);
            }
    }

    public static void serializare_deserializare(List<echipament> echipamente)
    {
        try
        {
            FileOutputStream f = new FileOutputStream("src/echip.bin");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            for (echipament ech : echipamente) {
                oos.writeObject(ech);
            }
            oos.close();
            f.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}