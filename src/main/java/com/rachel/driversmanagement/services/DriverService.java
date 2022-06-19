package com.rachel.driversmanagement.services;

import com.rachel.driversmanagement.dao.DriverRepository;
import com.rachel.driversmanagement.entities.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class DriverService {

    @Autowired
    DriverRepository drRepo;

    public Driver save(Driver driver) {
        return drRepo.save(driver);
    }

    public List<Driver> getAll() {
        List<Driver> drivers =
                StreamSupport.stream(drRepo.findAll().spliterator(), false)
                        .collect(Collectors.toList());
        return drivers;
    }

    public void delete(Driver driver) {
        drRepo.delete(driver);
    }

    public void deleteById(long id) {
        drRepo.deleteById(id);
    }

    public Driver getDriverById(long id) {
        return drRepo.findById(id);
    }

    public List<Driver> activeDriversSameMapBoundsSameTimeWindow(LocalDateTime start, LocalDateTime end) {
        return drRepo.activeDriversSameMapBoundsSameTimeWindow(start, end);
    }

}
