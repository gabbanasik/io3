package rentalcars.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rentalcars.repository.RentalCarDao;
import rentalcars.repository.ClientDao;
import rentalcars.repository.CarDao;
import rentalcars.model.RentalCar;
import rentalcars.model.Client;
import rentalcars.model.Car;
import rentalcars.service.CarService;

import java.util.List;
import java.util.logging.Logger;

@Component
public class CarServiceBean implements CarService {

    private static final Logger log = Logger.getLogger(CarService.class.getName());



    private ClientDao clientDao;
    private RentalCarDao rentalCarDao;
    private CarDao carDao;

    public CarServiceBean(ClientDao clientDao, RentalCarDao rentalCarDao, CarDao carDao) {
        this.clientDao = clientDao;
        this.rentalCarDao = rentalCarDao;
        this.carDao = carDao;
    }
    @Autowired
    public void setDirectorDao(ClientDao clientDao) {
        this.clientDao =clientDao;
    }
    public List<Car> getAllCars() { // Zmieniono z getAllMovies
        log.info("searching all cars...");
        return carDao.findAll();
    }

    public List<Car> getCarsByClient(Client c) { // Zmieniono z getMoviesByDirector
        log.info("searching cars by client " + c.getId());
        return carDao.findByClient(c); // Zaktualizowana metoda z CarDao
    }

    public List<Car> getCarsInRentalCar(RentalCar rc) { // Zmieniono z getMoviesInCinema
        log.info("searching cars available in rental car agency " + rc.getId());
        return carDao.findByRentalCar(rc); // Zaktualizowana metoda z CarDao
    }

    public Car getCarById(int id) { // Zmieniono z getMovieById
        log.info("searching car by id " + id);
        return carDao.findById(id);
    }

    public List<RentalCar> getAllRentalCars() { // Zmieniono z getAllCinemas
        log.info("searching all rental car agencies");
        return rentalCarDao.findAll();
    }

    public List<RentalCar> getRentalCarsByCar(Car c) { // Zmieniono z getCinemasByMovie
        log.info("searching rental car agencies by car " + c.getId());
        return rentalCarDao.findByCar(c);
    }

    public RentalCar getRentalCarById(int id) { // Zmieniono z getCinemaById
        log.info("searching rental car agency by id " + id);
        return rentalCarDao.findById(id);
    }

    public List<Client> getAllClients() { // Zmieniono z getAllDirectors
        log.info("searching all clients");
        return clientDao.findAll();
    }

    public Client getClientById(int id) { // Zmieniono z getDirectorById
        log.info("searching client by id " + id);
        return clientDao.findById(id);
    }

    @Override
    public Car addCar(Car c) { // Zmieniono z addMovie
        log.info("about to add car " + c);
        return carDao.add(c);
    }

    @Override
    public Client addClient(Client c) { // Zmieniono z addDirector
        log.info("about to add client " + c);
        return clientDao.add(c);
    }

}
