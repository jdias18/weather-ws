package com.company.weather_ws.v1.dao;

import com.company.weather_ws.v1.models.SensorReadings;
import com.company.weather_ws.v1.models.MetricType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

/**
 * Sensor DAO Layer Implementation.
 * @author Julio Dias
 */
@Repository
public class SensorServiceDaoImpl implements SensorServiceDao{

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Get the Average by specific metric and star/end dates.
     * Important: The AVG function is being used from the database due precise calculation.
     * in order to avoid p
     * @param metricType
     * @param startDate
     * @param endDate
     * @return
     */
    public Double getAvgByMetricAndDateRange(MetricType metricType, LocalDateTime startDate, LocalDateTime endDate) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> query = cb.createQuery(Double.class);
        Root<SensorReadings> root = query.from(SensorReadings.class);

        Predicate datePredicate = cb.between(root.get("readDate"), startDate, endDate);
        query.select(cb.avg(root.get(metricType.getValue()))).where(datePredicate);

        return entityManager.createQuery(query).getSingleResult();
    }

    /**
     * Get the Average by specific metric, sensorId and star/end dates.
     * Important: The AVG function is being used from the database due precise calculation.
     * @param metricType
     * @param sensorId
     * @param startDate
     * @param endDate
     * @return
     */
    public Double getAvgByMetricSensorIdAndDateRange(MetricType metricType, String sensorId, LocalDateTime startDate, LocalDateTime endDate) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> query = cb.createQuery(Double.class);
        Root<SensorReadings> root = query.from(SensorReadings.class);

        Predicate sensorIdPredicate = cb.equal(root.get("sensorId"), sensorId);
        Predicate datePredicate = cb.between(root.get("readDate"), startDate, endDate);

        query.select(cb.avg(root.get(metricType.getValue()))).where(sensorIdPredicate, datePredicate);

        return entityManager.createQuery(query).getSingleResult();
    }
}