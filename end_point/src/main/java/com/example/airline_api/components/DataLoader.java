package com.example.airline_api.components;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    public DataLoader() {
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //Flights
        Flight BA0105 = new Flight("Dubai", 350, LocalDateTime.of(2023, 2, 12, 20, 40));
        flightRepository.save(BA0105);

        Flight EZY2163 = new Flight("Amsterdam", 180, LocalDateTime.of(2023,2,21,20,00));
        flightRepository.save(EZY2163);

        Flight FR9814 = new Flight("Barcelona", 150, LocalDateTime.of(2023,3,12,19,20));
        flightRepository.save(FR9814);

        Flight BA0660 = new Flight("Larnaca", 180, LocalDateTime.of(2023,3,7,16,20));
        flightRepository.save(BA0660);

        Flight FR205 = new Flight("Dublin", 150, LocalDateTime.of(2023,3,9,6,10));
        flightRepository.save(FR205);

        Flight WI834 = new Flight("Hurghada", 300, LocalDateTime.of(2023,4,1,10,45));
        flightRepository.save(WI834);

        Flight EZY8415 = new Flight("Lyon", 180, LocalDateTime.of(2023,4,15,8,45));
        flightRepository.save(EZY8415);


        //Passengers
        Passenger tom = new Passenger("Tom Cruise", "tom@gmail.com");
        passengerRepository.save(tom);

        Passenger scarlett = new Passenger("Scarlett Johansson", "scar@gmail.com");
        passengerRepository.save(scarlett);

    }
}
