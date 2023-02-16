package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    public FlightService(){
    }

    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }

    public Flight getFlightById(long id){
        return flightRepository.findById(id).get();
    }

    public Flight addFlight(Flight flight){
        return flightRepository.save(flight);
    }

    public void deleteFlight(long id){
        flightRepository.deleteById(id);
    }

    public Flight bookPassengerToFlight(Long id, Passenger passenger) {
        Flight flight = getFlightById(id);
        if (passenger.getFlights() == null) {
            passenger.setFlights(new ArrayList<>());
        } else{
            flight.getPassengers().add(passenger);
            passenger.getFlights().add(flight);
        }
        return addFlight(flight);
    }


    public List<Flight> findFlightByDestination (String destination){
        List<Flight> flights = flightRepository.findByDestination(destination);
        return flights;
    }



}
