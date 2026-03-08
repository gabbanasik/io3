package rentalcars.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rentalcars.model.RentalCar;

import java.util.List;

public class RentalCarsServiceMain {

    public static void main(String[] args) {
        System.out.println("Let's find RentalCarss!");

        // service preparation przygotowanie serwisu który używa repo jednego cinemaservice
        //chcemy wyswielic lisę kin, repo dają dostęp do nich, ale z punktu widzenia apliakcji
        //powiinismy komunikowac sie przez serwis
        //serwis jest taka fasadą ponad repozytoriami - architektura wielowarstwowa
       // CinemaDao cinemaDao = new MemCinemaDao();
        ApplicationContext context = new AnnotationConfigApplicationContext("rentalcars");
      //  MovieDao movieDao = new MemMovieDao();
        //przygotowanie serwisu ma jedna impelemntacja, moze byc ich wiecej
        //ta impelemntacja potrzebuje w swoim konstruktorze dostarczyc obiekty dao
        //tu mamy znowu dwa intefejsy i trzeba sie zastanowic z których impelemntacji skorzystać
       // CinemaService service = new CinemaServiceBean(cinemaDao, movieDao);
        RentalCarService service = context.getBean(RentalCarService.class);
        RentalCarService service2 = context.getBean(RentalCarService.class);
        // service use
        //cinemaservice ma api zwraca wsyzstkie kina
      //  tylko że ten serwis trzeba przygotowac, trzeba pozyskać tą usługe.
        List<RentalCar> rentalCars = service.getAllRentalCars();
        System.out.println(rentalCars.size() + " rental car agencies found:");
        rentalCars.forEach(System.out::println);

        String foo=context.getBean(String.class);
        System.out.println("foo string: "+ foo);
    }
}
