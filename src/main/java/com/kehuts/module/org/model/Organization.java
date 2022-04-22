package com.kehuts.module.org.model;

import com.kehuts.module.utils.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@Entity
public class Organization extends BaseEntity {

    private String name;
    private String address;
    private Integer paybill;
    private String town;
    private String country;
    private String timezone;
    private double latitude;
    private double logititude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPaybill() {
        return paybill;
    }

    public void setPaybill(Integer paybill) {
        this.paybill = paybill;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLogititude() {
        return logititude;
    }

    public void setLogititude(double logititude) {
        this.logititude = logititude;
    }
}