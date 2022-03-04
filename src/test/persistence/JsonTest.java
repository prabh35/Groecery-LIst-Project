package persistence;

import model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkThingy(String name, int quantity, int toBeSold, Product product) {
        assertEquals(name, product.getProductName() );
        assertEquals(quantity, product.getQuantity());
        assertEquals(toBeSold,toBeSold);
    }
}
