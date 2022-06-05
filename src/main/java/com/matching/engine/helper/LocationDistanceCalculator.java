package com.matching.engine.helper;

public class LocationDistanceCalculator {

    /**
     * Using Haversine method to calculate distance between latitude and longitude in Kilometers
    **/
    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, String unit) {

        // Radius of the earth
        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c ;
        distance = Math.pow(distance, 2);
        distance = Math.sqrt(distance);
        return distance;
    }

    public static double distance(String lat1, String lat2, String lon1,
                                  String lon2, String unit) {
        double latitude1= Double.parseDouble(lat1);
        double latitude2 = Double.parseDouble(lat2);
        double longitude1= Double.parseDouble(lon1);
        double longitude2= Double.parseDouble(lon2);
        return distance(latitude1,latitude2,longitude1,longitude2, unit.toUpperCase());
    }
}