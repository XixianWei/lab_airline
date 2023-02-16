package com.example.airline_api.controllers;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.services.FlightService;
import com.example.airline_api.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;


    // Display all available flights
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(){
        List<Flight> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    // Display a specific flight
    @GetMapping(value = "/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable long id){
        Flight flight = flightService.getFlightById(id);
        if (flight == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flight,HttpStatus.OK);
    }

    // Add details of a new flight
    @PostMapping
    public ResponseEntity<Flight> addNewFlight(@RequestBody Flight flight) {
        flightService.addFlight(flight);
        return new ResponseEntity<>(flight, HttpStatus.CREATED);
    }

    // Book passenger on a flight
    @PatchMapping(value = "/{id}/addPassenger")
    public ResponseEntity<Flight> addPassengerToFlight(@PathVariable long id, @RequestBody Passenger passenger){
        Flight flight = flightService.bookPassengerToFlight(id, passenger);
        if (flight.getPassengers().size() < flight.getCapacity()) {
            flight.getPassengers().add(passenger);
            passenger.getFlights().add(flight);
            return new ResponseEntity<>(flight,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(flight, HttpStatus.BAD_REQUEST);
        }

    }



    // Cancel flight
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> cancelFlight(@PathVariable long id){
        flightService.deleteFlight(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    // Get flight by destination
   @GetMapping("/destination/{destination}")
    public ResponseEntity<List<Flight>> getFlightsByDestination (@PathVariable String destination){
       List<Flight> flights = flightService.findFlightByDestination(destination);
       if (flights.isEmpty()){
           return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
       } else {
           return new ResponseEntity<>(flights, HttpStatus.OK);
       }
   }

}
