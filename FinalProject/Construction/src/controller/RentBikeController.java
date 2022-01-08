package controller;

import common.exception.InvalidBarCodeException;
import subsystem.entity.bike.Bike;

import java.sql.SQLException;

/**
 * @author Hoàng Minh Lương - 20194108
 */
public class RentBikeController extends BaseController {

    public void processBarCode(String barCode) {
        if (!validateBarCode(barCode))
            throw new InvalidBarCodeException();
    }

    /**
     * @param barCode hợp gồm gồm 8 chữ số viết liền không cách
     * @return
     */
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
