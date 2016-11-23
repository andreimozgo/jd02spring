package by.academy.it.services;

import by.academy.it.entity.Flight;

import java.util.List;

public interface FlightService extends Service<Flight> {
    List<Flight> getAll();

    List<Flight> getAll(int recordsPerPage, int currentPage);

    int getNumberOfPages(int recordsPerPage);
}
