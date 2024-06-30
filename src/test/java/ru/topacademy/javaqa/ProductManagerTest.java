package ru.topacademy.javaqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository;
    private ProductManager manager;

    @BeforeEach
    void setUp() {
        repository = new ProductRepository();
        manager = new ProductManager(repository);
    }

    @Test
    void shouldAddProduct() {
        Product product = new Product(1, "Product1", 100);
        manager.add(product);
        Product[] expected = new Product[]{product};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByExistingText() {
        Product product1 = new Product(1, "Product1", 100);
        Product product2 = new Product(2, "Another", 200);
        manager.add(product1);
        manager.add(product2);
        Product[] expected = new Product[]{product1};
        Product[] actual = manager.searchBy("Product");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByNonExistingText() {
        Product product = new Product(1, "Product1", 100);
        manager.add(product);

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("NonExisting");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldAddToEndOfNonEmptyArray() {

        Product[] array = new Product[]{new Product(1, "Product1", 100)};
        Product newProduct = new Product(2, "Product2", 200);

        Product[] expected = new Product[]{array[0], newProduct};
        Product[] actual = manager.addToEnd(array, newProduct);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldAddToEndOfEmptyArray() {
        Product[] array = new Product[]{};
        Product newProduct = new Product(1, "Product1", 100);
        Product[] expected = new Product[]{newProduct};
        Product[] actual = manager.addToEnd(array, newProduct);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldMatchByName() {
        Product product = new Product(1, "Product1", 100);
        Assertions.assertTrue(manager.matches(product, "Product"));
    }

    @Test
    void shouldNotMatchByName() {
        Product product = new Product(1, "Product1", 100);
        Assertions.assertFalse(manager.matches(product, "NonExisting"));
    }
}