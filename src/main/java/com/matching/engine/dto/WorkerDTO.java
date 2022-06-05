package com.matching.engine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkerDTO {

    private int rating;

    private Boolean isActive;
    private List<String> certificates;

    private List skills;
    private com.matching.engine.dto.JobSearchAddressDTO jobSearchAddress;
    private String transportation;
    private boolean hasDriversLicense;

    //TODO Add constraint checks. Index should be from 1 to 7
    private Set<com.matching.engine.dto.AvailabilityDTO> availability;

    private String phone;
    private String email;
    private NameDTO nameDTO;

    private int age;
    private String guid;
    private String userId;

    public WorkerDTO(){}

    public WorkerDTO(String userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List getCertificates() {
        return certificates;
    }

    public void setCertificates(List certificates) {
        this.certificates = certificates;
    }

    public List getSkills() {
        return skills;
    }

    public void setSkills(List skills) {
        this.skills = skills;
    }

    public com.matching.engine.dto.JobSearchAddressDTO getJobSearchAddress() {
        return jobSearchAddress;
    }

    public void setJobSearchAddress(com.matching.engine.dto.JobSearchAddressDTO jobSearchAddress) {
        this.jobSearchAddress = jobSearchAddress;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public boolean isHasDriversLicense() {
        return hasDriversLicense;
    }

    public void setHasDriversLicense(boolean hasDriversLicense) {
        this.hasDriversLicense = hasDriversLicense;
    }

    public Set<com.matching.engine.dto.AvailabilityDTO> getAvailability() {
        return availability;
    }

    public void setAvailability(Set<com.matching.engine.dto.AvailabilityDTO> availability) {
        this.availability = availability;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NameDTO getName() {
        return nameDTO;
    }

    public void setName(NameDTO nameDTO) {
        this.nameDTO = nameDTO;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "rating=" + rating +
                ", isActive=" + isActive +
                ", certificates=" + certificates +
                ", skills=" + skills +
                ", jobSearchAddress=" + jobSearchAddress +
                ", transportation='" + transportation + '\'' +
                ", hasDriversLicense=" + hasDriversLicense +
                ", availability=" + availability +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", name=" + nameDTO +
                ", age=" + age +
                ", guid='" + guid + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkerDTO workerDTO = (WorkerDTO) o;

        return userId.equals(workerDTO.userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}
