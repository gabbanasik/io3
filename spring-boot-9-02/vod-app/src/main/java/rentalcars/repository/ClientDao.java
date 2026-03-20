package rentalcars.repository;

import rentalcars.model.Client;

import java.util.List;

public interface ClientDao {

    List<Client> findAll();

    Client findById(int id);

    Client save(Client c);


}
