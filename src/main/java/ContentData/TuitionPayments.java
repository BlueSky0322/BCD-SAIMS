package ContentData;

import java.io.Serializable;

public class TuitionPayments implements Serializable {

    private String transactionID;
    private String payerID;
    private String payeeID;
    private String transactionTime;
    private double amount;
    private String receiptID;

    public TuitionPayments(String ID) {
        this.transactionID = "TRANX-" + ID;
        this.payerID = "PAYER-" + ID;
        this.payeeID = "PAYEE-APU123";
        this.transactionTime = "22/3/23";
        this.amount = 69420;
        this.receiptID = "RCPT-" + ID;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getPayerID() {
        return payerID;
    }

    public String getPayeeID() {
        return payeeID;
    }

    public String getTimestamp() {
        return transactionTime;
    }

    public double getAmount() {
        return amount;
    }

    public String getReceiptID() {
        return receiptID;
    }

    @Override
    public String toString() {
        return "\n==------------== Payment Procedures ==------------=="
                + "\nTransaction ID: '" + transactionID + '\''
                + "\nPayer ID: '" + payerID + '\''
                + "\nPayee ID: '" + payeeID + '\''
                + "\nTime: '" + transactionTime + '\''
                + "\nAmount: RM" + amount
                + "\nReceiptID = '" + receiptID + '\''
                + "\n";
    }
}
