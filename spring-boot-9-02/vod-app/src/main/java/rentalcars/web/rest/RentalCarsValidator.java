package rentalcars.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rentalcars.model.RentalCar;
import rentalcars.service.RentalCarService;

@Component
@RequiredArgsConstructor
public class RentalCarsValidator implements Validator {

    private final RentalCarService rentalCarService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RentalCar.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RentalCar validatedRentalCar = (RentalCar) target;

        boolean duplicated = rentalCarService.getAllRentalCars().stream()
                .anyMatch(rc -> rc.getName().equalsIgnoreCase(validatedRentalCar.getName()));

        if (duplicated) {
            // Zmieniono klucz na rentalcar (zgodnie z ujednoliconym nazewnictwem)
            errors.rejectValue("name", "rentalcar.name.duplicated");
        }
    }
}