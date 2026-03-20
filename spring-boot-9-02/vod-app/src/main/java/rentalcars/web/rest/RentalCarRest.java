package rentalcars.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import rentalcars.model.Car;
import rentalcars.model.RentalCar;
import rentalcars.service.CarService;
import rentalcars.service.RentalCarService;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class RentalCarRest {

    private final RentalCarService rentalCarService;
    private final CarService carService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    @GetMapping("/rentalcars")
    public List<RentalCar> getRentalCars(
            @RequestParam(value = "phrase", required = false) String phrase,
            @RequestHeader(value = "custom-header", required = false) String customHeader,
            @CookieValue(value = "some-cookie", required = false) String someCookie
    ) {
        log.info("about to retrieve rental cars list");
        log.info("phrase param: {}", phrase);
        log.info("custom-header param: {}", customHeader);
        log.info("some-cookie param: {}", someCookie);

        if (phrase != null && phrase.equals("foo")) {
            throw new IllegalArgumentException("Foo!");
        }

        List<RentalCar> rentalCars = rentalCarService.getAllRentalCars();
        log.info("{} rental cars found", rentalCars.size());
        return rentalCars;
    }

    @GetMapping("/rentalcars/{id}")
    public ResponseEntity<RentalCar> getRentalCar(@PathVariable("id") int id) {
        log.info("about to retrieve rental car with id {}", id);
        RentalCar rentalCar = rentalCarService.getRentalCarById(id);
        log.info("rental car found: {}", rentalCar);

        if (rentalCar != null) {
            return ResponseEntity.ok(rentalCar);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/models/{carId}/rentalcars")
    public ResponseEntity<List<RentalCar>> getRentalCarsSellingModel(@PathVariable("carId") int carId) {
        log.info("about to retrieve rental cars having model {}", carId);
        Car car = carService.getCarById(carId);

        if (car == null) {
            return ResponseEntity.notFound().build();
        } else {
            List<RentalCar> rentalCars = rentalCarService.getRentalCarsByCar(car);
            log.info("there are {} rental cars having model {}", rentalCars.size(), car.getBrand());
            return ResponseEntity.ok(rentalCars);
        }
    }

    @PostMapping("/rentalcars")
    public ResponseEntity<?> addRentalCar(@Validated @RequestBody RentalCar rentalCar, Errors errors, HttpServletRequest request) {
        log.info("about to add new rental car {}", rentalCar);

        if (errors.hasErrors()) {
            Locale locale = localeResolver.resolveLocale(request);
            String errorMessage = errors.getAllErrors().stream()
                    .map(oe -> messageSource.getMessage(oe.getCode(), new Object[0], locale))
                    .reduce("errors:\n", (accu, oe) -> accu + oe + "\n");

            return ResponseEntity.badRequest().body(errorMessage);
        }

        RentalCar savedRentalCar = rentalCarService.addRentalCar(rentalCar);
        log.info("new rental car added {}", savedRentalCar);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRentalCar);
    }
}