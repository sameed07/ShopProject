package usama.utech.lect1.Model;

public class Expense {

    int id;
    String itemName;
    double amountPaid;
    String date;
    String time;

    public Expense() {
    }

    public Expense(int id, String itemName, double amountPaid, String date, String time) {
        this.id = id;
        this.itemName = itemName;
        this.amountPaid = amountPaid;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
