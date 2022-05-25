package com.niine.quarkus.service;

import com.niine.quarkus.model.entities.Vehicle;
import com.niine.quarkus.model.request.VehicleRequest;
import com.niine.quarkus.repositories.VehicleRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getVehicleByColor(String color) {
        return vehicleRepository.findByColor(color);
    }

    public List<Vehicle> getAllVehicles() {
        return StreamSupport.stream(vehicleRepository.findAll().spliterator(), false).collect(java.util.stream.Collectors.toList());
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle createVehicle(VehicleRequest vehicle) {
        return vehicleRepository.save(new Vehicle(vehicle.getColor(), vehicle.getBrand()));
    }

    public Vehicle updateVehicle(Long id, String color, String brand) {
        Optional<Vehicle> optional = vehicleRepository.findById(id);
        if (optional.isPresent()) {
            Vehicle vehicle = optional.get();
            if (vehicle.getColor().equals(color) && vehicle.getBrand().equals(brand)) {
                throw new IllegalArgumentException("Vehicle with id " + id + " no changes");
            }
            if(!color.isBlank())  vehicle.setColor(color);
            if(!brand.isBlank())  vehicle.setBrand(brand);
            return vehicleRepository.save(vehicle);
        }
        throw new IllegalArgumentException("No Vehicle with id " + id + " exists");
    }
}
