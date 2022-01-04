package views.screen.bikeinfo;

import controller.BikeInfoController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BikeInfoScreenHandler extends BaseScreenHandler {

	@FXML
	private ImageView ebrImage;

	@FXML
	private Label bikeid;

	@FXML
	private Label biketype;

	@FXML
	private Label enegy;

	@FXML
	private Label time;

	@FXML
	private Label cost;
	
	public BikeInfoScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);

		ebrImage.setOnMouseClicked(e -> {
			homeScreenHandler.show();
		});
	}

	public BikeInfoController getBController() {
		return (BikeInfoController) super.getBController();
	}

	public void requestToBikeInfo(BaseScreenHandler prevScreen) throws SQLException {
		setPreviousScreen(prevScreen);
		setScreenTitle("Bike Info Screen");
		show();
	}

	public void setImage() {
		// fix image path caused by fxml
		File file1 = new File(Configs.IMAGE_PATH + "/" + "Logo.png");
		Image img1 = new Image(file1.toURI().toString());
//        aimsImage.setImage(img1);

		File file2 = new File(Configs.IMAGE_PATH + "/" + "cart.png");
		Image img2 = new Image(file2.toURI().toString());
//        cartImage.setImage(img2);
	}
public ResultSet view(String bike){
		ResultSet resultSet = null;
		try {
			Statement statement = (Statement) connection.createStatement();
			String sql = "SELECT * FROM WHERE bikeid = ' " + bike + " '";

			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			return null;
		}
		try {
			bikeid.setText(resultSet.getString("bikeid"));
			biketype.setText(resultSet.getString("biketype"));
			enegy.setText(resultSet.getString("enegy"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
}
