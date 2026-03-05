package vod;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import vod.model.Cinema;
import vod.service.CinemaService;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class VodComponent implements CommandLineRunner, ApplicationListener<ContextRefreshedEvent> {
    private final CinemaService cinemaService;

    public VodComponent(CinemaService cinemaService) {this.cinemaService = cinemaService;}
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
        List<Cinema>cinemas=cinemaService.getAllCinemas();
        log.info("{} cinema found.", cinemas.size());
     cinemas.forEach(cinema->log.info("{}", cinema));
    }

}
