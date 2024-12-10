package com.bootcampW22.EjercicioGlobal.service;

import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;

import java.util.List;

public interface IVehicleService {
    List<VehicleDto> searchAllVehicles();

    VehicleDto addVehicle(VehicleDto vehicleDto);

    List<VehicleDto> findByColorAndYear(String color, int year);

    List<VehicleDto> getByBrandAndYearRange(String brand, int startYear, int endYear);

    List<VehicleDto> findByDimensionRange(double minLength, double maxLength, double minWidth, double maxWidth);

    double getCapacidadPromedio(String brand);
}
