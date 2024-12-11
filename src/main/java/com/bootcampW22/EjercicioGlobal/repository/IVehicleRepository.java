package com.bootcampW22.EjercicioGlobal.repository;

import com.bootcampW22.EjercicioGlobal.entity.Vehicle;

import java.util.List;

public interface IVehicleRepository {
    List<Vehicle> findAll();

    Vehicle addVehicle(Vehicle vehicle);

    Vehicle searchVehicleById(Long id);

    List<Vehicle> findByColorAndYear(String color, int year);

    List<Vehicle> getByBrandAndYearRange(String brand, int startYear, int endYear);

    List<Vehicle> findByDimensionRange(double minLength, double maxLength, double minWidth, double maxWidth);

    Vehicle findById(int id);

    void deleteById(int id);
}
