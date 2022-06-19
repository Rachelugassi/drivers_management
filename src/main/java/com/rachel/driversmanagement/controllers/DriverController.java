package com.rachel.driversmanagement.controllers;

import com.rachel.driversmanagement.entities.Driver;
import com.rachel.driversmanagement.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    DriverService drService;

    @GetMapping("/new")
    public String displayDriver(Model model) {
        Driver driver = new Driver();
        model.addAttribute("driver", driver);
        return "/drivers/new-driver";
    }

    @PostMapping("/save")
    public String createDriver(Model model, @Valid Driver driver, Errors errors) {
        if (errors.hasErrors()) {
            return "/drivers/new-driver";
        }

        drService.save(driver);
        return "redirect:/drivers";
    }


    @GetMapping("/update")
    public String displayDriverUpdateForm(@RequestParam("id") long theId, Model model) {
        Driver theDrv = drService.getDriverById(theId);
        model.addAttribute("driver", theDrv);
        return "/drivers/new-driver";
    }


    @GetMapping("/delete")
    public String deleteDriver(@RequestParam("id") long theId, Model model) {
        Driver theDrv = drService.getDriverById(theId);
        drService.delete(theDrv);

        return "redirect:/drivers";
    }

    @GetMapping
    public String displayDrivers(Model model) {
        Iterable<Driver> drivers = drService.getAll();
        model.addAttribute("drivers", drivers);
        return "/drivers/list-drivers";
    }


    @GetMapping("/atm")
    public String displayQueryEndPoint(@RequestParam("start")
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start, @RequestParam("end")
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end, Model model) {
        System.out.println(start + " , " + end);
        List<Driver> atm = drService.activeDriversSameMapBoundsSameTimeWindow(start, end);
        model.addAttribute("atm", atm);
        return "/qep/qepdrivers";
    }
}
