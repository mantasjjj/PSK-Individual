package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Client;
import lt.vu.entities.Mechanic;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.ClientDAO;
import lt.vu.persistence.MechanicDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class ClientsOfMechanic implements Serializable {
    @Inject
    private ClientDAO clientDAO;

    @Inject
    private MechanicDAO mechanicDAO;

    @Getter
    @Setter
    private Mechanic mechanic;

    @Getter @Setter
    private Client clientToCreate = new Client();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer mechanicId = Integer.parseInt(requestParameters.get("mechanicId"));
        this.mechanic = mechanicDAO.findOne(mechanicId);
    }

    @Transactional
    @LoggedInvocation
    public void createClient() {
        clientToCreate.setMechanic(this.mechanic);
        clientDAO.persist(clientToCreate);
    }
}
