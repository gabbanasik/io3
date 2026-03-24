package rentalcars.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rentalcars.model.Car;
import rentalcars.model.Client;
import rentalcars.service.CarService;
import rentalcars.web.rest.dto.CarDto;

@Component
@RequiredArgsConstructor
public class CarValidator implements Validator {
    private final CarService carService;
    /*public CarValidator(CarService carService) {
        this.carService = carService;
    }*/
    @Override
    public boolean supports(Class<?>clazz){return clazz.isAssignableFrom(CarDto.class);}
    @Override
    public void validate(Object target, Errors errors) {
        CarDto carDto = (CarDto) target;
        Client client=carService.getClientById(carDto.getClientId());
        if(client==null){
            errors.rejectValue("clientId", "car.client.missing");
        }
    }
}
