package rentalcars.repository.mem;

import org.springframework.stereotype.Component;
import rentalcars.repository.CarDao;
import rentalcars.model.RentalCar;
import rentalcars.model.Client;
import rentalcars.model.Car;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class MemCarDao implements CarDao {
    @Override
    public List<Car> findAll() {
        return SampleData.cars;
    }

    @Override
    public Car findById(int id) {
        // Zmieniono 'm' na 'c' dla spójności (c od car)
        return SampleData.cars.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    // Zmieniono nazwę metody na findByClient oraz parametr 'd' na 'client'
    public List<Car> findByClient(Client client) {
        return SampleData.cars.stream().filter(c -> c.getClient() == client).collect(Collectors.toList());
    }

    @Override
    // Zmieniono nazwę metody na findByRentalCar oraz parametr 'c' na 'rc'
    public List<Car> findByRentalCar(RentalCar rc) {
        return SampleData.cars.stream().filter(c -> c.getRentalCars().contains(rc)).collect(Collectors.toList());
    }

    @Override
    // Zmieniono parametr 'm' na 'car'
    public Car add(Car car) {
        // Zmieniono 'm1, m2' na 'c1, c2'
        int max = SampleData.cars.stream().max((c1, c2) -> c1.getId() - c2.getId()).get().getId();
        car.setId(++max);
        SampleData.cars.add(car);
        return car;
    }
}
