/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package organizacijadogadjaja;

import osobe.Napomena;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import osobe.Izvodjac;
import osobe.Organizator;
import osobe.Osoba;
import osobe.Posjetilac;
import osobe.Predavac;
import osobe.Ucesnik;

/**
 * FXML Controller class
 *
 * @author Semanic
 */
public class PodaciOsobeFormaFXMLController implements Initializable {

    @FXML
    private ComboBox<String> izaberiVrstuComboBox;
    @FXML
    private Button kreirajNovuOsobuBtn;
    @FXML
    private Button sacuvajBtn;
    @FXML
    private TableView<Osoba> tabelaOsobaTableView;
    @FXML
    private Label imeLabela;
    @FXML
    private Label prezimeLabela;
    @FXML
    private Label telefonPosjetilacOrganizatorLabela;
    @FXML
    private Label eMailPosjetilacOrganizatorLabela;
    @FXML
    private Label adresaPosjetilacLabela;
    @FXML
    private TextField imeTxtFld;
    @FXML
    private Label nazivOrgUcesnikLabela;
    @FXML
    private TextField nazivOrgUcesnikTxtFld;
    @FXML
    private Label napomenaTxtSadrzajOrganizatorLabela;
    @FXML
    private TextField prezimeTxtFld;
    @FXML
    private TextField napomenaTxtSadrzajOrganizatorTxtFld;
    @FXML
    private TextField adresaPosjetiocaTxtFld;
    @FXML
    private TextField telefonPosjetilacOrganizatorTxtFld;
    @FXML
    private TextField eMailPosjetilacOrganizatorTxtFld;
    private ArrayList<Napomena> napomene = new ArrayList<Napomena>();
    private String vrstaOznacena = "";
    private static Boolean x = false;
    private static Boolean y = false;
    private Osoba selektovanDogadjaj;
    @FXML
    private Button preuzmiBtn;
    @FXML
    private Button obrisiOsobuBtn;
    @FXML
    private Label labelaNovaOsobaVrsta;
    @FXML
    private Button dodajNapomenuBtn;
    @FXML
    private Label brojNapomenaLbl;
    @FXML
    private Label brojNapomenaInteger;

    /**
     * Initializes the controller class.
     */
    public void kreirajTabelu() {
        tabelaOsobaTableView.getColumns().clear();
        TableColumn prviRed = new TableColumn("Ime");
        TableColumn drugiRed = new TableColumn("Prezime");
        tabelaOsobaTableView.getColumns().addAll(prviRed, drugiRed);

        prviRed.setCellValueFactory(
                new PropertyValueFactory<>("ime")
        );
        drugiRed.setCellValueFactory(
                new PropertyValueFactory<>("prezime")
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        izaberiVrstuComboBox.getItems().addAll(
                "Posjetioci",
                "Predavaci",
                "Organizatori",
                "Ucesnici",
                "Izvodjaci"
        );
        kreirajTabelu();
        tabelaOsobaTableView.setStyle("-fx-color:lightblue");
        izaberiVrstuComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                labelaNovaOsobaVrsta.setVisible(false);
                dodajNapomenuBtn.setDisable(true);
                napomene.clear();
                brojNapomenaInteger.setText("0");
                if (arg2 != null) {
                    tabelaOsobaTableView.setVisible(true);
                    kreirajNovuOsobuBtn.setDisable(false);
                    //preuzmiBtn.setDisable(false);
                    tabelaOsobaTableView.getSelectionModel().clearSelection();
                    sacuvajBtn.setDisable(true);
                    ukloniTrenutnuFormu();
                    vrstaOznacena = arg2;
                    ArrayList<? extends Osoba> osobe = Klijent.ucitajOsobu(arg2);
                    ObservableList<Osoba> os = FXCollections.observableArrayList(osobe);
                    os.removeAll(os);
                    if (osobe.size() != 0) {
                        selektovanDogadjaj = osobe.get(0);
                    }
                    os = FXCollections.observableArrayList(osobe);
                    tabelaOsobaTableView.setItems(os);
                } else {

                    kreirajNovuOsobuBtn.setDisable(true);
                }
            }
        });
        preuzmiBtn.disableProperty().bind((izaberiVrstuComboBox.valueProperty().isNull()));
        obrisiOsobuBtn.disableProperty().bind(Bindings.isEmpty(tabelaOsobaTableView.getSelectionModel().getSelectedItems()));
        //  kreirajNovuOsobuBtn.disableProperty().bind(Bindings.isNotEmpty(tabelaOsobaTableView.getSelectionModel().getSelectedItems()));
        //ponistiBtn.disableProperty().bind(Bindings.isEmpty(tabelaOsobaTableView.getSelectionModel().getSelectedItems()));

        tabelaOsobaTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            if (newSelection != null) {
                ukloniTrenutnuFormu();
                kreirajFormu(newSelection);

            } else {
                kreirajFormu(selektovanDogadjaj);
            }
        });

    }

    public void ukloniTrenutnuFormu() {
        imeLabela.setVisible(false);
        imeTxtFld.setVisible(false);
        imeTxtFld.setDisable(true);
        prezimeLabela.setVisible(false);
        prezimeTxtFld.setVisible(false);
        prezimeTxtFld.setDisable(true);

        adresaPosjetilacLabela.setVisible(false);
        adresaPosjetiocaTxtFld.setVisible(false);
        adresaPosjetiocaTxtFld.setDisable(true);
        telefonPosjetilacOrganizatorLabela.setVisible(false);
        telefonPosjetilacOrganizatorTxtFld.setVisible(false);
        telefonPosjetilacOrganizatorTxtFld.setDisable(true);
        eMailPosjetilacOrganizatorLabela.setVisible(false);
        eMailPosjetilacOrganizatorTxtFld.setVisible(false);
        eMailPosjetilacOrganizatorTxtFld.setDisable(true);
        napomenaTxtSadrzajOrganizatorLabela.setVisible(false);
        napomenaTxtSadrzajOrganizatorTxtFld.setVisible(false);
        napomenaTxtSadrzajOrganizatorTxtFld.setDisable(true);
        nazivOrgUcesnikLabela.setVisible(false);
        nazivOrgUcesnikTxtFld.setVisible(false);
        nazivOrgUcesnikTxtFld.setDisable(true);
        dodajNapomenuBtn.setVisible(false);
        brojNapomenaInteger.setVisible(false);
        brojNapomenaLbl.setVisible(false);
        brojNapomenaInteger.setVisible(false);
        brojNapomenaLbl.setVisible(false);
        dodajNapomenuBtn.setVisible(false);

    }

    public void kreirajFormu(Osoba objekat) {
        ukloniTrenutnuFormu();

        imeLabela.setVisible(true);
        imeTxtFld.setVisible(true);
        prezimeLabela.setVisible(true);
        prezimeTxtFld.setVisible(true);
        if (!x) {
            imeTxtFld.setText(objekat.getIme());
            prezimeTxtFld.setText(objekat.getPrezime());
        } else {
            imeTxtFld.clear();
            prezimeTxtFld.clear();
            imeTxtFld.setDisable(false);
            prezimeTxtFld.setDisable(false);
        }
        if (objekat instanceof Posjetilac) {
            Posjetilac ob = (Posjetilac) objekat;
            adresaPosjetilacLabela.setVisible(true);
            adresaPosjetiocaTxtFld.setVisible(true);
            telefonPosjetilacOrganizatorLabela.setVisible(true);
            telefonPosjetilacOrganizatorTxtFld.setVisible(true);
            eMailPosjetilacOrganizatorLabela.setVisible(true);
            eMailPosjetilacOrganizatorTxtFld.setVisible(true);
            if (!x) {
                adresaPosjetiocaTxtFld.setText(ob.getAdresa());
                telefonPosjetilacOrganizatorTxtFld.setText(ob.getTelefon());
                eMailPosjetilacOrganizatorTxtFld.setText(ob.getMail());
            } else {
                adresaPosjetiocaTxtFld.clear();
                adresaPosjetiocaTxtFld.setDisable(false);
                telefonPosjetilacOrganizatorTxtFld.clear();
                telefonPosjetilacOrganizatorTxtFld.setDisable(false);
                eMailPosjetilacOrganizatorTxtFld.clear();
                eMailPosjetilacOrganizatorTxtFld.setDisable(false);
            }
        } else if (objekat instanceof Organizator) {
            Organizator ob = (Organizator) objekat;
            telefonPosjetilacOrganizatorLabela.setVisible(true);
            telefonPosjetilacOrganizatorTxtFld.setVisible(true);
            eMailPosjetilacOrganizatorLabela.setVisible(true);
            eMailPosjetilacOrganizatorTxtFld.setVisible(true);
            napomenaTxtSadrzajOrganizatorLabela.setVisible(true);
            napomenaTxtSadrzajOrganizatorTxtFld.setVisible(true);
            brojNapomenaInteger.setVisible(true);
            brojNapomenaLbl.setVisible(true);
            if (!x) {
                telefonPosjetilacOrganizatorTxtFld.setText(ob.getTelefon());
                eMailPosjetilacOrganizatorTxtFld.setText(ob.getMail());
                ArrayList<Napomena> nap = ob.getNapomene();
                brojNapomenaInteger.setText(nap.size() + "");
                if (nap.size() != 0) {
                    napomenaTxtSadrzajOrganizatorTxtFld.setText(nap.get(0).getTekstNapomene());
                } else {
                    napomenaTxtSadrzajOrganizatorTxtFld.setText("--korisnik nema napomena--");
                }
            } else {
                dodajNapomenuBtn.setVisible(true);

                telefonPosjetilacOrganizatorTxtFld.clear();
                telefonPosjetilacOrganizatorTxtFld.setDisable(false);
                eMailPosjetilacOrganizatorTxtFld.clear();
                eMailPosjetilacOrganizatorTxtFld.setDisable(false);
                napomenaTxtSadrzajOrganizatorTxtFld.clear();
                napomenaTxtSadrzajOrganizatorTxtFld.setDisable(false);
            }

        } else if (objekat instanceof Ucesnik) {
            Ucesnik ob = (Ucesnik) objekat;
            nazivOrgUcesnikLabela.setVisible(true);
            nazivOrgUcesnikTxtFld.setVisible(true);
            if (!x) {
                nazivOrgUcesnikTxtFld.setText(ob.getNazivOrganizacije());
            } else {
                nazivOrgUcesnikTxtFld.clear();
                nazivOrgUcesnikTxtFld.setDisable(false);
            }

        }
    }

    @FXML
    private void klikPrezuzmiiBtn(ActionEvent event) {
        ArrayList<? extends Osoba> osobe = Klijent.ucitajOsobu(vrstaOznacena);
        if (osobe.size() != 0) {
            Klijent.preuzmiListuOsoba(osobe, vrstaOznacena);
            prikaziObavjestenje("Obavjestenje", "Lista je uspjesno preuzeta !");
        } else {
            prikaziObavjestenje("Obavjestenje", "Ne mozete preuzeti listu jer je prazna !");
        }
    }

    @FXML
    private void klikSacuvajBtn(ActionEvent event) {

        String vrsta = izaberiVrstuComboBox.getSelectionModel().selectedItemProperty().getValue();

        if (!imeTxtFld.getText().isEmpty() && !prezimeTxtFld.getText().isEmpty()) {
            Osoba oss = new Osoba();
            if ("Posjetioci".equals(vrstaOznacena)) {
                oss = new Posjetilac(imeTxtFld.getText(), prezimeTxtFld.getText(), adresaPosjetiocaTxtFld.getText(), telefonPosjetilacOrganizatorTxtFld.getText(), eMailPosjetilacOrganizatorTxtFld.getText());
            } else if ("Organizatori".equals(vrstaOznacena)) {
                oss = new Organizator(imeTxtFld.getText(), prezimeTxtFld.getText(), telefonPosjetilacOrganizatorTxtFld.getText(), eMailPosjetilacOrganizatorTxtFld.getText(), napomene);
            } else if ("Predavaci".equals(vrstaOznacena)) {
                oss = new Predavac(imeTxtFld.getText(), prezimeTxtFld.getText());
            } else if ("Ucesnici".equals(vrstaOznacena)) {
                oss = new Ucesnik(imeTxtFld.getText(), prezimeTxtFld.getText(), nazivOrgUcesnikTxtFld.getText());
            } else if ("Izvodjaci".equals(vrstaOznacena)) {
                oss = new Izvodjac(imeTxtFld.getText(), prezimeTxtFld.getText());
            }

            Klijent.dodajOsobu(oss, vrstaOznacena);
            napomene.clear();
            brojNapomenaInteger.setText("0");
            ukloniTrenutnuFormu();
            sacuvajBtn.setDisable(true);
            prikaziObavjestenje("Obavjestenje !", "Uspjesno ste dodali novu osobu !");

            tabelaOsobaTableView.getColumns().get(0).setVisible(false);
            tabelaOsobaTableView.getColumns().get(0).setVisible(true);
            izaberiVrstuComboBox.getSelectionModel().clearSelection();
            labelaNovaOsobaVrsta.setVisible(false);
        } else {
            prikaziObavjestenje("Paznja", "Nisu unesene osnovne informacije !");
        }
    }

    public void prikaziObavjestenje(String naslov, String poruka) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(naslov);
        alert.setHeaderText(null);
        alert.setContentText(poruka);
        alert.showAndWait();
    }

    @FXML
    private void klikKreirajNovuOsobuBtn(ActionEvent event) {
        tabelaOsobaTableView.getSelectionModel().clearSelection();
        ukloniTrenutnuFormu();
        labelaNovaOsobaVrsta.setText("Nova osoba za tabelu : " + vrstaOznacena);

        tabelaOsobaTableView.setVisible(false);
        x = true;
        kreirajFormu(selektovanDogadjaj);
        x = false;
        sacuvajBtn.setDisable(false);
        labelaNovaOsobaVrsta.setVisible(true);
        izaberiVrstuComboBox.getSelectionModel().clearSelection();
        dodajNapomenuBtn.setDisable(false);
        labelaNovaOsobaVrsta.setVisible(true);

    }

    @FXML
    private void klikObrisiOsobu(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Paznja !");
        alert.setHeaderText(null);
        alert.setContentText("Da li ste sigurni da zelite obrisati oznacenu osobu?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Klijent.obrisiOsobu(tabelaOsobaTableView.getSelectionModel().getSelectedItem(), vrstaOznacena);
            prikaziObavjestenje("Obavjestenje !", "Osoba je uspjesno obrisana !");
            izaberiVrstuComboBox.getSelectionModel().clearSelection();
            tabelaOsobaTableView.getSelectionModel().clearSelection();
            ukloniTrenutnuFormu();
            tabelaOsobaTableView.setVisible(false);
        }
    }

    @FXML
    private void klikDodajNapomenu(ActionEvent event) {
        if (!napomenaTxtSadrzajOrganizatorTxtFld.getText().isEmpty()) {
            napomene.add(new Napomena(napomenaTxtSadrzajOrganizatorTxtFld.getText()));
            Integer broj = Integer.parseInt(brojNapomenaInteger.getText());
            broj++;
            brojNapomenaInteger.setText(broj + "");
            napomenaTxtSadrzajOrganizatorTxtFld.clear();
        }
    }

}
