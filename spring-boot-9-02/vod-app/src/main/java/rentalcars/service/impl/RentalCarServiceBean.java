package rentalcars.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import rentalcars.model.RentalCar;
import rentalcars.model.Car;
import rentalcars.repository.RentalCarDao;
import rentalcars.repository.CarDao;
import rentalcars.service.RentalCarService;

import java.util.List;
import java.util.logging.Logger;

@Component
@Scope("prototype")
public class RentalCarServiceBean implements RentalCarService {

    private static final Logger log = Logger.getLogger(RentalCarService.class.getName());

    private RentalCarDao rentalCarDao;
    private CarDao carDao;

    public RentalCarServiceBean(RentalCarDao rentalCarDao, CarDao carDao) {
        log.info("creating rental car service bean");
        this.rentalCarDao = rentalCarDao;
        this.carDao = carDao;
    }

    @Override
    public RentalCar getRentalCarById(int id) {
        log.info("searching rental car agency by id " + id);
        return rentalCarDao.findById(id);
    }

    @Override
    public List<Car> getCarsInRentalCar(RentalCar rc) {
        log.info("searching cars available in rental car agency " + rc.getId());
        return carDao.findByRentalCar(rc);
    }

    @Override
    public List<RentalCar> getAllRentalCars() {
        log.info("searching all rental car agencies");
        return rentalCarDao.findAll();
    }

    @Override
    public List<RentalCar> getRentalCarsByCar(Car c) {
        log.info("searching rental car agencies by car " + c.getId());
        return rentalCarDao.findByCar(c);
    }

}
