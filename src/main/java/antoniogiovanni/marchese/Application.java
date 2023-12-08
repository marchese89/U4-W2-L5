package antoniogiovanni.marchese;

import antoniogiovanni.marchese.catalogo.*;
import com.github.javafaker.Faker;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Application {

    public static void main(String[] args) {


        Catalogo c = new Catalogo();

        try {
            c.leggiFileDaDisco();
            //c.rimuoviElemento("9791894358278");

//            c.salvaFileSuDisco();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("------------------------------------ RICERCA PER ISBN --------------------------------------------");

        Leggibile perISBN = c.ricercaPerISBN("9780852058411");

        System.out.println(perISBN);

        System.out.println("----------------------------------- RICERCA PER AUTORE --------------------------------------------");

        List<Leggibile> perAutore = c.ricercaPerAutore("Poppy");

        perAutore.forEach(System.out::println);

        System.out.println("------------------------- RICERCA PER ANNO PUBBLICAZIONE ---------------------------------------------");

        List<Leggibile> perAnnoPubblicazione = c.ricercaPerAnnoPubblicazione(1989);

        perAnnoPubblicazione.forEach(System.out::println);

        //c.getCatalogo().forEach(System.out::println);


//        System.out.println("Scrivo file su disco");
//        try {
//            c.salvaFileSuDisco();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }
    public static Catalogo generaLeggibiliCasuali(){

        Faker faker = new Faker(Locale.ITALIAN);

        Catalogo c = new Catalogo();

        Random r = new Random();
        for(int i = 0; i < 30; i++){
            Libro l = new Libro(faker.code().isbn13(),faker.leagueOfLegends().champion(),r.nextInt(1910,2023),r.nextInt(100,450),faker.leagueOfLegends().champion(),faker.starTrek().specie());
            int pr = r.nextInt(1,4);
            Periodicita p = null;
            switch (pr){
                case 1 ->{
                    p = Periodicita.SETTIMANALE;
                }
                case 2 ->{
                    p = Periodicita.MENSILE;
                }
                case 3 ->{
                    p = Periodicita.SEMESTRALE;
                }
            }
            Rivista rv = new Rivista(faker.code().isbn13(),faker.leagueOfLegends().champion(),r.nextInt(1910,2023),r.nextInt(100,450), p);
            c.aggiugiElemento(l);
            c.aggiugiElemento(rv);

        }

        return c;
    }

}
