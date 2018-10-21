package usama.utech.lect1.Model;

public class Products {

    private String id;
    private String barCode;
    private String name;
    private String quantity;
    private String salePrice;
    private String purchasePrice;
    private String wholeSalePrice;

    public Products() {

    }

    public Products(String name, String barCode, String id, String quantity, String salePrice, String
            purchasePrice, String wholeSalePrice) {
        this.name = name;
        this.barCode = barCode;
        this.id = id;
        this.quantity = quantity;
        this.salePrice = salePrice;
        this.purchasePrice = purchasePrice;
        this.wholeSalePrice = wholeSalePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getWholeSalePrice() {
        return wholeSalePrice;
    }

    public void setWholeSalePrice(String wholeSalePrice) {
        this.wholeSalePrice = wholeSalePrice;
    }
}
