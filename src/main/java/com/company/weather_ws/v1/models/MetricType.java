package com.company.weather_ws.v1.models;

/**
 * Metric Type to represent weather parameters.
 * @author Julio Dias
 */
public enum MetricType {
        TEMPERATURE("temperature"),
        FEELS_LIKE("feels_like"),
        HUMIDITY("humidity_percent"),
        WIND("wind"),
        SPEED("speed"),
        QUALITY("quality"),
        VISIBILITY("visibility"),
        PRESSURE("pressure");

        private final String value;

        MetricType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
}
