package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Mechanic;
import lt.vu.persistence.MechanicDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Mechanics {
    @Inject
    private MechanicDAO mechanicDAO;

    @Getter
    @Setter
    private Mechanic mechanicToCreate = new Mechanic();

    @Getter
    private List<Mechanic> allMechanics;

    @PostConstruct
    public void init(){
        loadAllMechanics();
    }

    @Transactional
    public void createMechanic(){
        this.mechanicDAO.persist(mechanicToCreate);
    }

    private void loadAllMechanics(){
        this.allMechanics = mechanicDAO.loadAll();
    }
}
