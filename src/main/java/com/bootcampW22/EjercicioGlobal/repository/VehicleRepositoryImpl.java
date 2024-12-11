package com.bootcampW22.EjercicioGlobal.repository;

import com.bootcampW22.EjercicioGlobal.entity.Vehicle;
import com.bootcampW22.EjercicioGlobal.exception.NotFoundException;
import com.bootcampW22.EjercicioGlobal.exception.ResourceAlreadyExistException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Repository
public class VehicleRepositoryImpl implements IVehicleRepository {

    private List<Vehicle> listOfVehicles = new ArrayList<>();

    public VehicleRepositoryImpl() throws IOException {
        loadDataBase();
    }

    @Override
    public List<Vehicle> findAll() {
        return listOfVehicles;
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        Optional<Vehicle> vehicleExist = listOfVehicles.stream().filter(v -> v.getId().equals(vehicle.getId())).findFirst();
        if (vehicleExist.isPresent()) {
            throw new ResourceAlreadyExistException("Ya existe un vehìculo con este Id: " + vehicle.getId());
        }
        listOfVehicles.add(vehicle);
        return searchVehicleById(vehicle.getId());
    }

    @Override
    public Vehicle searchVehicleById(Long id) {
        Optional<Vehicle> vehicle = listOfVehicles.stream().filter(v -> v.getId().equals(id)).findFirst();
        if (vehicle.isPresent()) {
            return vehicle.get();
        } else {
            throw new NotFoundException("No se encontrò un vehìculo con este id:" + id);
        }
    }

    @Override
    public List<Vehicle> findByColorAndYear(String color, int year) {
        return listOfVehicles.stream()
                .filter(v -> v.getColor().equalsIgnoreCase(color) && v.getYear() == year).toList();
    }

    @Override
    public List<Vehicle> getByBrandAndYearRange(String brand, int startYear, int endYear) {
        return listOfVehicles.stream().filter(x -> x.getBrand().equalsIgnoreCase(brand) && x.getYear() >= startYear && x.getYear() <= endYear).toList();
    }

    @Override
    public List<Vehicle> findByDimensionRange(double minLength, double maxLength, double minWidth, double maxWidth) {
        return listOfVehicles.stream().filter(v -> v.getHeight() >= minLength
                && v.getHeight() <= maxLength && v.getWidth() >= minWidth && v.getWidth() <= maxWidth).toList();
    }

    @Override
    public Vehicle findById(int id) {
        return listOfVehicles.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void deleteById(int id) {
        listOfVehicles.removeIf(x -> x.getId() == id);

    }

    private void loadDataBase() throws IOException {
        File file;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Vehicle> vehicles;

        file = ResourceUtils.getFile("classpath:vehicles_100.json");
        vehicles = objectMapper.readValue(file, new TypeReference<List<Vehicle>>() {
        });

        listOfVehicles = vehicles;
    }
}
