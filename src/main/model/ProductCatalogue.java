package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalogue implements Writable {

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

    // EFFECTS: returns the catalogue
    public List<Product> viewCatalogue() {
        return catalogue;
    }

    // EFFECTS: returns the size of the catalogue
    public int catalogueSize() {
        return catalogue.size();
    }


    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("catalogue", thingiesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Product p : catalogue) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

}
