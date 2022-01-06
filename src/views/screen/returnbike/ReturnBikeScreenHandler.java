package views.screen.returnbike;

import controller.PaymentController;
import controller.ReturnBikeController;
import entity.bike.Bike;
import entity.bikerental.BikeRental;
import entity.station.Station;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.payment.PaymentScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class ReturnBikeScreenHandler extends BaseScreenHandler {

    @FXML
    private ImageView ebrImage;

    @FXML
    private TextField stationIdTextField;

    @FXML
    private ImageView stationImage;

    @FXML
    private Label stationLabel;

    @FXML
    private Label bikeIdLabel;

    @FXML
    private Label timeStart;

    @FXML
    private Label timeCurrent;

    @FXML
    private Label deposit;

    @FXML
    private Label fee;

    @FXML
    private Label amountToPay;

    private int customerId;
    private int stationId;
    private ReturnBikeController controller;
    private Bike bike;
    private BikeRental bikeRental;
    private boolean isValidStationId = false;

    public ReturnBikeScreenHandler(Stage stage, String screenPath, int customerId) throws IOException, SQLException, ParseException {
        super(stage, screenPath);
        this.customerId = customerId;

        ebrImage.setOnMouseClicked(e -> {
            homeScreenHandler.show();
        });

        controller = new ReturnBikeController();
        bike = controller.getBikeFromCustomerId(customerId);
        bikeRental = controller.getBikeRentalFromCustomerId(customerId);
        bikeIdLabel.setText("" + bikeRental.getBikeId());
        timeStart.setText(bikeRental.getTime());
        deposit.setText("" + bike.getDeposit());
        timeCurrent.setText(Utils.getToday());
        fee.setText("" + controller.calculateFee(bikeRental.getTime(), Utils.getToday()));
        amountToPay.setText("" + (Integer.parseInt(fee.getText()) - bike.getDeposit()));
    }

    public ReturnBikeController getBController() {
        return (ReturnBikeController) super.getBController();
    }

    public void requestToRentBike(BaseScreenHandler prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("Return Bike Screen");
        show();
    }

    @FXML
    void pay(MouseEvent event) throws IOException, ParseException {
        if (!isValidStationId) {
            PopupScreen.error("Invalid stationId");
        } else {
            BaseScreenHandler paymentScreen = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN_PATH, controller.calculateFee(bikeRental.getTime(), Utils.getToday()) - bike.getDeposit(),
                    bike.getBikeId(), stationId, "fee");
            paymentScreen.setBController(new PaymentController());
            paymentScreen.setPreviousScreen(this);
            paymentScreen.setHomeScreenHandler(homeScreenHandler);
            paymentScreen.setScreenTitle("Payment Screen");
            paymentScreen.show();
        }
    }

    @FXML
    void confirm(MouseEvent event) throws IOException, InterruptedException, SQLException {
        if (stationIdTextField.getText() == null) {
            PopupScreen.error("Invalid stationId");
            return;
        }
        stationId = Integer.parseInt(stationIdTextField.getText());
        Station station = controller.getStationFromId(stationId);
        if (station != null) {
            stationImage.setVisible(true);
            stationLabel.setVisible(true);
            stationLabel.setText(station.getName());
            isValidStationId = true;
        } else {
            PopupScreen.error("Invalid stationId");
            stationImage.setVisible(false);
            stationLabel.setVisible(false);
            isValidStationId = false;
        }
    }
}
