package rentalcars.web.rest.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rentalcars.model.Car;
import rentalcars.model.Client;
import rentalcars.model.RentalCar;
import rentalcars.service.CarService;
import rentalcars.service.RentalCarService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CarController {

    private final RentalCarService rentalCarService;
    private final CarService carService;

    @GetMapping("/cars")
    public String getCars(Model model,
                          @RequestParam(value = "rentalCarID", required = false) Integer rentalCarID,
                          @RequestParam(value = "clientID", required = false) Integer clientID) {

        log.info("about to display cars list in rental car {}", rentalCarID);

        if (rentalCarID != null) {
            RentalCar rentalCar = rentalCarService.getRentalCarById(rentalCarID);
            List<Car> cars = rentalCarService.getCarsInRentalCar(rentalCar);
            model.addAttribute("cars", cars);
            model.addAttribute("name", "Cars in rental car " + rentalCar.getName());

        } else if (clientID != null) {
            Client client = carService.getClientById(clientID);
            List<Car> cars = carService.getCarsByClient(client);
            model.addAttribute("cars", cars);
            model.addAttribute("name", "Cars rented by " + client.getLastName());

        } else {
            List<Car> cars = carService.getAllCars();
            model.addAttribute("cars", cars);
            model.addAttribute("name", "All cars");
        }

        return "CarsView";
    }
}