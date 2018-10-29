package usama.utech.lect1.Model;

public class Sale {

    int id;
    String customerName;
    String productName;
    int quantitySold;
    String date;
    String time;

    public Sale() {
    }

    public Sale(int id, String customerName, String productName, int quantitySold, String date, String time) {
        this.id = id;
        this.customerName = customerName;
        this.productName = productName;
        this.quantitySold = quantitySold;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
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
