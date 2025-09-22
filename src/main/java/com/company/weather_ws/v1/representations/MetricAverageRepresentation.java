package com.company.weather_ws.v1.representations;

import com.company.weather_ws.v1.models.MetricType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Metric Average Representation as result from the API.
 * @author Julio Dias
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MetricAverageRepresentation {

    @JsonProperty("metric")
    private MetricType metricType;

    @JsonProperty("average")
    private double average;
}
