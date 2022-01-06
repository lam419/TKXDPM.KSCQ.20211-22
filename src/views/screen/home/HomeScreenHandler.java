package views.screen.home;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.*;
import entity.station.Station;
import javafx.scene.input.MouseEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.payment.PaymentScreenHandler;
import views.screen.rentbike.RentBikeScreenHandler;
import views.screen.viewbikesinstation.ViewBikesInStationScreenHandler;

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
//        addMenuItem(0, "Book", splitMenuBtnSearch);
//        addMenuItem(1, "DVD", splitMenuBtnSearch);
//        addMenuItem(2, "CD", splitMenuBtnSearch);
    }

    //
    public void setImage() {
        // fix image path caused by fxml
        File file1 = new File(Configs.IMAGE_PATH + "/" + "Logo.png");
        Image img1 = new Image(file1.toURI().toString());
//        ebrImage.setImage(img1);
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

//    private void addMenuItem(int position, String text, MenuButton menuButton) {
//        MenuItem menuItem = new MenuItem();
//        Label label = new Label();
//        label.prefWidthProperty().bind(menuButton.widthProperty().subtract(31));
//        label.setText(text);
//        label.setTextAlignment(TextAlignment.RIGHT);
//        menuItem.setGraphic(label);
//        menuItem.setOnAction(e -> {
//            // empty home station
//            hboxStation.getChildren().forEach(node -> {
//                VBox vBox = (VBox) node;
//                vBox.getChildren().clear();
//            });
//
//            // filter only station with the choosen category
//            List filteredItems = new ArrayList<>();
//            homeItems.forEach(me -> {
//                StationHandler station = (StationHandler) me;
//                if (station.getStation().getName().toLowerCase().startsWith(text.toLowerCase())) {
//                    filteredItems.add(station);
//                }
//            });
//
//            // fill out the home with filted station as category
//            addStationHome(filteredItems);
//        });
//        menuButton.getItems().add(position, menuItem);
//    }

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
