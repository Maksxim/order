package by.tms.order.repositories;

import by.tms.order.entities.Order;
import by.tms.order.entities.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private static Logger log = LogManager.getLogger(ProductRepository.class);
    private List<Product> products = new ArrayList<>();

    public Product create(Product product){
        log.info("ProductRepository: Method create was called");
        products.add(product);
        return product;
    }

    public Product getById(int productId) {
        log.info("ProductRepository: Method getById was called with productId={}", productId);
        Product product = null;
        for (int i = 0; i < products.size();i++) {
            if (productId == products.get(i).getId()) {
                product = products.get(i);
            }
        }
        return product;
    }

    public void update(Product product){
        log.info("ProductRepository: update was called for productId={}", product.getId());
        Product existingProduct = getById(product.getId());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
    }

    public void delete(int productId){
        log.info("ProductRepository: Method delete was called");
        Product product = getById(productId);
        products.remove(product);
    }
}
