package ContentData;

import java.io.Serializable;

public class OutstandingPayments implements Serializable {

    private double totalOutstandingAmount;
    private double accumulatedPaid;
    private double amountUnpaid;
    private String paymentDeadline;

    public OutstandingPayments() {
        this.totalOutstandingAmount = 69420;
        this.accumulatedPaid = 69000;
        this.amountUnpaid = totalOutstandingAmount - accumulatedPaid;
        this.paymentDeadline = "31/12/2023";
    }

    public OutstandingPayments(double totalOutstandingAmount, double accumulatedPaid, String paymentDeadline) {
        this.totalOutstandingAmount = totalOutstandingAmount;
        this.accumulatedPaid = accumulatedPaid;
        this.amountUnpaid = totalOutstandingAmount - accumulatedPaid;
        this.paymentDeadline = paymentDeadline;
    }

    public double getOutstandingAmount() {
        return totalOutstandingAmount;
    }

    public double getAccumulatedPaid() {
        return accumulatedPaid;
    }

    public double getTotalAmount() {
        return amountUnpaid;
    }

    public String getDueDate() {
        return paymentDeadline;
    }

    @Override
    public String toString() {
        if (amountUnpaid != 0) {
            return "You have outstanding payments: RM" + amountUnpaid + "\nPlease make the outstanding payment before: " + paymentDeadline + "\n";
        }
        return "You have no outstanding payments. Good Job!";
    }
}
