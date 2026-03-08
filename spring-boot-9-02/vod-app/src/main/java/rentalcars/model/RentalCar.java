package rentalcars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class RentalCar {

    private int id;
    private String name;
    private String logo; //url logo w przypadku UI będzie zaciągany dynamicznie
    @JsonIgnore
    private List<Car> cars = new ArrayList<>();//struktura kolekcyjna związana z dostępnymi samochodami, uproszczone
    //relacja wiele do wiele
    public RentalCar(int id, String name, String logo) {//konstruktor
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public RentalCar() {//bezparametrowy
    }
    //settery, gettery i to String - później będziemy korzystać z wynalazku Lombok
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car c) {
        this.cars.add(c);
    }

    @Override
    public String toString() {
        return "RentalCar{" +
                "name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
