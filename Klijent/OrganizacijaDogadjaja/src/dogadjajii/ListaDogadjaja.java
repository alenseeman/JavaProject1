package dogadjajii;

import java.util.*;
import java.io.*;
import osobe.Organizator;
import osobe.Ucesnik;

public class ListaDogadjaja extends Dogadjaj implements Serializable {

    protected ArrayList<Dogadjaj> dogadjaji;

    public ListaDogadjaja() {
        super();
        
    }

    public ListaDogadjaja(String n, String dp, String dk, String vp, String vk, String o, Organizator org, ArrayList<Ucesnik> uc, ArrayList<File> f, ArrayList<Dogadjaj> d) {
        super(n, dp, dk, vp, vk, o, org, uc, f);
        dogadjaji = d;
    }

    public ArrayList<Dogadjaj> getListuDogadjaja() {
        return dogadjaji;
    }

}
