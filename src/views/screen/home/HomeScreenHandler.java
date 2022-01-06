package views.screen.home;

import controller.HomeController;
import controller.RentBikeController;
import controller.ReturnBikeController;
import controller.ViewBikesInStationController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import subsystem.entity.station.Station;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;
import views.screen.rentbike.RentBikeScreenHandler;
import views.screen.returnbike.ReturnBikeScreenHandler;
import views.screen.viewbikesinstation.ViewBikesInStationScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * @author Hoàng Minh Lương - 20194108
 */
public class HomeScreenHandler extends BaseScreenHandler implements Initializable {

    public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());

    @FXML
    private ImageView ebrImage;

    @FXML
    private VBox vboxStation1;

    @FXML
    private VBox vboxStation2;

    @FXML
    private VBox vboxStation3;

    @FXML
    private HBox hboxStation;

    @FXML
    private SplitMenuButton splitMenuBtnSearch;

    @FXML
    private Button rentBikeButton;

    private List homeItems;

    public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public HomeController getBController() {
        return (HomeController) super.getBController();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setBController(new HomeController());
        try {
            List stations = getBController().getAllStation();
            this.homeItems = new ArrayList<>();
            for (Object object : stations) {
                Station station = (Station) object;
                StationHandler m1 = new StationHandler(Configs.HOME_STATION_PATH, station, this);
                this.homeItems.add(m1);
            }
        } catch (SQLException | IOException e) {
            LOGGER.info("Errors occurred: " + e.getMessage());
            e.printStackTrace();
        }

        ebrImage.setOnMouseClicked(e -> {
            addStationHome(this.homeItems);
        });

        addStationHome(this.homeItems);
    }

    public void addStationHome(List items) {
        ArrayList stationItems = (ArrayList) ((ArrayList) items).clone();
        hboxStation.getChildren().forEach(node -> {
            VBox vBox = (VBox) node;
            vBox.getChildren().clear();
        });
        while (!stationItems.isEmpty()) {
            hboxStation.getChildren().forEach(node -> {
                int vid = hboxStation.getChildren().indexOf(node);
                VBox vBox = (VBox) node;
                while (vBox.getChildren().size() < 3 && !stationItems.isEmpty()) {
                    StationHandler station = (StationHandler) stationItems.get(0);
                    vBox.getChildren().add(station.getContent());
                    stationItems.remove(station);
                }
            });
            return;
        }
    }

    @FXML
    void requestToRentBike(MouseEvent event) throws IOException {
        RentBikeScreenHandler rentBikeScreen;
        try {
            rentBikeScreen = new RentBikeScreenHandler(this.stage, Configs.RENT_BIKE_SCREEN_PATH);
            rentBikeScreen.setHomeScreenHandler(this);
            rentBikeScreen.setBController(new RentBikeController());
            rentBikeScreen.requestToRentBike(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void requestToReturnBike(MouseEvent event) throws IOException {
        ReturnBikeScreenHandler rentBikeScreen;
        try {
            // TODO tạm coi customerid = 10001
            rentBikeScreen = new ReturnBikeScreenHandler(this.stage, Configs.RETURN_BIKE_SCREEN_PATH, 10001);
            rentBikeScreen.setHomeScreenHandler(this);
            rentBikeScreen.setBController(new ReturnBikeController());
            rentBikeScreen.requestToRentBike(this);
        } catch (Exception e) {
            PopupScreen.error("Ban chua thue xe");
            //e.printStackTrace();
        }
    }

    public void viewBikesInStation(int stationId) throws IOException {
        ViewBikesInStationScreenHandler viewBikesInStationScreen;
        try {
            viewBikesInStationScreen = new ViewBikesInStationScreenHandler(this.stage, Configs.VIEW_BIKES_IN_STATION_SCREEN_PATH, stationId);
            viewBikesInStationScreen.setHomeScreenHandler(this);
            viewBikesInStationScreen.setBController(new ViewBikesInStationController());
            viewBikesInStationScreen.requestToViewBikes(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
