package problema1;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Main {
    public static void main(String[] args) {
        List<PerecheNumere> lista = citire();
        scriere(lista);
        lista.forEach(System.out::println);

    }

    public static List<PerecheNumere> citire() {
        try {
            File file=new File("src/main/resources/NumerePereche.json");
            ObjectMapper mapper=new ObjectMapper();
            List<PerecheNumere> persoane = mapper.readValue(file, new TypeReference<List<PerecheNumere>>(){});
            return persoane;
        } catch (IOException e)
        {
            e.printStackTrace();
        }         return null;
    }

    public static void scriere(List<PerecheNumere> lista)
    {
        try {
            ObjectMapper mapper=new ObjectMapper();
            File file=new File("src/main/resources/out.json");
            mapper.writeValue(file,lista);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
