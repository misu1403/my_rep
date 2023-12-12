package org.example;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main
{
    public static void main(String[] args) throws IOException {

        Map<Integer,Carte> carti = citire_fisier();
        //1
        System.out.println("SUBPCT1");
        afisare(carti);
        /*
        //2
        System.out.println("SUBPCT2");
        subpct2(5,carti );
        afisare(carti);
        //3
        System.out.println("SUBPCT3");
        subpct3(carti);
        afisare(carti);
        //4
        subpct4(carti);
         */
        //5
        System.out.println("\n\nSetul de carti: ");
        Set<Carte> c_autor= subpct5(carti);
        //6
        System.out.println("\n\nSetul ordonat dupa titlu: ");
        subpct6(c_autor);
        //7
        System.out.println("\n\nCartea cea mai veche: ");
        subpct7(c_autor);

    }

    public static Map<Integer,Carte> citire_fisier() throws IOException {
        File file=new File("src/main/resources/carti.json");
        ObjectMapper mapper=new ObjectMapper();
        Map<Integer,Carte> mapa = mapper.readValue(file, new TypeReference<Map<Integer, Carte>>() {});
        return mapa;
    }

    public static void afisare(Map<Integer,Carte> carti)
    {
//        for(int key: carti.keySet()) {
//            System.out.println("\nCheia: "+key+"\nValoarea: "+carti.get(key));
//        }
        carti.entrySet().forEach(System.out::println);
    }

    public static void subpct2(int id,Map<Integer,Carte> carti)
    {
        carti.remove(id);
    }

    public static void subpct3(Map<Integer,Carte> carti)
    {
        Carte carte= new Carte("Ion", "Liviu Rebreanu", 1920);
        carti.putIfAbsent(5, carte);
    }

    public static void subpct4(Map<Integer,Carte> carti) throws IOException {
        File file = new File("src/main/resources/carti.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, carti);
    }


    public static Set<Carte> subpct5(Map<Integer,Carte> carti)
    {
        Set<Carte> anumit_autor=carti.values().stream()
                .filter(carte -> carte.autorul().trim().equals("Yuval Noah Harari".trim()))
                .collect(Collectors.toSet());

        anumit_autor.forEach(System.out::println);
        return anumit_autor;
    }

    public static void subpct6(Set<Carte> carti)
    {
        carti.stream()
                .sorted(Comparator.comparing(Carte::titlul))
                .forEach(System.out::println);
    }

    public static void subpct7(Set<Carte> carti)
    {
        Optional<Carte> ceaMaiVeche=carti.stream()
                .min((c1, c2)->Integer.compare(c1.anul(),c2.anul()));

        ceaMaiVeche.ifPresent(carte ->
        {
            System.out.println(carte);
        });
    }
}