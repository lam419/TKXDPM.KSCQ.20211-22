package controller;

import javax.swing.JDialog;
import java.util.ArrayList;

import api.EcoBikeApi;
import api.EcoBikeApiFactory;

public class RentingBikeController {
	EcoBikeApi api;
	private SearchPanel searchPanel;
	private DataPanel dataPanel;
	Runnable finishedEventCallback;
	
	public RentingBikeController() {
		api = EcoBikeApiFactory.getInstance();
		searchPanel = new SearchPanel();
		dataPanel = new DataPanel();
		searchPanel.setController(this);
		dataPanel.setController(this);
		createRetingBikeDialog();
	}
	
	public RentingBikeController(Bike bike) {
		api = EcoBikeApiFactory.getInstance();
		dataPanel = new DataPanel();
		dataPanel.setController(this);
		updateDataPanel(bike);
		createRetingBikeDialog(bike);
	}
	
	public Bike getBikeByCode(String bikeCode) {
		Bike res = api.getBikeByCode(bikeCode);
		if (res!=null) {
			if (res.isRent()) return null;
		}
		return res;
	} 
	
	public void createRetingBikeDialog(Bike bike) {
		dataPanel = new DataPanel();
		dataPanel.setController(this);
		updateDataPanel(bike);
		RentingBikeView scr=  new RentingBikeView(searchPanel,dataPanel);
		scr.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		scr.setVisible(true);
	}
	public void createRetingBikeDialog() {
		searchPanel = new SearchPanel();
		dataPanel = new DataPanel();
		searchPanel.setController(this);
		dataPanel.setController(this);
		RentingBikeView scr=  new RentingBikeView(searchPanel,dataPanel);
		scr.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		scr.setVisible(true);
	}
	public void updateDataPanel(Bike bike) {
		dataPanel.setData(bike);
	}
	public Order addOrder(Order order) {
		Order res = api.addOrder(order);
		return res;
	} 
	public CreditCard getCard(String cardId) {
		CreditCard res = api.getCard(cardId);
		return res;
	}
	public double minusAmountCard(String cardId,double money) {
		double res = api.minusAmount(cardId, money);
		return res;
	}
	public DockingStation updateDockingStation(DockingStation station) {
		DockingStation res = api.updateDockingStation(station);
		return res;
	}
	public Bike updateBike(Bike bike) {
		Bike res = api.updateBike(bike);
		return res;
	}
	public DockingStation getDockingStation(String id) {
		DockingStation res = api.getDockingStation(id);
		return res;
	}
	public boolean checkCard (String cardId) {
		Customer customer = api.getCustomer("customer1");
		ArrayList<Order> orders = api.getOrder();
		for (Order o : orders) {
			if (o.getCustomerId().equals(customer.getCustomerId())) {
				if (o.getCardId().equals(cardId) && o.isPayment()== false) return false;
			}
		}
		return true;
	}
	
	public void setFinishedEventCallback(Runnable finishedEventCallback) {
		this.finishedEventCallback = finishedEventCallback;
	}
}
