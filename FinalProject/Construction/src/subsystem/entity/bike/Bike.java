package subsystem.entity.bike;

import subsystem.entity.db.EBRDB;
import utils.Configs;
import utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Hoàng Minh Lương - 20194108
 */
public class Bike {

    public final int BIKE = 0;
    public final int EBIKE = 1;
    public final int TWINBIKE = 2;

    private int bikeId;
    private int type;
    private int stationId;

    public Bike() {
        super();
    }

    public Bike(int bikeId, int type, int stationId) {
        super();
        this.bikeId = bikeId;
        this.type = type;
        this.stationId = stationId;
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public int getType() {
        return type;
    }

    public String getTypeString() {
        switch (type) {
            case BIKE:
                return "Xe đạp đơn";
            case EBIKE:
                return "Xe đạp điện";
            default:
                return "Xe đạp đôi";
        }
    }

    public int getDeposit() {
        switch (type) {
            case BIKE:
                return 400000;
            case EBIKE:
                return 700000;
            default:
                return 550000;
        }
    }

    public String getImageUrl() {
        String url = Configs.IMAGE_PATH;
        switch (type) {
            case BIKE:
                url += "/" + "Bike.jpg";
                break;
            case EBIKE:
                url += "/" + "ElectricBike.png";
                break;
            default:
                url += "/" + "TwinBike.jpg";
        }
        return url;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public Bike getBikeFromId(String code) throws SQLException {
        String sql = "SELECT * FROM bike where id = " + code;
        Statement stm = EBRDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if (res.next()) {
            Bike bike = new Bike();
            bike.setBikeId(res.getInt("id"));
            bike.setType(res.getInt("type"));
            bike.setStationId(res.getInt("station"));
            return bike;
        }
        return null;
    }

    public Bike getBikeFromCustomerId(int customerId) throws SQLException {
        // TODO tạm coi customerId = 10001
        Bike bike = new Bike();
        customerId = 10001;
        String sql = "SELECT * FROM bike, bikerental where customerid = " + customerId;
        Statement stm = EBRDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if (res.next()) {
            bike.setBikeId(res.getInt("id"));
            bike.setType(res.getInt("type"));
            bike.setStationId(0);
        }
        return bike;
    }

    public void rentBike(int bikeId) throws SQLException {
        // TODO tạm coi customerId = 10001
        int customerId = 10001;
        int rentedat = 1000;

        String sql = "SELECT * FROM bike where id = " + bikeId;
        Statement stm = EBRDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if (res.next()) {
            rentedat = res.getInt("station");
        }

        sql = "UPDATE bike SET station = 0 WHERE id = " + bikeId;
        stm.executeUpdate(sql);

        sql = "INSERT INTO bikerental (bikeid, customerid, rentedat, time) " +
                "VALUES ('" + bikeId + "','" + customerId + "','" + rentedat + "','" + Utils.getToday() + "')";
        stm.executeUpdate(sql);
    }

    public void returnBike(int bikeId, int stationId) throws SQLException {
        Statement stm = EBRDB.getConnection().createStatement();
        String sql = "UPDATE bike SET station = " + stationId + " WHERE id = " + bikeId;
        stm.executeUpdate(sql);

        sql = "DELETE  FROM bikerental WHERE bikeid = " + bikeId;
        stm.executeUpdate(sql);
    }
}
