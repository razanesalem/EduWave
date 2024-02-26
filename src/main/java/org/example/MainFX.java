package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainFX extends Application {
 /*
    @Override
    public void start(Stage primaryStage) throws IOException {
        URL fxmlUrl = getClass().getResource("/test.fxml");
        System.out.println("FXML URL: " + fxmlUrl);

        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("promo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



*/

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL fxmlUrl = getClass().getResource("/tableview.fxml");
        System.out.println("FXML URL: " + fxmlUrl);
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Code Promo Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    }



