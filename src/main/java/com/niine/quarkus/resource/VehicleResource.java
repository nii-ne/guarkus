package com.niine.quarkus.resource;

import com.niine.quarkus.model.entities.Vehicle;
import com.niine.quarkus.model.request.VehicleRequest;
import com.niine.quarkus.service.VehicleService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/vehicles")
public class VehicleResource {
    private final VehicleService vehicleService;

    public VehicleResource(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }
    @GET
    @Path("/color/{color}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vehicle> getVehicleByColor(String color) {
        return vehicleService.getVehicleByColor(color);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteVehicle(Long id) {
        vehicleService.deleteVehicle(id);
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Vehicle createVehicle(VehicleRequest vehicle) {
        return vehicleService.createVehicle(vehicle);
    }
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Vehicle updateVehicle(Long id, @QueryParam("color") String color, @QueryParam("brand") String brand) {
        return vehicleService.updateVehicle(id, color, brand);
    }

}
