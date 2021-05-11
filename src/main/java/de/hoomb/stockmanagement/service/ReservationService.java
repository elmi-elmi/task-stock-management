package de.hoomb.stockmanagement.service;

import de.hoomb.stockmanagement.model.Reservation;
import de.hoomb.stockmanagement.repository.ProductRepository;
import de.hoomb.stockmanagement.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);

    @Resource
    private ProductRepository productRepository;

    @Resource
    private ReservationRepository reservationRepository;

    @Value("${reservation.expired.seconds}")
    private int expiredSeconds;

    @Transactional
    @Scheduled(fixedRate = 5000)
    public void removeExpired() {
        LOGGER.info("Executing removeExpired");

        final List<Reservation> expiredReservations = reservationRepository.findExpiredReservations(expiredSeconds);
        expiredReservations.stream()
                .collect(Collectors.groupingBy(Reservation::getProduct, Collectors.summarizingLong(Reservation::getAmount)))
                .forEach((product, amount) -> {
                    product.setStock(product.getStock() + amount.getSum());

                    productRepository.save(product);
                    reservationRepository.removeExpiredProductReservations(product.getId(), expiredSeconds);

                    LOGGER.info("Reverted product reserved {} of id: {} to {}", amount.getSum(), product.getId(), product.getStock());
                });
    }
}
