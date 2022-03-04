package ui;

import model.Product;
import model.ProductCatalogue;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//runs the product catalogue app
public class ProductCatalogueApp {

    private ProductCatalogue productCatalogue;
    Scanner productInfo = new Scanner(System.in);
    private static final String JSON_STORE = "./data/workroom.json";
    JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    JsonReader jsonReader = new JsonReader(JSON_STORE);

    //EFFECTS: constructs the product catalogue app
    public ProductCatalogueApp() {
        productCatalogue = new ProductCatalogue();
        initiate();

    }

    //EFFECTS: initiates the user interface
    private void initiate() {
        System.out.println("Product Catalogue");
        features();
        productInfo = new Scanner(System.in);
        String f = productInfo.nextLine();
        featuresButtons(f);
    }


    //EFFECTS: processes user command
    private void featuresButtons(String f) {
        if (f.equals("A")) {
            addProduct();
        } else if (f.equals("R")) {
            removeProduct();
        } else if (f.equals("D")) {
            isDeliverable();
        } else if (f.equals("P")) {
            productDetails();
        } else if (f.equals("E")) {
            System.out.println("Thank You ! Have a nice day !");
        } else if (f.equals("S")) {
            saveProduct();
        } else if (f.equals("L")) {
            loadProduct();
        } else {
            System.out.println("Please enter the correct option");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadProduct() {
        try {
            productCatalogue = jsonReader.read();
            System.out.println("Loaded " + productCatalogue + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        initiate();
    }

    // EFFECTS: saves the workroom to file
    private void saveProduct() {
        try {
            jsonWriter.open();
            jsonWriter.write(productCatalogue);
            jsonWriter.close();
            System.out.println("Saved " + productCatalogue + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS: give details of the product in catalogue
    private void productDetails() {
        for (Product p : productCatalogue.viewCatalogue()) {
            System.out.println("Product name is " + p.getProductName());
            System.out.println("Quantity available is " + p.getQuantity());
            System.out.println("Product needed is " + p.productLeft());
        }
        initiate();
    }

    //EFFECT: check whether the product is available to be delivered
    private void isDeliverable() {
        for (Product product : productCatalogue.viewCatalogue()) {
            if (product.productLeft() > 0) {
                System.out.println("The product available in the stock are :");
                System.out.println(product.getProductName());
            }
        }
        initiate();

    }

    //EFFECTS: removes the product from the catalogue
    private void removeProduct() {
        System.out.println("Enter the name of the product to be delivered :");
        String name = productInfo.nextLine();
        boolean contains = false;
        Product toBeRemoved = new Product("", 1, 1);

        for (Product product : productCatalogue.viewCatalogue()) {
            if (product.getProductName().equals(name)) {
                contains = true;
                toBeRemoved = product;
            }
        }

        if (!contains) {
            System.out.println("Error: Product entered not in the catalogue");
        } else {
            productCatalogue.removeProduct(toBeRemoved);
        }
        initiate();
    }


    //EFFECTS: adds the product to the catalogue
    private void addProduct() {
        System.out.println("Enter product name ");
        String name = productInfo.nextLine();
        System.out.println("Enter quantity");
        int quantity = productInfo.nextInt();
        System.out.println("Product quantity to be sold");
        int toBeSold = productInfo.nextInt();
        Product p = new Product(name, quantity, toBeSold);
        productCatalogue.addProduct(p);
        initiate();
    }

    //EFFECTS: provide user options to select from
    private void features() {
        System.out.println("Press A for adding a product");
        System.out.println("Press R for removing a product");
        System.out.println("Press D to check if the product is deliverable");
        System.out.println("Press P to see the product information");
        System.out.println("Press E to exit the catalogue");
        System.out.println("Press S to save the catalogue");
        System.out.println("Press L to load the catalogue");
    }


}
