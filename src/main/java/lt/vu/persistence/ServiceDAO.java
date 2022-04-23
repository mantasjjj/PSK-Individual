package lt.vu.persistence;

import lt.vu.entities.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class ServiceDAO {
    @Inject
    private EntityManager em;

    public void persist(Service service) {
        this.em.persist(service);
    }

    public Service findOne(Integer id) {
        return em.find(Service.class, id);
    }

    public Service update(Service service) {
        return em.merge(service);
    }
}
