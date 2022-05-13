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
        @NamedQuery(name = "Mechanic.findAll", query = "select a from Mechanic as a")
})
@Table(name = "MECHANIC")
@Getter
@Setter
public class Mechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @JoinColumn(name = "ROLE")
    private String role;

    @OneToMany(mappedBy = "mechanic")
    private List<Client> clients = new ArrayList<>();

    @ManyToMany(mappedBy = "mechanics")
    private List<Service> services = new ArrayList<>();

    public Mechanic() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mechanic mechanic = (Mechanic) o;
        return Objects.equals(id, mechanic.id) &&
                Objects.equals(name, mechanic.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
