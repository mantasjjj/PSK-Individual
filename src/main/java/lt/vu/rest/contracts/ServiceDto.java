package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Mechanic;

import java.util.List;

@Getter
@Setter
public class ServiceDto {

    public String Name;

    public List<Mechanic> Mechanics;
}
