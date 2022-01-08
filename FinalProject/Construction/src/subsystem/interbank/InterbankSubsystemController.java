package subsystem.interbank;

import common.exception.InvalidCardException;
import common.exception.UnrecognizedException;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import utils.Configs;
import utils.MyMap;
import utils.Utils;

import java.util.Map;

public class InterbankSubsystemController {

    private static final String APP_CODE = "B5pajA7L4VU=";
    private static final String SECRET_KEY = "BZl0Q/U8834=";
    private static final String PAY_COMMAND = "pay";
    private static final String REFUND_COMMAND = "refund";
    private static final String VERSION = "1.0.1";

    private static InterbankBoundary interbankBoundary = new InterbankBoundary();

    public PaymentTransaction refund(CreditCard card, int amount, String contents) {
        return null;
    }

    private String generateData(Map<String, Object> data) {
        return ((MyMap) data).toJSON();
    }

    public PaymentTransaction payOrder(CreditCard card, int amount, String contents) {
        Map<String, Object> transaction = new MyMap();

        try {
            transaction.putAll(MyMap.toMyMap(card));
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new InvalidCardException();
        }
        if (amount < 0) {
            amount = -amount;
            transaction.put("command", REFUND_COMMAND);
        } else {
            transaction.put("command", PAY_COMMAND);
        }
        transaction.put("transactionContent", contents);
        transaction.put("amount", amount);
        transaction.put("createdAt", Utils.getToday());

        Map<String, Object> hashCodeMap = new MyMap();
        hashCodeMap.put("secretKey", SECRET_KEY);
        hashCodeMap.put("transaction", transaction);
        String hashCode = generateData(hashCodeMap);

        Map<String, Object> requestMap = new MyMap();
        requestMap.put("version", VERSION);
        requestMap.put("transaction", transaction);
        requestMap.put("appCode", APP_CODE);
        requestMap.put("hashCode", Utils.md5(hashCode));
        String responseText = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateData(requestMap));
        MyMap response = null;
        try {
            response = MyMap.toMyMap(responseText, 0);
        } catch (IllegalArgumentException e) {
            throw new UnrecognizedException();
        }

        return makePaymentTransaction(response);
    }

    private PaymentTransaction makePaymentTransaction(MyMap response) {
        String errorCode = (String) response.get("errorCode");
//        switch (errorCode) {
//            case "00":
//                break;
//            case "01":
//                throw new InvalidCardException();
//            case "02":
//                throw new NotEnoughBalanceException();
//            case "03":
//                throw new InternalServerErrorException();
//            case "04":
//                throw new SuspiciousTransactionException();
//            case "05":
//                throw new NotEnoughTransactionInfoException();
//            case "06":
//                throw new InvalidVersionException();
//            case "07":
//                throw new InvalidTransactionAmountException();
//            default:
//                throw new UnrecognizedException();
//        }
        if (!response.containsKey("transaction"))
            return null;
        MyMap transaction = (MyMap) response.get("transaction");
        CreditCard card = new CreditCard((String) transaction.get("cardCode"), (String) transaction.get("owner"),
                Integer.parseInt((String) transaction.get("cvvCode")), (String) transaction.get("dateExpired"));
        PaymentTransaction trans = new PaymentTransaction((String) response.get("errorCode"), card,
                (String) transaction.get("transactionId"), (String) transaction.get("transactionContent"),
                Integer.parseInt((String) transaction.get("amount")), (String) transaction.get("createdAt"));

        return trans;
    }
}
