package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Mechanic;
import lt.vu.entities.Service;
import lt.vu.persistence.MechanicDAO;
import lt.vu.persistence.ServiceDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class Services {
    @Inject
    private ServiceDAO serviceDAO;

    @Inject
    private MechanicDAO mechanicDAO;

    @Getter
    @Setter
    private Service serviceToCreate = new Service();

    @Getter
    private List<Service> allServices;

    @Getter
    @Setter
    private String serviceIdToSet;

    @Getter
    @Setter
    private Mechanic mechanic = new Mechanic();

    @PostConstruct
    public void init() {
        loadAllServices();
        setCurrentMechanic();
    }

    @Transactional
    public String createService() {
        serviceToCreate.addMechanic(this.mechanic);
        this.serviceDAO.persist(serviceToCreate);
        return "clients.xhtml?mechanicId=" + this.mechanic.getId() + "&faces-redirect=true";
    }

    @Transactional
    public String addServiceToMechanic() {
        Service service = serviceDAO.findOne(Integer.parseInt(serviceIdToSet));
        service.addMechanic(mechanic);
        serviceDAO.update(service);
        return "clients.xhtml?mechanicId=" + this.mechanic.getId() + "&faces-redirect=true";
    }

    private void loadAllServices() {
        this.allServices = serviceDAO.loadAll();
    }

    private void setCurrentMechanic() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer mechanicId = Integer.parseInt(requestParameters.get("mechanicId"));
        this.mechanic = mechanicDAO.findOne(mechanicId);
    }
}
