/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizacijadogadjaja;

import dogadjajii.Dogadjaj;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import osobe.Osoba;

/**
 *
 * @author Semanic
 */
public class Klijent {

    private static Socket sock;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;

    public static void pokreniSocket() {
        try {
            InetAddress addr = InetAddress.getByName("127.0.0.1");
            sock = new Socket(addr, 9000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void poveziInOut() {
        try {
            in = new ObjectInputStream(sock.getInputStream());
            out = new ObjectOutputStream(sock.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Dogadjaj> ucitajTabelu() {
        ArrayList<Dogadjaj> dog = new ArrayList<Dogadjaj>();

        try {
            out.writeObject("UCITAJ#DOGADJAJE#Dogadjaji");
            out.flush();
            out.reset();
            dog = (ArrayList<Dogadjaj>) in.readObject();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return dog;
    }

    public static void obrisiDogadjaj(Dogadjaj dog) {
        try {
            out.writeObject("OBRISI#DOGADJAJ#" + dog.getNaziv());
            out.flush();
            out.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<? extends Osoba> ucitajOsobu(String a) {
        ArrayList<Osoba> osobe = new ArrayList<Osoba>();
        try {
            out.writeObject("UCITAJ#OSOBE#" + a);
            out.flush();
            out.reset();
            osobe = (ArrayList<Osoba>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return osobe;
    }

    public static <T> void dodajOsobu(T a, String vrsta) {
        try {
            out.writeObject("NOVO#OSOBA#" + vrsta);
            out.writeObject(a);
            out.flush();
            out.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void preuzmiListuOsoba(List<? extends Osoba> lista, String naziv) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        try {
            File fajl = new File("Liste" + File.separator + sdf.format(new Date()));
            if (!fajl.exists()) {
                fajl.mkdir();
            }
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fajl + File.separator + naziv + ".txt")));
            for (int i = 0; i < lista.size(); i++) {
                out.println(lista.get(i).csvToString());
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void preuzmiListuDogadjaja(List<? extends Dogadjaj> lista, String naziv) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        try {
            File fajl = new File("Liste" + File.separator + sdf.format(new Date()));
            if (!fajl.exists()) {
                fajl.mkdir();
            }
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fajl + File.separator + naziv + ".txt")));
            for (int i = 0; i < lista.size(); i++) {
                out.println(lista.get(i).csvToString());
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> void dodajNoviDogadjaj(T dog, String vrsta) {
        try {
            out.writeObject("NOVO#DOGADJAJ#" + vrsta);
            out.writeObject(dog);
            out.flush();
            out.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void posaljiFajl(File file) {

        long length = file.length();
        try {
            out.writeObject("SLANJEFAJLA#" + file.getName());
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[(int) length];
            input.read(bytes, 0, bytes.length);
            out.writeObject(bytes);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void obrisiOsobu(Osoba selectedItem, String vrsta) {
        try {
            out.writeObject("OBRISI#OSOBA#" + vrsta);
            out.writeObject(selectedItem);
            out.flush();
            out.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
