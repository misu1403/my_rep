package org.example;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public record Carte(String titlul, String autorul, int anul) {
    @Override
    public String toString() {
        return "\nTitlu: "+titlul+"\nAutor: "+autorul+"\nAnul aparitiei: "+anul;
    }
}
