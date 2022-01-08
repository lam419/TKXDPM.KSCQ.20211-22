package controller;

import entity.station.Station;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Hoàng Minh Lương - 20194108
 */
public class HomeController extends BaseController {

    public List getAllStation() throws SQLException {
        return new Station().getAllStations();
    }

}
