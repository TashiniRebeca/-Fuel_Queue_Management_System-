package com.example.coursework_task_04;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewQueueController {

    @FXML
    private CheckBox chbQ1;

    @FXML
    private CheckBox chbQ2;

    @FXML
    private CheckBox chbQ3;

    @FXML
    private CheckBox chbQ4;

    @FXML
    private CheckBox chbQ5;

    @FXML
    private CheckBox chbWq;

    @FXML
    private Button btnBack;

    @FXML
    private TableView<Passenger> tblFuel;

    @FXML
    private Pane pane;

    @FXML
    protected void backToMain() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FuelQueue.class.getResource("fuel-queue.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("First Window");
        stage.setScene(scene);
        stage.show();

        ((Stage) btnBack.getScene().getWindow()).close();
    }


    @FXML
    protected void chb1Event()
    {
        pane.setVisible(chbQ1.isSelected());
        ObservableList<Passenger> data = FXCollections.observableArrayList();
        for (int i=0;i<6;i++)
        {
            if (FuelQueue.fuelQueue[0][i] != null){ data.add(FuelQueue.fuelQueue[0][i].passenger);}
        }
        tblFuel.setItems(data);
        chbQ2.setSelected(false);
        chbQ3.setSelected(false);
        chbQ4.setSelected(false);
        chbQ5.setSelected(false);
        chbWq.setSelected(false);
    }

    @FXML
    protected void chb2Event()
    {
        pane.setVisible(chbQ2.isSelected());
        ObservableList<Passenger> data = FXCollections.observableArrayList();
        for (int i=0;i<6;i++)
        {
            if (FuelQueue.fuelQueue[1][i] != null){ data.add(FuelQueue.fuelQueue[1][i].passenger);}
        }
        tblFuel.setItems(data);
        chbQ1.setSelected(false);
        chbQ3.setSelected(false);
        chbQ4.setSelected(false);
        chbQ5.setSelected(false);
        chbWq.setSelected(false);
    }

    @FXML
    protected void chb3Event()
    {
        pane.setVisible(chbQ3.isSelected());
        ObservableList<Passenger> data = FXCollections.observableArrayList();
        for (int i=0;i<6;i++)
        {
            if (FuelQueue.fuelQueue[2][i] != null){ data.add(FuelQueue.fuelQueue[2][i].passenger);}
        }
        tblFuel.setItems(data);
        chbQ1.setSelected(false);
        chbQ2.setSelected(false);
        chbQ4.setSelected(false);
        chbQ5.setSelected(false);
        chbWq.setSelected(false);
    }

    @FXML
    protected void chb4Event()
    {
        pane.setVisible(chbQ4.isSelected());
        ObservableList<Passenger> data = FXCollections.observableArrayList();
        for (int i=0;i<6;i++)
        {
            if (FuelQueue.fuelQueue[3][i] != null){ data.add(FuelQueue.fuelQueue[3][i].passenger);}
        }
        tblFuel.setItems(data);
        chbQ1.setSelected(false);
        chbQ2.setSelected(false);
        chbQ3.setSelected(false);
        chbQ5.setSelected(false);
        chbWq.setSelected(false);
    }

    @FXML
    protected void chb5Event()
    {
        pane.setVisible(chbQ5.isSelected());
        ObservableList<Passenger> data = FXCollections.observableArrayList();
        for (int i=0;i<6;i++)
        {
            if (FuelQueue.fuelQueue[4][i] != null){ data.add(FuelQueue.fuelQueue[4][i].passenger);}
        }
        tblFuel.setItems(data);
        chbQ1.setSelected(false);
        chbQ2.setSelected(false);
        chbQ3.setSelected(false);
        chbQ4.setSelected(false);
        chbWq.setSelected(false);
    }

    @FXML
    protected void chbWaitingEvent()
    {
        pane.setVisible(chbWq.isSelected());
        ObservableList<Passenger> waitingData = FXCollections.observableArrayList();
        for (int i=0;i<6;i++)
        {
            if (FuelQueue.waitingQueues.waitingQueue[i] != null){ waitingData.add(FuelQueue.waitingQueues.waitingQueue[i].passenger);}
        }
        tblFuel.setItems(waitingData);
        chbQ1.setSelected(false);
        chbQ2.setSelected(false);
        chbQ3.setSelected(false);
        chbQ4.setSelected(false);
        chbQ5.setSelected(false);
    }
}
