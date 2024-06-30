package ru.topacademy.javaqa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class ProductRepositoryTest {
    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ProductRepository();
    }

    @Test
    void shouldAddProduct() {
        Product product = new Product(1, "Product1", 100);
        repository.add(product);
        Product[] expected = new Product[]{product};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindAllProducts() {
        Product product1 = new Product(1, "Product1", 100);
        Product product2 = new Product(2, "Product2", 200);
        repository.add(product1);
        repository.add(product2);
        Product[] expected = new Product[]{product1, product2};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveById() {
        Product product1 = new Product(1, "Product1", 100);
        Product product2 = new Product(2, "Product2", 200);
        repository.add(product1);
        repository.add(product2);
        repository.removeById(1);
        Product[] expected = new Product[]{product2};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldReturnCorrectAuthor() {
        Book book = new Book(2, "Book1", 150, "Author1");
        String author = book.getAuthor();

        Assertions.assertEquals("Author1", author);
    }

    @Test
    public void shouldReturnCorrectManufacturer() {
        Smartphone smartphone = new Smartphone(1,"Smartphone1",300,"Manufacturer1");
        String manufacturer = smartphone.getManufacturer();

        Assertions.assertEquals("Manufacturer1", manufacturer);
    }

    @Test
    public void shouldReturnCorrectPrice() {
        Product product = new Product(1, "Product1", 100);
        repository.add(product);

        int expected = 100;
        int actual = product.getPrice();

        Assertions.assertEquals(expected, actual, "Цена продукта соответствует");
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenIdNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.removeById(10); //предполагаем,что элемента с ID 10 нет в репозитории
        });
    }

    @Test
    public void shouldRemoveProductSuccessfully() {
        //убеждаемся в том,что после удаления продукта с ID 1 из репозитория,репозиторий пуст.
        Product product = new Product(1, "Product1", 100);
        repository.add(product);
        repository.removeById(1);

        Product[] expected = new Product[]{};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

}
