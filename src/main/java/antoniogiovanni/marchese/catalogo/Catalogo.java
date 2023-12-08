package antoniogiovanni.marchese.catalogo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Predicate;

public class Catalogo {
    private List<Leggibile> catalogo;

    public List<Leggibile> getCatalogo() {
        return catalogo;
    }

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

    public Leggibile ricercaPerISBN(String isbn){
        return catalogo.stream().filter(leggibile -> leggibile.getCodiceISBN().equals(isbn)).toList().get(0);
    }

    public List<Leggibile> ricercaPerAnnoPubblicazione(int annoPubblicazione){
        return catalogo.stream().filter(leggibile -> leggibile.getAnnoPubblicazione() == annoPubblicazione).toList();
    }

    public List<Leggibile> ricercaPerAutore(String autore){
        return catalogo.stream().filter(leggibile -> leggibile instanceof Libro).map(leggibile -> (Libro)leggibile).filter(libro -> libro.getAutore().equals(autore)).map(libro -> (Leggibile)libro).toList();
    }

    public void salvaFileSuDisco() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File("catalogo.txt"));
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
        pw.close();
    }

    public void leggiFileDaDisco() throws FileNotFoundException {

        Scanner sc = new Scanner(new File("catalogo.txt"));
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            StringTokenizer stk = new StringTokenizer(line,"#");
            String tipo = stk.nextToken();
            if(tipo.equals("b")){
                String codiceISBN = stk.nextToken();
                String titolo = stk.nextToken();
                String annoPubblicazione = stk.nextToken();
                String numeroPagine = stk.nextToken();
                String autore = stk.nextToken();
                String genere = stk.nextToken();
                Libro l = new Libro(codiceISBN,titolo,Integer.parseInt(annoPubblicazione),Integer.parseInt(numeroPagine),autore,genere);
                this.catalogo.add(l);
            } else if (tipo.equals("m")) {
                String codiceISBN = stk.nextToken();
                String titolo = stk.nextToken();
                String annoPubblicazione = stk.nextToken();
                String numeroPagine = stk.nextToken();
                String periodicita = stk.nextToken();
                Periodicita p;
                switch (periodicita){
                    case "SETTIMANALE" ->{
                        p = Periodicita.SETTIMANALE;
                    }
                    case "MENSILE" ->{
                        p = Periodicita.MENSILE;
                    }
                    case "SEMESTRALE" -> {
                        p = Periodicita.SEMESTRALE;
                    }
                    default -> {
                        p = null;
                    }
                }
                Rivista r = new Rivista(codiceISBN,titolo,Integer.parseInt(annoPubblicazione),Integer.parseInt(numeroPagine),p);

                this.catalogo.add(r);
            }
        }
        sc.close();
    }
}
