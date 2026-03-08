package rentalcars.service;

import rentalcars.model.Client;
import rentalcars.model.Car;

import java.util.List;

public interface CarService {



    List<Car> getAllCars();

    List<Car> getCarsByClient(Client c);

    Car getCarById(int id);

    Car addCar(Car c);


    List<Client> getAllClients();

    Client getClientById(int id);

    Client addClient(Client c);
}
