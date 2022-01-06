package entity.station;

import entity.bike.Bike;
import entity.db.EBRDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Station {
    private int stationId;
    private String name;
    private String address;
    private int capacity;

    public Station() {
        super();
    }

    public Station(int stationId, String name, String address, int capacity) {
        this.stationId = stationId;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Station> getAllStations() throws SQLException {
        List<Station> stations = new ArrayList<>();
        String sql = "SELECT * FROM station";
        Statement stm = EBRDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        while (res.next()) {
            Station station = new Station();
            station.setStationId(res.getInt("id"));
            station.setName(res.getString("name"));
            station.setAddress(res.getString("address"));
            station.setCapacity(res.getInt("capacity"));
            stations.add(station);
        }
        return stations;
    }

    public List<Bike> getAllBikeInStation(int stationId) throws SQLException {
        List<Bike> bikes = new ArrayList<>();
        String sql = "SELECT * FROM bike where station = " + stationId;
        Statement stm = EBRDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        while (res.next()) {
            Bike bike = new Bike();
            bike.setBikeId(res.getInt("id"));
            bike.setType(res.getInt("type"));
            bike.setStationId(res.getInt("station"));
            bikes.add(bike);
        }
        return bikes;
    }

    public Station getStationFromId(int stationId) throws SQLException {
        Station station = new Station();
        String sql = "SELECT * FROM station where id = " + stationId;
        Statement stm = EBRDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if (res.next()) {
            station.setStationId(stationId);
            station.setName(res.getString("name"));
            station.setAddress(res.getString("address"));
            station.setCapacity(res.getInt("capacity"));
            return station;
        } else return null;
    }
}
