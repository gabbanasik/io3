package rentalcars.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import rentalcars.model.Car;

public interface CarRepository extends JpaRepository<Car,Integer> {

}
