package views.screen.viewbikesinstation;

import controller.ViewBikesInStationController;
import entity.bike.Bike;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.home.StationHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ViewBikesInStationScreenHandler extends BaseScreenHandler {

    public static Logger LOGGER = Utils.getLogger(ViewBikesInStationScreenHandler.class.getName());

    @FXML
    private ImageView ebrImage;

    @FXML
    private VBox vboxBike1;

    @FXML
    private VBox vboxBike2;

    @FXML
    private VBox vboxBike3;

    @FXML
    private HBox hboxBike;

    private List bikeItems;
    private int stationId;

    public ViewBikesInStationScreenHandler(Stage stage, String screenPath, int stationId) throws IOException {
        super(stage, screenPath);
        this.stationId = stationId;

        ebrImage.setOnMouseClicked(e -> {
            homeScreenHandler.show();
        });

        try {
            List bikes = new ViewBikesInStationController().getAllBikeInStation(stationId);
            this.bikeItems = new ArrayList<>();
            for (Object object : bikes) {
                Bike bike = (Bike) object;
                BikeHandler m1 = new BikeHandler(Configs.HOME_BIKE_PATH, bike);
                this.bikeItems.add(m1);
            }
        } catch (SQLException | IOException e) {
            LOGGER.info("Errors occurred: " + e.getMessage());
            e.printStackTrace();
        }

        addBike(this.bikeItems);
    }

    public void requestToViewBikes(BaseScreenHandler prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("View Bikes In Station Screen");
        show();
    }

    public void addBike(List items) {
        ArrayList bikeItems = (ArrayList) ((ArrayList) items).clone();
        hboxBike.getChildren().forEach(node -> {
            VBox vBox = (VBox) node;
            vBox.getChildren().clear();
        });
        while (!bikeItems.isEmpty()) {
            hboxBike.getChildren().forEach(node -> {
                int vid = hboxBike.getChildren().indexOf(node);
                VBox vBox = (VBox) node;
                while (vBox.getChildren().size() < 3 && !bikeItems.isEmpty()) {
                    BikeHandler bike = (BikeHandler) bikeItems.get(0);
                    vBox.getChildren().add(bike.getContent());
                    bikeItems.remove(bike);
                }
            });
            return;
        }
    }

    public ViewBikesInStationController getBController() {
        return (ViewBikesInStationController) super.getBController();
    }

}
