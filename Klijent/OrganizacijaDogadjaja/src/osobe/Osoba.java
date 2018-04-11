package osobe;

import java.io.*;

public class Osoba implements Serializable {

    protected String ime;
    protected String prezime;

    public Osoba() {
        super();
    }

    public Osoba(String i, String p) {
        super();
        ime = i;
        prezime = p;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setIme(String i) {
        ime = i;
    }

    public void setPrezime(String p) {
        prezime = p;
    }

    public String toString() {
        return ime + " " + prezime;
    }

    public String csvToString() {
        return ime + "," + prezime;
    }
}
