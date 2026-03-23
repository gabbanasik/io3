package rentalcars.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import rentalcars.model.Car;
import rentalcars.model.RentalCar;
import rentalcars.repository.RentalCarDao;

import java.util.List;

@Repository
public class JpaRentalCarDao implements RentalCarDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<RentalCar>findAll() {
        return entityManager
                .createQuery("select r from RentalCar r")
                .getResultList();
    }
    @Override
    public RentalCar findById(int id) {
        return entityManager.find(RentalCar.class, id);

    }
    @Override
    public List<RentalCar> findByCar(Car c){
        return entityManager
                .createQuery("select r from RentalCar r inner join r.cars car where car=:car ")
                .setParameter("car",c)
                .getResultList();
    }
    @Override
    public RentalCar save(RentalCar rentalCar) {
        entityManager.persist(rentalCar);
        return rentalCar;
    }
}
