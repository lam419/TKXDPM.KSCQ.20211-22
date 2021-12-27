package entity.order;

import java.util.Date;

public class Order {

	private String orderId;
	private String customerId;
	private String cardId;
	private double amount;
	private String startStationId;
	private String finishStationId;
	private long timeRent;
	private Date startTime;
	private Date finishTime;
	private boolean isPayment;
	private String bikeId;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(String orderId, String customerId, String cardId, double amount, String startStationId,
			String finishStationId, long timeRent, Date startTime, Date finishTime, boolean isPayment, String bikeId) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.cardId = cardId;
		this.amount = amount;
		this.startStationId = startStationId;
		this.finishStationId = finishStationId;
		this.timeRent = timeRent;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.isPayment = isPayment;
		this.bikeId = bikeId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStartStationId() {
		return startStationId;
	}

	public void setStartStationId(String startStationId) {
		this.startStationId = startStationId;
	}

	public String getFinishStationId() {
		return finishStationId;
	}

	public void setFinishStationId(String finishStationId) {
		this.finishStationId = finishStationId;
	}

	public long getTimeRent() {
		if (startTime != null && finishTime != null) {
			return finishTime.getTime() - startTime.getTime();
		} else {
			return 0;
		}
	}

	public void setTimeRent(long timeRent) {
		this.timeRent = timeRent;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getBikeId() {
		return bikeId;
	}

	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public boolean isPayment() {
		return isPayment;
	}

	public void setPayment(boolean isPayment) {
		this.isPayment = isPayment;
	}

	public boolean search(Order order) {
		if (order == null)
			return true;
		if (this.orderId != null && !this.orderId.equals("") && !this.orderId.contains(order.orderId)) {
			return false;
		}
		if (this.customerId != null && !this.customerId.equals("") && !this.customerId.contains(order.customerId)) {
			return false;
		}
		if (this.bikeId != null && !this.bikeId.equals("") && !this.bikeId.contains(order.bikeId)) {
			return false;
		}
		return true;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Order) {
			return this.orderId.equals(((Order) obj).orderId);
		}
		return false;
	}
}
