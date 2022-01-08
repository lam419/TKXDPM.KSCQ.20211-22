package subsystem;

import subsystem.entity.payment.CreditCard;
import subsystem.entity.payment.PaymentTransaction;
import subsystem.interbank.InterbankSubsystemController;

public class InterbankSubsystem implements InterbankInterface {

    /**
     * Represent the controller of the subsystem
     */
    private InterbankSubsystemController ctrl;

    /**
     * Initializes a newly created {@code InterbankSubsystem} object so that it
     * represents an Interbank subsystem.
     */
    public InterbankSubsystem() {
        String str = new String();
        this.ctrl = new InterbankSubsystemController();
    }

    /**
     * @see InterbankInterface#payOrder(CreditCard, int,
     * java.lang.String)
     */
    public PaymentTransaction payOrder(CreditCard card, int amount, String contents) {
        PaymentTransaction transaction = ctrl.payOrder(card, amount, contents);
        return transaction;
    }

    /**
     * @see InterbankInterface#refund(CreditCard, int,
     * java.lang.String)
     */
    public PaymentTransaction refund(CreditCard card, int amount, String contents) {
        PaymentTransaction transaction = ctrl.refund(card, amount, contents);
        return transaction;
    }

}
