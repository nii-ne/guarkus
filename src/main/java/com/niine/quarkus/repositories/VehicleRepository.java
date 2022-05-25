package com.niine.quarkus.repositories;

import com.niine.quarkus.model.entities.Vehicle;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    List<Vehicle> findByColor(String color);
}
