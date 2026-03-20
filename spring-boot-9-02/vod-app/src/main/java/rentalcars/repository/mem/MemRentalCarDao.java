package rentalcars.repository.mem;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import rentalcars.repository.RentalCarDao;
import rentalcars.model.RentalCar;
import rentalcars.model.Car;

import java.util.List;
import java.util.stream.Collectors;

@Repository("RentalCarDao")
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
    @Override
    public RentalCar save(RentalCar rc) { // Dodano implementację zapisu [cite: 1507]
        int maxId = SampleData.rentalCars.stream()
                .map(RentalCar::getId)
                .max((id1, id2) -> id1 - id2)
                .orElse(0);
        rc.setId(++maxId);
        SampleData.rentalCars.add(rc);
        return rc;
    }
}
