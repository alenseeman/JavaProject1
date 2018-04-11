package organizacijadogadjaja;

import dogadjajii.Dogadjaj;
import dogadjajii.Izlozba;
import dogadjajii.Koncert;
import dogadjajii.ListaDogadjaja;
import dogadjajii.Predavanja;
import dogadjajii.Promocija;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import osobe.Izvodjac;
import osobe.Organizator;
import osobe.Predavac;
import osobe.Ucesnik;

/**
 * FXML Controller class
 *
 * @author Semanic
 */
public class KreirajDogadjajFormaFXMLController implements Initializable {

    @FXML
    private Button dodajFajlBtn;
    @FXML
    private Button snimiIzmjeneBtn;
    private TextField kompanijaTxtField;
    @FXML
    private DatePicker datumPocetkaDatePicker;
    @FXML
    private DatePicker datumZavrsetkaDatePicker;
    @FXML
    private TextField nazivDogadjajaTxtFld;
    @FXML
    private TextField vrijemePocetkaTxtFld;
    @FXML
    private TextField vrijemeKrajaTxtFld;
    @FXML
    private ComboBox<String> vrstaDogadjajaComboBox;
    @FXML
    private TextField opisTxtFld;
    @FXML
    private ComboBox<Organizator> organizatorComboBox;
    @FXML
    private ComboBox<Ucesnik> ucesniciComboBox;
    @FXML
    private Label labelaKompanijaPromocija;
    @FXML
    private TextField kompanijaPromocijaTxtField;
    @FXML
    private Label labelaTemaPredavanja;
    @FXML
    private Label labelaPredavacPredavanje;
    @FXML
    private Label labelaSadrzajPredavanje;
    @FXML
    private TextField temaPredavanjaTxtFld;
    @FXML
    private TextField sadrzajPredavanjeTxtFld;
    @FXML
    private RadioButton prijePodneBtn;
    @FXML
    private RadioButton poslijePodneBtn;
    @FXML
    private RadioButton prijePodne2Btn;
    @FXML
    private RadioButton poslijePodne2Btn;
    @FXML
    private Label nazivDogadjajaLbl;
    @FXML
    private Label datumPocetkaLbl;
    @FXML
    private Label datumZavrsetkaLbl;
    @FXML
    private Label vrijemePocetkaLbl;
    @FXML
    private Label vrijemeKrajaLbl;
    @FXML
    private Label opisLbl;
    @FXML
    private ComboBox<Predavac> predavacPredavanjaComboBox;
    @FXML
    private Button dodajUcesnikaBtn;
    @FXML
    private ListView<Ucesnik> listaUcesnikaList;
    @FXML
    private Label listaDodanihUcesnikaLbl;
    @FXML
    private Label trajanjeLabel;
    @FXML
    private ComboBox<Integer> trajanjeComboBox;
    @FXML
    private ComboBox<Izvodjac> izvodjaciComboBox;
    @FXML
    private Button dodajIzvodjaceBtn;
    @FXML
    private Label listaDodanihIzvodjacaLabel;
    @FXML
    private ListView<Izvodjac> listaDodanihIzvodjacaList;
    @FXML
    private Label temaIzlozbaLabel;
    @FXML
    private Label autorLabel;
    @FXML
    private TextField autorTxtFld;

    private String vrstaDogadjaja;
    @FXML
    private Label organizatorLabela;
    @FXML
    private Label ucesniciLabela;
    @FXML
    private Slider vrijemePocetkaSlider = new Slider(0, 720, 0);
    @FXML
    private Slider vrijemeKrajaSlider = new Slider(0, 1, 0);
    @FXML
    private TextField temaIzlozbaTxtFld;
    @FXML
    private Label izvodjaciLabel;
    @FXML
    private ListView<String> listaDodanihAutoraList;
    @FXML
    private Label listaDodanihAutoraLabela;
    @FXML
    private Button dodajAutoraBtn;
    private boolean kreiranjeNovogDogadjaja = false;
    private ObservableList<Izvodjac> izz = FXCollections.observableArrayList();
    private ObservableList<Ucesnik> ucc = FXCollections.observableArrayList();
    private ObservableList<String> aut = FXCollections.observableArrayList();
    private ObservableList<Dogadjaj> dogg = FXCollections.observableArrayList();
    private ArrayList<File> fajlovi = new ArrayList<File>();
    private Button kreirajMiniDogadjajBtn;

    private boolean viseDogajaja = false;
    private ArrayList<Dogadjaj> podDogadjaji = new ArrayList<Dogadjaj>();
    @FXML
    private Label listaDodanihDogadjajaLabel;
    @FXML
    private ListView<Dogadjaj> listaDodanihDogadjajaListView;
    @FXML
    private ComboBox<Dogadjaj> izabraniDogadjajiComboBox;
    @FXML
    private Button dodajIzabraniDogadjajBtn;
    @FXML
    private Label labelaDogadjaji;
    @FXML
    private Label labelaBrojFajlova;
    @FXML
    private Label labelaKolicinaFajlova;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final Callback<DatePicker, DateCell> dayCellFactory
                = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(
                                datumPocetkaDatePicker.getValue().plusDays(0))) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        final Callback<DatePicker, DateCell> dayCellFactory2
                = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(
                                LocalDate.now().plusDays(0))) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        datumZavrsetkaDatePicker.setDayCellFactory(dayCellFactory);
        datumPocetkaDatePicker.setDayCellFactory(dayCellFactory2);
        datumPocetkaDatePicker.setValue(LocalDate.now());
        datumPocetkaDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            if (datumZavrsetkaDatePicker.getValue().isBefore(newValue)) {
                datumZavrsetkaDatePicker.setValue(newValue.plusDays(0));
            }
        });
        datumZavrsetkaDatePicker.setValue(datumPocetkaDatePicker.getValue().plusDays(0));
        final ToggleGroup grupa1 = new ToggleGroup();
        prijePodneBtn.setSelected(true);
        prijePodneBtn.setToggleGroup(grupa1);
        poslijePodneBtn.setToggleGroup(grupa1);
        final ToggleGroup grupa2 = new ToggleGroup();
        poslijePodne2Btn.setSelected(true);
        prijePodne2Btn.setToggleGroup(grupa2);
        poslijePodne2Btn.setToggleGroup(grupa2);

        podesiSlider(vrijemeKrajaSlider, vrijemeKrajaTxtFld);
        podesiSlider(vrijemePocetkaSlider, vrijemePocetkaTxtFld);

        vrstaDogadjajaComboBox.getItems().addAll(
                "Promocije",
                "Predavanja",
                "Koncerti",
                "Izlozbe",
                "Grupa dogadjaja",
                "Ostali ..."
        );
        trajanjeComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        dodajUcesnikaBtn.disableProperty().bind(ucesniciComboBox.valueProperty().isNull());
        dodajAutoraBtn.disableProperty().bind(autorTxtFld.textProperty().isEmpty());
        dodajIzabraniDogadjajBtn.disableProperty().bind(izabraniDogadjajiComboBox.valueProperty().isNull());
        dodajIzvodjaceBtn.disableProperty().bind(izvodjaciComboBox.valueProperty().isNull());
        vrstaDogadjajaComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                vrstaDogadjaja = arg2;
                ucitajComboBox(ucesniciComboBox, "Ucesnici");
                ucitajComboBox(organizatorComboBox, "Organizatori");
                if ("Grupa dogadjaja".equals(arg2)) {
                    izabraniDogadjajiComboBox.getSelectionModel().clearSelection();
                    ucitajComboBox(izabraniDogadjajiComboBox, "Dogadjaji");
                }
                ukloniTrenutnuFormu(true);
                izz.clear();
                ucc.clear();
                aut.clear();
                fajlovi.clear();
                labelaKolicinaFajlova.setText(0 + "");
                dogg.clear();
                listaDodanihAutoraList.getItems().clear();
                listaDodanihIzvodjacaList.getItems().clear();
                listaUcesnikaList.getItems().clear();
                listaDodanihDogadjajaListView.getItems().clear();
                postaviOdgovarajucuFormu(vrstaDogadjaja);

            }
        });
    }

    public <T> void ucitajComboBox(ComboBox cmx, String vrsta) {
        ArrayList<T> lista;
        if (!"Dogadjaji".equals(vrsta)) {
            lista = (ArrayList<T>) Klijent.ucitajOsobu(vrsta);
        } else {
            lista = (ArrayList<T>) Klijent.ucitajTabelu();
        }
        ObservableList<T> os = FXCollections.observableArrayList(lista);
        cmx.setItems(os);
    }

    public String upisiTacnoVrijeme(String vrijeme, boolean pocetak) {
        int sat = 0;
        try {
            sat = Integer.parseInt(vrijeme.split(" : ")[0]);
        } catch (NumberFormatException e) {
            prikaziObavjestenje("Greska", "Pogresno unesen format vremena");
            return vrijeme;
        }
        if (pocetak) {
            if (poslijePodneBtn.isSelected()) {
                sat += 12;
            }
        } else if (poslijePodne2Btn.isSelected()) {
            sat += 12;
        }
        return sat + " : " + vrijeme.split(" : ")[1];
    }

    public void podesiSlider(Slider slajder, TextField polje) {
        slajder.setMajorTickUnit(30);
        slajder.setMinorTickCount(0);
        slajder.setMin(0);
        slajder.setMax(719);
        slajder.setSnapToTicks(true);
        slajder.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                Integer a = ((int) slajder.getValue()) % 60;
                Integer b = ((int) slajder.getValue()) / 60;
                String bb = b + "";
                String aa = a + "";
                polje.setText(("00" + bb).substring(bb.length()) + " : " + ("00" + aa).substring(aa.length()));
            }
        });
    }

    public void prikaziObavjestenje(String naslov, String poruka) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(naslov);
        alert.setHeaderText(null);
        alert.setContentText(poruka);
        alert.showAndWait();
    }

    public void postaviOdgovarajucuFormu(String vrsta) {
        if (vrsta != null) {
            nazivDogadjajaLbl.setDisable(false);
            nazivDogadjajaTxtFld.setDisable(false);
            // nazivDogadjajaTxtFld.clear();
            datumPocetkaDatePicker.setDisable(false);
            datumZavrsetkaDatePicker.setDisable(false);
            vrijemeKrajaTxtFld.setDisable(false);
            vrijemePocetkaTxtFld.setDisable(false);
            opisTxtFld.setDisable(false);
            // opisTxtFld.clear();

            datumZavrsetkaLbl.setDisable(false);
            vrijemeKrajaLbl.setDisable(false);
            vrijemePocetkaLbl.setDisable(false);
            datumPocetkaLbl.setDisable(false);
            opisLbl.setDisable(false);
            organizatorComboBox.setDisable(false);
            ucesniciComboBox.setDisable(false);
            listaDodanihUcesnikaLbl.setDisable(false);
            listaUcesnikaList.setDisable(false);
            dodajFajlBtn.setDisable(false);
            labelaBrojFajlova.setDisable(false);
            labelaKolicinaFajlova.setDisable(false);
            prijePodneBtn.setDisable(false);
            prijePodne2Btn.setDisable(false);
            poslijePodne2Btn.setDisable(false);
            poslijePodneBtn.setDisable(false);
            vrijemeKrajaSlider.setDisable(false);
            vrijemePocetkaSlider.setDisable(false);
            organizatorLabela.setDisable(false);
            ucesniciLabela.setDisable(false);
            snimiIzmjeneBtn.setDisable(false);
//   ??     dodajIzabraniDogadjajBtn.setDisable(false);
        }
        Stage stage = (Stage) snimiIzmjeneBtn.getScene().getWindow();
        if ("Promocije".equals(vrsta)) {
            if (kreiranjeNovogDogadjaja) {
                if (!nazivDogadjajaTxtFld.getText().isEmpty() && !opisTxtFld.getText().isEmpty() && vrijemePocetkaTxtFld != null && vrijemeKrajaTxtFld != null && organizatorComboBox.getSelectionModel().getSelectedItem() != null && ucc.size() != 0 && fajlovi.size() != 0 && !kompanijaPromocijaTxtField.getText().isEmpty()) {
                    Promocija a = new Promocija(nazivDogadjajaTxtFld.getText(), datumPocetkaDatePicker.getValue().toString(), datumZavrsetkaDatePicker.getValue().toString(), upisiTacnoVrijeme(vrijemePocetkaTxtFld.getText(), true), upisiTacnoVrijeme(vrijemeKrajaTxtFld.getText(), false), opisTxtFld.getText(), organizatorComboBox.getValue(), new ArrayList<Ucesnik>(ucc), fajlovi, kompanijaPromocijaTxtField.getText());
                    Klijent.dodajNoviDogadjaj(a, vrsta);
                    prikaziObavjestenje("Informacija", "Uspjesno ste kreirali novi dogadjaj !");
                    stage.close();
                    for (int i = 0; i < fajlovi.size(); i++) {
                        Klijent.posaljiFajl(fajlovi.get(i));
                    }
                } else {
                    prikaziObavjestenje("Paznja", "Niste unijeli sve potrebne informacije!");
                }
            }
            kompanijaPromocijaTxtField.setVisible(true);
            labelaKompanijaPromocija.setVisible(true);
        } else if ("Predavanja".equals(vrsta)) {
            if (kreiranjeNovogDogadjaja) {
                if (!nazivDogadjajaTxtFld.getText().isEmpty() && !opisTxtFld.getText().isEmpty() && vrijemePocetkaTxtFld != null && vrijemeKrajaTxtFld != null && organizatorComboBox.getSelectionModel().getSelectedItem() != null && ucc.size() != 0 && fajlovi.size() != 0 && !temaPredavanjaTxtFld.getText().isEmpty() && predavacPredavanjaComboBox.getSelectionModel().getSelectedItem() != null && !sadrzajPredavanjeTxtFld.getText().isEmpty()) {
                    Predavanja predavanje = new Predavanja(nazivDogadjajaTxtFld.getText(), datumPocetkaDatePicker.getValue().toString(), datumZavrsetkaDatePicker.getValue().toString(), upisiTacnoVrijeme(vrijemePocetkaTxtFld.getText(), true), upisiTacnoVrijeme(vrijemeKrajaTxtFld.getText(), false), opisTxtFld.getText(), organizatorComboBox.getValue(), new ArrayList<Ucesnik>(ucc), fajlovi, temaPredavanjaTxtFld.getText(), predavacPredavanjaComboBox.getSelectionModel().getSelectedItem(), sadrzajPredavanjeTxtFld.getText());
                    prikaziObavjestenje("Informacija", "Uspjesno ste kreirali novi dogadjaj !");
                    stage.close();
                    Klijent.dodajNoviDogadjaj(predavanje, vrsta);
                    for (int i = 0; i < fajlovi.size(); i++) {
                        Klijent.posaljiFajl(fajlovi.get(i));
                    }
                } else {
                    prikaziObavjestenje("Paznja", "Niste unijeli sve potrebne informacije!");
                }

            }
            temaPredavanjaTxtFld.setVisible(true);
            labelaTemaPredavanja.setVisible(true);
            predavacPredavanjaComboBox.setVisible(true);
            ucitajComboBox(predavacPredavanjaComboBox, "Predavaci");
            labelaPredavacPredavanje.setVisible(true);
            sadrzajPredavanjeTxtFld.setVisible(true);
            labelaSadrzajPredavanje.setVisible(true);
        } else if ("Koncerti".equals(vrsta)) {
            ucitajComboBox(izvodjaciComboBox, "Izvodjaci");
            if (kreiranjeNovogDogadjaja) {
                if (!nazivDogadjajaTxtFld.getText().isEmpty() && !opisTxtFld.getText().isEmpty() && vrijemePocetkaTxtFld != null && vrijemeKrajaTxtFld != null && organizatorComboBox.getSelectionModel().getSelectedItem() != null && ucc.size() != 0 && fajlovi.size() != 0 && izz.size() != 0 && trajanjeComboBox.getSelectionModel().getSelectedItem() != null) {
                    Koncert koncert = new Koncert(nazivDogadjajaTxtFld.getText(), datumPocetkaDatePicker.getValue().toString(), datumZavrsetkaDatePicker.getValue().toString(), upisiTacnoVrijeme(vrijemePocetkaTxtFld.getText(), true), upisiTacnoVrijeme(vrijemeKrajaTxtFld.getText(), false), opisTxtFld.getText(), organizatorComboBox.getValue(), new ArrayList<Ucesnik>(ucc), fajlovi, new ArrayList<Izvodjac>(izz), trajanjeComboBox.getSelectionModel().getSelectedItem());

                    prikaziObavjestenje("Informacija", "Uspjesno ste kreirali novi dogadjaj !");
                    stage.close();
                    Klijent.dodajNoviDogadjaj(koncert, vrsta);
                    for (int i = 0; i < fajlovi.size(); i++) {
                        Klijent.posaljiFajl(fajlovi.get(i));
                    }
                } else {
                    prikaziObavjestenje("Paznja", "Niste unijeli sve potrebne informacije!");
                }

            }
            izvodjaciComboBox.setVisible(true);
            izvodjaciLabel.setVisible(true);
            dodajIzvodjaceBtn.setVisible(true);
            listaDodanihIzvodjacaLabel.setVisible(true);
            listaDodanihIzvodjacaList.setVisible(true);
            trajanjeComboBox.setVisible(true);
            trajanjeLabel.setVisible(true);
        } else if ("Izlozbe".equals(vrsta)) {
            if (kreiranjeNovogDogadjaja) {
                if (!nazivDogadjajaTxtFld.getText().isEmpty() && !opisTxtFld.getText().isEmpty() && vrijemePocetkaTxtFld != null && vrijemeKrajaTxtFld != null && organizatorComboBox.getSelectionModel().getSelectedItem() != null && ucc.size() != 0 && fajlovi.size() != 0 && !temaIzlozbaTxtFld.getText().isEmpty() && aut.size() != 0) {
                    Izlozba izlozba = new Izlozba(nazivDogadjajaTxtFld.getText(), datumPocetkaDatePicker.getValue().toString(), datumZavrsetkaDatePicker.getValue().toString(), upisiTacnoVrijeme(vrijemePocetkaTxtFld.getText(), true), upisiTacnoVrijeme(vrijemeKrajaTxtFld.getText(), false), opisTxtFld.getText(), organizatorComboBox.getValue(), new ArrayList<Ucesnik>(ucc), fajlovi, temaIzlozbaTxtFld.getText(), new ArrayList<String>(aut));
                    prikaziObavjestenje("Informacija", "Uspjesno ste kreirali novi dogadjaj !");
                    stage.close();
                    Klijent.dodajNoviDogadjaj(izlozba, vrsta);
                    for (int i = 0; i < fajlovi.size(); i++) {
                        Klijent.posaljiFajl(fajlovi.get(i));
                    }
                } else {
                    prikaziObavjestenje("Paznja", "Niste unijeli sve potrebne informacije!");
                }

            }
            temaIzlozbaLabel.setVisible(true);
            temaIzlozbaTxtFld.setVisible(true);
            autorLabel.setVisible(true);
            listaDodanihAutoraList.setVisible(true);
            listaDodanihAutoraLabela.setVisible(true);
            dodajAutoraBtn.setVisible(true);
            autorTxtFld.setVisible(true);

        } else if ("Ostali ...".equals(vrsta)) {
            if (kreiranjeNovogDogadjaja) {
                if (!nazivDogadjajaTxtFld.getText().isEmpty() && !opisTxtFld.getText().isEmpty() && vrijemePocetkaTxtFld.getText() != null && vrijemeKrajaTxtFld.getText() != null && organizatorComboBox.getSelectionModel().getSelectedItem() != null && ucc.size() != 0 && fajlovi.size() != 0) {
                    Dogadjaj dog = new Dogadjaj(nazivDogadjajaTxtFld.getText(), datumPocetkaDatePicker.getValue().toString(), datumZavrsetkaDatePicker.getValue().toString(), upisiTacnoVrijeme(vrijemePocetkaTxtFld.getText(), true), upisiTacnoVrijeme(vrijemeKrajaTxtFld.getText(), false), opisTxtFld.getText(), organizatorComboBox.getValue(), new ArrayList<Ucesnik>(ucc), fajlovi);
                    prikaziObavjestenje("Informacija", "Uspjesno ste kreirali novi dogadjaj !");
                    stage.close();
                    Klijent.dodajNoviDogadjaj(dog, vrsta);
                    for (int i = 0; i < fajlovi.size(); i++) {
                        Klijent.posaljiFajl(fajlovi.get(i));
                    }
                } else {
                    prikaziObavjestenje("Paznja", "Niste unijeli sve potrebne informacije!");
                }
            }
        } else if ("Grupa dogadjaja".equals(vrsta)) {
            if (kreiranjeNovogDogadjaja) {
                if (!nazivDogadjajaTxtFld.getText().isEmpty() && !opisTxtFld.getText().isEmpty() && vrijemePocetkaTxtFld.getText() != null && vrijemeKrajaTxtFld.getText() != null && organizatorComboBox.getSelectionModel().getSelectedItem() != null && ucc.size() != 0 && fajlovi.size() != 0 && dogg.size() != 0) {
                    ListaDogadjaja dog = new ListaDogadjaja(nazivDogadjajaTxtFld.getText(), datumPocetkaDatePicker.getValue().toString(), datumZavrsetkaDatePicker.getValue().toString(), upisiTacnoVrijeme(vrijemePocetkaTxtFld.getText(), true), upisiTacnoVrijeme(vrijemeKrajaTxtFld.getText(), false), opisTxtFld.getText(), organizatorComboBox.getValue(), new ArrayList<Ucesnik>(ucc), fajlovi, new ArrayList<Dogadjaj>(dogg));

                    Klijent.dodajNoviDogadjaj(dog, vrsta);
                    prikaziObavjestenje("Informacija", "Uspjesno ste kreirali novi dogadjaj !");
                    stage.close();
                    for (int i = 0; i < fajlovi.size(); i++) {
                        Klijent.posaljiFajl(fajlovi.get(i));
                    }
                } else {
                    prikaziObavjestenje("Paznja", "Niste unijeli sve potrebne informacije!");
                }
            }
            izabraniDogadjajiComboBox.setVisible(true);
            listaDodanihDogadjajaListView.setVisible(true);
            listaDodanihDogadjajaLabel.setVisible(true);
            labelaDogadjaji.setVisible(true);
            dodajIzabraniDogadjajBtn.setVisible(true);

        }

    }

    public void ukloniTrenutnuFormu(boolean sve) {
        //dogadjaj
        if (sve) {
            nazivDogadjajaLbl.setDisable(true);
            nazivDogadjajaTxtFld.setDisable(true);
            nazivDogadjajaTxtFld.clear();
            datumPocetkaDatePicker.setDisable(true);
            datumZavrsetkaDatePicker.setDisable(true);
            vrijemeKrajaTxtFld.setDisable(true);
            vrijemePocetkaTxtFld.setDisable(true);
            opisTxtFld.setDisable(true);
            opisTxtFld.clear();

            datumZavrsetkaLbl.setDisable(true);
            vrijemeKrajaLbl.setDisable(true);
            vrijemePocetkaLbl.setDisable(true);
            datumPocetkaLbl.setDisable(true);
            opisLbl.setDisable(true);
            organizatorComboBox.setDisable(true);
            ucesniciComboBox.setDisable(true);
            listaDodanihUcesnikaLbl.setDisable(true);
            listaUcesnikaList.setDisable(true);
            dodajFajlBtn.setDisable(true);
            labelaBrojFajlova.setDisable(true);
            labelaKolicinaFajlova.setDisable(true);
            prijePodneBtn.setDisable(true);
            prijePodne2Btn.setDisable(true);
            poslijePodne2Btn.setDisable(true);
            poslijePodneBtn.setDisable(true);
            vrijemeKrajaSlider.setDisable(true);
            vrijemePocetkaSlider.setDisable(true);
            organizatorLabela.setDisable(true);
            ucesniciLabela.setDisable(true);
            snimiIzmjeneBtn.setDisable(true);
            //dodajUcesnikaBtn.setDisable(false);
            //        izabraniDogadjajiComboBox.setDisable(false);
//        listaDodanihDogadjajaListView.setDisable(false);
//        listaDodanihAutoraLabela.setDisable(false);
//        labelaDogadjaji.setDisable(false);
//    ??    dodajIzabraniDogadjajBtn.setDisable(false);
        }
        //Promocije
        labelaKompanijaPromocija.setVisible(false);
        kompanijaPromocijaTxtField.setVisible(false);

        //Predavanja
        labelaTemaPredavanja.setVisible(false);
        temaPredavanjaTxtFld.setVisible(false);
        temaPredavanjaTxtFld.clear();
        labelaPredavacPredavanje.setVisible(false);
        predavacPredavanjaComboBox.setVisible(false);
        sadrzajPredavanjeTxtFld.setVisible(false);
        sadrzajPredavanjeTxtFld.clear();
        labelaSadrzajPredavanje.setVisible(false);
        //Koncerti
        izvodjaciLabel.setVisible(false);
        izvodjaciComboBox.setVisible(false);
        listaDodanihIzvodjacaLabel.setVisible(false);
        dodajIzvodjaceBtn.setVisible(false);
        listaDodanihIzvodjacaList.setVisible(false);
        trajanjeLabel.setVisible(false);
        trajanjeComboBox.setVisible(false);

//Izlozbe
        temaIzlozbaTxtFld.setVisible(false);
        temaIzlozbaLabel.setVisible(false);
        autorLabel.setVisible(false);
        autorTxtFld.setVisible(false);
        listaDodanihAutoraList.setVisible(false);
        listaDodanihAutoraLabela.setVisible(false);
        dodajAutoraBtn.setVisible(false);
        //grupa dogadjaja
        izabraniDogadjajiComboBox.setVisible(false);
        listaDodanihDogadjajaListView.setVisible(false);
        listaDodanihDogadjajaLabel.setVisible(false);
        labelaDogadjaji.setVisible(false);
        dodajIzabraniDogadjajBtn.setVisible(false);

    }

    @FXML
    private void klikDodajFajl(ActionEvent event) {
        FileChooser fc = new FileChooser();
        List<File> izabraniFajlovi = fc.showOpenMultipleDialog(null);
        if (izabraniFajlovi != null) {
            Integer broj = Integer.parseInt(labelaKolicinaFajlova.getText());
            broj += izabraniFajlovi.size();
            labelaKolicinaFajlova.setText(broj + "");
            for (int i = 0; i < izabraniFajlovi.size(); i++) {
                if (!fajlovi.contains(new File(izabraniFajlovi.get(i).getAbsolutePath()))) {
                    fajlovi.add(new File(izabraniFajlovi.get(i).getAbsolutePath()));
                    System.out.println(izabraniFajlovi.get(i).getAbsolutePath());
                }
            }
        } else {
            System.out.println("Fajl nije izabran");
        }
    }

    @FXML
    private void klikSnimiIzmjene(ActionEvent event) {
        kreiranjeNovogDogadjaja = true;
        postaviOdgovarajucuFormu(vrstaDogadjaja);
        kreiranjeNovogDogadjaja = false;

    }

    @FXML
    private void klikDodajUcesnikaBtn(ActionEvent event) {
        if (!ucc.contains(ucesniciComboBox.getSelectionModel().getSelectedItem())) {
            ucc.add(ucesniciComboBox.getSelectionModel().getSelectedItem());
            listaUcesnikaList.setItems(ucc);
        }
    }

    @FXML
    private void klikDodajIzvodjaceBtn(ActionEvent event) {
        if (!izz.contains(izvodjaciComboBox.getSelectionModel().getSelectedItem())) {
            izz.add(izvodjaciComboBox.getSelectionModel().getSelectedItem());
            listaDodanihIzvodjacaList.setItems(izz);
        }
    }

    @FXML
    private void klikDodajAutoreBtn(ActionEvent event) {
        if (!aut.contains(autorTxtFld.getText())) {
            aut.add(autorTxtFld.getText());
            listaDodanihAutoraList.setItems(aut);
        }
        autorTxtFld.clear();

    }

    @FXML
    private void klikDodajDogadjajBtn(ActionEvent event) {
        if (!dogg.contains(izabraniDogadjajiComboBox.getSelectionModel().getSelectedItem())) {
            dogg.add(izabraniDogadjajiComboBox.getSelectionModel().getSelectedItem());
            listaDodanihDogadjajaListView.setItems(dogg);
        }

    }
}
