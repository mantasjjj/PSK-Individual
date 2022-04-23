package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Client;
import lt.vu.persistence.ClientDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Clients {
    @Inject
    private ClientDAO clientDAO;

    @Getter
    @Setter
    private Client clientToCreate = new Client();

    @Getter
    private List<Client> allClients;

    @PostConstruct
    public void init(){
        loadAllClients();
    }

    @Transactional
    public void createClient(){
        this.clientDAO.persist(clientToCreate);
    }

    private void loadAllClients(){
        this.allClients = clientDAO.loadAll();
    }
}
