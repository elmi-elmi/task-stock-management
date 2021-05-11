package de.hoomb.stockmanagement.service;

import de.hoomb.stockmanagement.exception.ProductChangedException;
import de.hoomb.stockmanagement.model.Product;
import de.hoomb.stockmanagement.model.Reservation;
import de.hoomb.stockmanagement.repository.ProductRepository;
import de.hoomb.stockmanagement.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Resource
    private ProductRepository productRepository;

    @Resource
    private ReservationRepository reservationRepository;

    @Value("${reservation.expired.seconds}")
    private int expiredSeconds;

    /**
     * returns a Product associated to given Id.
     * If Product could not be found, an empty {@link Optional}
     * will be returned.
     *
     * @param id the Id of Product
     * @return an {@link Optional} containing found Product
     */
    public Optional<Product> getProduct(final Long id) {
        LOGGER.info("Getting Product Stock for Id: " + id);

        return productRepository.findById(id);
    }

    public Iterable<Product> getProducts() {
        LOGGER.info("Getting All Products");

        return productRepository.findAll();
    }

    /**
     * Increases the Product stock by given amount.
     *
     * @param id     the Id of Product
     * @param amount number of stock that must be added to current stock
     * @return an {@link Optional} containing the newly changed Product
     */
    public Optional<Product> refillProductStock(final Long id, final long amount) {
        LOGGER.info("Refilling Product Stock for Id: " + id);

        return productRepository.findById(id).map(
                product -> {
                    final long newStock = product.getStock() + amount;

                    LOGGER.info(String.format("Refill Product Stock with amount: %d, new Stock: %d", amount, newStock));

                    product.setStock(newStock);
                    productRepository.save(product);

                    return product;
                }
        );
    }

    public Optional<Product> decreaseProductStock(final Long id, final long amount) {
        return productRepository.findById(id).map(product -> {
            final long productStock = product.getStock();
            if (amount > productStock) { // Do we have enough Products to Buy?
                LOGGER.error(String.format("Exceeding the product stock of: %d", productStock));

                throw new IllegalArgumentException("Not enough Product stock for Id: " + id);
            }

            final List<Reservation> freeReservations = reservationRepository.findExpiredReservations(product.getId(), expiredSeconds);
            final long sumOfReservation = freeReservations.stream().mapToLong(Reservation::getAmount).sum();
            reservationRepository.removeExpiredProductReservations(product.getId(), expiredSeconds);

            // Substract amount from current product stock
            final long newStock = productStock + sumOfReservation - amount;

            LOGGER.info(String.format("Decreased from Product Id: %s: %d Products, new Stock: %d", product.getId(), amount, newStock));

            product.setStock(newStock);
            productRepository.save(product);

            return product;
        });
    }

    public Optional<Product> reserveProduct(final Long id, final long amount) {
        final Optional<Product> product = decreaseProductStock(id, amount);

        final Product product1 = product.orElseThrow(IllegalStateException::new);

        final Reservation reservation = new Reservation(amount, product1, new Date());

        final Set<Reservation> reservations = product1.getReservations();
        reservations.add(reservation);

        productRepository.save(product1);

        return Optional.of(product1);
    }

    /**
     * Decreases the product stock by given amount.
     * If the current stock is less than bought stocks,
     * an {@link IllegalArgumentException} will be raised.
     *
     * @param id     the Id of Product
     * @param amount number of stock that must be subtracted from current stock
     * @return an {@link Optional} containing the newly changed Product
     */
    public Optional<Product> buyProduct(final Long id, final long amount) {
        LOGGER.info("Buying Product Id: " + id);

        return decreaseProductStock(id, amount);
    }

    public Product updateProduct(final Product product) {
        LOGGER.info("Updating Product: " + product);

        productRepository.findById(product.getId()).map(savedProduct -> {
            savedProduct.setName(product.getName());
            savedProduct.setStock(product.getStock());
            savedProduct.setReservations(product.getReservations());

            try {
                return productRepository.save(savedProduct);
            } catch (ObjectOptimisticLockingFailureException e) {
                throw new ProductChangedException(savedProduct.getId());
            }
        });

        return product;
    }
}
