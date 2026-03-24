package rentalcars.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import rentalcars.model.RentalCar;
import rentalcars.service.CarService;

@Component
@RequiredArgsConstructor
public class VodInfoConstribution implements InfoContributor{
private final CarService carService;
@Override
    public void contribute(Info.Builder builder){
    builder.withDetail("cars",carService.getAllCars().size());

}
}
