package rentalcars.service;

import rentalcars.model.RentalCar;
import rentalcars.model.Car;

import java.util.List;

public interface RentalCarService {
    //api zwraca nam wszystkie wypozyczalnie
    RentalCar getRentalCarById(int id);

    List<RentalCar> getAllRentalCars();

    List<RentalCar> getRentalCarsByCar(Car c);

    List<Car> getCarsInRentalCar(RentalCar rc);

}
