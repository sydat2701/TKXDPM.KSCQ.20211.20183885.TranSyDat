package subsystem;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import exception.PaymentException;
import exception.UnrecognizedException;

public interface IRefund {
    /**
     * Refund, and then return the payment transaction
     *
     * @param card     - the credit card which would be refunded to
     * @param amount   - the amount to refund
     * @param contents - the transaction contents
     * @return {@link PaymentTransaction PaymentTransaction} - if the
     *         payment is successful
     * @throws PaymentException      if responded with a pre-defined error code
     * @throws UnrecognizedException if responded with an unknown error code or
     *                               something goes wrong
     */
    public abstract PaymentTransaction refund(CreditCard card, int amount, String contents)
            throws PaymentException, UnrecognizedException;
}
