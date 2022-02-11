package model;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalogue {

    private List<Product> catalogue;

    // EFFECTS: creates a new empty product list
    public ProductCatalogue() {

        catalogue = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: adds product to the catalogue
    public void addProduct(Product product) {
        catalogue.add(product);

    }

    // MODIFIES: this
    // EFFECTS: removes product from the catalogue
    public void removeProduct(Product product) {
        catalogue.remove(product);
    }

    // EFFECTS: returns true if product is in catalogue, false otherwise
    public boolean containsProduct(String name) {
        for (Product someProduct : catalogue) {
            if (someProduct.getProductName().equals(name)) {
                return true;
            }
        }
        return false;
    }

//    // MODIFIES: this
//    // EFFECTS: returns true if the product is delivered; false otherwise
//    public boolean isProductDelivered(String name) {
//        for (Product someProduct : catalogue) {
//            if (someProduct.getProductName().equals(name) && someProduct.isDelivered()) {
//                return true;
//            }
//        }
//        return false;
//    }


    // EFFECTS: returns the catalogue
    public List<Product> viewCatalogue() {
        return catalogue;
    }

    // EFFECTS: returns the size of the catalogue
    public int catalogueSize() {
        return catalogue.size();
    }

}
