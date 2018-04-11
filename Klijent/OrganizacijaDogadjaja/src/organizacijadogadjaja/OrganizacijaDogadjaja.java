/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package organizacijadogadjaja;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Semanic
 */
public class OrganizacijaDogadjaja extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, Exception {
        Klijent.pokreniSocket();
        Klijent.poveziInOut();
        Parent root = FXMLLoader.load(getClass().getResource("GlavnaFormaFXML.fxml"));
        init();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Organizacija dogadjaja ");
        //primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(700);

        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
