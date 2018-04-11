package dogadjajii;

import osobe.Organizator;
import osobe.Ucesnik;
import java.util.*;
import java.io.*;
import osobe.Predavac;

public class Predavanja extends Dogadjaj {

    protected String tema;
    protected Predavac predavac;
    protected String sadrzaj;

    public Predavanja() {
        super();
    }

    public Predavanja(String n, String dp, String dk, String vp, String vk, String o, Organizator org, ArrayList<Ucesnik> uc, ArrayList<File> f, String te, Predavac pre, String sa) {
        super(n, dp, dk, vp, vk, o, org, uc, f);
        tema = te;
        predavac = pre;
        sadrzaj = sa;
    }

    public String getTema() {
        return tema;
    }

    public Predavac getPredavac() {
        return predavac;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public String csvToString() {
        return super.csvToString() + "," + tema + "," + predavac.csvToString() + "," + sadrzaj;
    }

}
