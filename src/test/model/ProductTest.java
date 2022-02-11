package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private Product p1;
    private Product p2;
    private Product p3;

    @BeforeEach

    public void runBefore() {

        p1 = new Product("Lay's Indian Magic Masala",100,25);
        p2 = new Product("Lay's BBQ",250,75);
        p3 = new Product("Lay's Classic",30,30);
    }

    @Test

    public void testProduct() {

        assertEquals(75, p1.productLeft());
        assertEquals(175,p2.productLeft());
    }

    @Test

    public void testIsDelivered() {

        assertFalse(p1.isDelivered());
        assertFalse(p2.isDelivered());
        assertTrue(p3.isDelivered());
    }


    @Test

    public void testGetQuantity() {
        assertEquals(100,p1.getQuantity());
        assertEquals(250,p2.getQuantity());
        assertEquals(30,p3.getQuantity());
    }
}
