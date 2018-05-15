package com.xo.service;

import java.util.Date;
import java.util.List;

import com.xo.exception.FlightException;
import com.xo.model.entity.City;
import com.xo.repository.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.xo.model.dto.FlightDto;
import com.xo.model.entity.Flight;
import com.xo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Service
class FlighServiceImpl implements FlightService {


    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private FlightRepository flightRepository;
    
    @Override
    public Flight addFlight(FlightDto flightDto) {
        
        City from = cityRepository.findByCode(flightDto.getFromCityCode());
        City to = cityRepository.findByCode(flightDto.getToCityCode());
        
        if(from.equals(to)){
            throw new FlightException("Two city cannot be same");            
        }
        
        List<Flight> flights = listFlights(flightDto.getFromCityCode(),flightDto.getToCityCode(), flightDto.getFlightDate());
        if(!flights.isEmpty()){
            throw new FlightException("There may be only one flight between these cities");
        }
        
        Flight flight = new Flight(from, to, flightDto.getFlightNo(), flightDto.getFlightDate());
        return flightRepository.save(flight);
    }
    
    
     @Override
     public List<Flight> listFlights(String fromCityCode, String toCityCode, Date date) {
         return flightRepository.findAll();
     }
}
