package antoniogiovanni.marchese.catalogo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Catalogo {
    private List<Leggibile> catalogo;

    public Catalogo() {
        this.catalogo = new ArrayList<>();
    }

    public void aggiugiElemento(Leggibile leggibile){
        this.catalogo.add(leggibile);
    }

    public void rimuoviElemento(String codiceISBN){
        Predicate<String> isbnOK = (codice) -> codice.equals(codiceISBN);
        this.catalogo.removeIf(leggibile -> isbnOK.test(leggibile.getCodiceISBN()));
    }

    public List<Leggibile> ricercaPerAnnoPubblicazione(int annoPubblicazione){
        return catalogo.stream().filter(leggibile -> leggibile.getAnnoPubblicazione() == annoPubblicazione).toList();
    }
}
