package com.bootcampW22.EjercicioGlobal.service;

import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.entity.Vehicle;
import com.bootcampW22.EjercicioGlobal.exception.NotFoundException;
import com.bootcampW22.EjercicioGlobal.repository.IVehicleRepository;
import com.bootcampW22.EjercicioGlobal.repository.VehicleRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements IVehicleService {

    IVehicleRepository vehicleRepository;
    @Autowired
    private ObjectMapper mapper;

    public VehicleServiceImpl(VehicleRepositoryImpl vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<VehicleDto> searchAllVehicles() {
        //ObjectMapper mapper = new ObjectMapper(); Se comenta debido a que es una dependencia que se puede inyenctar
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        if (vehicleList.isEmpty()) {
            throw new NotFoundException("No se encontró ningun auto en el sistema.");
        }
        return vehicleList.stream()
                .map(v -> mapper.convertValue(v, VehicleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public VehicleDto addVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = mapper.convertValue(vehicleDto, Vehicle.class);
        return mapper.convertValue(vehicleRepository.addVehicle(vehicle), VehicleDto.class);
    }

    @Override
    public List<VehicleDto> findByColorAndYear(String color, int year) {
        List<Vehicle> listVehicle = vehicleRepository.findByColorAndYear(color, year);
        if (listVehicle.isEmpty()) {
            throw new NotFoundException("No se encontraron vehìculos con color: " + color + " y año: " + year);
        }
        return listVehicle.stream().map(v -> mapper.convertValue(v, VehicleDto.class)).toList();
    }

    @Override
    public List<VehicleDto> getByBrandAndYearRange(String brand, int startYear, int endYear) {
        List<Vehicle> listVehicle = vehicleRepository.getByBrandAndYearRange(brand, startYear, endYear);
        if (listVehicle.isEmpty()) {
            throw new NotFoundException("No se encontraron vehìculos con marca: " + brand + " y años entre: " + startYear + " y " + endYear);
        }
        return listVehicle.stream().map(v -> mapper.convertValue(v, VehicleDto.class)).toList();
    }

    @Override
    public List<VehicleDto> findByDimensionRange(double minLength, double maxLength, double minWidth, double maxWidth) {
        List<Vehicle> listVehicles = vehicleRepository.findByDimensionRange(minLength, maxLength, minWidth, maxWidth);
        if (listVehicles.isEmpty()) {
            throw new NotFoundException("No se encontraron vehìculos con las siguientes caracterìsticas:\n" +
                    "Width: " + minWidth + "-" + maxWidth + "\n" +
                    "Height: " + minLength + "-" + maxLength);
        }
        return listVehicles.stream().map(x -> mapper.convertValue(x, VehicleDto.class)).toList();
    }
}
