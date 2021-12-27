package entity.station;

import java.util.ArrayList;

import entity.bike.Bike;

public class DockingStation {

	private String stationId;
	private String name;
	private String address;
	private int freeSpace;
	private int totalSpace;
	private int phone;
	private ArrayList<Bike> listBike;

	public DockingStation() {
		super();
	}

	public DockingStation(String stationId, String name, String address, int freeSpace, int totalSpace, int phone) {
		super();
		this.stationId = stationId;
		this.name = name;
		this.address = address;
		this.freeSpace = freeSpace;
		this.totalSpace = totalSpace;
		this.phone = phone;
		this.listBike = new ArrayList<Bike>();
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
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

	public int getFreeSpace() {
		return freeSpace;
	}

	public void setFreeSpace(int freeSpace) {
		this.freeSpace = freeSpace;
	}

	public int getTotalSpace() {
		return totalSpace;
	}

	public void setTotalSpace(int totalSpace) {
		this.totalSpace = totalSpace;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public ArrayList<Bike> getListBike() {
		return listBike;
	}

	public void setListBike(ArrayList<Bike> listBike) {
		this.listBike = listBike;
	}

	public boolean match(DockingStation dockingStation) {
		if (dockingStation == null)
			return true;

		if (dockingStation.stationId != null && !dockingStation.stationId.equals("")
				&& !this.stationId.contains(dockingStation.stationId)) {
			return false;
		}
		if (dockingStation.name != null && !dockingStation.name.equals("")
				&& !this.name.contains(dockingStation.name)) {
			return false;
		}
		if (dockingStation.address != null && !dockingStation.address.equals("")
				&& !this.address.contains(dockingStation.address)) {
			return false;
		}
		if (dockingStation.totalSpace != 0 && this.totalSpace != dockingStation.totalSpace) {
			return false;
		}
		if (dockingStation.freeSpace != 0 && this.freeSpace != dockingStation.freeSpace) {
			return false;
		}
		if (dockingStation.phone != 0 && this.phone != dockingStation.phone) {
			return false;
		}
		return true;
	}

	public boolean equals(DockingStation obj) {
		return this.stationId.equals(obj.stationId);
	}

}
