/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizacijadogadjaja;

import dogadjajii.KampanjaInterface;
import dogadjajii.Dogadjaj;
import osobe.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Semanic
 */
public class GlavnaFormaFXMLController implements Initializable {

    @FXML
    private TableView<Dogadjaj> tabelaDogadjaja;
    @FXML
    private Button kreirajDogadjajBtn;
    @FXML
    private Button pokreniKampanjuBtn;
    @FXML
    private Button obrisiDogadjajBtn;
    @FXML
    private SplitPane splitPane;
    @FXML
    private Button podaciOsobeBtn;
    @FXML
    private Button preuzmiListuDogadjajaBtn;
    private static final String COMMA_DELIMITER = ",";
    private static ObservableList<Dogadjaj> data;
    @FXML
    private Button osvjeziTabeluBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Dogadjaj> dog = Klijent.ucitajTabelu();
        ObservableList<Dogadjaj> data = FXCollections.observableArrayList(dog);
        TableColumn prviRed = new TableColumn("Naziv Dogadjaja");
        TableColumn drugiRed = new TableColumn("Datum pocetka");
        TableColumn treciRed = new TableColumn("Datum kraja");
        TableColumn trRed = new TableColumn("Vrijeme pocetka");
        TableColumn cetvrtiRed = new TableColumn("Opis");
        TableColumn petiRed = new TableColumn("Organizator");
        trRed.setMaxWidth(3000);
        prviRed.setMinWidth(80);
        cetvrtiRed.setMinWidth(130);
        tabelaDogadjaja.getColumns().addAll(prviRed, drugiRed, treciRed, trRed, cetvrtiRed, petiRed);
        data = FXCollections.observableArrayList(dog);
        prviRed.setCellValueFactory(
                new PropertyValueFactory<>("naziv")
        );
        drugiRed.setCellValueFactory(
                new PropertyValueFactory<>("datumPocetka")
        );
        treciRed.setCellValueFactory(
                new PropertyValueFactory<>("datumKraja")
        );

        trRed.setCellValueFactory(
                new PropertyValueFactory<>("vrijemePocetka")
        );
        cetvrtiRed.setCellValueFactory(
                new PropertyValueFactory<>("opis")
        );
        petiRed.setCellValueFactory(
                new PropertyValueFactory<>("organizator")
        );

        tabelaDogadjaja.setItems(data);
//IDEJA ZA RAZLIKOVANJE DOGADJAJA

        tabelaDogadjaja.setRowFactory(tv -> new TableRow<Dogadjaj>() {
            @Override
            public void updateItem(Dogadjaj item, boolean empty) {
                super.updateItem(item, empty);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String datum = sdf.format(new Date());
                if (item == null) {
                    setStyle("");
                } else {
                    Integer comp = item.getDatumPocetka().compareTo(datum);
                    Integer comb = item.getDatumKraja().compareTo(datum);

                    if (comb < 0) {
                        setStyle("-fx-text-background-color: red;");
                    }
                    if (comp > 0) {
                        setStyle("");
                    }

                    if (comb == 0 || comp == 0) {
                        setStyle("-fx-font-weight:bold;");
                    }
                    if (comb <= 0 && comp >= 0) {
                        setStyle("-fx-font-weight:bold;");
                    }
                }
            }
        });
        tabelaDogadjaja.setStyle("-fx-color:lightblue");

        obrisiDogadjajBtn.disableProperty().bind(Bindings.isEmpty(tabelaDogadjaja.getSelectionModel().getSelectedItems()));

        tabelaDogadjaja.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection instanceof KampanjaInterface) {
                pokreniKampanjuBtn.setDisable(false);
            } else {
                pokreniKampanjuBtn.setDisable(true);
            }
        });

    }

    @FXML
    private void klikPokreniKampanju(ActionEvent event) {
        prikaziObavjestenje("Obavjestenje", "Kampanja za izabrani dogadjaj je pocela !");
        Kampanja a = new Kampanja(tabelaDogadjaja.getSelectionModel().getSelectedItem());
        a.start();
    }

    @FXML
    private void klikKreirajDogadjaj(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        Parent root3 = FXMLLoader.load(getClass().getResource("KreirajDogadjajFormaFXML.fxml"));
        Scene scene = new Scene(root3);
        stage.setTitle("Novi Dogadjaj");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public void prikaziObavjestenje(String naslov, String poruka) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(naslov);
        alert.setHeaderText(null);
        alert.setContentText(poruka);
        alert.showAndWait();
    }

    @FXML
    private void klikObrisiDogadjaj(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Paznja !");
        alert.setHeaderText(null);
        alert.setContentText("Da li ste sigurni da zelite obrisati oznaceni dogadjaj?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Dogadjaj ddd = tabelaDogadjaja.getSelectionModel().getSelectedItem();
            Klijent.obrisiDogadjaj(ddd);
            ArrayList<Dogadjaj> dog = Klijent.ucitajTabelu();
            ObservableList<Dogadjaj> data = FXCollections.observableArrayList(dog);
            data = FXCollections.observableArrayList(dog);
            tabelaDogadjaja.setItems(data);

        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    @FXML
    private void klikPodaciOsobe(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root4 = FXMLLoader.load(getClass().getResource("PodaciOsobeFormaFXML.fxml"));
        Scene scene = new Scene(root4);
        stage.setTitle("Podaci o osobama");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    @FXML
    private void klikPreuzmiListuDogadjaja(ActionEvent event) {
        ArrayList<Dogadjaj> dogadjaji = Klijent.ucitajTabelu();
        Klijent.preuzmiListuDogadjaja(dogadjaji, "Dogadjaji");
        if (dogadjaji.size() != 0) {
            prikaziObavjestenje("Obavjestenje", "Uspjesno ste preuzeli fajl !");
        } else {
            prikaziObavjestenje("Obavjestenje", "Preuzeli ste prazan fajl !");
        }
    }

    @FXML
    private void klikOsvjeziTabeluBtn(ActionEvent event) {

        ArrayList<Dogadjaj> dog = Klijent.ucitajTabelu();
        data = FXCollections.observableArrayList(dog);
        tabelaDogadjaja.setItems(data);
    }

}
