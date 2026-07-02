package com.ibm.day3;

import java.util.ArrayList;
interface Verifiable {
    boolean verify();
}
abstract class Payment {
    private double amount;
    public Payment(double amount) {
        this.amount = amount;
    }
    public double getAmount() {
        return amount;
    }
    abstract void processPayment();
}
class CreditCardPayment
extends Payment
implements Verifiable {
    private String cardNumber;
    public CreditCardPayment(
        double amount,
        String cardNumber
    ) {
        super(amount);
        this.cardNumber = cardNumber;
    }
    public boolean verify() {
        return cardNumber.length() == 16;
    }
    void processPayment() {
        System.out.println(
            "Processing credit card payment..."
        );
    }
}
class PayPalPayment
extends Payment
implements Verifiable {
    private String email;
    public PayPalPayment(
        double amount,
        String email
    ) {
        super(amount);
        this.email = email;
    }
    public boolean verify() {
        return email.contains("@");
    }
    void processPayment() {
        System.out.println(
            "Processing PayPal payment..."
        );
    }
}
class BankTransferPayment
extends Payment
implements Verifiable {
    private String accountNumber;
    public BankTransferPayment(
        double amount,
        String accountNumber
    ) {
        super(amount);
        this.accountNumber = accountNumber;
    }
    public boolean verify() {
        return accountNumber.length() == 10;
    }
    void processPayment() {
        System.out.println(
            "Processing bank transfer payment..."
        );
    }
}
sealed class PaymentType
permits OnlinePayment {
}
non-sealed class OnlinePayment
extends PaymentType {
}
record PaymentDetails(
    String transactionId,
    double amount,
    String paymentMethod
) {}
public class PaymentSystem {
    public static void main(String[] args) {
        ArrayList<Payment> payments =
            new ArrayList<>();
        payments.add(
            new CreditCardPayment(
                1000,
                "1234567890123456"
            )
        );
        payments.add(
            new PayPalPayment(
                500,
                "user@gmail.com"
            )
        );
        payments.add(
            new BankTransferPayment(
                700,
                "1234567890"
            )
        );
        for (Payment p : payments) {
            Verifiable v = (Verifiable) p;
            if (v.verify()) {
                p.processPayment();
            } else {
                System.out.println(
                    "Verification failed."
                );
            }
        }
        PaymentDetails details =
            new PaymentDetails(
                "TXN001",
                1000,
                "Credit Card"
            );
        System.out.println(details);
    }
}