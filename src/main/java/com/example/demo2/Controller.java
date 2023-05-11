package com.example.demo2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    public ImageView player;

    @FXML
    public void onMauseClicked(MouseEvent mouseEvent) {
        player.setLayoutX(100);
    }
    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        player.setLayoutY(100);
    }
    @FXML
    public void onKeyTyped(KeyEvent keyEvent) {
        player.setLayoutY(100);
    }
}
