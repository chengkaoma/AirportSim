package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.*;

public class Controller {

    @FXML
    TextField airplaneNumber;
    @FXML
    TextField simulationTime;
    @FXML
    TextField airplaneSpeed;
    @FXML
    TextField airplaneCapacity;
    @FXML
    CheckBox multileRunways;
    @FXML
    TextArea simulationOutput;
    @FXML
    TableView<Res> resTable;
    @FXML
    TableColumn<Res, String> airportNameColumn;
    @FXML
    TableColumn<Res, String> circlingTimeColumn;
    @FXML
    TableColumn<Res, String> refuelDelayColumn;
    @FXML
    TableColumn<Res, String> weatherDelayColumn;
    @FXML
    TableColumn<Res, String> passengerFlowColumn;
    @FXML
    Button calculate;



    public void calculateButtonClicked() {
        HashMap<String, List<String>> res = new HashMap<>();

        simulationOutput.setText(AirportSim.simulation( Integer.valueOf(airplaneNumber.getText()),  Integer.valueOf(simulationTime.getText())*60,  Integer.valueOf(airplaneSpeed.getText()),  Integer.valueOf(airplaneCapacity.getText()),  multileRunways.isSelected(),res));

        airportNameColumn.setCellValueFactory(cellData -> cellData.getValue().airportNameProperty());
        circlingTimeColumn.setCellValueFactory(cellData -> cellData.getValue().circlingTimeProperty());
        refuelDelayColumn.setCellValueFactory(cellData -> cellData.getValue().refuelDelayProperty());
        weatherDelayColumn.setCellValueFactory(cellData -> cellData.getValue().weatherDelayProperty());
        passengerFlowColumn.setCellValueFactory(cellData -> cellData.getValue().passengerFlowProperty());

        ObservableList<Res> resData = FXCollections.observableArrayList();

        for(String s : res.keySet()) resData.add(new Res(s,res.get(s).get(0),res.get(s).get(1),res.get(s).get(2),res.get(s).get(3)));
        resTable.setItems(resData);



        calculate.setDisable(true);

    }
}
