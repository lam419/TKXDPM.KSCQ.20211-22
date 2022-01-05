package views.screen.payment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

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

    public PaymentScreenHandler(Stage stage, String screenPath, int amount, int bikeId, String contents)
            throws IOException {
        super(stage, screenPath);
        this.amount = amount;
        this.bikeId = bikeId;

        btnConfirmPayment.setOnMouseClicked(e -> {
            try {
                confirmToPayDeposit();
            } catch (Exception exp) {
                System.out.println(exp.getStackTrace());
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

    void confirmToPayDeposit() throws IOException, SQLException {
        String contents = "pay deposit";
        PaymentController ctrl = (PaymentController) getBController();
        Map<String, String> response = ctrl.payOrder(10000, contents, cardNumber.getText(), holderName.getText(),
                expirationDate.getText(), securityCode.getText());
        if (response.get("RESULT") == "PAYMENT SUCCESSFUL!") {
            ctrl.addDepositTransactionToDatabse(bikeId);
        }

        BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH,
                response.get("RESULT"), response.get("MESSAGE"));
        resultScreen.setPreviousScreen(this);
        resultScreen.setHomeScreenHandler(homeScreenHandler);
        resultScreen.setScreenTitle("Result Screen");
        resultScreen.show();
    }

}