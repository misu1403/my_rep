package problema3;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import problema1.PerecheNumere;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner =  new Scanner(System.in);
        List<Mobilier> mobiliere = citire();
        mobiliere.forEach(System.out::println);
        System.out.println("Dati numele mobilierului: ");
        String nume = scanner.nextLine();
        for (Mobilier m : mobiliere)
        {
            if(m.getNume().trim().equals(nume.trim()))
                System.out.println(m);
        }
        System.out.println("*****");
        afisareaArie(mobiliere);
    }

    public static List<Mobilier> citire() throws IOException {
        File file = new File("src/main/resources/mobilier.json");

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectMapper mapper = new ObjectMapper();
            List<Mobilier> mobiliere = mapper.readValue(fileInputStream, new TypeReference<List<Mobilier>>() {});
            return mobiliere;
        } catch (IOException e) {
            // Log or print the exception
            e.printStackTrace();
            // Re-throw the exception to be handled by the calling code
            throw e;
        }
    }

    public static double calculArie(List<Placa> placi)
    {
        double arieMobilier = 0;
        for (Placa placa : placi) {
            arieMobilier += placa.getLungime() * placa.getLatime();
        }
        return arieMobilier;
    }

    private static double calculeazaNumarColtiPal(double arieMobilier) {
        // Dimensiunile unei coale de pal
        double lungimeCoalaPal = 2800; // mm
        double latimeCoalaPal = 2070; // mm
        double arieCoalaPal = lungimeCoalaPal * latimeCoalaPal;

        // Calculeaza numarul de colti de pal necesari
        return arieMobilier / arieCoalaPal;
    }

    public static void afisareaArie(List<Mobilier> mobiliere)
    {
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Dati numele mobilierului: ");
        String nume = scanner.nextLine();
        for(Mobilier m : mobiliere)
        {
            if(m.getNume().trim().equals(nume.trim()))
            {
                double arieMobilier= calculArie(m.getPlaci());
                double nrColtiPal = calculeazaNumarColtiPal(arieMobilier);
                System.out.println("Numar estimativ de colti de pal necesari pentru " + nume + ": " + nrColtiPal);
                return;
            }
        }

    }

}
