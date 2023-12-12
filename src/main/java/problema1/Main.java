package problema1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Angajat> angajati = citire();
        Scanner scanner= new Scanner(System.in);
        int opt;
        do {
            System.out.println("\n0. Iesire");
            System.out.println("1. Afisarea angajatilor");
            System.out.println("2. Afisarea angajatilor care au salarul sub 2500");
            System.out.println("3. Crearea unei liste de angajati care sa contina sefi sau directori, cu data angajarii in aprilie anul trecut");
            System.out.println("4. Afisarea angajatilor care nu au functie de conducere in ordine descrescatoare a salarului");
            System.out.println("5. Extragerea din lista a numelor angajatilor si afisarea lor cu litere mari");
            System.out.println("6. Afisarea doar a salariilor mai mici de 3000");
            System.out.println("7. Afisarea primului angajat din firma");
            System.out.println("8. Afisarea salarului mediu, minim si maxim");
            System.out.println("9. EXISTA ION PRINTRE ANGAJATI?");
            System.out.println("10. Numarul angajatilor care s-au angajat in vara anului trecut");
            System.out.println("Dati optiunea:");
            opt=scanner.nextInt();
            switch (opt)
            {
                case 0 :
                    System.out.println("BYEEE!");
                    break;
                case 1:
                    angajati.forEach(System.out::println);
                    break;
                case 2:
                    subpct2(angajati);
                    break;
                case 3:
                    subpct3(angajati);
                    break;
                case 4:
                    subpct4(angajati);
                    break;
                case 5:
                    subpct5(angajati);
                    break;
                case 6:
                    subpct6(angajati);
                    break;
                case 7:
                    subpct7(angajati);
                    break;
                case 8:
                    subpct8(angajati);
                    break;
                case 9:
                    subpct9(angajati);
                    break;
                case 10:
                    subpct10(angajati);
                    break;
                default:
                    System.out.println("Optiune inexistenta!");
                    break;
            }


        }while(opt!=0);

    }

    public static List<Angajat> citire() {
        try {
            File file=new File("src/main/resources/angajati.json");
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            List<Angajat> angajati = mapper.readValue(file, new TypeReference<List<Angajat>>(){});
            return angajati;
        } catch (IOException e)
        {
            e.printStackTrace();
        }         return null;
    }

    public static void subpct2(List<Angajat> lista)
    {
        lista.stream()
                .filter(angajat -> angajat.getSalariul() >= 2500)
                .forEach(System.out::println);
    }

    public static void subpct3(List<Angajat> lista)
    {
        LocalDate data_c = LocalDate.now();
        List<Angajat> barosanii = lista.stream()
                .filter(angajat -> angajat.getData_angajarii().getYear() == data_c.getYear()-1)
                .filter(angajat -> angajat.getData_angajarii().getMonthValue() == 4)
                .filter(angajat -> angajat.getPost().equals("director")||angajat.getPost().equals("sef"))
                .collect(Collectors.toList());

        barosanii.forEach(System.out::println);
    }

    public static void subpct4(List<Angajat> lista)
    {
        lista.stream()
                .filter(angajat ->!(angajat.getPost().equals("director")||angajat.getPost().equals("sef")))
                .sorted(Comparator.comparing(Angajat::getSalariul).reversed())
                .forEach(System.out::println);
    }

    public static void subpct5(List<Angajat> lista)
    {
        List<String> nume= lista.stream()
                .map(angajat -> angajat.getNume().toUpperCase())
                .collect(Collectors.toList());

        nume.forEach(System.out::println);
    }

    public static void subpct6(List<Angajat> lista)
    {
        lista.stream()
                .filter(angajat -> angajat.getSalariul()<=3000)
                .map(angajat -> angajat.getSalariul())
                .forEach(System.out::println);
    }

    public static void subpct7(List<Angajat> lista)
    {
        Optional<Angajat> primulAngajat = lista.stream()
                .min(Comparator.comparing(Angajat::getData_angajarii));
        primulAngajat.ifPresentOrElse(
                angajat -> System.out.println("Primul angajat: "+angajat),
                () -> System.out.println("Nu exista angajati!")
        );
    }

    public static void subpct8(List<Angajat> lista)
    {
        DoubleSummaryStatistics statistics = lista.stream()
                .collect(Collectors.summarizingDouble(Angajat::getSalariul));
        System.out.println("Salarul mediu: "+statistics.getAverage());
        System.out.println("Salariul minim: "+statistics.getMin());
        System.out.println("Salariul maxim: "+statistics.getMax());
    }

    public static void subpct9(List<Angajat> lista)
    {
        Optional<Angajat> ionAngajat = lista.stream()
                .filter(angajat -> angajat.getNume().equals("Ion"))
                .findAny();
        ionAngajat.ifPresentOrElse(
                angajat -> System.out.println("Firma are cel puțin un Ion angajat."),
                () -> System.out.println("Firma nu are nici un Ion angajat.")
        );
    }

    public static void subpct10(List<Angajat> lista)
    {
        long nr_angajati = lista.stream()
                .filter(angajat -> angajat.getData_angajarii().getYear()==LocalDate.now().getYear()-1)
                .filter(angajat -> angajat.getData_angajarii().getMonthValue()>=6 && angajat.getData_angajarii().getMonthValue()<=8)
                .count();

        System.out.println("Numarul angajatilor angajati in vara anului trecut: "+nr_angajati);
    }

}
