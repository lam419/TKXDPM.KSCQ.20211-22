package controller;

import java.io.IOException;

import common.exception.InvalidBarCodeException;
import entity.bike.Bike;

public class RentBikeController extends BaseController {

	public void processBarCode(String barCode) {
		if (!validateBarCode(barCode))
			throw new InvalidBarCodeException();
	}

	/**
	 * 
	 * @param barCode hợp gồm gồm 13 chữ số viết liền không cách
	 * @return
	 */
	public boolean validateBarCode(String barCode) {
		if (barCode == null)
			return false;
		if (barCode.length() != 13)
			return false;

		char[] chars = barCode.toCharArray();
		for (char c : chars) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}

	public Bike getBikeFromBarCode(String barCode) {
		return new Bike().getBikeFromId(barCode);
	}
}
