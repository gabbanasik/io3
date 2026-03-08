package rentalcars.repository.mem;

import rentalcars.model.RentalCar;
import rentalcars.model.Client;
import rentalcars.model.Car;

import java.util.ArrayList;
import java.util.List;

class SampleData {

    static List<RentalCar> rentalCars = new ArrayList<>();

    static List<Client> clients = new ArrayList<>();

    static List<Car> cars = new ArrayList<>();

    static {

        Client kowalski = new Client(1, "Jan", "Kowalski");
        Client nowak = new Client(2, "Anna", "Nowak");
        Client wisniewski = new Client(3, "Piotr", "Wiśniewski");
        Client wojcik = new Client(4, "Katarzyna", "Wójcik");

        // Tworzymy samochody (id, marka, model, rok produkcji, klient, cena za dzień)
        Car toyota = new Car(1, "Toyota", "Corolla", 2020, kowalski, 120.0f);
        Car skoda = new Car(2, "Skoda", "Octavia", 2021, kowalski, 140.0f);

        Car bmw = new Car(3, "BMW", "X5", 2022, nowak, 350.0f);
        Car audi = new Car(4, "Audi", "A6", 2019, nowak, 280.0f);

        Car ford = new Car(5, "Ford", "Focus", 2018, wisniewski, 100.0f);
        Car vw = new Car(6, "Volkswagen", "Golf", 2021, wisniewski, 130.0f);

        Car mercedes = new Car(7, "Mercedes-Benz", "C-Class", 2023, wojcik, 300.0f);
        Car volvo = new Car(8, "Volvo", "XC60", 2022, wojcik, 250.0f);
        bind(toyota, kowalski);
        bind(skoda, kowalski);

        bind(bmw, nowak);
        bind(audi, nowak);

        bind(ford, wisniewski);
        bind(vw, wisniewski);

        bind(mercedes, wojcik);
        bind(volvo, wojcik);

        RentalCar rentWawa = new RentalCar(1, "Rent-A-Car Warszawa", "https://images.seeklogo.com/logo-png/47/1/warsaw-logo-png_seeklogo-477873.png");
        RentalCar rentKrk = new RentalCar(2, "Auto-Wynajem Kraków", "https://www.climate-kic.org/wp-content/uploads/2018/05/Logo-Krakow_C_rgb.jpg");
        RentalCar rentWro = new RentalCar(3, "SuperCars Wrocław", "https://i.pinimg.com/originals/73/7f/f2/737ff20de51e5907c6924164e4c862e3.png");
        RentalCar rentGdn = new RentalCar(4, "Gdańsk Drive", "https://download.cloudgdansk.pl/gdansk-pl/t/20170184249.png");

        // Przypisanie aut do oddziałów wypożyczalni (bindowanie relacji wiele-do-wielu)
        bind(rentWawa, skoda);
        bind(rentWro, skoda);
        bind(rentWro, toyota);
        bind(rentWro, bmw);

        bind(rentWawa, vw);
        bind(rentGdn, vw);
        bind(rentGdn, mercedes);
        bind(rentKrk, mercedes);
        bind(rentKrk, bmw);

        // Dodawanie do list w pamięci
        cars.add(toyota);
        cars.add(skoda);
        cars.add(bmw);
        cars.add(audi);
        cars.add(ford);
        cars.add(vw);
        cars.add(mercedes);
        cars.add(volvo);

        clients.add(kowalski);
        clients.add(nowak);
        clients.add(wisniewski);
        clients.add(wojcik);

        rentalCars.add(rentWawa);
        rentalCars.add(rentKrk);
        rentalCars.add(rentWro);
        rentalCars.add(rentGdn);

    }

    private static void bind(RentalCar rc, Car c) {
        rc.addCar(c);
        c.addRentalCar(rc);
    }

    // Metoda wiążąca samochód z klientem (dawniej film z reżyserem)
    private static void bind(Car c, Client client) {
        client.addCar(c);
        c.setClient(client);
    }

}
