package rentalcars.repository.dummy;

import org.springframework.stereotype.Component;
import rentalcars.model.RentalCar;
import rentalcars.model.Car;
import rentalcars.repository.RentalCarDao;


import java.util.List;
@Component
public class DummyRentalCarDao implements RentalCarDao {
    @Override
    public List<RentalCar> findAll() {return List.of();}
    @Override
    public RentalCar findById(int id) {return null;}
    @Override
    public List<RentalCar> findByCar(Car m) {return List.of();}
}
