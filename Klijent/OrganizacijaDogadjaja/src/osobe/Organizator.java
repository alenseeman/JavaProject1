package osobe;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class Organizator extends Osoba implements Serializable {

    protected String telefon;
    protected String mail;
    protected ArrayList<Napomena> napomene;

    public Organizator() {
        super();
    }

    public Organizator(String i, String p, String tel, String mail, ArrayList<Napomena> nap) {
        super(i, p);
        telefon = tel;
        this.mail = mail;
        napomene = nap;
//    napomene=new ArrayList<Napomena>();
//    Napomena a=new Napomena();
//    napomene.add(a);
    }

    public String getTelefon() {
        return telefon;
    }

    public String getMail() {
        return mail;
    }

    public void setTelefon(String tel) {
        telefon = tel;
    }

    public void setMail(String m) {
        mail = m;
    }

    public void setNapomena(String nap) {
        napomene.get(0).setTekstNapomene(nap);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy.");
        napomene.get(0).setDatumKreiranja(sdf.format(new Date()));
    }

    public ArrayList<Napomena> getNapomene() {
        return napomene;
    }

    public String csvToString() {
        return super.csvToString() + "," + telefon + "," + mail;
    }

}
