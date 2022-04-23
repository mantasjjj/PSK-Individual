package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Client;

import java.util.List;

@Getter
@Setter
public class MechanicDto {

    public String Name;

    public String Role;

    public List<Client> Clients;
}
