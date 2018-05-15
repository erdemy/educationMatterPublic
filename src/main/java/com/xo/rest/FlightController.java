package com.xo.rest;

import com.xo.model.dto.FlightDto;
import com.xo.model.entity.Flight;
import com.xo.service.FlightService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class FlightController {

    @Autowired
    private FlightService flightService;
            
    @PostMapping("/flight/add")
    public FlightDto addFlight(FlightDto flightDto) {
        Flight flight = flightService.addFlight(flightDto);
        return new FlightDto(flight);
    }


    @GetMapping("/flight/all")
    public List<FlightDto> listFlights(String fromCityCode, String toCityCode, Date date) {
        
        return flightService.listFlights(fromCityCode, toCityCode, date)
                .stream()
                .map(FlightDto::new)
                .collect(Collectors.toList());
    }
}
