package rentalcars.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import rentalcars.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
