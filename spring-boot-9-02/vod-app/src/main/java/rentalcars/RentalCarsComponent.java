package rentalcars;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import rentalcars.model.RentalCar;
import rentalcars.service.RentalCarService;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class RentalCarsComponent implements CommandLineRunner, ApplicationListener<ContextRefreshedEvent> {
    private final RentalCarService rentalCarService;

    public RentalCarsComponent(RentalCarService rentalCarService) {this.rentalCarService = rentalCarService;}
    @PostConstruct
    void init(){
        log.info("in post construct. ");}
     @Override
    public void run(String... args) throws Exception {
        log.info("program arguments: {}", Arrays.toString(args));
     }

     @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("on context refreshed");
        List<RentalCar> rentalCars = rentalCarService.getAllRentalCars();
        log.info("{} rental car agencies found.", rentalCars.size());
     rentalCars.forEach(agency->log.info("{}", agency));
    }

}
