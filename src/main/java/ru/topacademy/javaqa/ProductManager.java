package ru.topacademy.javaqa;

public class ProductManager {
    private ProductRepository repository;

    // Конструктор класса, принимающий репозиторий в качестве параметра
    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    // Метод для добавления продукта в репозиторий
    public void add(Product product) {
        repository.add(product);
    }

    // Метод для поиска продуктов по тексту
    public Product[] searchBy(String text) {
        Product[] allProducts = repository.findAll();
        Product[] result = new Product[0]; // временный массив для хранения результатов поиска
        for (Product product : allProducts) {
            if (matches(product, text)) {
                // "добавляем в конец" массива result продукт product
                result = addToEnd(result, product);
            }
        }
        return result;
    }

    // Вспомогательный метод для добавления продукта в конец массива
    public Product[] addToEnd(Product[] array, Product newProduct) {
        int length = array.length;
        Product[] newArray = new Product[length + 1];
        for (int i = 0; i < length; i++) {
            newArray[i] = array[i];
        }
        newArray[length] = newProduct;
        return newArray;
    }


    public boolean matches(Product product, String search) {
        return product.getName().contains(search);
    }
}
