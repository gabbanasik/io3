package rentalcars.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import rentalcars.model.Car;
import rentalcars.model.Client;
import rentalcars.model.RentalCar;
import rentalcars.repository.CarDao;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class DataCarDao implements CarDao {
    private final CarRepository carRepository;
    @Override
   public List<Car> findAll(){
        return carRepository.findAll();
   }
    @Override
    public Car findById(int id){
    return carRepository.findById(id).orElse(null);
    }
    @Override
    public List<Car> findByClient(Client c){
   return carRepository.findAllByClient(c);
    }
    @Override
    public List<Car> findByRentalCar(RentalCar rc) {
    return carRepository.findAllByRentalCarsContaining(rc);
    }
    @Override
    public Car add(Car c) {
    return carRepository.save(c);
    }
}
