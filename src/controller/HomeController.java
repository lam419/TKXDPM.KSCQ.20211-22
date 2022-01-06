package controller;

import subsystem.entity.station.Station;

import java.sql.SQLException;
import java.util.List;

public class HomeController extends BaseController {

    public List getAllStation() throws SQLException {
        return new Station().getAllStations();
    }

}
