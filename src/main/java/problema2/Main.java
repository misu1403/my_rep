package problema2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args)
    {
        //1
        Set<InstrumentMuzical> instrumenteSet = new HashSet<>();
        instrumenteSet.add(new Chitara("Fender", 1000, TipChitara.ELECTRICA, 6));
        instrumenteSet.add(new Chitara("Yamaha", 800, TipChitara.ACUSTICA, 4));
        instrumenteSet.add(new Chitara("Gibson", 3500, TipChitara.CLASICA, 8));
        instrumenteSet.add(new SetTobe("Roland", 2000, TipTobe.ELECTRONICE, 5, 3));
        instrumenteSet.add(new SetTobe("Pearl", 1500, TipTobe.ACUSTICE, 8, 2));
        instrumenteSet.add(new SetTobe("Yamaha", 3800, TipTobe.ACUSTICE, 4, 2));
        //2
        incarcareFisierJson(instrumenteSet);

        //3
        Set<InstrumentMuzical> instrumenteSet2 = citire();

        //4
        instrumenteSet2.forEach(System.out::println);

        //5
        Chitara duplicata= new Chitara("Fender", 1000, TipChitara.ELECTRICA, 6);
        if(adaugare(instrumenteSet2,duplicata))
        {
            System.out.println("Chitara a fost adaugata in colectie!");
        }
        else
        {
            System.out.println("Chitara aceasta exista deja!");
        }

        /*
        //6
        stergere(instrumenteSet2);
        System.out.println("\n\nInstrumente dupa stergere: ");
        instrumenteSet2.forEach(System.out::println);
         */

        //7
        System.out.println("\n\nAFISAREA CHITARELOR: ");
        subpct7(instrumenteSet2);

        //8
        System.out.println("\n\nAFISAREA TOBELOR: ");
        subpct8(instrumenteSet2);

        //9
        System.out.println("\n\nChitara cu cele mai multe corzi: ");
        subpct9(instrumenteSet2);

        //10
        instrumenteSet2.add(new SetTobe("Yamaha", 2800, TipTobe.ACUSTICE, 5, 2));
        System.out.println("\n\nTobele ACUSTICE ordonate dupa numarul de tobe: ");
        subpct10(instrumenteSet2);
    }

    public static void incarcareFisierJson(Set<InstrumentMuzical> set)
    {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
            mapper.writeValue(new File("src/main/resources/instrumente.json"), set);
            System.out.println("Colecția a fost salvată în instrumente.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<InstrumentMuzical> citire()
    {
        Set<InstrumentMuzical> set = new HashSet<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File("src/main/resources/instrumente.json"), new TypeReference<Set<InstrumentMuzical>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean adaugare(Set<InstrumentMuzical> set, InstrumentMuzical c)
    {
        return set.add(c);
    }

    public static void stergere(Set<InstrumentMuzical> set) {
        set.removeIf(instrumentMuzical -> instrumentMuzical.getPret()>=3000);
    }

    public static void subpct7(Set<InstrumentMuzical> set)
    {
        set.stream()
                .filter(instrumentMuzical -> instrumentMuzical instanceof Chitara)
                .forEach(System.out::println);
    }

    public static void subpct8(Set<InstrumentMuzical> set)
    {
        set.stream()
                .filter(instrumentMuzical -> instrumentMuzical.getClass() == SetTobe.class)
                .forEach(System.out::println);
    }

    public static void subpct9(Set<InstrumentMuzical> set)
    {
        Optional<Chitara> multeCorzi = set.stream()
                .filter(instrumentMuzical -> instrumentMuzical instanceof Chitara)
                .map(instrumentMuzical -> (Chitara)instrumentMuzical)
                .max(Comparator.comparing(Chitara::getNr_corzi));

        multeCorzi.ifPresent(chitara -> System.out.println(chitara));
    }

    public static void subpct10(Set<InstrumentMuzical> set)
    {
        set.stream()
                .filter(instrumentMuzical -> instrumentMuzical instanceof SetTobe)
                .map(instrumentMuzical ->(SetTobe) instrumentMuzical)
                .filter(setTobe -> setTobe.getTipTobe().equals(TipTobe.ACUSTICE))
                .sorted(Comparator.comparing(SetTobe::getNr_tobe))
                .forEach(System.out::println);

    }

}