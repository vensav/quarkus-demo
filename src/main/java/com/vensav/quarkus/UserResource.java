package com.vensav.quarkus;

import com.vensav.quarkus.model.User;
import com.vensav.quarkus.repository.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;



@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserRepository userRepository;

    @GET
    public Response getAllUsers() {
        return Response.ok().entity(userRepository.getAllUsers()).build();
    }

    @GET
    @Path("/{userId}")
    public Response getUserById(@PathParam("userId") int userId) {
        return Response.ok().entity(userRepository.getUserById(userId)).build();
    }

    @POST
    public Response addNewUser(User user) {
        User resUser = userRepository.addNewUser(user);
        return Response.created(URI.create("/users/"+resUser.getUserId())).entity(resUser).build();
    }

    @PUT
    @Path("/{userId}")
    public Response updateUserById(@PathParam("userId") int userId, User user) {
        userRepository.updateUserById(userId, user);
        return Response.accepted().build();
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteUserById(@PathParam("userId") int userId) {
        userRepository.deleteUserById(userId);
        return Response.noContent().build();
    }

}