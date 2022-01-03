package controller;

import java.sql.SQLException;

import common.exception.InvalidBarCodeException;
import entity.bike.Bike;

public class BikeInfoController extends BaseController {

	public void processBarCode(String barCode) {
		if (!validateBarCode(barCode))
			throw new InvalidBarCodeException();
	}

	public boolean validateBarCode(String barCode) {
		if (barCode == null)
			return false;
		if (barCode.length() != 8)
			return false;

		char[] chars = barCode.toCharArray();
		for (char c : chars) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}

	public Bike getBikeFromBarCode(String barCode) throws SQLException {
		return new Bike().getBikeFromId(barCode);
	}
}
