package de.hoomb.stockmanagement.repository;

import de.hoomb.stockmanagement.model.Reservation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Reservation Repository to execute CRUD operations on Reservation entity.
 *
 * @author Hooman Behmanesh
 */
@Repository
@Transactional
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE product.id = :productId AND DATEDIFF('SECOND', r.pointOfTime, CURRENT_TIMESTAMP) > :expiredSeconds")
    List<Reservation> findExpiredReservations(@Param("productId") final Long productId, @Param("expiredSeconds") final int expiredSeconds);

    @Transactional
    @Modifying
    @Query("DELETE FROM Reservation r WHERE product.id = :productId AND DATEDIFF('SECOND', r.pointOfTime, CURRENT_TIMESTAMP) > :expiredSeconds")
    void removeExpiredProductReservations(@Param("productId") final Long productId, @Param("expiredSeconds") final int expiredSeconds);

    @Query("SELECT r FROM Reservation r WHERE DATEDIFF('SECOND', r.pointOfTime, CURRENT_TIMESTAMP) > :expiredSeconds")
    List<Reservation> findExpiredReservations(@Param("expiredSeconds") final int expiredSeconds);
}
