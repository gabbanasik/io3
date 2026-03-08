package rentalcars.repository.mem;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import rentalcars.repository.RentalCarDao;
import rentalcars.model.RentalCar;
import rentalcars.model.Car;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
public class MemRentalCarDao implements RentalCarDao {

    @Override
    public List<RentalCar> findAll() {
        return SampleData.rentalCars;
    }

    @Override
    public RentalCar findById(int id) {
        // Zmieniono 'c' na 'rc' (od rental car)
        return SampleData.rentalCars.stream().filter(rc -> rc.getId() == id).findFirst().orElse(null);
    }

    @Override
    // Zmieniono z findByMovie na findByCar oraz parametr 'm' na 'car'
    public List<RentalCar> findByCar(Car car) {
        // Zmieniono 'c' na 'rc' oraz c.getMovies() na rc.getCars()
        return SampleData.rentalCars.stream().filter(rc -> rc.getCars().contains(car)).collect(Collectors.toList());
    }
}
