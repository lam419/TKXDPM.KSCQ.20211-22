package views.screen.rentbike;

import java.io.IOException;
import java.sql.SQLException;

import controller.PaymentController;
import controller.RentBikeController;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.payment.PaymentScreenHandler;

public class RentBikeScreenHandler extends BaseScreenHandler {
	
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
		BaseScreenHandler paymentScreen = new PaymentScreenHandler(this.stage, Configs.PAYMENT_SCREEN_PATH, 400000, "deposit");
		paymentScreen.setBController(new PaymentController());
		paymentScreen.setPreviousScreen(this);
		paymentScreen.setHomeScreenHandler(homeScreenHandler);
		paymentScreen.setScreenTitle("Payment Screen");
		paymentScreen.show();
	}

}
