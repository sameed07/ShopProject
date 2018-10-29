package usama.utech.lect1.Model;

public class Buyer {

    int id;
    String buyerName;
    double totalAmount;
    double paidAmount;
    double remainingAmount;
    String lastPaidDate;


    public Buyer() {
    }

    public Buyer(int id, String buyerName, double totalAmount, double paidAmount, double remainingAmount, String lastPaidDate) {
        this.id = id;
        this.buyerName = buyerName;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.remainingAmount = remainingAmount;
        this.lastPaidDate = lastPaidDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public String getLastPaidDate() {
        return lastPaidDate;
    }

    public void setLastPaidDate(String lastPaidDate) {
        this.lastPaidDate = lastPaidDate;
    }
}
