package views.screen.rentbike;

import java.io.IOException;
import java.sql.SQLException;

import common.exception.InvalidBarCodeException;
import controller.PaymentController;
import controller.RentBikeController;
import entity.bike.Bike;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.payment.PaymentScreenHandler;
import views.screen.popup.PopupScreen;

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
	private boolean isValidBarCode = false;

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
		if (isValidBarCode) {
			BaseScreenHandler paymentScreen = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN_PATH, deposit,
					"deposit");
			paymentScreen.setBController(new PaymentController());
			paymentScreen.setPreviousScreen(this);
			paymentScreen.setHomeScreenHandler(homeScreenHandler);
			paymentScreen.setScreenTitle("Payment Screen");
			paymentScreen.show();
		} else {
			PopupScreen.error("Invalid barcode");
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
				bikeLabel.setText(bike.getTypeString());

				isValidBarCode = true;
				bikeLabel.setText(bike.getTypeString());
				bikeLabel.setVisible(true);
				bikeImage.setImage(bike.getImage());
				bikeImage.setVisible(true);
				
			}
		} catch (InvalidBarCodeException e) {
			PopupScreen.error("Invalid barcode");
			isValidBarCode = false;
			bikeLabel.setVisible(false);
			bikeImage.setVisible(false);
		}
	}

}
