package entity.bike;

import java.util.Date;

public class Bike {

//	public final int BIKE = 0;
//	public final int EBIKE = 1;
//	public final int TWINBIKE = 2;

	private String bikeId;
	private String name;
	private double weight;
	private String lisencePlate;
	private Date manuafaturingDate;
	private String manuafaturer;
	private double cost;
	private int type;
	private String stationId;
	// TODO JsonProperty
	private /* @JsonProperty("isRent") */ boolean isRent;

	public Bike() {
		super();
	}

	public Bike(String bikeId, String name, double weight, String lisencePlate, Date manuafaturingDate,
			String manuafaturer, double cost, int type, String stationId, boolean isRent) {
		super();
		this.bikeId = bikeId;
		this.name = name;
		this.weight = weight;
		this.lisencePlate = lisencePlate;
		this.manuafaturingDate = manuafaturingDate;
		this.manuafaturer = manuafaturer;
		this.cost = cost;
		this.type = type;
		this.stationId = stationId;
		this.isRent = isRent;
	}

	public String getBikeId() {
		return bikeId;
	}

	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getLisencePlate() {
		return lisencePlate;
	}

	public void setLisencePlate(String lisencePlate) {
		this.lisencePlate = lisencePlate;
	}

	public Date getManuafaturingDate() {
		return manuafaturingDate;
	}

	public void setManuafaturingDate(Date manuafaturingDate) {
		this.manuafaturingDate = manuafaturingDate;
	}

	public String getManuafaturer() {
		return manuafaturer;
	}

	public void setManuafaturer(String manuafaturer) {
		this.manuafaturer = manuafaturer;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
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
			case 0: return 400000;
			case 1: return 700000;
			default: return 550000;
		}
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

	public boolean isRent() {
		return isRent;
	}

	public void setRent(boolean isRent) {
		this.isRent = isRent;
	}

	public boolean match(Bike bike) {
		if (bike == null)
			return true;

		if (bike.bikeId != null && !bike.bikeId.equals("") && !this.bikeId.contains(bike.bikeId)) {
			return false;
		}
		if (bike.name != null && !bike.name.equals("") && !this.name.contains(bike.name)) {
			return false;
		}
		if (bike.weight != 0 && this.weight != bike.weight) {
			return false;
		}
		if (bike.lisencePlate != null && !bike.lisencePlate.equals("")
				&& !this.lisencePlate.contains(bike.lisencePlate)) {
			return false;
		}
		if (bike.manuafaturingDate != null && !this.manuafaturingDate.equals(bike.manuafaturingDate)) {
			return false;
		}
		if (bike.manuafaturer != null && !bike.manuafaturer.equals("")
				&& !this.manuafaturer.contains(bike.manuafaturer)) {
			return false;
		}
		if (bike.cost != 0 && this.cost != bike.cost) {
			return false;
		}
		if (bike.stationId != null && !bike.stationId.equals("") && !this.stationId.contains(bike.stationId)) {
			return false;
		}
		return true;
	}

	public Bike getBikeFromId(String code) {
		// TODO get bike from database
		return this;
	}

	public boolean equals(Bike obj) {
		return this.bikeId.equals(obj.bikeId);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
