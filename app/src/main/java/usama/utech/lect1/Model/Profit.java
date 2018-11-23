package usama.utech.lect1.Model;

public class Profit {

    int id;
    String date;
    String time;
    double profit;

    public Profit() {
    }

    public Profit(int id, String date, String time, double profit) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.profit = profit;
    }
    public Profit( String date, String time, double profit) {

        this.date = date;
        this.time = time;
        this.profit = profit;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
