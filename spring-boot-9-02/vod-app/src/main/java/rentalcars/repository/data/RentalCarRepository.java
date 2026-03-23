package rentalcars.repository.data;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rentalcars.model.Car;
import rentalcars.model.RentalCar;
import rentalcars.repository.RentalCarDao;

import java.util.List;

public interface RentalCarRepository extends JpaRepository<RentalCar,Integer> {
    List<RentalCar> findAllByNameContaining(String name);
    @Query("select rc from RentalCar rc inner join rc.cars car where car=:car")
    List<RentalCar> findAllByCar(@Param("car") Car car);

}
