package ContentData;

import java.io.Serializable;

public class Payments implements Serializable {

    private String transactionID;
    private String payerID;
    private String payeeID;
    private String transactionTime;
    private double amount;
    private String receiptID;

    public Payments(String transactionID, String payerID, String payeeID, String timestamp, double amount, String receiptID) {
        this.transactionID = transactionID;
        this.payerID = payerID;
        this.payeeID = payeeID;
        this.transactionTime = timestamp;
        this.amount = amount;
        this.receiptID = receiptID;
    }

    public Payments() {
        this.transactionID = "T001";
        this.payerID = "PAYER001";
        this.payeeID = "PAYEE001";
        this.transactionTime = "22/3/23";
        this.amount = 6969;
        this.receiptID = "R001";
    }

    public Payments(String transactionID) {
        this.transactionID = transactionID;
        this.payerID = "PAYER001";
        this.payeeID = "PAYEE001";
        this.transactionTime = "22/3/23";
        this.amount = 6969;
        this.receiptID = "R001";
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
                + "Transaction ID: '" + transactionID + '\''
                + "\nPayer ID: '" + payerID + '\''
                + "\nPayee ID: '" + payeeID + '\''
                + "\nTime: '" + transactionTime + '\''
                + "\nAmount: RM" + amount
                + "\nReceiptID = '" + receiptID + '\''
                + "\n";
    }
}
