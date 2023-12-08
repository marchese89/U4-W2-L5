package antoniogiovanni.marchese.catalogo;

public class Rivista extends Leggibile{

    private Periodicita periodicita;
    public Rivista(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine,Periodicita periodicita) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }
}
