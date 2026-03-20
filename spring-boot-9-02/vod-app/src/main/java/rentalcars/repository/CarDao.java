package rentalcars.repository;

import rentalcars.model.RentalCar;
import rentalcars.model.Client;
import rentalcars.model.Car;

import java.util.List;

public interface CarDao {

    List<Car> findAll();

    Car findById(int id);

    List<Car> findByClient(Client c);

    List<Car> findByRentalCar(RentalCar rc);

    Car save(Car c);

}
