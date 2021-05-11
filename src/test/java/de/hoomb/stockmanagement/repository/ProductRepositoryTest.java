package de.hoomb.stockmanagement.repository;

import de.hoomb.stockmanagement.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryTest.class);

    @Resource
    private TestEntityManager entityManager;

    @Resource
    private ProductRepository productRepository;

    @Test
    public void testCreateProduct() {
        final Product product = new Product("Test Product", 125, new HashSet<>(), 0);

        this.entityManager.persist(product);

        LOGGER.info("Saved Product: " + product);

        final Optional<Product> savedProd = productRepository.findById(1L);

        assertThat(savedProd).isPresent();
        assertThat(savedProd.get().getStock()).isEqualTo(125);
        assertThat(savedProd.get().getName()).isEqualTo("Test Product");
    }

    @Test
    public void testUpdateProduct() {
        final Product product = new Product("Test Product", 125, new HashSet<>(), 0);

        this.entityManager.persist(product);

        LOGGER.info("Saved Product: " + product);

        final Optional<Product> savedProd1 = productRepository.findById(1L);

        assertThat(savedProd1).isPresent();
        assertThat(savedProd1.get().getStock()).isEqualTo(125);
        assertThat(savedProd1.get().getName()).isEqualTo("Test Product");

        LOGGER.info("Change Product stock to 360");
        product.setStock(360);

        final Optional<Product> savedProd2 = productRepository.findById(1L);

        assertThat(savedProd2).isPresent();
        assertThat(savedProd2.get().getStock()).isEqualTo(360);
    }
}
