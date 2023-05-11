package com.example.demo2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @FXML
    public Button button;
    @FXML
    public void onMouseClicked(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("level1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
