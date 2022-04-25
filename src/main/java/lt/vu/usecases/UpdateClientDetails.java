package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Car;
import lt.vu.entities.Client;
import lt.vu.entities.Player;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.ClientDAO;
import lt.vu.persistence.PlayersDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class UpdateClientDetails implements Serializable {
    private Client client;

    @Inject
    private ClientDAO clientDAO;

    @Getter @Setter
    private Car carOfClient = new Car();

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer clientId = Integer.parseInt(requestParameters.get("clientId"));
        this.client = clientDAO.findOne(clientId);
    }

    @Transactional
    @LoggedInvocation
    public String updateClientCar() {
//        //TODO: fix
//        carOfClient.setMake(this.client.getCarMake());
//        this.client.setCar(carOfClient);
        try{
            clientDAO.update(this.client);
        } catch (OptimisticLockException e) {
            return "/clientDetails.xhtml?faces-redirect=true&client=" + this.client.getId() + "&error=optimistic-lock-exception";
        }
        return "clients.xhtml?mechanicId=" + this.client.getMechanic().getId() + "&faces-redirect=true";
    }
}
