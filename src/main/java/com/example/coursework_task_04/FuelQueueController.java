package com.example.coursework_task_04;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FuelQueueController {

    @FXML
    private Button btnView;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnExit;

    @FXML
    protected void closeWindow() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(FuelQueue.class.getResource("fuel-queue.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);

        ((Stage)btnExit.getScene().getWindow()).close();

    }

    @FXML
    protected void openSearchWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FuelQueue.class.getResource("search-fuel-queue.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Search Window");
        stage.setScene(scene);
        stage.show();

        Stage previousStage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }

    @FXML
    protected void openViewWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FuelQueue.class.getResource("view-queue.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("View Window");
        stage.setScene(scene);
        stage.show();

        Stage previousStage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
    }
}