package model;

public class Product {

    private String productName;
    private int quantity;
    private int toBeSold;
    private boolean isDeliverable;

    // EFFECTS: constructs a product
    public Product(String productName, int quantity, int toBeSold) {
        this.productName = productName;
        this.toBeSold = toBeSold;
        this.quantity = quantity;
        this.isDeliverable = false;
    }

    public String getProductName() {
        return this.productName;
    }


    public int getQuantity() {
        return this.quantity;
    }

    //REQUIRES: quantity >= sold
    //EFFECTS: return quantity - needed product
    public int productLeft() {
        return quantity - toBeSold;
    }

    // EFFECTS: is true if productLeft == 0, false otherwise
    public boolean isDelivered() {
        if (productLeft() == 0) {
            isDeliverable = true;
        }
        return isDeliverable;
    }
}