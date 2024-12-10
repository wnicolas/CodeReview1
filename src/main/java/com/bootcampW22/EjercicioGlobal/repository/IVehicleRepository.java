package com.bootcampW22.EjercicioGlobal.repository;

import com.bootcampW22.EjercicioGlobal.entity.Vehicle;

import java.util.List;

public interface IVehicleRepository {
    List<Vehicle> findAll();

    Vehicle addVehicle(Vehicle vehicle);

    Vehicle searchVehicleById(Long id);

    List<Vehicle> findByColorAndYear(String color, int year);
}
