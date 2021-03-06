package views.screen.rentbike;

import common.exception.InvalidBarCodeException;
import controller.PaymentController;
import controller.RentBikeController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import subsystem.entity.bike.Bike;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.payment.PaymentScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Hoàng Minh Lương - 20194108
 */
public class RentBikeScreenHandler extends BaseScreenHandler {

    @FXML
    private ImageView ebrImage;

    @FXML
    private TextField barCodeTextField;

    @FXML
    private ImageView bikeImage;

    @FXML
    private Label bikeLabel;

    private String barCode;
    private int deposit;
    private int bikeId;
    private boolean isValidBarCode = false;
    private boolean hasRented = false;

    public RentBikeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);

        ebrImage.setOnMouseClicked(e -> {
            homeScreenHandler.show();
        });
    }

    public RentBikeController getBController() {
        return (RentBikeController) super.getBController();
    }

    public void requestToRentBike(BaseScreenHandler prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("Rent Bike Screen");
        show();
    }

    @FXML
    void payDeposit(MouseEvent event) throws IOException {
        if (isValidBarCode && !hasRented) {
            BaseScreenHandler paymentScreen = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN_PATH, deposit,
                    bikeId, 0, "deposit");
            paymentScreen.setBController(new PaymentController());
            paymentScreen.setPreviousScreen(this);
            paymentScreen.setHomeScreenHandler(homeScreenHandler);
            paymentScreen.setScreenTitle("Payment Screen");
            paymentScreen.show();
        } else if (!isValidBarCode) {
            PopupScreen.error("Invalid barcode");
        } else if (hasRented) {
            PopupScreen.error("This bike has been rented");
        }
    }

    @FXML
    void confirm(MouseEvent event) throws IOException, InterruptedException, SQLException {
        try {
            barCode = barCodeTextField.getText();
            getBController().processBarCode(barCode);
            Bike bike = getBController().getBikeFromBarCode(barCode);
            if (bike != null) {
                deposit = bike.getDeposit();
                bikeId = bike.getBikeId();
                hasRented = bike.getStationId() == 0;

                isValidBarCode = true;
                bikeLabel.setText(bike.getTypeString());
                bikeLabel.setVisible(true);
                setImage(bikeImage, bike.getImageUrl());
                bikeImage.setVisible(true);

            } else {
                PopupScreen.error("Invalid barcode");
            }
        } catch (InvalidBarCodeException e) {
            PopupScreen.error("Invalid barcode");
            isValidBarCode = false;
            bikeLabel.setVisible(false);
            bikeImage.setVisible(false);
        }
    }

}
