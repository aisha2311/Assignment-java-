package com.countryLocator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CountryLocator {
	
	
    private static final Map<String, String> countryBoundaries = new HashMap<>();

    static {
        try (BufferedReader reader = new BufferedReader(new FileReader("country_boundaries.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String countryCode = parts[0];
                String boundary = parts[1];
                countryBoundaries.put(boundary, countryCode);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCountryCode(double latitude, double longitude) {
        for (Map.Entry<String, String> entry : countryBoundaries.entrySet()) {
            String boundary = entry.getKey();
            String[] points = boundary.split(" ");
            boolean inside = false;
            for (int i = 0, j = points.length - 1; i < points.length; j = i++) {
                double xi = Double.parseDouble(points[i].split(",")[0]);
                double yi = Double.parseDouble(points[i].split(",")[1]);
                double xj = Double.parseDouble(points[j].split(",")[0]);
                double yj = Double.parseDouble(points[j].split(",")[1]);
                if ((yi > latitude) != (yj > latitude) && (longitude < (xj - xi) * (latitude - yi) / (yj - yi) + xi)) {
                    inside = !inside;
                }
            }
            if (inside) {
                return entry.getValue();
            }
        }
        return null;
    }
}
