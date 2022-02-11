package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCatalogueTest {

    private ProductCatalogue catalogue;
    private Product p1;
    private Product p2;
    private Product p3;

    @BeforeEach

    public void runBefore() {
        catalogue = new ProductCatalogue();
        p1 = new Product("Lay's Indian Magic Masala",100,25);
        p2 = new Product("Lay's BBQ",250,75);
        p3 = new Product("Lay's Classic",30,30);
    }

    @Test

    public void testAddProduct() {
        catalogue.addProduct(p1);
        catalogue.addProduct(p2);
        catalogue.addProduct(p3);

        assertEquals(3,catalogue.catalogueSize());

    }

    @Test

    public void testRemoveProduct() {
        catalogue.addProduct(p1);
        catalogue.addProduct(p2);
        catalogue.addProduct(p3);
        assertEquals(3,catalogue.catalogueSize());
        catalogue.removeProduct(p1);
        assertEquals(2,catalogue.catalogueSize());



    }

    @Test

    public void testContainsProduct() {
        catalogue.addProduct(p1);
        catalogue.addProduct(p2);


        assertTrue(catalogue.containsProduct(p1.getProductName()));
        assertTrue(catalogue.containsProduct(p2.getProductName()));
        assertFalse(catalogue.containsProduct(p3.getProductName()));


    }

    @Test

    public void testIsProductDelivered() {
        assertFalse(p1.isDelivered());
        assertFalse(p2.isDelivered());
        assertTrue(p3.isDelivered());
    }

    @Test

    public void testViewCatalogue() {
     catalogue.addProduct(p1);
     catalogue.addProduct(p2);
     catalogue.addProduct(p3);
     assertEquals(3, catalogue.viewCatalogue().size());
    }

    @Test
    public void testCatalogueSize() {
        catalogue.addProduct(p1);
        catalogue.addProduct(p2);
        catalogue.addProduct(p3);
        assertEquals(3, catalogue.catalogueSize());
    }


}
