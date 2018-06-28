/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alientware.usermanagement;

import com.alientware.usermanagement.dto.UserDto;
import com.alientware.usermanagement.managers.UserManager;
import static com.alientware.usermanagement.util.ResponseBuilder.buildGetSuccess;
import static com.alientware.usermanagement.util.ResponseBuilder.buildSimpleSuccess;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Rahul.Avaghan
 */
@Path("user")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class UserService {

    @Inject
    UserManager userManager;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getUsers() {
        return buildGetSuccess(userManager.getAllUsers());

    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getUserById(@PathParam("id") Long id) {
        return buildGetSuccess(userManager.findById(id));

    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional
    public Response creatUser(UserDto user) {
        return buildGetSuccess(userManager.createUser(user));
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional
    public Response updateUser(UserDto user) {
        return buildGetSuccess(userManager.updateUser(user));

    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional
    public Response deleteUser(@PathParam("id") Long id) {
        userManager.deleteUser(id);
        return buildSimpleSuccess();

    }

}
