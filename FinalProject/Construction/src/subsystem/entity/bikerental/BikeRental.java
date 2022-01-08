package subsystem.entity.bikerental;

import subsystem.entity.db.EBRDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Hoàng Minh Lương - 20194108
 */
public class BikeRental {
    private int bikeId;
    private int customerId;
    private int stationId;
    private String time;

    public BikeRental() {
    }

    public BikeRental(int bikeId, int customerId, int stationId, String time) {
        this.bikeId = bikeId;
        this.customerId = customerId;
        this.stationId = stationId;
        this.time = time;
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BikeRental getBikeRentalFromCustomerId(int customerId) throws SQLException {
        // TODO tạm coi customerId = 10001
        BikeRental bikeRental = new BikeRental();
        customerId = 10001;
        String sql = "SELECT * FROM bikerental where customerid = " + customerId;
        Statement stm = EBRDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if (res.next()) {
            bikeRental.setBikeId(res.getInt("bikeid"));
            bikeRental.setCustomerId(customerId);
            bikeRental.setStationId(res.getInt("rentedat"));
            bikeRental.setTime(res.getString("time"));
        }
        return bikeRental;
    }
}
