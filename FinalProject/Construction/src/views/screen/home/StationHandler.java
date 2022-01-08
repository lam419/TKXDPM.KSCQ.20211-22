package views.screen.home;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import entity.station.Station;
import utils.Utils;
import views.screen.FXMLScreenHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * @author Hoàng Minh Lương - 20194108
 */
public class StationHandler extends FXMLScreenHandler {

    public static Logger LOGGER = Utils.getLogger(StationHandler.class.getName());

    @FXML
    HBox item;

    @FXML
    Label nameStation;
    @FXML
    Label addressStation;
    @FXML
    Label numBike;

    private Station station;
    private HomeScreenHandler home;

    public StationHandler(String screenPath, Station station, HomeScreenHandler home) throws SQLException, IOException {
        super(screenPath);
        this.station = station;
        this.home = home;

        setStationInfo();
    }

    public Station getStation() {
        return station;
    }

    private void setStationInfo() throws SQLException {
        nameStation.setText(station.getName());
        addressStation.setText(station.getAddress());
        numBike.setText("Sức chứa: " + station.getCapacity());
    }

    @FXML
    void viewBikes(MouseEvent event) throws IOException {
//        ViewBikesInStationScreenHandler viewBikesInStationScreen;
//        try {
//            viewBikesInStationScreen = new ViewBikesInStationScreenHandler(this.stage, Configs.VIEW_BIKES_IN_STATION_SCREEN_PATH, station.getStationId());
//            viewBikesInStationScreen.setHomeScreenHandler(home);
//            viewBikesInStationScreen.setBController(new ViewBikesInStationController());
//            viewBikesInStationScreen.requestToViewBikes(home);
//        } catch (SQLException e) {
//            LOGGER.info(e.getMessage());
//            e.printStackTrace();
//        }
        home.viewBikesInStation(station.getStationId());
    }
}
