package ru.topacademy.javaqa;

public class ProductRepository {
    private Product[] products = new Product[0];

    // Метод для добавления нового продукта в массив products
    public void add(Product product) {
        Product[] tmp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        tmp[tmp.length - 1] = product;
        products = tmp;
    }

    // Метод получения всех продуктов
    public Product[] findAll() {
        return products;
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    // Метод удаления продукта по ID
    public void removeById(int id) {
        Product productToFind = findById(id);
        if (productToFind == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }

        // Создаем новый массив для хранения продуктов без удаленного
        Product[] tmp = new Product[products.length - 1];

        // Находим индекс продукта, который нужно удалить
        int copyToIndex = 0;
        for (Product currentProduct : products) {
            if (currentProduct.getId() != id) {
                tmp[copyToIndex] = currentProduct;
                copyToIndex++;
            }
        }

        // Обновляем массив продуктов
        products = tmp;
    }

}