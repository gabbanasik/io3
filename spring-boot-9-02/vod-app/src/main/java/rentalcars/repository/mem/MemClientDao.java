package rentalcars.repository.mem;

import org.springframework.stereotype.Component;
import rentalcars.repository.ClientDao;
import rentalcars.model.Client;

import java.util.List;

@Component
public class MemClientDao implements ClientDao {
    @Override
    public List<Client> findAll() {
        return SampleData.clients;
    }

    @Override
    public Client findById(int id) {
        // Zmieniono zmienną 'd' na 'c' (od client) w wyrażeniu lambda
        return SampleData.clients.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Client add(Client client) { // Zmieniono parametr 'd' na 'client'
        // Zmieniono 'd1, d2' na 'c1, c2'
        int max = SampleData.clients.stream().max((c1, c2) -> c1.getId() - c2.getId()).get().getId();
        client.setId(++max);
        SampleData.clients.add(client);
        return client;
    }
}
