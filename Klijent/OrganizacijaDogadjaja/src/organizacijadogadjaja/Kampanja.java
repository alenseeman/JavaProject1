/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizacijadogadjaja;

import dogadjajii.Dogadjaj;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import osobe.Posjetilac;

/**
 *
 * @author Semanic
 */
public class Kampanja extends Thread {

    private Dogadjaj dogadjaj;

    public Kampanja(Dogadjaj a) {
        dogadjaj = a;

    }

    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh.mm");
        File fajl = new File("kampanja" + File.separator + sdf.format(new Date()));
        fajl.mkdir();
        ArrayList<Posjetilac> posjetioci = (ArrayList<Posjetilac>) Klijent.ucitajOsobu("Posjetioci");
        if (posjetioci.size() != 0) {
            for (Posjetilac posjetilac : posjetioci) {
                try {
                    sleep(1000);
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fajl + File.separator + posjetilac.getIme() + ".txt")));
                    out.println(posjetilac.getIme() + " " + posjetilac.getPrezime());
                    out.println("Naziv dogadjaja: " + dogadjaj.getNaziv());
                    out.println("Datum pocetka dogadjaja: " + dogadjaj.getDatumPocetka());
                    out.println("Datum zavrsetka dogadjaja: " + dogadjaj.getDatumKraja());
                    out.println("Opis dogadjaja :" + dogadjaj.getOpis());
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Obavjestenje !");
                    alert.setHeaderText(null);
                    alert.setContentText("Promotivna kampanja za dogadjaj " + dogadjaj.getNaziv() + " je upravo zavrsila");
                    alert.showAndWait();
                }
            });
        } else {
            System.err.println("Lista posjetilaca je prazna, ne moze se izvrsiti promotivna kampanja !");
        }
    }
}
