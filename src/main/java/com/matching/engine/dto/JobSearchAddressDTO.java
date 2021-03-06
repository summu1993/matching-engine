package com.matching.engine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobSearchAddressDTO {

    private String unit;
    private int maxJobDistance;
    private String longitude;
    private String latitude;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getMaxJobDistance() {
        return maxJobDistance;
    }

    public void setMaxJobDistance(int maxJobDistance) {
        this.maxJobDistance = maxJobDistance;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "JobSearchAddress{" +
                "unit='" + unit + '\'' +
                ", maxJobDistance=" + maxJobDistance +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
