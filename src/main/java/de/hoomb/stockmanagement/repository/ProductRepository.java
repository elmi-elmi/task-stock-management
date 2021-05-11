package de.hoomb.stockmanagement.repository;

import de.hoomb.stockmanagement.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Product Repository to execute CRUD operations on Product entity.
 *
 * @author Hooman Behmanesh
 */
@Repository
@Transactional
public interface ProductRepository extends CrudRepository<Product, Long> {
}
