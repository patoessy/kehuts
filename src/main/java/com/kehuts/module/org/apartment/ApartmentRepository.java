package com.kehuts.module.org.apartment;

import com.kehuts.module.org.apartment.model.Apartment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class ApartmentRepository implements PanacheRepository<Apartment> {

}
