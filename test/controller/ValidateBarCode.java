package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateBarCode {

	private RentBikeController rentBikeController;

	@BeforeEach
	void setUp() throws Exception {
		rentBikeController = new RentBikeController();
	}

	@ParameterizedTest
	@CsvSource({ "8900123456789,true", "01234,false", "abc123,false", ",false" })

	void test(String code, Boolean expected) {
		boolean isValid = rentBikeController.validateBarCode(code);
		assertEquals(expected, isValid);
	}

}
