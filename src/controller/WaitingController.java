package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WaitingController {

    private int bikeID;

    public int getBikeID() {
        return bikeID;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    public void saveBikeIDForSession(int id) {
        bikeID = id;
    }

    public void returnBike(ActionEvent e) throws IOException {

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/ReturnBike.fxml"));
        Parent viewBikeParent = loader.load();
        Scene scene = new Scene(viewBikeParent);
        stage.setScene(scene);

        ReturnController controller = (ReturnController) loader.getController();
        controller.addData();
        controller.giveBikeID(bikeID);
    }
}