package dogadjajii;

import osobe.Organizator;
import osobe.Ucesnik;
import java.util.*;
import java.io.*;
import osobe.Izvodjac;

public class Koncert extends Dogadjaj implements KampanjaInterface {

    protected ArrayList<Izvodjac> izvodjaci;
    protected int trajanjeSati;

    public Koncert() {
        super();
    }

    public Koncert(String n, String dp, String dk, String vp, String vk, String o, Organizator org, ArrayList<Ucesnik> uc, ArrayList<File> f, ArrayList<Izvodjac> izv, int trajanje) {
        super(n, dp, dk, vp, vk, o, org, uc, f);
        izvodjaci = izv;
        trajanjeSati = trajanje;
    }

    public ArrayList<Izvodjac> getIzvodjaci() {
        return izvodjaci;
    }

    public int getTrajanjeMinute() {
        return trajanjeSati;
    }

    public String csvToString() {
        return super.csvToString() + "," + trajanjeSati;
    }
}
