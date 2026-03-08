package rentalcars.model;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private int id;
    private String firstName;
    private String lastName;
    private List<Car> cars = new ArrayList<>();//relacja 1 do wielu
//listy zeby przey przełączniu na SpringDate nie było komplikacji
//lista od seta różni się tym że są w niej powtórzenia oraz trzymamy kolejność wrzucania
    //struktury danych wazna rzecz w zachowaniu spójności danych w warstwie aplikacyjnej

    public Client(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
