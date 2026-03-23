package rentalcars.repository.data;

import jakarta.persistence.PersistenceContext;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import rentalcars.model.Car;
import rentalcars.model.RentalCar;
import rentalcars.repository.RentalCarDao;

import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class DataRentslCarDao implements RentalCarDao {
    private final RentalCarRepository repository;
    @Override
    public List<RentalCar> findAll(){
        return repository.findAll();
    }
    @Override
    public RentalCar findById(int id) {
        return repository.findById(id).orElse(null);

    }

@Override
public List<RentalCar>findByCar(Car c){
        return repository.findAllByCar(c);
}

    @Override
    public RentalCar save(RentalCar entity) {
        return repository.save(entity);

    }


}
