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
	private TextField barCodeTextField;

	@FXML
	private ImageView bikeImage;

	@FXML
	private Label bikeLabel;

	private String barCode;
	private int deposit;

	public RentBikeScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);

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
		BaseScreenHandler paymentScreen = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN_PATH, deposit,
				"deposit");
		paymentScreen.setBController(new PaymentController());
		paymentScreen.setPreviousScreen(this);
		paymentScreen.setHomeScreenHandler(homeScreenHandler);
		paymentScreen.setScreenTitle("Payment Screen");
		paymentScreen.show();
	}

	@FXML
	void confirm(MouseEvent event) throws IOException, InterruptedException, SQLException {
		try {
			barCode = barCodeTextField.getText();
			getBController().processBarCode(barCode);
			Bike bike = getBController().getBikeFromBarCode(barCode);
			deposit = bike.getDeposit();
			bikeLabel.setText(bike.getTypeString());
		} catch (InvalidBarCodeException e) {
			PopupScreen.error("Invalid barcode");
		} 
	}

}
