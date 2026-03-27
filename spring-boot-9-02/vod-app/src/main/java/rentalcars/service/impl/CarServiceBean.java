package rentalcars.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import rentalcars.repository.RentalCarDao;
import rentalcars.repository.ClientDao;
import rentalcars.repository.CarDao;
import rentalcars.model.RentalCar;
import rentalcars.model.Client;
import rentalcars.model.Car;
import rentalcars.service.CarService;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class CarServiceBean implements CarService {

    private static final Logger log = Logger.getLogger(CarService.class.getName());


    private final ClientDao clientDao;
    private final RentalCarDao rentalCarDao;
    private final CarDao carDao;
    private final PlatformTransactionManager transactionManager;



    public List<Car> getAllCars() {
        log.info("searching all cars...");
        return carDao.findAll();
    }

    public List<Car> getCarsByClient(Client c) {
        log.info("searching cars by client " + c.getId());
        return carDao.findByClient(c);
    }

    public List<Car> getCarsInRentalCar(RentalCar rc) {
        log.info("searching cars available in rental car agency " + rc.getId());
        return carDao.findByRentalCar(rc);
    }

    public Car getCarById(int id) {
        log.info("searching car by id " + id);
        return carDao.findById(id);
    }

    public List<RentalCar> getAllRentalCars() {
        log.info("searching all rental car agencies");
        return rentalCarDao.findAll();
    }

    public List<RentalCar> getRentalCarsByCar(Car c) {
        log.info("searching rental car agencies by car " + c.getId());
        return rentalCarDao.findByCar(c);
    }

    public RentalCar getRentalCarById(int id) {
        log.info("searching rental car agency by id " + id);
        return rentalCarDao.findById(id);
    }

    public List<Client> getAllClients() {
        log.info("searching all clients");
        return clientDao.findAll();
    }

    public Client getClientById(int id) {
        log.info("searching client by id " + id);
        return clientDao.findById(id);
    }




   /* @Override
    public Car addCar(Car c) {
        log.info("about to add car " + c);
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionAttribute());
        try {
            c = carDao.add(c);
            if (c.getBrand().equals("Apocalypse Now")) {
                throw new RuntimeException("not yet!");
            }
            transactionManager.commit(transactionStatus);
        } catch (RuntimeException e) {
            transactionManager.rollback(transactionStatus);
            throw e;
        }
        return c;
    }
*/
   @Override
   public Car addCar(Car c) {
       log.info("--- ROZPOCZYNAM DODAWANIE AUTA ---");
       log.info("Chcesz dodac: Marka='" + c.getBrand() + "', Model='" + c.getModel() + "'");

       TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionAttribute());
       try {

           List<Car> allCarsInDb = carDao.findAll();
           log.info("Pobralem z bazy " + allCarsInDb.size() + " aut do sprawdzenia.");


           boolean carExists = false;
           for (Car existingCar : allCarsInDb) {
               if (existingCar.getBrand() != null && existingCar.getModel() != null) {
                   if (existingCar.getBrand().equalsIgnoreCase(c.getBrand()) &&
                           existingCar.getModel().equalsIgnoreCase(c.getModel())) {

                       carExists = true;
                       log.info("ZNALAZLEM DUPLIKAT! W bazie jest juz ID=" + existingCar.getId());
                       break;
                   }
               }
           }


           if (carExists) {
               throw new RuntimeException("BLOKADA: Auto " + c.getBrand() + " " + c.getModel() + " juz istnieje w systemie!");
           }


           log.info("Brak duplikatow. Zapisuje auto do bazy...");
           c = carDao.add(c);

           if ("Apocalypse Now".equals(c.getModel())) {
               throw new RuntimeException("not yet!");
           }

           transactionManager.commit(transactionStatus);
           log.info("--- AUTO DODANE SUKCESEM ---");

       } catch (RuntimeException e) {
           log.severe("WYSTAPIL BLAD, COFAM TRANSAKCJE: " + e.getMessage());
           transactionManager.rollback(transactionStatus);
           throw e;
       }

       return c;
   }
    @Override
    public Client addClient(Client c) {
        log.info("about to add client " + c);
        return clientDao.save(c);
    }
}