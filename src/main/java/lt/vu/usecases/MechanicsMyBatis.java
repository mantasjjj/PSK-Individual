//package lt.vu.usecases;
//
//import lombok.Getter;
//import lombok.Setter;
//import lt.vu.entities.Mechanic;
//
//import javax.annotation.PostConstruct;
//import javax.enterprise.inject.Model;
//import javax.inject.Inject;
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Model
//public class MechanicsMyBatis {
//    @Inject
//    private MechanicsMapper mechanicsMapper;
//
//    @Getter
//    private List<Mechanic> allMechanics;
//
//    @Getter @Setter
//    private Mechanic mechanicToCreate = new Mechanic();
//
//    @PostConstruct
//    public void init() {
//        this.loadAllMechanics();
//    }
//
//    private void loadAllMechanics() {
//        this.allMechanics = mechanicsMapper.selectAll();
//    }
//
//    @Transactional
//    public String createMechanic() {
//        mechanicsMapper.insert(mechanicToCreate);
//        return "/myBatis/teams?faces-redirect=true";
//    }
//}
