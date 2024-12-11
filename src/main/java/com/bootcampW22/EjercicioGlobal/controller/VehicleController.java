package com.bootcampW22.EjercicioGlobal.controller;

import com.bootcampW22.EjercicioGlobal.dto.UpdateFuelTypeDto;
import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.service.IVehicleService;
import com.bootcampW22.EjercicioGlobal.service.VehicleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    IVehicleService vehicleService;

    public VehicleController(VehicleServiceImpl vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<?> getVehicles() {
        Map<Integer, String> prueba = new HashMap<>();
        System.out.println(prueba);
        return new ResponseEntity<>(vehicleService.searchAllVehicles(), HttpStatus.OK);
    }



    @GetMapping("/color/{color}/year/{year}")
    public ResponseEntity<List<VehicleDto>> getByColorAndYear(@PathVariable String color, @PathVariable int year) {
        return new ResponseEntity<List<VehicleDto>>(vehicleService.findByColorAndYear(color, year), HttpStatus.OK);
    }

    @GetMapping("/brand/{brand}/between/{startYear}/{endYear}")
    public ResponseEntity<List<VehicleDto>> getByBrandAndYearRange(@PathVariable String brand, @PathVariable int startYear, @PathVariable int endYear) {
        return new ResponseEntity<List<VehicleDto>>(vehicleService.getByBrandAndYearRange(brand, startYear, endYear), HttpStatus.OK);
    }

    @GetMapping("/dimensions")
    public ResponseEntity<List<VehicleDto>> findByDimensionRange(
            @RequestParam String length,
            @RequestParam String width) {

        String[] len = length.split("-");
        String[] wid = width.split("-");

        return new ResponseEntity<List<VehicleDto>>(vehicleService.findByDimensionRange(Double.parseDouble(len[0]),
                Double.parseDouble(len[1]), Double.parseDouble(wid[0]), Double.parseDouble(wid[1])), HttpStatus.OK);
    }

    @GetMapping("/average_capacity/brand/{brand}")
    public ResponseEntity<String> getCapacidadPromedio(@PathVariable String brand){
        double capacidad=vehicleService.getCapacidadPromedio(brand);
        return new ResponseEntity<String>("La capacidad promedio de pasajeros es: "+capacidad,HttpStatus.OK);
    }

    @PutMapping("/{id}/update_fuel")
    public ResponseEntity<String> updateFuelType(@RequestBody UpdateFuelTypeDto updateFuelTypeDto, @PathVariable int id){
        return new ResponseEntity<String>(vehicleService.updateFuelType(updateFuelTypeDto,id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){
            return new ResponseEntity<String>(vehicleService.deleteById(id), HttpStatus.NO_CONTENT);
    }

    @PostMapping("/batch")
    public ResponseEntity<String> batchAdding(@RequestBody List<VehicleDto> vehiclesDto){
        return new ResponseEntity<String>(vehicleService.batchAdding(vehiclesDto), HttpStatus.CREATED);
    }
    @PostMapping
    public ResponseEntity<?> addVehicle(@RequestBody VehicleDto vehicleDto) {
        return new ResponseEntity<>(vehicleService.addVehicle(vehicleDto), HttpStatus.CREATED);
    }
}
