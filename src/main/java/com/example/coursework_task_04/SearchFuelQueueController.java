package com.example.coursework_task_04;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchFuelQueueController {
    @FXML
    private Button btnBack;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<Passenger> tblSearch;

    @FXML
    protected void back() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FuelQueue.class.getResource("fuel-queue.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("First Window");
        stage.setScene(scene);
        stage.show();

        ((Stage)btnBack.getScene().getWindow()).close();
    }

    @FXML
    protected void searchByVehNo()
    {
        ObservableList<Passenger> searchData = FXCollections.observableArrayList();
        for (int i=0;i<5;i++)
        {
            for (int j=0;j<6;j++)
            {
                if (FuelQueue.fuelQueue[i][j] != null)
                {
                    if (FuelQueue.fuelQueue[i][j].passenger.getVehicleNumber().contains(txtSearch.getText())){ searchData.add(FuelQueue.fuelQueue[i][j].passenger);}
                }
            }
        }
        tblSearch.setItems(searchData);
    }
}
