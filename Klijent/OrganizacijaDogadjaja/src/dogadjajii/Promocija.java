package dogadjajii;

import osobe.Organizator;
import osobe.Ucesnik;
import java.util.*;
import java.io.*;

public class Promocija extends Dogadjaj implements KampanjaInterface {

    protected String nazivBrenda;

    public Promocija() {
        super();
    }

    public Promocija(String n, String dp, String dk, String vp, String vk, String o, Organizator org, ArrayList<Ucesnik> uc, ArrayList<File> f, String nb) {
        super(n, dp, dk, vp, vk, o, org, uc, f);
        nazivBrenda = nb;
    }

    public String getNazivBrenda() {
        return nazivBrenda;
    }

    public String csvToString() {
        return super.csvToString() + "," + nazivBrenda;
    }
}
