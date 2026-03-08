package rentalcars.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rentalcars.model.RentalCar;
import rentalcars.service.RentalCarService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RentalCarRest { // Zmieniono z CinemaRest

    private final RentalCarService rentalCarService;

    @GetMapping("/rentalcars") // Zmieniono endpoint z /cinemas
    List<RentalCar> getRentalCars() { // Zmieniono nazwę metody
        log.info("about to retrieve rental cars list");

        // Zmieniono wywołanie na nową metodę z RentalCarService
        List<RentalCar> rentalCars = rentalCarService.getAllRentalCars();

        log.info("{} rental car agencies found.", rentalCars.size());
        return rentalCars;
    }
}