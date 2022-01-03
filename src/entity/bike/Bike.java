package entity.bike;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.db.EBRDB;
import utils.Configs;
import utils.Utils;

public class Bike {

//	public final int BIKE = 0;
//	public final int EBIKE = 1;
//	public final int TWINBIKE = 2;

	private int bikeId;
	private int type;
	private String stationId;

	public Bike() {
		super();
	}

	public Bike(int bikeId, int type, String stationId) {
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
		case 0:
			return "Xe đạp đơn";
		case 1:
			return "Xe đạp điện";
		default:
			return "Xe đạp đôi";
		}
	}

	public int getDeposit() {
		switch (type) {
		case 0:
			return 400000;
		case 1:
			return 700000;
		default:
			return 550000;
		}
	}

	public String getImageUrl() {
		String url = Configs.IMAGE_PATH;
		switch (type) {
		case 0:
			url += "/" + "Bike.jpg";
			break;
		case 1:
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

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
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
			return bike;
		}
		return null;
	}

	public void rentBike(int bikeId) throws SQLException {
		int customerId = 10000;
		int rentedat = 1000;

		String sql = "SELECT * FROM bike where id = " + bikeId;
		Statement stm = EBRDB.getConnection().createStatement();
		ResultSet res = stm.executeQuery(sql);
		if (res.next()) {
			rentedat = res.getInt("station");
		}

		sql = "UPDATE bike SET station = NULL WHERE id = " + bikeId;
		stm.executeQuery(sql);

		sql = "INSERT INTO bikerental (bikeId, customerId, rentedat, time) VALUES(" + bikeId + "," + customerId + ","
				+ rentedat + "," + Utils.getToday() + ")";
		stm.executeQuery(sql);
	}
}
