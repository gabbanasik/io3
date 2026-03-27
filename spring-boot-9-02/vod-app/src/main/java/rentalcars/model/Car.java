package rentalcars.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Car {

@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String brand;
    private String model;
    private int productionYear;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    private float dailyRate;
    // Cena za dzień wypożyczenia

    // Relacja wiele do wielu - punkty wypożyczalni
    @ManyToMany
    @JoinTable(
            name="car_rental_car",
            joinColumns=@JoinColumn(name="car_id"),
            inverseJoinColumns=@JoinColumn(name="rental_car_id")
    )
    private List<RentalCar> rentalCars = new ArrayList<>();

    public Car(int id, String brand, String model, int productionYear, Client client, float dailyRate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.client = client;
        this.dailyRate = dailyRate;
    }

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public float getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(float dailyRate) {
        this.dailyRate = dailyRate;
    }

    public List<RentalCar> getRentalCars() {
        return rentalCars;
    }

    public void setRentalCars(List<RentalCar> rentalCars) {
        this.rentalCars = rentalCars;
    }

    public void addRentalCar(RentalCar rentalCar) {
        this.rentalCars.add(rentalCar);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", productionYear=" + productionYear +
                ", currentClient=" + (client != null ? client.getLastName() : "Brak") +
                ", dailyRate=" + dailyRate +
                '}';
    }
}
