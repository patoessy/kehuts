package com.kehuts.module.org.apartment;

import com.kehuts.module.org.apartment.model.Apartment;
import com.kehuts.module.org.apartment.model.dto.ApartmentDTO;
import com.kehuts.module.utils.PagedResults;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("apartment")
@Transactional
public class ApartmentResource {

    @Inject
    ApartmentService apartmentService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addApartment(ApartmentDTO apartment){
        Apartment p = apartmentService.addApartment(apartment);
        return Response.status(Response.Status.CREATED).entity(p).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApartments(@QueryParam("page") @DefaultValue("0") int page,
                                  @QueryParam("size") @DefaultValue("50") int size){
        PagedResults p = apartmentService.getApartments(page, size);
        return Response.status(Response.Status.OK).entity(p).build();
    }
}
