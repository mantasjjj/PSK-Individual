package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    @ManyToMany(mappedBy = "services")
    private List<Mechanic> mechanics;

    public Service() {
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
    public int hashCode() {
        return Objects.hash(id, address);
    }
}
