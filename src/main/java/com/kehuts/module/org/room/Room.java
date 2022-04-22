package com.kehuts.module.org.room;

import com.kehuts.module.org.apartment.model.Apartment;
import com.kehuts.module.users.model.Tenant;
import com.kehuts.module.utils.BaseEntity;

import javax.persistence.*;

@Table(name = "room")
@Entity
public class Room extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @Column(name = "room_number")
    private String roomNumber;

    @OneToOne
    @JoinColumn(name = "tenant_id")
    private Tenant currentTenant;

    public Tenant getCurrentTenant() {
        return currentTenant;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}