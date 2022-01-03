package controller;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StationItemController{
	
	@FXML
	HBox item;
	
	@FXML
	Label nameStation;
	@FXML
	Label addressStation;
	@FXML
	Label numBike;
	@FXML
	Button checkBike;
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public HBox getItem(int stationID, String name, String address, int num) {
		setId(stationID);
		nameStation.setText(name);
		addressStation.setText(address);
		numBike.setText("Còn " + Integer.toString(num) + " xe");
		return item;
	}
	public HBox getItem() {
		return item;
	}
	public void setItem(HBox item) {
		this.item = item;
	}
	public Label getNameStation() {
		return nameStation;
	}
	public void setNameStation(Label nameStation) {
		this.nameStation = nameStation;
	}
	public Label getAddressStation() {
		return addressStation;
	}
	public void setAddressStation(Label addressStation) {
		this.addressStation = addressStation;
	}
	public Label getNumBike() {
		return numBike;
	}
	public void setNumBike(Label numBike) {
		this.numBike = numBike;
	}
	public void checkBikeInStation(ActionEvent e) throws IOException, SQLException {
		Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../views/Bike.fxml"));
		Parent viewBikeParent = loader.load();
		Scene scene = new Scene(viewBikeParent);
		stage.setScene(scene);
		
		BikeController bikeController = (BikeController)loader.getController();
		bikeController.addBikeItem(getId());
	}
}