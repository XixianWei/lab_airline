package com.example.airline_api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column (name = "destination")
    private String destination;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "departure_date_time")
    private LocalDateTime departureDateAndTime;

    @ManyToMany
    @JoinTable
    @JsonIgnoreProperties("flights")
    private List<Passenger> passengers;

    public Flight(String destination, int capacity, LocalDateTime departureDateAndTime) {
        this.destination = destination;
        this.capacity = capacity;
        this.departureDateAndTime = departureDateAndTime;
        this.passengers = new ArrayList<>();
    }

    public Flight() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public LocalDateTime getDepartureDateAndTime() {
        return departureDateAndTime;
    }

    public void setDepartureDateAndTime(LocalDateTime departureDateAndTime) {
        this.departureDateAndTime = departureDateAndTime;
    }
}
