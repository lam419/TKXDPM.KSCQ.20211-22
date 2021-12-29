package common.exception;

public class InvalidBarCodeException extends RentBikeException {

	public InvalidBarCodeException() {
		super("ERROR: Invalid barcode!");
	}

}
