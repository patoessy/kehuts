package com.kehuts.module.org.estate;

import com.kehuts.module.org.apartment.model.Apartment;
import com.kehuts.module.org.model.Organization;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "estate")
public class Estate{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String longitude;
    private String latitude;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "estate", fetch = FetchType.LAZY)
    private Set<Apartment> apartments;
}