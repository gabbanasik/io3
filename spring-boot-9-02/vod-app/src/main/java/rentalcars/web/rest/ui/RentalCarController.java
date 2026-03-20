package rentalcars.web.rest.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rentalcars.model.Car;
import rentalcars.model.RentalCar;
import rentalcars.service.CarService;
import rentalcars.service.RentalCarService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RentalCarController {

    private final RentalCarService rentalCarService;
    private final CarService carService;

    @GetMapping("/rentalcars")
    public String getRentalCars(Model model, @RequestParam(value = "carID", required = false) Integer carID) {
        log.info("about to display rentalCars list having cars {}", carID);

        if (carID != null) {
            Car car = carService.getCarById(carID);
            List<RentalCar> rentalCars = rentalCarService.getRentalCarsByCar(car);

            model.addAttribute("rentalCars", rentalCars);
            model.addAttribute("model", "Rental cars having " + car.getModel());
        } else {
            List<RentalCar> rentalCars = rentalCarService.getAllRentalCars();

            model.addAttribute("rentalCars", rentalCars);
            model.addAttribute("model", "All rental cars");
        }

        return "RentalCarsView";
    }
}