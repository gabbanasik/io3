package rentalcars.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import rentalcars.model.Car;
import rentalcars.model.Client;
import rentalcars.model.RentalCar;
import rentalcars.repository.CarDao;

import java.util.List;

@Repository
//@Primary
public class JpaCarDao implements CarDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> findAll() {
        return entityManager.createQuery
                ("SELECT c from Car c").getResultList();
    }

    @Override
    public Car findById(int id) {
        return entityManager.find(Car.class, id);

    }

    @Override
    public List<Car> findByClient(Client c) {
        return entityManager
                .createQuery("select c from Car c where c.client=:client ")
                .setParameter("client", c)
                .getResultList();

    }
    @Override
    public List<Car>findByRentalCar(RentalCar rentalCar) {
         return entityManager
                 .createQuery("select c from Car c inner join c.rentalCars rentalcar where rentalcar=:rentalcar")
                .setParameter("rentalcar", rentalCar)
                .getResultList();
}
@Override
    public Car add(Car car) {
        entityManager.persist(car);
        return car;
}

}
