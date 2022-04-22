package com.kehuts.module.org.apartment;


import com.kehuts.module.org.apartment.model.Apartment;
import com.kehuts.module.org.apartment.model.dto.ApartmentDTO;
import com.kehuts.module.utils.PagedResults;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ApartmentService {

    @Inject
    ApartmentRepository apartmentRepository;

    public Optional<Apartment> getApartment(long apartmentId){
        return apartmentRepository.findByIdOptional(apartmentId);
    }

    @Transactional(Transactional.TxType.MANDATORY)
    public Apartment addApartment(ApartmentDTO apartmentDTO) {
        Apartment p = new Apartment();
        p.setName(apartmentDTO.name());
        p.setLatitude(apartmentDTO.latitude());
        p.setLogititude(apartmentDTO.logititude());
        p.setLocation(apartmentDTO.location());
        apartmentRepository.persist(p);
        return p;
    }

    public PagedResults getApartments(int page, int size) {
        var all = apartmentRepository.findAll();
        all.page(Page.ofSize(size));
        int total = all.pageCount();
        var data = all.page(page, size).list();

        if(page>total){
            data = all.lastPage().list();
        }else if (page<0){
            data = all.list();
        }

        return new PagedResults(page, total, size, data);
    }
}
