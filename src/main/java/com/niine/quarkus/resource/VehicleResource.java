package com.niine.quarkus.resource;

import com.niine.quarkus.exception.ValidateException;
import com.niine.quarkus.model.entities.Vehicle;
import com.niine.quarkus.model.request.VehicleRequest;
import com.niine.quarkus.model.response.CommonResponse;
import com.niine.quarkus.service.VehicleService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/vehicles")
public class VehicleResource {
    private final VehicleService vehicleService;
    private final Validator validator;

    public VehicleResource(VehicleService vehicleService , Validator validator) {
        this.vehicleService = vehicleService;
        this.validator = validator;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CommonResponse<List<Vehicle>> getAllVehicles() {
        return new CommonResponse<>("200-000", "Success", vehicleService.getAllVehicles());
    }

    @GET
    @Path("/color/{color}")
    @Produces(MediaType.APPLICATION_JSON)
    public CommonResponse<List<Vehicle>> getVehicleByColor(String color) {
        return new CommonResponse<>("200-000", "Success", vehicleService.getVehicleByColor(color));
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CommonResponse<Void> deleteVehicle(Long id) {
        vehicleService.deleteVehicle(id);
        return new CommonResponse<>("200-000", "Success");
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CommonResponse<Vehicle> createVehicle(@Valid VehicleRequest vehicle) {
        return new CommonResponse<>("200-000", "Success", vehicleService.createVehicle(vehicle));
    }

    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CommonResponse<Vehicle> updateVehicle(Long id, @QueryParam("color") String color, @QueryParam("brand") String brand) {
        return new CommonResponse<>("200-000", "Success", vehicleService.updateVehicle(id, color, brand));
    }
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CommonResponse<Vehicle> updateVehicle(Long id, VehicleRequest vehicle) {
        Set<ConstraintViolation<VehicleRequest>> violations = validator.validate(vehicle);
        if (!violations.isEmpty()) {
            String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
            throw new ValidateException(message);
        }
        return new CommonResponse<>("200-000", "Success", vehicleService.updateVehicle(id, vehicle.getColor(), vehicle.getBrand()));
    }

}
