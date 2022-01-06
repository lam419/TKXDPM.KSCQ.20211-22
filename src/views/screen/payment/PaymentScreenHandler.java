package views.screen.payment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

import controller.PaymentController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;

public class PaymentScreenHandler extends BaseScreenHandler {

    @FXML
    private Button btnConfirmPayment;

    @FXML
    private ImageView loadingImage;

    private int amount;
    private int bikeId;
    private int stationId;
    private String contents;

    public PaymentScreenHandler(Stage stage, String screenPath, int amount, int bikeId, int stationId, String contents)
            throws IOException {
        super(stage, screenPath);
        this.amount = amount;
        this.bikeId = bikeId;
        this.stationId = stationId;
        this.contents = contents;

        btnConfirmPayment.setOnMouseClicked(e -> {
            try {
                confirmToPay();
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });
    }

    @FXML
    private Label pageTitle;

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField holderName;

    @FXML
    private TextField expirationDate;

    @FXML
    private TextField securityCode;

    void confirmToPay() throws IOException, SQLException {
        PaymentController ctrl = (PaymentController) getBController();
        Map<String, String> response = ctrl.payOrder(amount, contents, cardNumber.getText(), holderName.getText(),
                expirationDate.getText(), securityCode.getText());

        if (Objects.equals(response.get("RESULT"), "PAYMENT SUCCESSFUL!")) {
            if (stationId == 0) ctrl.addDepositTransactionToDatabase(bikeId);
            else ctrl.modifyDatabaseWhenReturnBike(bikeId, stationId);
        }

        BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH,
                response.get("RESULT"), response.get("MESSAGE"));
        resultScreen.setPreviousScreen(this);
        resultScreen.setHomeScreenHandler(homeScreenHandler);
        resultScreen.setScreenTitle("Result Screen");
        resultScreen.show();
    }

}