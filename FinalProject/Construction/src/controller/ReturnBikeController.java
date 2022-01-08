package controller;

import entity.bike.Bike;
import entity.bikerental.BikeRental;
import entity.station.Station;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Hoàng Minh Lương - 20194108
 */
public class ReturnBikeController extends BaseController {
    public BikeRental getBikeRentalFromCustomerId(int customerId) throws SQLException {
        return new BikeRental().getBikeRentalFromCustomerId(customerId);
    }

    public Bike getBikeFromCustomerId(int customerId) throws SQLException {
        return new Bike().getBikeFromCustomerId(customerId);
    }

    public int calculateFee(String timeStart, String timeReturn) throws ParseException {
        int fee = 0;
        Date dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStart);
        Date dateReturn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeReturn);
        int minutes = (int) ((dateReturn.getTime() - dateStart.getTime()) / 1000 / 60);
        if (minutes > 30) fee += (minutes - 30) / 15 * 3000 + 10000;
        if (minutes > 10 && minutes <= 30) fee += 10000;
        if (minutes <= 10) fee = 0;
        return fee;
    }

    public Station getStationFromId(int stationId) throws SQLException {
        return new Station().getStationFromId(stationId);
    }
}
