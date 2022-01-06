package controller;

import entity.station.Station;

import java.sql.SQLException;
import java.util.List;

public class ViewBikesInStationController extends BaseController {

    public List getAllBikeInStation(int stationId) throws SQLException {
        return new Station().getAllBikeInStation(stationId);
    }
}
