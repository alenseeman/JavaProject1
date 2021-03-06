package dogadjajii;

import osobe.Organizator;
import osobe.Ucesnik;
import java.util.*;
import java.io.*;

public class Dogadjaj implements Serializable, Comparable {

    protected String naziv;
    protected String datumPocetka;
    protected String datumKraja;
    protected String vrijemePocetka;
    protected String vrijemeKraja;
    protected String opis;
    protected Organizator organizator;
    protected ArrayList<Ucesnik> ucesnici;
    protected ArrayList<File> fajlovi;

    public Dogadjaj() {
        super();
    }

    public Dogadjaj(String a) {
        super();
        naziv = a;
    }

    public Dogadjaj(String n, String dp, String dk, String vp, String vk, String o, Organizator org, ArrayList<Ucesnik> uc, ArrayList<File> f) {
        super();
        naziv = n;
        datumPocetka = dp;
        datumKraja = dk;
        vrijemePocetka = vp;
        vrijemeKraja = vk;
        opis = o;
        organizator = org;
        ucesnici = uc;
        fajlovi = f;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String n) {
        naziv = n;
    }

    public String getDatumPocetka() {
        return datumPocetka;
    }

    public String getDatumKraja() {
        return datumKraja;
    }

    public String getVrijemePocetka() {
        return vrijemePocetka;
    }

    public String getVrijemeKraja() {
        return vrijemeKraja;
    }

    public void setDatumPocetka(String a) {
        datumPocetka = a;
    }

    public void setDatumKraja(String a) {
        datumKraja = a;
    }

    public void setVrijemePocetka(String a) {
        vrijemePocetka = a;
    }

    public void setOpis(String a) {
        opis = a;
    }

    public String getOpis() {
        return opis;
    }

    public void setVrijemeKraja(String a) {
        vrijemeKraja = a;
    }

    public Organizator getOrganizator() {
        return organizator;
    }

    public ArrayList<Ucesnik> getUcesnici() {
        return ucesnici;
    }

    public ArrayList<File> getFajlovi() {
        return fajlovi;
    }

    public String toString() {
        return "Naziv dogadjaja: " + naziv;
    }

    public String csvToString() {
        return naziv + "," + datumPocetka + "," + datumKraja + "," + vrijemePocetka + "," + vrijemeKraja + "," + opis + "," + organizator.csvToString();
    }

    @Override
    public int compareTo(Object o) {
        Dogadjaj dog = (Dogadjaj) o;
        if (datumPocetka.equals(dog.datumPocetka)) {
            return vrijemePocetka.compareTo(dog.vrijemePocetka);
        } else {
            return datumPocetka.compareTo(dog.datumPocetka);
        }
    }

}
