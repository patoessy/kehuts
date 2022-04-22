package com.kehuts.module.org.apartment.model.dto;

import com.kehuts.module.org.estate.Estate;
import com.kehuts.module.org.model.Organization;
import com.kehuts.module.org.room.Room;

import java.util.List;

public record ApartmentDTO(String name,
                           String location,
                           String logititude,
                           String latitude,
                           Organization organization,
                           Estate estate,
                           List<Room> rooms) {

}
