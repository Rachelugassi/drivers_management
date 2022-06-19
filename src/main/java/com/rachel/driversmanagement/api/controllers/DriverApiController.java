package com.rachel.driversmanagement.api.controllers;


import com.rachel.driversmanagement.entities.Driver;
import com.rachel.driversmanagement.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/app-api/drivers")
public class DriverApiController {

    @Autowired
    DriverService driverService;

    @GetMapping
    public List<Driver> getDrivers() {
        return driverService.getAll();
    }

    @GetMapping("/{id}")
    public Driver getDriverById(@PathVariable("id") Long id) {
        return driverService.getDriverById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Driver create(@RequestBody @Valid Driver driver) {
        return driverService.save(driver);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Driver Update(@PathVariable("id") long id, @RequestBody @Valid Driver driver) {
        Driver dr = driverService.getDriverById(id);

        if (driver.getAddress() != null) {
            dr.setAddress(driver.getAddress());
        }
        if (driver.getAge() != 0) {
            dr.setAge(driver.getAge());
        }
        if (driver.getName() != null) {
            dr.setName(driver.getName());
        }
        if (driver.getStatus() != null) {
            dr.setStatus(driver.getStatus());
        }
        if (driver.getStartTime() != null) {
            dr.setStartTime(driver.getStartTime());
        }
        if (driver.getEndTime() != null) {
            dr.setEndTime(driver.getEndTime());
        }
        if (driver.getCurrentLocation() != null) {
            dr.setCurrentLocation(driver.getCurrentLocation());
        }

        return driverService.save(dr);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        try {
            driverService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

        }
    }
}
