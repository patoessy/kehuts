package com.kehuts.module.org.apartment.model;

import com.kehuts.module.org.estate.Estate;
import com.kehuts.module.org.model.Organization;
import com.kehuts.module.org.room.Room;
import com.kehuts.module.users.model.User;
import com.kehuts.module.utils.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Apartment extends BaseEntity {

    private String name;

    private String location;
    private String logititude;
    private String latitude;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "apartment")
    private List<Room> rooms;

    @ManyToOne
    @JoinColumn(name = "estate_id")
    private Estate estate;

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
    }

    public Estate getEstate() {
        return estate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLogititude() {
        return logititude;
    }

    public void setLogititude(String logititude) {
        this.logititude = logititude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
