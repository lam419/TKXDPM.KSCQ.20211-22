package views.screen.viewbikesinstation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import subsystem.entity.bike.Bike;
import utils.Utils;
import views.screen.FXMLScreenHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * @author Hoàng Minh Lương - 20194108
 */
public class BikeHandler extends FXMLScreenHandler {

    @FXML
    protected ImageView bikeImage;

    @FXML
    protected Label bikeId;

    @FXML
    protected Label bikeType;

    private static Logger LOGGER = Utils.getLogger(BikeHandler.class.getName());
    private Bike bike;

    public BikeHandler(String screenPath, Bike bike) throws SQLException, IOException {
        super(screenPath);
        this.bike = bike;

        setBikeInfo();
    }

    public Bike getBike() {
        return bike;
    }

    private void setBikeInfo() throws SQLException {
        setImage(bikeImage, bike.getImageUrl());
        bikeId.setText("" + bike.getBikeId());
        bikeType.setText(bike.getTypeString());
    }
}
