package rentalcars.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import rentalcars.model.Car;
import rentalcars.model.Client;
import rentalcars.model.RentalCar;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {
List<Car> findAllByClient(Client client);
List<Car> findAllByRentalCarsContaining(RentalCar rentalCar);
}
