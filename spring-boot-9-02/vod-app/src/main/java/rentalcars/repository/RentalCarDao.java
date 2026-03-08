package rentalcars.repository;

import rentalcars.model.RentalCar;
import rentalcars.model.Car;

import java.util.List;

public interface RentalCarDao {

    List<RentalCar> findAll();

    RentalCar findById(int id);

    List<RentalCar> findByCar(Car m);

}
