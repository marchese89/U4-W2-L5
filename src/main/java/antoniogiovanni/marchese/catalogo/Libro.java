package antoniogiovanni.marchese.catalogo;

public class Libro extends Leggibile{

    private String autore;
    private String genere;
    public Libro(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine,String autore, String genere) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }
}
