package rentalcars.repository.data;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import rentalcars.model.Client;
import rentalcars.repository.ClientDao;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class DataClientDao implements ClientDao {
    private final ClientRepository clientRepository;
    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
    @Override
    public Client findById(int id) {
        return clientRepository.findById(id).orElse(null);
    }
    @Override
    public Client save(Client c){
        return clientRepository.save(c);
    }
}
