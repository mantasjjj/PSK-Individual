package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Service.findAll", query = "select a from Service as a")
})
@Table(name = "SERVICE")
@Getter
@Setter
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @JoinColumn(name="ADDRESS")
    private String address;

    @ManyToMany
    @JoinColumn(name = "MECHANIC_ID")
    private List<Mechanic> mechanics = new ArrayList<>();

    public Service() {
    }

    public void addMechanic(Mechanic mechanic) {
        if (mechanic != null) {
            this.mechanics.add(mechanic);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(id, service.id) &&
                Objects.equals(address, service.address);
    }

    @Override
    public String toString() {
        return id != null ? id.toString() : null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address);
    }
}
