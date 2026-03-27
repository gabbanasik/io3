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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rentalcars.model.Car;
import rentalcars.service.CarService;
import rentalcars.service.RentalCarService;
import rentalcars.web.rest.dto.CarDto;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class CarRest {

    private final RentalCarService rentalCarService;
    private final CarService carService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final CarValidator carValidator;

    @InitBinder
    protected void initBinder(org.springframework.web.bind.WebDataBinder binder) {
        binder.addValidators(carValidator);
    }
    @GetMapping("/cars")
    public List<Car> getCars() {
        log.info("about to retrieve cars list");
        List<Car> cars = carService.getAllCars();
        log.info("{} cars found.", cars.size());
        return cars;
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCar(@PathVariable("id") int id) {
        log.info("about to retrieve car with id {}", id);
        Car car = carService.getCarById(id);

        if (car != null) {
            log.info("car found: {}", car);
            return ResponseEntity.ok(car);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/cars")
    public ResponseEntity<?> addCar(@Validated @RequestBody CarDto carDto, Errors errors) {
        log.info("about to add new car {}", carDto);

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Car car = new Car();
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setProductionYear(carDto.getProductionYear());
        car.setDailyRate(carDto.getDailyRate());
        car.setClient(carService.getClientById(carDto.getClientId()));

        car = carService.addCar(car);
        log.info("new car added: {}", car);

        return ResponseEntity
                //.status(HttpStatus.CREATED)
                .created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequestUri()
                                .path("/" + car.getId())
                                .build()
                                .toUri())
                .body(car);
    }
}