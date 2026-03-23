package rentalcars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name= "rental_car")
public class RentalCar {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min = 2, max = 30)
    private String name;
    @Column(name = "logo")
    private String logo; //url logo w przypadku UI będzie zaciągany dynamicznie
    @ManyToMany(mappedBy = "rentalCars")
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
