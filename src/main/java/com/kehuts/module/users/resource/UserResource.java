/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kehuts.module.users.resource;

import com.kehuts.module.users.model.dto.UserDto;
import com.kehuts.module.users.model.dto.UserInputModel;
import com.kehuts.module.users.service.UserService;
import com.kehuts.module.utils.Roles;
import com.kehuts.module.utils.ServerReponse;
import com.kehuts.module.utils.UserType;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author patrick
 */
@Path("user")
public class UserResource {

    @Inject
    UserService userService;

    @POST
    @Path("register-tenant")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response tenantRegisteration(UserInputModel inputModel) {
        if (inputModel.email() == null || inputModel.email().isEmpty()) {
            return Response.status(400).entity("All fields are mandatory").build();
        }
        userService.createUser(inputModel, UserType.TENANT);
        return Response.status(201).entity("User created successfully").build();
    }

    @POST
    @Path("register-landloard")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response landloardRegisteration(UserInputModel inputModel) {
        if (inputModel.email() == null || inputModel.email().isEmpty()) {
            return Response.status(400).entity("All fields are mandatory").build();
        }
        userService.createUser(inputModel, UserType.LANDLORD);
        return Response.status(201).entity("User created successfully").build();
    }

    @POST
    @Path("register-admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({Roles.ADMIN_ROLE, Roles.SUPER_ADMIN_ROLE})
    public Response adminRegistration(UserInputModel inputModel) {
        if (inputModel.email() == null || inputModel.email().isEmpty()) {
            return Response.status(400).entity("All fields are mandatory").build();
        }
        userService.createUser(inputModel, UserType.ADMIN);
        return Response.status(201).entity("User created successfully").build();
    }

    @POST
    @Path("register-super-admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({Roles.ADMIN_ROLE, Roles.SUPER_ADMIN_ROLE})
    public Response superAdminRegistration(UserInputModel inputModel) {
        if (inputModel.email() == null || inputModel.email().isEmpty()) {
            return Response.status(400).entity("All fields are mandatory").build();
        }
        userService.createUser(inputModel, UserType.SUPER_ADMIN);
        return Response.status(201).entity("User created successfully").build();
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response login(UserInputModel inputModel) {
        if (inputModel.email() == null || inputModel.email().isEmpty()) {
            var response = new ServerReponse<>(
                    null,
                    false,
                    "All fields are required",
                    400
            );
            return Response.status(400).entity(response).build();
        }
        UserDto userDto = userService.authoriseUser(inputModel);
        if(userDto==null){
            var response = new ServerReponse<>(
                    null,
                    false,
                    "Username or password is invalid",
                    400
            );
            return Response.status(200).entity(response).build();
        }

        return Response.status(200).entity(userDto).build();
    }
}
