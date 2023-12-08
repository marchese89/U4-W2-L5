package antoniogiovanni.marchese.catalogo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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

    public List<Leggibile> ricercaPerAutore(String autore){
        return catalogo.stream().filter(leggibile -> leggibile instanceof Libro).map(leggibile -> (Libro)leggibile).filter(libro -> libro.getAutore() == autore).map(libro -> (Leggibile)libro).toList();
    }

    public void salvaFileSuDisco() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File("src/main/java/antoniogiovanni.marchese/catalogo.txt"));
        pw.write("");
        for (Leggibile leggibile : catalogo){
            if(leggibile instanceof Libro){
                pw.append("b#");
            } else if (leggibile instanceof Rivista) {
                pw.append("m#");
            }
            pw.append(leggibile.getCodiceISBN());
            pw.append("#");
            pw.append(leggibile.getTitolo());
            pw.append("#");
            pw.append(""+leggibile.getAnnoPubblicazione());
            pw.append("#");
            pw.append(""+leggibile.getNumeroPagine());
            pw.append("#");
            if(leggibile instanceof Libro){
                pw.append(((Libro)leggibile).getAutore());
                pw.append("#");
                pw.append(((Libro)leggibile).getGenere());
                pw.append(System.lineSeparator());
            } else if (leggibile instanceof Rivista) {
                pw.append(((Rivista)leggibile).getPeriodicita().toString());
                pw.append(System.lineSeparator());
            }
        }

    }
}
