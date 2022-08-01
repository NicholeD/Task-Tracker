package com.kenzie.hotel.activity;

import com.amazonaws.services.cloudwatch.model.StandardUnit;
import com.kenzie.hotel.dao.ReservationDao;
import com.kenzie.hotel.dao.models.Reservation;
import com.kenzie.hotel.metrics.MetricsPublisher;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * Handles requests to cancel a reservation.
 */
public class CancelReservationActivity {

    private ReservationDao reservationDao;
    private MetricsPublisher metricsPublisher;

    /**
     * Constructs a CancelReservationActivity.
     * @param reservationDao Dao used to update reservations.
     * @param metricsPublisher MetricsPublisher used to publish metrics.
     */
    @Inject
    public CancelReservationActivity(ReservationDao reservationDao, MetricsPublisher metricsPublisher) {
        this.reservationDao = reservationDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Cancels the given reservation.
     * @param reservationId of the reservation to cancel.
     * @return canceled reservation
     */
    public Reservation handleRequest(final String reservationId) {
        Reservation response = new Reservation();
        Reservation reservation = reservationDao.getReservation(reservationId);
        try {
            response = reservationDao.cancelReservation(reservationId);
            metricsPublisher.addMetric("CanceledReservationCount", 1, StandardUnit.Count);
        } catch (Exception e) {
            System.out.println("Error canceling reservation " + reservationId);
        }

        metricsPublisher.addMetric("ReservationRevenue", -(reservation.getTotalCost().doubleValue()), StandardUnit.None);

        return response;
    }
}
