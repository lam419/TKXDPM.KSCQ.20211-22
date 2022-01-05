package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import DAO.BikeDAO;
import DAO.RentDAO;
import DAO.StationDAO;
import DAO.TransactionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Bike;
import model.Rent;
import model.Station;
import javafx.scene.Node;


public class ReturnController {
    private Calendar cal;
    private int bikeID;

    public int getBikeID() {
        return bikeID;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    public Calendar getCal() {
        return cal;
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
    }

    @FXML
    public TableView<Station> table;
    @FXML
    public TableColumn<Station, Integer> idColumn;
    @FXML
    public TableColumn<Station, String> nameColumn;
    @FXML
    public TableColumn<Station, String> addressColumn;
    @FXML
    public TableColumn<Station, Integer> emptyColumn;
    @FXML
    public TableColumn<Station, Integer> maxBikeColumn;


    private ObservableList<Station> stationList;


    public void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/Waiting.fxml"));
        Parent Home = loader.load();
        Scene sceneHome = new Scene(Home);
        stage.setScene(sceneHome);

        WaitingController controller = new WaitingController();
        controller.saveBikeIDForSession(bikeID);
    }

    public void goPayment(ActionEvent event) throws IOException, SQLException, ParseException {
        //update endtime at rent table to database
        RentDAO rentDAO = new RentDAO();
        setCal(Calendar.getInstance());
        String str = String.valueOf(cal.get(Calendar.YEAR)) + "-" + String.valueOf(cal.get(Calendar.MONTH) + 1) + "-" + String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + " " + String.valueOf(cal.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(cal.get(Calendar.MINUTE)) + ":00";
        rentDAO.updateRent(bikeID, str);

        //save transaction
        TransactionDAO transactionDAO = new TransactionDAO();
        long totalTimeRent = rentDAO.getTotalTimeRent(bikeID);
        Rent rent = new Rent();
        rent = rentDAO.getRent(bikeID);
        transactionDAO.saveTransaction(totalTimeRent, rent.getTotalMoney(totalTimeRent), rent.getId());


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/PaymentForm_return.fxml"));
        Parent Payment = loader.load();
        Scene scene = new Scene(Payment);
        stage.setScene(scene);

        PaymentController controller = (PaymentController) loader.getController();
        Station selected = table.getSelectionModel().getSelectedItem();
        controller.addData(bikeID, selected);
    }

    public void addData() {
        ArrayList<Station> listStation = new ArrayList<Station>();
        StationDAO stationDAO = new StationDAO();
        try {
            listStation = stationDAO.getListStation();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stationList = FXCollections.observableArrayList(listStation);
        idColumn.setCellValueFactory(new PropertyValueFactory<Station, Integer>("stationID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Station, String>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Station, String>("address"));
        emptyColumn.setCellValueFactory(new PropertyValueFactory<Station, Integer>("numBike"));
        maxBikeColumn.setCellValueFactory(new PropertyValueFactory<Station, Integer>("maxBike"));
        table.setItems(stationList);
    }

    public void giveBikeID(int id) {
        bikeID = id;
    }
}
