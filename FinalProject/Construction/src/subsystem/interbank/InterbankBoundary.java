package subsystem.interbank;

import common.exception.UnrecognizedException;
import utils.API;

public class InterbankBoundary {

    String query(String url, String data) {
        String response = null;
        try {
            response = API.post(url, data);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnrecognizedException();
        }
        return response;
    }

}
